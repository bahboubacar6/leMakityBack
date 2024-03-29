package com.group.makity.leMakity.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    private String lastName;

    private String firstName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String image;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rePassword", nullable = false)
    private String resetPasswordToken;

    private String telephone;

    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "users_roles_Associations",
            joinColumns = @JoinColumn( name = "id_user" ),
            inverseJoinColumns = @JoinColumn( name = "id_role" ) )
    private List<AppRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;

}
