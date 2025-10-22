package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static java.lang.System.in;

@Service
public class FileStorageService {

    private final String uploadDir = "uploads/images/announcements/";

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de uploads", e);
        }
    }

    public String saveImage(MultipartFile image, String originalFilename) {

        try {
            String contentType = image.getContentType();
            if (contentType == null || !contentType.toLowerCase().startsWith("image/")) {
                throw new IllegalArgumentException("Solo se permiten imágenes");
            }

            String fileName = generateUniqueFileName(originalFilename);
            String filePath = uploadDir + fileName;

            Path path = Paths.get(filePath);
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);

            return filePath;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }
    }

    public void deleteImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                Files.deleteIfExists(Paths.get(imagePath));
            } catch (IOException e) {
                System.err.println("Error al eliminar archivo: " + imagePath);
            }
        }
    }

    public byte[] getImageBytes(String imagePath) {
        try {
            return Files.readAllBytes(Paths.get(imagePath));
        } catch (IOException e) {
            throw new RuntimeException("Error al leer la imagen", e);
        }
    }

    private String generateUniqueFileName(String originalFilename) {
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return UUID.randomUUID() + extension;
    }
}
