package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract;


import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.UserRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.UserDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;

import java.util.List;

public interface UserServiceContract {
    public void save(UserRequestDTO userRequestDTO);
    public void delete(Long id) throws BadRequestException;
    public void update(UserDTO userDTO) throws BadRequestException;
    public List<UserDTO> findAll();
    public UserDTO findById(Long id) throws BadRequestException;
    public UserDTO findByUsername(String username) throws BadRequestException;
}
