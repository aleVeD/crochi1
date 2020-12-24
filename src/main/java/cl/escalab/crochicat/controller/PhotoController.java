package cl.escalab.crochicat.controller;

import cl.escalab.crochicat.model.Photo;
import cl.escalab.crochicat.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public ResponseEntity<List<Photo>> getAllPhotos(){
        List<Photo> photos = photoService.getAll();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Photo> savePhoto(@RequestBody Photo photo){
        Photo photo1 = photoService.save(photo);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Photo> deletePhoto(@PathVariable("id") UUID id){
        Photo photoDeleted = getPhotoById(id);
        photoService.delete(id);
        return new ResponseEntity<>(photoDeleted, HttpStatus.OK);
    }

    private Photo getPhotoById(UUID id){
        Photo photo = photoService.get(id);
        return photo;
    }

}
