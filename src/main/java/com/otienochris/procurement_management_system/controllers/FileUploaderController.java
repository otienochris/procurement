package com.group4.procurement1.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class FileUploaderController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	
	@RequestMapping("upload")
	public String upload(Model model,@RequestParam("file") MultipartFile [] files ) throws IOException {
		StringBuilder fileNames = new StringBuilder();
		for(MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename() + " ");
			Files.write(fileNameAndPath, file.getBytes());
		}
		
		model.addAttribute("msg", "succesfully uploaded files" + fileNames.toString());
		return "uploadedpageview";
	}
	
}
