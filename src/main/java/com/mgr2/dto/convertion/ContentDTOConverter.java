package com.mgr2.dto.convertion;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.mgr2.dto.ContentDTO;
import com.mgr2.model.Content;
import com.mgr2.model.Training;
import com.mgr2.repository.TrainingRepository;
import com.mgr2.storage.StorageService;

@Service("contentDTOConverter")
public class ContentDTOConverter {
	
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	
	
	public ContentDTO covertModelToDTO(Content content){
		ContentDTO cDTO = new ContentDTO();
		cDTO.setId(content.getId());
		//cDTO.setFile(storageService.loadAsMultipartFile(Paths.get(content.getPath())));
		cDTO.setPath(content.getPath());
		cDTO.setOrder_nr(content.getOrder_nr());
		cDTO.setTraining_id(content.getTraining().getId());
		cDTO.setDescription(content.getDescription());
		return cDTO;
	}
	
	public Content convertDTOtoModel(ContentDTO cDTO){
		Content c = new Content();
		c.setId(cDTO.getId()); // to chyba trzeba wywalic
		//String path = cDTO.getFile().getOriginalFilename();
		//System.out.println("sciezynka: " + path);
		System.out.println("id kursu: " + cDTO.getTraining_id() + " desc " + cDTO.getDescription() + " order nr: " + cDTO.getOrder_nr());
		c.setPath(cDTO.getPath());
		c.setOrder_nr(cDTO.getOrder_nr());
		c.setTraining(trainingRepository.findById(cDTO.getTraining_id()));
		c.setDescription(cDTO.getDescription());
		return c;
	}

	public List<ContentDTO> convertListOfModelsToDTO(List<Content> cList){
		List<ContentDTO> cDTOlist = new ArrayList<>();
		for(Content c : cList){
			cDTOlist.add(covertModelToDTO(c));
		}
		return cDTOlist;
		
	}
}
