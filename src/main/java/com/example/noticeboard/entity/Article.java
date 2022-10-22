package com.example.noticeboard.entity;

import lombok.*;


import javax.persistence.*;

@NoArgsConstructor // 파라미터가 아무것도 없는 디폴트 생성자 추가
@AllArgsConstructor // 생성자 주입하는 어노테이션
@Entity // DB가 해당 객체를 인식 가능하게 함 (해당 클래스로 테이블을 만든다)
@ToString // 객체가 가지고있는 정보나 값들을 문자열로 만들어 리턴
@Getter
public class Article {

    @Id // 대푯값으로 사용 (사람으로 치면 주민등록번호와 같음)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동생성하는 어노테이션
    private Long id;

    @Column //객체 필드를 테이블의 컬럼에 매핑시켜 줌
    private String title;

    @Column
    private String content;


    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }
    // patch메소드를 만든이유는 이 메소드가 없으면 title이나 content의 값을 빼고 넣었을 경우 null이 나오는 것을 방지하기위해
}
