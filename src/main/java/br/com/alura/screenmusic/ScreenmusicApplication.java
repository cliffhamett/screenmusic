package br.com.alura.screenmusic;

import br.com.alura.screenmusic.principal.Principal;
import br.com.alura.screenmusic.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmusicApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.exibeMenu();
	}

}
