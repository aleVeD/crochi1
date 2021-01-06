package cl.escalab.crochicat.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name="photo")
public class Photo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID idPhoto;
    @Column(name="title", length = 400)
    private String title;
    @ApiModelProperty(notes = "esta propiedad es obligatoria")
    @Column(name = "image")
    private Byte image;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK1_user_id"))
    private User user;

    public Photo(UUID idPhoto, String title, Byte image, User user) {
        this.idPhoto = idPhoto;
        this.title = title;
        this.image = image;
        this.user = user;
    }

    public UUID getIdPhoto() {
        return idPhoto;
    }
    public void setIdPhoto(UUID idPhoto) {
        this.idPhoto = idPhoto;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Byte getImage() {
        return image;
    }

    public void setImage(Byte image) {
        this.image = image;
    }
}
