package com.mgr2.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service("storageService")
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public String store(MultipartFile file, String trainingName) { // dodac jakies zabezpieczenie przed dodaniem tego samego pliku?
		init();
		Path filePath;
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			// Path newPath = Paths.get(rootLocation.toString(), trainingName);
			Path newPath = rootLocation.resolve(trainingName);
			System.out.println("Nowa ścieżka " + newPath);
			if (!Files.exists(newPath)) {
				Files.createDirectory(newPath);
			}
			filePath = newPath.resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), filePath);
			System.out.println("Cala ścieżka " + filePath);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
		
		String pathToStore = "content\\" + trainingName + "\\" + file.getOriginalFilename(); 
		System.out.println(pathToStore);
		return pathToStore;
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override // chyba bezuzyteczny
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public MultipartFile loadAsMultipartFile(String pathString) { // chyba nie przydatne
															// anymore
		Path path = Paths.get(pathString);
		String name = path.getFileName().toString();
		System.out.println("Whole path: " + path.toString() + "filename: " + name);
		String originalFileName = name;
		String contentType = "text/plain";
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
			throw new StorageFileNotFoundException("Could not read file: " + name);
		}
		MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
		return result;
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			if (!Files.exists(rootLocation)) {
				Files.createDirectory(rootLocation);
			}
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public void delete(String path) {
		try {
			Files.delete(Paths.get(path));
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", path);
		} catch (IOException x) {
			// File permission problems are caught here.
			System.err.println(x);
		}

	}
}
