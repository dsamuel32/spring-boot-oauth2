package br.com.oauth.resources;

import br.com.oauth.builder.Resposta;
import br.com.oauth.builder.RespostaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.oauth.model.User;
import br.com.oauth.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resposta<User>> getUsers() {
        Resposta<User> resposta = RespostaBuilder.getBuilder()
                .setResultado(userService.findAll()).build();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}
