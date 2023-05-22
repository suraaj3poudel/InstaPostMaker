package com.instagram.postmaker.instaPostMaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import com.instagram.postmaker.instaPostMaker.captionModel.CaptionModel;

@Service
public class CaptionGeneratorService {
	
	@Value(value = "${post.caption}")
	private String captionUrl;
	
	
	@Autowired
	RestTemplate restTemplate;
	
	public String generateCaption() {
		String captionContent = "";
		try {
			URI uri = new URI(captionUrl);
			ResponseEntity<CaptionModel> caption = restTemplate.getForEntity(uri, CaptionModel.class);
			captionContent += caption.getBody().getContent()+ "\n\n";
			captionContent += "-" + caption.getBody().getAuthor();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return captionContent;
	}
}
