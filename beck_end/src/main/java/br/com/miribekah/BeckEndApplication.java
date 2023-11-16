package br.com.miribekah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BeckEndApplication {

	public static void main(String[] args) {

		System.out.println(new BCryptPasswordEncoder().encode("123456"));
		
		SpringApplication.run(BeckEndApplication.class, args);
	}

}
