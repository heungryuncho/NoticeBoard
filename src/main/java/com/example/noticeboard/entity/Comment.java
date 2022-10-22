package com.example.noticeboard.entity;

import com.example.noticeboard.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 Article에연관된다!
    @JoinColumn(name = "article_id") // "article_id"컬럼에 Article의 대표값을 저장!
    private Article article; //댓글의 부모 게시물

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if(dto.getId() != null) //잘못된 id가 입력되었다면
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getArticleId() != article.getId()) // url에 입력된 id와 dto에 담긴 id가 다르게 입력되었다면
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");

        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();

        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
