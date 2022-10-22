# 게시판API만들기
#### 프로젝트 이름 : NoticeBoard

#### 프로젝트 기간 : 2022.10.11~2022.10.17

#### 프로젝트 올린이 :  조흥륜

## 소개
스프링부트 배운 것을 연습하기 위해 게시판API 만들기를 진행하였습니다.  
클론 코딩을 통해 만들어 보았는데 작성되어있는 코드를 나름대로 뜯어보며 고쳐보았습니다.  
그런데 만들어진 프로젝트의 환경이 제가 진행하는 환경과 달라 마주하게 된 이슈들이 있었는데  이를 해결해보고 이해가 안되는 부분들을 주석 처리하며 공부하면서 진행하였습니다.

## 사용기술 및 환경
Spring boot, Gradle, JPA, Mysql, Java11, Window

## Issue 
### Issue 1
더미 데이터를 만들어서 작동하는지 확인하기위해 application.properties에서 `spring.datasource.initialization-mode` 를 성정했는데 프로젝트에 사용된 버전이 달라서 `spring.sql.init.mode`를 사용하여 문제를 해결

v2.4 이하 설정: `spring.datasource.initialization-mode` 
v2.5 이상 설정: `spring.sql.init.mode`

### Issue2
`ArticleServiceTest.java`에서 테스트를 진행시 오류 발생

오류메시지 전문
Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.NullPointerException] with root cause

→ 구글 검색을 통해 의존성주입 `@Autowired` 안해주거나  또는 application.properties에서 스프링 부트의 버전을 낮추어 실행하여 정상 작동하는 것을 확인

### Issue3
`CommentRepositoryTest`에서 “특정 게시글의 모든 댓글 조회”에서 4번 게시글 조회 테스트 중 오류 발생

오류메시지 전문
For queries with named parameters you need to use provide names for method parameters. Use @Param for query method parameters, or when on Java 8+ use the javac flag -parameters.; nested exception is java.lang.IllegalStateException: For queries with named parameters you need to use provide names for method parameters. Use @Param for query method parameters, or when on Java 8+ use the javac flag -parameters.

→CommentRepository에서 `articleId`를 찾지 못하여 테스트 실패 `@Param("articleId")` 어노테이션을 추가 하여 테스트 통과

### Issue4
`CommentRepositoryTest`에서 “특정 닉네임의 모든 댓글 조회”에서  “Park”이라는 nickname으로 조회 테스트 중 오류 발생

오류메시지 전문
Could not locate ordinal parameter [1], expecting one of []; nested exception is java.lang.IllegalArgumentException: Could not locate ordinal parameter [1], expecting one of []

→CommentRepository에서 `nickname` 찾지 못하여  `@Param("nickname")` 어노테이션을 추가 하여 테스트 통과
