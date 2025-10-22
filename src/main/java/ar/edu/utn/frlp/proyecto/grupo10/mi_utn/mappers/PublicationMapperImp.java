package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PublicationRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.PublicationDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.PublicationMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Publication;
import org.springframework.stereotype.Component;

@Component
public class PublicationMapperImp implements PublicationMapper {

    @Override
    public Publication toEntity(PublicationDTO publicationDTO) {
        return Publication.builder()
                          .id(publicationDTO.getId())
                          .title(publicationDTO.getTitle())
                          .description(publicationDTO.getDescription())
                          .content(publicationDTO.getContent())
                          .hidden(publicationDTO.getHidden())
                          .priority(publicationDTO.getHidden())
                          .imagePath(publicationDTO.getImagePath())
                          .expirable(publicationDTO.getExpirable())
                          .expirationDate(publicationDTO.getExpirationDate())
                          .publicationMode(publicationDTO.getPublicationMode())
                          .scheduledDate(publicationDTO.getScheduledDate())
                          .build();
    }

    @Override
    public Publication toEntity(PublicationRequestDTO publicationRequestDTO) {
        return Publication.builder()
                          .id(publicationRequestDTO.getId())
                          .title(publicationRequestDTO.getTitle())
                          .description(publicationRequestDTO.getDescription())
                          .content(publicationRequestDTO.getContent())
                          .hidden(publicationRequestDTO.getHidden())
                          .priority(publicationRequestDTO.getPriority())
                          .expirable(publicationRequestDTO.getExpirable())
                          .expirationDate(publicationRequestDTO.getExpirationDate())
                          .publicationMode(publicationRequestDTO.getPublicationMode())
                          .scheduledDate(publicationRequestDTO.getScheduledDate())
                          .build();
    }

    @Override
    public PublicationDTO toDTO(Publication publication) {
        return PublicationDTO.builder()
                             .id(publication.getId())
                             .title(publication.getTitle())
                             .description(publication.getDescription())
                             .content(publication.getContent())
                             .hidden(publication.getHidden())
                             .priority(publication.getPriority())
                             .imagePath(publication.getImagePath())
                             .expirable(publication.getExpirable())
                             .expirationDate(publication.getExpirationDate())
                             .publicationMode(publication.getPublicationMode())
                             .scheduledDate(publication.getScheduledDate())
                             .build();
    }
}
