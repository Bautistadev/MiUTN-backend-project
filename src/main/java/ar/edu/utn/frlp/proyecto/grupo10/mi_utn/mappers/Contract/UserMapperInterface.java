package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract;


import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.UserRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.UserDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.User;

public interface UserMapperInterface {

    public User toEntity(UserRequestDTO userRequestDTO);
    public User toEntity(UserDTO userDTO);
    public UserDTO toDTO(User user);
}
