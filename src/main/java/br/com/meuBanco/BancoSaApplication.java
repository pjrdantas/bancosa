package br.com.meuBanco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoSaApplication {

	private static final Logger log = LoggerFactory.getLogger(BancoSaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BancoSaApplication.class, args);
		log.info("--------------------------------------------------------------------> SERVIÇO PRONTO PARA SER CONSUMIDO!");
	}

}
