package cl.escalab.crochicat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.UUID;

@ApiModel(description = "Post acerca de una foto")
@Entity
@Table(name="photo")
public class Photo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID idPhoto;
    @ApiModelProperty(notes = "esta propiedad es obligatoria")
    @Column(name = "filename", nullable = false)
    private String filename;
    @Column(name = "filetype", nullable = false)
    private String filetype;
    @Column(name = "image", nullable = false)
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK1_user_id"))
    private User user;


    public Photo(UUID idPhoto, String filename, String filetype, byte[] image, User user) {
        this.idPhoto = idPhoto;
        this.filename = filename;
        this.filetype = filetype;
        this.image = image;
        this.user = user;
    }

    public Photo() {

    }
    public Photo(UUID id) {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(UUID idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

