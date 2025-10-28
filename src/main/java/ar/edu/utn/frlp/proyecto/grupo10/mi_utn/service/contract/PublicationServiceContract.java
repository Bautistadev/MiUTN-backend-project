package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PublicationRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.PublicationDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Publication;

import java.util.List;

public interface PublicationServiceContract {
    public PublicationDTO findById (Long id) throws BadRequestException;
    public List<PublicationDTO> findAll (Integer from, Integer to);
    public Publication save(PublicationRequestDTO publicationRequestDTO);
    public Publication update(PublicationRequestDTO publicationRequestDTO) throws BadRequestException;
    public void delete(Long id) throws BadRequestException;
}
