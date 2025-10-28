package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.UUID;


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

    public String saveImage(MultipartFile image) {

        try {
            String contentType = image.getContentType();
            if (contentType == null || !contentType.toLowerCase().startsWith("image/")) {
                throw new IllegalArgumentException("Solo se permiten imágenes");
            }

            String fileName = generateUniqueFileName(image.getOriginalFilename());
            String filePath = uploadDir + fileName;

            Path path = Paths.get(filePath);
            try(InputStream in  = image.getInputStream()) {
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
            }
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

    /**
     * Obtiene una imagen como arreglo de bytes.
     */
    public byte[] getImageAsBytes(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        try {
            Path path = Paths.get(imagePath);
            if (!Files.exists(path)) {
                throw new RuntimeException("La imagen no existe: " + imagePath);
            }
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer la imagen: " + imagePath, e);
        }
    }

    /**
     * Obtiene una imagen codificada en Base64 (útil para enviar en JSON).
     */
    public String getImageAsBase64(String imagePath) {
        byte[] bytes = getImageAsBytes(imagePath);
        if (bytes == null) {
            return null;
        }
        String mimeType = "image/png";
        try {
            Path path = Paths.get(imagePath);
            mimeType = Files.probeContentType(path);
        } catch (IOException ignored) {}
        return "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Genera un nombre único para cada archivo.
     */
    private String generateUniqueFileName(String originalFilename) {
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return UUID.randomUUID() + extension;
    }
}
