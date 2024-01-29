package com.preorder.user.domain.dto;

import com.preorder.user.domain.entity.Post;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Builder
@Getter
public class FeedPostDto {
    private Long postId;
    private String title;
    private String userName;

    private static FeedPostDto toDto(Post post){
        return FeedPostDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .userName(post.getUserName())
                .build();
    }
    public static Page<FeedPostDto> toPageDto(List<Post> postList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), postList.size());

        List<FeedPostDto> feedPostDtoList = postList.subList(start, end)
                .stream()
                .map(FeedPostDto::toDto)
                .toList();

        return new PageImpl<>(feedPostDtoList, pageable, postList.size());
    }
}
