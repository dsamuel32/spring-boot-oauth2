package br.com.oauth.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.oauth.model.User;
import br.com.oauth.repository.UserRepository;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GET
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
