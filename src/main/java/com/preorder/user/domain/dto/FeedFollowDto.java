package com.preorder.user.domain.dto;

import com.preorder.user.domain.entity.Follow;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Builder
@Getter
public class FeedFollowDto {
    private String from;
    private String to;

    private static FeedFollowDto toDto(Follow follow) {
        return FeedFollowDto.builder()
                .from(follow.getUser().getName())
                .to(follow.getFollowId().getName())
                .build();
    }

    public static Page<FeedFollowDto> toPageDto(List<Follow> followList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), followList.size());

        List<FeedFollowDto> feedFollowDtoList = followList.subList(start, end)
                .stream()
                .map(FeedFollowDto::toDto)
                .toList();

        return new PageImpl<>(feedFollowDtoList, pageable, followList.size());
    }
}
