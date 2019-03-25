package edu.ap.spring;

import edu.ap.spring.service.Block;
import edu.ap.spring.service.BlockChain;
import edu.ap.spring.service.Wallet;
import edu.ap.spring.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class BlockChainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockChainApplication.class, args);
	}

	@Bean
  	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {};
  	}
}
