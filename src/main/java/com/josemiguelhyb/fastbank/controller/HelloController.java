package com.josemiguelhyb.fastbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
	
	@GetMapping("/hello")
	public String Hello() {
        return "👋 Hola, el servidor FastBank está funcionando correctamente.";		 
	}
}
