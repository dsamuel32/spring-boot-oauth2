package br.com.diego.springplayground.apresentacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    @GetMapping
    public ResponseEntity<String> getPessoa() {
        return new ResponseEntity<>("teste", HttpStatus.OK);
    }

}
