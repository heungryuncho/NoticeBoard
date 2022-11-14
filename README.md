# :pushpin: CRUD 게시판 
>CRUD게시판


</br>

## 1. 제작 기간 & 참여 인원
- 2022년 10월 11일 ~ 10월 17일
- 개인 프로젝트

</br>

## 2. 사용 기술
#### `Back-end`
  - Java 11
  - Spring Boot 2.6.2
  - Gradle
  - Spring Data JPA
  - H2
  - MySQL 8.0.3


</br>

## 3. 트러블 슈팅
### Issue 1
더미 데이터를 만들어서 작동하는지 확인하기위해 application.properties에서 `spring.datasource.initialization-mode`를 설정했지만 
프로젝트에 사용된 버전과 달라서 `spring.sql.init.mode`를 사용하여 해결하였습니다.

v2.4 이하 설정: `spring.datasource.initialization-mode` 
v2.5 이상 설정: `spring.sql.init.mode`

### Issue2
`ArticleServiceTest.java`에서 테스트를 진행시 오류 발생

오류메시지 전문
Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.NullPointerException] with root cause

→ 구글 검색을 통해 의존성주입 `@Autowired` 안해주거나
또는 application.properties에서 스프링 부트의 버전을 낮추어 실행하여 정상 작동하는 것을 확인하였습니다.




</div>
</details>
    
</br>

## 4. 느낀점
자바와 스프링 부트를 연습하기 위해 게시판 API 프로젝트를 진행하였습니다.  
클론 코딩을 통해 만들어 보았는데 작성되어있는 코드를 나름대로 뜯어보며 고쳐보았습니다.  
그런데 클론 코딩의 프로젝트 환경이 제가 진행하는 환경과 달라 마주하게 된 이슈들이 있었는데  
이를 해결해보고 이해가 안 되는 부분들을 주석 처리하며 공부하면서 진행하였습니다.  
