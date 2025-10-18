package ar.edu.utn.frlp.proyecto.grupo10.mi_utn;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.Role;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.User;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.ProfessorRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = "ar.edu.utn.frlp.proyecto.grupo10.mi_utn")
public class MiUtnApplication implements CommandLineRunner {



	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(MiUtnApplication.class, args);

		ProfessorRepository professorRepository =applicationContext.getBean(ProfessorRepository.class);


	}

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void run(String... args) throws Exception {
		UserRepository userRepository = this.applicationContext.getBean(UserRepository.class);
		PasswordEncoder passwordEncoder = this.applicationContext.getBean(PasswordEncoder.class);
		try {
			if (!userRepository.existsByUsername("admin"))
				userRepository.save(User.builder()
						.role(Role.ADMIN)
						.username("admin")
						.password(passwordEncoder.encode("admin"))
						.person(Professor.builder()
								.id(1L)
								.build())
						.build());
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		System.out.println(userRepository.findById(1L).get());
	}
}
