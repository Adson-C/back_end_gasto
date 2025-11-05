       package com.ads.gasto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GastoApplication {
	public static void main(String[] args) {
		SpringApplication.run(GastoApplication.class, args);

		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
		String senhaOriginal = "minhaSenha123";
		String senhaCodificada = encoder.encode(senhaOriginal);

		System.out.println("Senha codificada: " + senhaCodificada);
		}
}
 