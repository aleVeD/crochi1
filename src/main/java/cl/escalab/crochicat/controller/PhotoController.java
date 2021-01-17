package cl.escalab.crochicat.controller;

import cl.escalab.crochicat.model.Photo;
import cl.escalab.crochicat.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
    @ResponseBody
    public ResponseEntity<Photo> savePhoto(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user){
        cl.escalab.crochicat.model.User user1 = new cl.escalab.crochicat.model.User(user.getUsername());
        Photo photo = photoService.save(new Photo(file, user1.getIdUser()));
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Photo> deletePhoto(@PathVariable("id") UUID id){
        Photo photoDeleted = getPhotoById(id);
        photoService.delete(id);
        return new ResponseEntity<>(photoDeleted, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhoto(UUID id){
        Photo photo = getPhotoById(id);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    private Photo getPhotoById(UUID id){
        Photo photo = photoService.get(id);
        return photo;
    }

}
