개발 과정 기록
https://statuesque-smash-e48.notion.site/1-e854c37aa42e45d38b0f9cbc70a1c191?pvs=4

엘리스 1차 프로젝트 CRUD 게시판 만들기

1차 프로젝트 기획

주제 : CRUD 게시판 개발

1. 엘리스랩 예약인원 커뮤니티 -> 엘리스랩 예약 시 예약 사이트만 존재.. 필요한 커뮤니티가 없는것 같아 나만의 커뮤니티를 만들어 보기 위함 **(결정)**


2. 노래 추천 커뮤니티 -> 여러 상황에 맞는 본인만의 추천곡, 추천 이유, 감상평을 추천할 수 있는 커뮤니티


#### 페르소나 설정
1. 홍길동(25) : 엘리스랩 오프라인으로 예약을 할 건데 어떻게 해야 하는지도 모르고 어떤 준비를 해야 하는지도 모르겠다.
2. 거주 : 부산
3. 학습 : 대부분 온라인으로만 진행


* 개발 순서
1. 반드시 구현되어야 하는 내용 : 게시판 Board, 게시글 Post, 댓글 Comment
   (생성, 조회, 수정, 삭제 기능 CRUD)

2. 개인별로 테마를 정하여 기본적인 게시판 페이지를 기반으로 좀더 차별화 된 컨셉의 나만의 게시판을 커스터마이징

3. SSR 방식과 CSR 방식을 사용해보면서 그 차이를 이해하기
   -> SSR : Thymeleaf 템플릿 엔진 / CSR : RestAPI 기반

4. 서비스 목적과 목표 정의
- 서비스 개발 목적 및 목표
- 서비스 이용 대상
- 상세 기능

Master Branch (Reviewer) : 코치님 (매주 토요일)
Develop Branch (Reviewer) : 개인 (매일)

* 게시판
1. 게시글의 집합소
2. 게시글 정렬
3. 새로운 게시물 작성
4. 게시글 pagination
5. 게시글 Title, content, writer 검색

* 게시글
1. 게시글 번호
2. 제목 (Title)
3. 작성자 (Writer)
4. 등록일
5. 조회수

* 게시글 생성
1. 제목(Title) / 내용(Contents) / 작성자(Writer) 입력
2. 입력중 초기화 버튼으로 초기화

* 게시글 수정/삭제
1. Title, Contents, Writer 변경 후 수정버튼 클릭시 수정
2. 삭제버튼 클릭시 삭제
3. 목록버튼 이동시 수정/삭제 작업 취소 후 보고 있던 페이지로 이동

* 게시글 조회 (게시판에서)
1. 검색조건 (Title, Contents, Writer)
2. 키워드 검색 후 검색버튼
3. 게시글의 Title 클릭시 조회수 + 1

* 댓글
1. 내용 : 작성자, 댓글, 댓글 등록/수정 시간

* 댓글 생성
1. 작성자, 댓글 내용 입력 후 등록 버튼

*댓글 수정/삭제
1. 등록된 댓글 옆 수정/삭제 버튼 클릭시 수정 칸 생성
2. 작성자, 내용 변경 후 수정버튼 클릭시 수정
3. 삭제버튼 클릭시 삭제

* 댓글 조회 (게시글에서)
1. 검색조건 (작성자, 댓글 내용)
2. 키워드 검색 후 검색버튼


API 계획
![image](https://github.com/user-attachments/assets/44645827-0aec-4868-a19b-88ec839e968a)

![image](https://github.com/user-attachments/assets/99005844-398f-47e4-ab93-7ee3f317b9d2)

![스크린샷 2024-03-01 153431](https://github.com/user-attachments/assets/3fdcfb80-5d1b-4594-afd2-4ea62e454a6e)

![스크린샷 2024-03-01 154008](https://github.com/user-attachments/assets/8e85270b-1c30-4096-9bed-67f2c2a9c0cf)

![스크린샷 2024-03-01 154254](https://github.com/user-attachments/assets/bd91b772-14c7-4cec-aac5-7007e4605e81)

![스크린샷 2024-03-01 154440](https://github.com/user-attachments/assets/0aac3403-e10f-4887-8975-b83c8cd424fd)

![스크린샷 2024-03-01 154607](https://github.com/user-attachments/assets/ccb84b3c-6fe5-4f45-a215-ff75d29143ba)

