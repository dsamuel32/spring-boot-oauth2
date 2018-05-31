package br.com.diego.springplayground.service;

import br.com.diego.springplayground.domain.Usuario;
import br.com.diego.springplayground.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", login));
        }
        return usuario;
    }

    public Usuario getUsuarioLogado() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication  authentication = securityContext.getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

}
