package edu.miu.cs.cs425.eregistrar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(nullable = false)
    @NotBlank(message = "* First Name is required")
    private String  name;
    @Column(nullable = true)
    private String  username;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "* Email is required")
    @Email(message = "{errors.invalid_email}")
    private String  email;
    @Column(nullable = false)
    @NotBlank(message = "* Password is required")
    @Size(min = 8)
    private String  password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    private Role role;

    // Data fields needed for implementing methods of UserDetails interface
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}

