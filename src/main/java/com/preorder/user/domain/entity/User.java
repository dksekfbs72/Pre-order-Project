package com.preorder.user.domain.entity;

import com.preorder.global.type.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String email;
    String password;
    boolean emailCert;
    String emailKey;
    String profileImage;
    String description;
    String follow_id;
    String post_id;
    UserRole role;
}
