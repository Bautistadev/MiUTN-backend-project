package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public boolean existsByUsername(String username);
    public Optional<User> findByUsername(String name);
    public Boolean existsByPersonEmail(String email);
    public Optional<User> findByPersonEmail(String email);
}
