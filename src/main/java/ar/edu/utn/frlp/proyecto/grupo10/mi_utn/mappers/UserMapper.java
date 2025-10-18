package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.UserRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.UserDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.ProfessorMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.UserMapperInterface;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper implements UserMapperInterface {

    private ProfessorMapper personMapper;
    private PasswordEncoder passwordEncoder;


    @Override
    public User toEntity(UserRequestDTO userRequestDTO) {
        return User.builder()
                .username(userRequestDTO.getUsername())
                .password(this.passwordEncoder.encode(userRequestDTO.getPassword()))
                .role(userRequestDTO.getRole())
                .person(this.personMapper.toEntity(userRequestDTO.getPerson()))
                .build();
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .dateDeleted(userDTO.getDateDeleted())
                .dateCreated(userDTO.getDateCreated())
                .dateUpdate(userDTO.getDateUpdate())
                .person(this.personMapper.toEntity(userDTO.getPerson()))
                .build();
    }

    @Override
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .dateCreated(user.getDateCreated())
                .dateDeleted(user.getDateDeleted())
                .dateUpdate(user.getDateUpdate())
                .person(this.personMapper.toDTO(user.getPerson()))
                .build();
    }
}
