package cl.escalab.crochicat.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.xml.soap.Text;
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
    @Column(name = "image", nullable = false)
    private Byte image;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK1_user_id"))
    private User user;



    public Photo() {
    }



    public UUID getIdPhoto() {
        return idPhoto;
    }
    public void setIdPhoto(UUID idPhoto) {
        this.idPhoto = idPhoto;
    }

    public Byte getImage() {
        return image;
    }

    public void setImage(Byte image) {
        this.image = image;
    }
}
