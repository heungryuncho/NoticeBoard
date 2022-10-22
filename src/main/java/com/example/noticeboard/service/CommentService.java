package com.example.noticeboard.service;

import com.example.noticeboard.dto.CommentDto;
import com.example.noticeboard.entity.Article;
import com.example.noticeboard.entity.Comment;
import com.example.noticeboard.repository.ArticleRepository;
import com.example.noticeboard.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // 아티클 데이터도 db에서 가져올 필요가 있기때문

    public List<CommentDto> comments(Long articleId) {
//        // 조회 : 댓글 목록
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//        // 변환 : 엔티티 -> DTO
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i = 0; i< comments.size(); i++){
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//
//        // 반환
//        return dtos;
//
//        stream문법을 사용해보기
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

    }

    @Transactional // db를 건들기 때문에 중간에 문제가 생기면 롤백되도록!
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 처리 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);

    }

    @Transactional // db를 건들기 때문에 중간에 문제가 생기면 롤백되도록!
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() ->  new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
   }

   @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
       Comment target = commentRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

       // 댓글 DB에서 삭제
       commentRepository.delete(target);

       // 삭제 댓글을 DTO로 반환
       return CommentDto.createCommentDto(target);

    }
}
