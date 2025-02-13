package com.example.application.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    String avatar;

    @Column(unique = true, nullable = false)
    String email;

    @Column(unique = true, nullable = false)
    String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Address> addresses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    Role role;

    @Builder.Default
    Boolean userStatus = true;
}
