package com.example.noticeboard.repository;

import com.example.noticeboard.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

//리파짓토리는 음식점으로 비유하면 보조주방장!

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

//JpaRepository <1,2> 1에는 관리 대상 엔티티를 넣어줌, 2에는 관리 대상 엔티티의 대푯값의 타입을 넣어줌