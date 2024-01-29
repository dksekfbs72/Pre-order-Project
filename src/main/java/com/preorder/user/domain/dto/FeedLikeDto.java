package com.preorder.user.domain.dto;

import com.preorder.user.domain.entity.LikeTable;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Builder
public class FeedLikeDto {
    private Long postId;
    private String from;
    private String to;
    private static FeedLikeDto toDto(LikeTable like) {
        return FeedLikeDto.builder()
                .from(like.getUser().getName())
                .to(like.getPost() == null ? like.getComment().getUser().getName() : like.getPost().getUserName())
                .postId(like.getPost() == null ? like.getComment().getId() : like.getPost().getId())
                .build();
    }

    public static Page<FeedLikeDto> toPageDto(List<LikeTable> likeList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), likeList.size());

        List<FeedLikeDto> feedFollowDtoList = likeList.subList(start, end)
                .stream()
                .map(FeedLikeDto::toDto)
                .toList();

        return new PageImpl<>(feedFollowDtoList, pageable, likeList.size());
    }
}
