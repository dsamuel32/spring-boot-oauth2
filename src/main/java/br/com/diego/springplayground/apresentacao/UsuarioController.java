package br.com.diego.springplayground.apresentacao;

import br.com.diego.springplayground.domain.Usuario;
import br.com.diego.springplayground.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "v1/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping()
    public ResponseEntity<Usuario> getUsuarioLogado() {
        Usuario usuario = usuarioService.getUsuarioLogado();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
