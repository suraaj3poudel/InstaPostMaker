package com.instagram.postmaker.instaPostMaker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;

@SpringBootApplication
public class InstaPostMakerApplication {
	
	@Value(value = "${insta.username}")
	private String username;
	
	@Value(value = "${insta.password}")
	private String password;

	
	public static void main(String[] args) {
		SpringApplication.run(InstaPostMakerApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public IGClient client() {
		IGClient client = null;
		try {
			client = IGClient.builder()
			        .username(username)
			        .password(password)
			        .login();
		} catch (IGLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return client;
	}

}
