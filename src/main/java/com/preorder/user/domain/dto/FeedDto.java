package com.preorder.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Builder
@Getter
public class FeedDto {
    Page<FeedPostDto> postList;
    Page<FeedCommentDto> commentList;
    Page<FeedFollowDto> followList;
    Page<FeedLikeDto> likeList;
}
