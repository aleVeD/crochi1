package cl.escalab.crochicat.service;

import cl.escalab.crochicat.dto.SavePhotoDto;
import cl.escalab.crochicat.model.Photo;
import cl.escalab.crochicat.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

public interface PhotoService extends Iservice<Photo> {
    SavePhotoDto savePhoto(UUID id, MultipartFile file) throws IOException;

}
