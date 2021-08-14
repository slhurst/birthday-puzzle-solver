package org.merrihurst.puzzlesolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
@ComponentScan(basePackages = "org.merrihurst")
@EnableAutoConfiguration
public class PuzzleSolverApplication {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(PuzzleSolverApplication.class, args);
		context.getBean(SolverWrapper.class).testyMcTestFace();
	}
}
