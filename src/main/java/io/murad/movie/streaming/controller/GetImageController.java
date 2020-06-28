package io.murad.movie.streaming.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetImageController {

	@RequestMapping(value = "getImage/{thumbnail}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable("thumbnail") String thumbnail) {
		if (!thumbnail.equals("") || thumbnail != null) {
			try {
				Path thumbnailName = Paths.get("uploads/", thumbnail);
				byte[] buffer = Files.readAllBytes(thumbnailName);
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().body(byteArrayResource);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
}
