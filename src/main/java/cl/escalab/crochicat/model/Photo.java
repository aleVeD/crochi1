package cl.escalab.crochicat.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name="photo")
public class Photo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name="title", length = 400)
    private String title;
    @Column(name = "image")
    private Byte image;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
