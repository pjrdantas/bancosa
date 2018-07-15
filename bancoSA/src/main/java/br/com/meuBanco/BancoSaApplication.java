package br.com.meuBanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan
@EnableWebMvc
@EnableAutoConfiguration
@Configuration
@SpringBootApplication
public class BancoSaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoSaApplication.class, args);
	}
}
