package org.example.usermanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name is required")
    @Size(min = 5, message = "the name should be longer than 4 characters")
    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "a name should only contain small and capital letters")
    private String name;
    @NotEmpty(message = "username is required")
    @Size(min = 5, message = "the username should be longer than 4 characters")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;
    @NotEmpty(message = "password is required")
    @Column(columnDefinition = "varchar(25) not null")
    private String password;
    @Email
    @NotEmpty(message = "email is required")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;
    @NotEmpty(message = "role is required")
    @Pattern(regexp = "^(user|admin)$", message = "role should be either user or admin")
    private String role;
    @NotNull(message = "age is required")
    @Column(columnDefinition = "int not null")
    private Integer age;

}
