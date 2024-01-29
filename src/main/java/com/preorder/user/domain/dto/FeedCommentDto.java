package com.preorder.user.domain.dto;

import com.preorder.user.domain.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Builder
@Getter
public class FeedCommentDto {
    private Long commentId;
    private String from;
    private String to;

    private static FeedCommentDto toDto(Comment comment) {
        return FeedCommentDto.builder()
                .commentId(comment.getId())
                .from(comment.getUser().getName())
                .to(comment.getPost().getUserName())
                .build();
    }

    public static Page<FeedCommentDto> toPageDto(List<Comment> commentList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), commentList.size());

        List<FeedCommentDto> feedFollowDtoList = commentList.subList(start, end)
                .stream()
                .map(FeedCommentDto::toDto)
                .toList();

        return new PageImpl<>(feedFollowDtoList, pageable, commentList.size());
    }
}
