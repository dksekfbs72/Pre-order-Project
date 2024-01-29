package com.preorder.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createAt;
    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }
}
