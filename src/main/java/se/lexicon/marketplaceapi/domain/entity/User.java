package se.lexicon.marketplaceapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")
@Builder
@Entity
public class User {

    @Id
    @Column(updatable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    private boolean expired;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

}

