package com.preorder.user.domain.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.preorder.user.domain.entity.Post;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.io.IOException;

@Builder
@Getter
@JsonSerialize(using = FeedDto.FeedDtoSerializer.class)
public class FeedDto {
    Long postId;
    String title;
    String userName;

    private static FeedDto toDto(Post post){
        return FeedDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .userName(post.getUserName())
                .build();
    }
    public static Page<FeedDto> toPageDto(Page<Post> postList) {
        return postList.map(FeedDto::toDto);
    }

    public static class FeedDtoSerializer extends JsonSerializer<FeedDto> {
        @Override
        public void serialize(FeedDto feedDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("postId", feedDto.getPostId());
            jsonGenerator.writeStringField("title", feedDto.getTitle());
            jsonGenerator.writeStringField("userName", feedDto.getUserName());
            jsonGenerator.writeEndObject();
        }
    }
}
