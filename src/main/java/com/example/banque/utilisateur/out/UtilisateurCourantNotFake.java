package com.example.banque.utilisateur.out;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.banque.utilisateur.Utilisateur;
import com.example.banque.utilisateur.UtilisateurRepository;


@Component
public class UtilisateurCourantNotFake implements UtilisateurCourant {

    private final UtilisateurRepository utilisateurs;

    public UtilisateurCourantNotFake(UtilisateurRepository utilisateurs) {
        this.utilisateurs = utilisateurs;

    }

    @Override
    public Optional<Utilisateur> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            return utilisateurs.findByLogin(authentication.getName());
        }
        return Optional.empty();
    }
}
