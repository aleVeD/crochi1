package cl.escalab.crochicat.model;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
@ApiModel(description = "post acerca de las fotos publicadas")
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name="title", length = 300, nullable = false)
    private String title;
    @Column(name="text_post", length = 600, nullable = false)
    private String textPost;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_user_id"))
    private User user;

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

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }
}
