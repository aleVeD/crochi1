package cl.escalab.crochicat.model;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel(description = "rol del usuario dentro de la app")
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    private Integer idRol;
    @Column(name="name")
    private String name;
    @Column(name = "description", nullable = false)
    private String description;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
