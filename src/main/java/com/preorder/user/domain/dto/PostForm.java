package com.preorder.user.domain.dto;

import com.preorder.user.domain.entity.Post;
import com.preorder.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostForm {
    private String title;
    private String text;

    public Post toEntity(User user) {
        return Post.builder()
                .user(user)
                .title(this.title)
                .text(this.text)
                .userName(user.getName())
                .build();
    }
}
