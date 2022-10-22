package com.example.noticeboard.repository;

import com.example.noticeboard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * " +
            "FROM comment " +
            "WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId); // articleId를 찾지못해 @Param("articleId") 추가

    // 특정 닉네임의 모든 댓글 조회
    // 네이티브 쿼리 XML로 @Query 어노테이션 대체
    List<Comment> findByNickname(@Param("nickname") String nickname); // nickname을 찾지못해 @Param("nickname")추가


}