package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.UserRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.UserDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.ConflictException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.UserMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.UserRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract.UserServiceContract;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserServiceContract {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    @Transactional
    public void save(UserRequestDTO userRequestDTO) {

        if(this.userRepository.existsByUsername(userRequestDTO.getUsername()))
            throw new ConflictException("Usuario existente");

        this.userRepository.save(this.userMapper.toEntity(userRequestDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) throws BadRequestException {

        if(!this.userRepository.existsById(id))
            throw new BadRequestException("Bad request, the entity not exists");

        this.userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) throws BadRequestException {

        if(!this.userRepository.existsById(userDTO.getId()))
            throw new BadRequestException("Bad request, the entity not exists");

        this.userRepository.save(this.userMapper.toEntity(userDTO));
    }

    @Override
    public List<UserDTO> findAll() {
        return this.userRepository
                .findAll()
                .stream()
                .map(this.userMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO findById(Long id) throws BadRequestException {
        return this.userRepository.findById(id)
                .map(this.userMapper::toDTO)
                .orElseThrow(BadRequestException::new);
    }

    @Override
    public UserDTO findByUsername(String username) throws BadRequestException {
        return this.userRepository.findByUsername(username)
                .map(this.userMapper::toDTO)
                .orElseThrow(BadRequestException::new);
    }

    public Boolean existsByUsername(String name){
        return this.userRepository.existsByUsername(name);
    }

    public Boolean existsByEmail(String email){
        return this.userRepository.existsByPersonEmail(email);
    }

    public UserDTO findByEmail(String email) throws BadRequestException {
        return this.userRepository.findByPersonEmail(email)
                .map(this.userMapper::toDTO)
                .orElseThrow(BadRequestException::new);
    }
}
