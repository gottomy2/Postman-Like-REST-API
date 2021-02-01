package edu.pjatk.postman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Igor Motowidło (gottomy2)
 * Main Application. Runs whole project
 */

@SpringBootApplication
public class PostmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostmanApplication.class, args);
	}

	@RestController
	static class HelloController{
		@GetMapping("/")
		String hello(){
			return "HELLO!";
		}
	}
}
