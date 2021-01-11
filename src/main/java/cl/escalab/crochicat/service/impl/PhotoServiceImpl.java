package cl.escalab.crochicat.service.impl;

import cl.escalab.crochicat.exception.ModelNotFoundException;
import cl.escalab.crochicat.model.Photo;
import cl.escalab.crochicat.model.Post;
import cl.escalab.crochicat.repo.PhotoRepoInterface;
import cl.escalab.crochicat.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {
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
        Photo photo = getPhotoById(id);
        photoRepoInterface.getOne(photo.getIdPhoto());
        return photo;
    }

    @Override
    public Boolean delete(UUID id) {
        Photo photo = getPhotoById(id);
        photoRepoInterface.deleteById(id);
        return true;
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
