package com.mgr2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mgr2.model.User;
import com.mgr2.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        System.out.println("email: " + username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        System.out.println("Dane: "+ user.getId() + " i "+ user.getEmail() + " i " + user.getPassword());
        return new MyUserPrincipal(user);
    }
}
