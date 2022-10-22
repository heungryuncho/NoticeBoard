package com.example.noticeboard.api;

import com.example.noticeboard.dto.ArticleForm;
import com.example.noticeboard.entity.Article;
import com.example.noticeboard.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//컨트롤러는 음식점으로 비유하면 점원(웨이터) 역할

@RestController // RestAPI용 컨트롤러! JSON 객체 또는 텍스트를 반환 (데이터를 반환)
//@Controller는 html을 반환
public class ArticleApiController {

    @Autowired //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동연결
    private ArticleService articleService; // 보통 new를 써서 객체를 만들어야하는데 스프링부트가 알아서 만들어줌

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }
    //List<> 묶음으로 반환

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    } //타입이 List가 아닌 이유는 단일 반환,
    // url 요청으로 id값을 가져올때는 @PathVariable 사용

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } //ArticleForm dto를 엔티티로 반환 -> 반환된 dto에 article을 db에 저장
    //@RequestBody http요청의 본문(body)이 그대로 전달, HTTP 요청의 바디내용을 통째로 자바객체로 변환해서 매핑된 메소드 파라미터로 전달

    //PATCH 수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        //ResponseEntity에 Article데이터가 담겨서 JSON으로 반환.
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


