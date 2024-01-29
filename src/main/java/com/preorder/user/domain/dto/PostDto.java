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
@JsonSerialize(using = PostDto.FeedDtoSerializer.class)
public class PostDto {
    private Long postId;
    private String title;
    private String userName;

    private static PostDto toDto(Post post){
        return PostDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .userName(post.getUserName())
                .build();
    }
    public static Page<PostDto> toPageDto(List<Post> postList, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), postList.size());

        List<PostDto> postDtoList = postList.subList(start, end)
                .stream()
                .map(PostDto::toDto)
                .toList();

        return new PageImpl<>(postDtoList, pageable, postList.size());
    }

    public static class FeedDtoSerializer extends JsonSerializer<PostDto> {
        @Override
        public void serialize(PostDto postDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("postId", postDto.getPostId());
            jsonGenerator.writeStringField("title", postDto.getTitle());
            jsonGenerator.writeStringField("userName", postDto.getUserName());
            jsonGenerator.writeEndObject();
        }
    }
}
