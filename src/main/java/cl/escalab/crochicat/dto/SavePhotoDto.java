package cl.escalab.crochicat.dto;
import cl.escalab.crochicat.model.Photo;
import cl.escalab.crochicat.model.User;
import org.springframework.hateoas.RepresentationModel;

public class SavePhotoDto extends RepresentationModel {
    private User user;
    private Photo photo;

    public SavePhotoDto(User user, Photo photo) {
        this.user = user;
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
