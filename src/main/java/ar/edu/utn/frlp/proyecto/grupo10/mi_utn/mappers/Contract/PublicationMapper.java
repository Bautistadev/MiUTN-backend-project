package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract;


import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PublicationRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.PublicationDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Publication;

public interface PublicationMapper {
    public Publication toEntity(PublicationDTO publicationDTO);
    public Publication toEntity(PublicationRequestDTO publicationRequestDTO);
    public PublicationDTO toDTO(Publication publication);
}
