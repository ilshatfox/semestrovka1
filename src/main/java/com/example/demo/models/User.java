package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_info")
//@FieldMatch.List({@FieldMatch(first = "password", second = "passwordRepeat", message = "passwords vary")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 128)
    private String email;

    @NotNull
    @Size(min = 4, max = 128)
    private String password;

    @Transient
    @NotNull
    @Size(min = 4, max = 128)
    private String passwordRepeat;

    @Enumerated(value = EnumType.STRING)
    private Role role;


//    @OneToMany(
//            mappedBy = "bookCategory",
//            cascade = CascadeType.PERSIST,
//            fetch = FetchType.LAZY
//    )
//    private Set<Order> orders;

}
