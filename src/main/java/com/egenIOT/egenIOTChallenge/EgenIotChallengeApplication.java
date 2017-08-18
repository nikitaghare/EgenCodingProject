package com.egenIOT.egenIOTChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.egenIOT.egenIOTChallenge")
public class EgenIotChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgenIotChallengeApplication.class, args);
	}
}
