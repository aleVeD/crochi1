package cl.escalab.crochicat.service.impl;

import cl.escalab.crochicat.dto.SavePhotoDto;
import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.Photo;
import cl.escalab.crochicat.model.User;
import cl.escalab.crochicat.repo.PhotoRepoInterface;
import cl.escalab.crochicat.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService{
    @Autowired
    private PhotoRepoInterface photoRepoInterface;

    @Override
    public List<Photo> getAll() {
        return photoRepoInterface.findAll();
    }

    @Override
    public Photo save(Photo obj) {
        return photoRepoInterface.save(obj);
    }

    @Override
    public Photo get(UUID id) {
        return null;
    }


    @Override
    public Boolean delete(UUID id) {
        Photo photo = getPhotoById(id);
        photoRepoInterface.deleteById(id);
        return true;
    }

    @Override
    public Photo savePhoto(UUID id, MultipartFile file) throws IOException {
        try {
            User user = new User(id);
            Photo photo = new Photo();
            photo.setFiletype(file.getContentType());
            photo.setFilename(file.getName());
            photo.setImage(file.getBytes());
            photo.setUser(user);
            Photo photo2 = photoRepoInterface.save(photo);
            return photo2;
        }catch(IOException e){
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public Photo update(Photo photo, UUID id) {
        Photo photo1 = photoRepoInterface.save(photo);
        return photo1;
    }


    private Photo getPhotoById(UUID id){
        Optional<Photo> photo = photoRepoInterface.findById(id);
        if(photo.isPresent()) {
            return photo.get();
        }else{
            throw new ModelNotFoundException("El id "+id+" no se encuentra");
        }
    }

}
