package com.instagram.postmaker.instaPostMaker.service;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;

@Service
@EnableScheduling 
public class InstagramPostService {
	
	@Value(value = "${insta.username}")
	private String username;
	
	@Value(value = "${insta.password}")
	private String password;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	CaptionGeneratorService captionService;
	
	@Value(value = "${image.path}")
	private String imagFilePath;
	
	@Autowired
	IGClient client;
	
	@Scheduled(cron = "${cron.expression}")
	public String post() {
		if(!client.isLoggedIn()) {
			client = reinitializeClient();
		}
		try {
			client.actions()
			      .timeline()
			      .uploadPhoto(getImageBytes(), captionService.generateCaption())
			      .thenAccept(response -> {
			    System.out.println("Successfully uploaded photo!");
			})
			.join();
			

		} catch (IGLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "There was an error?";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Successfully Posted to Instagram";
	}
	
	
	private IGClient reinitializeClient() {
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


	private byte[] getImageBytes() throws IOException {
		URL url = new URL("https://picsum.photos/2048");
        try (InputStream inputStream = url.openStream();

            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteOutput.write(buffer, 0, bytesRead);
            }
            return byteOutput.toByteArray();
        }
        
	}
}
