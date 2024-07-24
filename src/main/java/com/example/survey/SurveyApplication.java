package com.example.survey;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class SurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
	}

	@GetMapping("/hello")
	public User hello(@RequestParam(value = "name", defaultValue = "World") String name,
                      @RequestParam(value = "a") String a,
                      @RequestBody Body body) {
        System.out.println(body.name);

	    User user = new User();
        user.name = name;


        System.out.println(name);
		return user;
	}

	@NoArgsConstructor
    @Setter
    @Getter
	public class Body{
	    private String name;
    }

	private class User {
	    public String name;
	    public int age = 13;
	    public String gender = "male";
    }

}
