package ar.edu.utn.frlp.proyecto.grupo10.mi_utn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ar.edu.utn.frlp.proyecto.grupo10.mi_utn")
public class MiUtnApplication {

	public static void main(String[] args) {

		SpringApplication.run(MiUtnApplication.class, args);
	}

}
