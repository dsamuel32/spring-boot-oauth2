package br.com.oauth.endpoints;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.oauth.Greeting;
import br.com.oauth.model.User;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RestController
@Path("/greeting")
public class GreetingController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
        @GET
	public Greeting greeting(@AuthenticationPrincipal User user) {
		return new Greeting(counter.incrementAndGet(),
				String.format(template, user.getName()));
	}

}
