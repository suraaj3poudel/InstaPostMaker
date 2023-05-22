package com.meme.postmaker.memePostMaker.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.meme.postmaker.memePostMaker.service.MemePostService;

@RestController
public class MemePostController {
	
	@Autowired
	MemePostService instaService;
	
	@GetMapping("/post")
	public String post() {
		return instaService.post();
	}
}
