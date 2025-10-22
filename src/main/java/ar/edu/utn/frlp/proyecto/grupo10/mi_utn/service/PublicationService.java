package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PublicationRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.PublicationDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.PublicationMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Publication;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.PublicationRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract.PublicationServiceContract;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PublicationService implements PublicationServiceContract {

    private final PublicationRepository publicationRepository;
    private final FileStorageService fileStorageService;
    private final PublicationMapper publicationMapper;

    @Override
    public PublicationDTO findById(Long id) throws BadRequestException {
        return publicationRepository.findById(id).map(publicationMapper::toDTO).orElseThrow(BadRequestException::new);
    }

    @Override
    public List<PublicationDTO> findAll(Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return publicationRepository.findAll().stream().map(publicationMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return publicationRepository.findAll(pageable).stream().map(publicationMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return publicationRepository.findAll(pageable).stream().map(publicationMapper::toDTO).toList();
    }

    @Override
    public Publication save(PublicationRequestDTO publicationRequestDTO) {
        Publication publication = publicationMapper.toEntity(publicationRequestDTO);
        if (publicationRequestDTO.getImage() != null && !publicationRequestDTO.getImage().isEmpty()) {
            String imagePath = fileStorageService.saveImage(publicationRequestDTO.getImage());
            publication.setImagePath(imagePath);
        }

        return publicationRepository.save(publication);
    }

    @Override
    public Publication update(PublicationRequestDTO publicationRequestDTO) throws BadRequestException {
        Long id = publicationRequestDTO.getId();
        if (!publicationRepository.existsById(id))
            throw new BadRequestException(String.format("Anuncio inexistente. Id: %s", id));
        Publication publication = publicationMapper.toEntity(publicationRequestDTO);
        if (publication.getImagePath() != null) {
            fileStorageService.deleteImage(publication.getImagePath());
        }

        if (publicationRequestDTO.getImage() != null && !publicationRequestDTO.getImage().isEmpty()) {
            String imagePath = fileStorageService.saveImage(publicationRequestDTO.getImage());
            publication.setImagePath(imagePath);
        } else {
            publication.setImagePath(null);
        }

        return publicationRepository.save(publication);
    }

    @Override
    public void delete(Long id) throws BadRequestException {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() ->  new IllegalArgumentException(String.format("Anuncio inexistente. Id: %s", id)));

        if (publication.getImagePath() != null) {
            fileStorageService.deleteImage(publication.getImagePath());
        }

        publicationRepository.delete(publication);
    }
}
