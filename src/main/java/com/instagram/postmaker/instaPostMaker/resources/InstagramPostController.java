package com.instagram.postmaker.instaPostMaker.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.instagram.postmaker.instaPostMaker.service.InstagramPostService;

@RestController
public class InstagramPostController {
	
	@Autowired
	InstagramPostService instaService;
	
	@GetMapping("/post")
	public String post() {
		return instaService.post();
	}
}
