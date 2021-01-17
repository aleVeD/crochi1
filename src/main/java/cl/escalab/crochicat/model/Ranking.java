package cl.escalab.crochicat.model;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@ApiModel(description = "evaluación de cada foto")
@Entity
@Table(name = "ranking")
public class Ranking {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID idRanking;
    @Column(name="vote", nullable = false)
    private Float vote;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "photo_ranking",
               joinColumns = @JoinColumn(name = "id_ranking",
                                        referencedColumnName="idRanking"),
               inverseJoinColumns = @JoinColumn(name = "id_photo",
                                                referencedColumnName="idPhoto"))
    private List<Photo> photos;

    public UUID getIdRanking() {
            return idRanking;
    }

    public void setIdRanking(UUID idRanking) {
        this.idRanking = idRanking;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Float getVote() {
        return vote;
    }

    public void setVote(Float vote) {
        this.vote = vote;
    }
}
