package spring.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@Entity
@Table
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    @Size(min=2, message = "Минимум 2 знака")
    private  String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> userSet;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getAuthority() {
        return getName();
    }
}
