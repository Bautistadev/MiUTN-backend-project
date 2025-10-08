package ar.edu.utn.frlp.proyecto.grupo10.mi_utn;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.ProfessorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ar.edu.utn.frlp.proyecto.grupo10.mi_utn")
public class MiUtnApplication {



	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(MiUtnApplication.class, args);

		ProfessorRepository professorRepository =applicationContext.getBean(ProfessorRepository.class);

		System.out.println(professorRepository.findAll());

	}

}
