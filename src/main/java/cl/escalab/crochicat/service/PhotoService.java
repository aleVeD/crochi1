package cl.escalab.crochicat.service;

import cl.escalab.crochicat.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface PhotoService extends Iservice<Photo> {
    Photo savePhoto(UUID id, MultipartFile file) throws IOException;

}
