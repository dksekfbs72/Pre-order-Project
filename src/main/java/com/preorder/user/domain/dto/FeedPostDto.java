package com.preorder.user.domain.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.preorder.user.domain.entity.Post;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

@Builder
@Getter
@JsonSerialize(using = FeedPostDto.FeedDtoSerializer.class)
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

    public static class FeedDtoSerializer extends JsonSerializer<FeedPostDto> {
        @Override
        public void serialize(FeedPostDto feedPostDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("postId", feedPostDto.getPostId());
            jsonGenerator.writeStringField("title", feedPostDto.getTitle());
            jsonGenerator.writeStringField("userName", feedPostDto.getUserName());
            jsonGenerator.writeEndObject();
        }
    }
}
