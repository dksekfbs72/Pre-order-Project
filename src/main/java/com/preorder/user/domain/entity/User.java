package com.preorder.user.domain.entity;

import com.preorder.global.type.UserRole;
import jakarta.persistence.*;
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
    private Long id;

    private String name;
    private String email;
    private String password;
    private boolean emailCert;
    private String emailKey;
    private String profileImage;
    private String description;
    private String post_id;
    private UserRole role;
}
