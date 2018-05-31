package br.com.diego.springplayground.apresentacao;

import br.com.diego.springplayground.domain.dto.UsuarioDTO;
import br.com.diego.springplayground.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping()
    public ResponseEntity<UsuarioDTO> getUsuarioLogado() {
        UsuarioDTO usuarioDTO = usuarioService.getUsuarioLogado();
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

}
