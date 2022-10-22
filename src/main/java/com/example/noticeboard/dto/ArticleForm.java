package com.example.noticeboard.dto;


import com.example.noticeboard.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 필드에 대한 생성자 생성
@ToString
@Getter
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

    public Article toEntity() {

        return new Article(id, title, content);
    }
}
