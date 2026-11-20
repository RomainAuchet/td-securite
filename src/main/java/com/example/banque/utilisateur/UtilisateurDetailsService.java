package com.example.banque.utilisateur;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsService implements UserDetailsService {
    private static Logger log = LoggerFactory.getLogger(UtilisateurDetailsService.class);
    @Autowired
    UtilisateurRepository repository;
    @Override
    public UserDetails loadUserByUsername(final String login)
            throws UsernameNotFoundException {
        Optional<Utilisateur> res = repository.findByLogin(login);
        if(res.isPresent()){
            return new User(res.get().getLogin(),res.get().getMotDePasse(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");
        }
        
    }
    
}