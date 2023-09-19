# 중고거래 커뮤니티 플랫폼

## 주요기능
1. 회원가입, 로그인
2. 게시글 CRUD, 이미지 첨부
3. 댓글
4. 회원정보 수정

## 개발기간
2023.03-2023.07

## 개발인원
1명(개인프로젝트)

## 개발환경
- Springboot
- java
- JPA
- javascript,AJAX
- HTML

## 세부기능
### 회원가입
1. 아이디 중복체크(AJAX): 중복체크 완료하지 않을 시 회원가입 불가함.
2. 회원가입 시 비밀번호 암호화
3. validation
![2](https://github.com/uuuuuunu/Boardpj/assets/125693102/7a95fa78-84cc-4922-856a-fc15e1b5f466)
![3](https://github.com/uuuuuunu/Boardpj/assets/125693102/0c38c7e5-14b3-40fd-83f6-adb7fc7324d2)

### 로그인, 로그아웃
1. spring security ->로그인 하지 않을 시 사이트 접속 제한
![4](https://github.com/uuuuuunu/Boardpj/assets/125693102/0c309dea-1f10-476c-8c69-7e5be22b918b)

### 게시글 목록, 작성
1. 게시글 검색 기능
2. 판매중,예약,판매완료 여부
3. 작성자(닉네임), 조회수 확인
4. 왼쪽 상단에 현재 접속중인 회원 닉네임 표시
5. 게시글 작성: 제목, 금액, 판매상태, 이미지 첨부
![5](https://github.com/uuuuuunu/Boardpj/assets/125693102/1ccbef97-5e42-41d8-8b98-e5410b894112)
![6](https://github.com/uuuuuunu/Boardpj/assets/125693102/9e591341-5018-4ecd-ab60-b5dabe33437b)
![화면 캡처 2023-09-19 153645](https://github.com/uuuuuunu/Boardpj/assets/125693102/2fd2774f-caab-4fb1-ab4e-672e7a749f52)

### 게시글 확인, 댓글
1. 게시글 목록에서 게시글 제목 클릭 시 개별 게시물 내용 확인
2. 글마다 댓글 작성
![d](https://github.com/uuuuuunu/Boardpj/assets/125693102/3572a82e-1981-469b-8f00-262e977bacc9)
![확인1](https://github.com/uuuuuunu/Boardpj/assets/125693102/ff0e371c-1766-42b9-ac37-9b7f3662f6b2)
![확인2](https://github.com/uuuuuunu/Boardpj/assets/125693102/b761f46a-2822-420e-92f4-edec6b4848c5)

### 회원정보 수정
1. 아이디를 제외 한 회원정보 수정
![회원정보1](https://github.com/uuuuuunu/Boardpj/assets/125693102/028cebc4-aa92-4c95-adac-cd0b614de096)
![회원정보2](https://github.com/uuuuuunu/Boardpj/assets/125693102/51fdeedc-7bbd-4b0d-85bc-7f753d6e0a24)

### 댓글 삭제, 게시글 수정삭제
1. 현재 접속 한 회원이 작성 한 댓글 삭제, 게시글 수정삭제
2. 작성 글 관리 -> 게시글 번호 클릭 시 게시글 수정 가능
3. 댓글 다중선택 삭제
![수정1](https://github.com/uuuuuunu/Boardpj/assets/125693102/82f6e43d-dfa6-4bb1-9b89-96313135eb18)
![수정2](https://github.com/uuuuuunu/Boardpj/assets/125693102/444c92e6-801e-4b2e-b65f-fa5314fe333b)
![수정3](https://github.com/uuuuuunu/Boardpj/assets/125693102/69f21375-8aa3-421a-b97b-538d1a8f2b42)

### ERD
![1](https://github.com/uuuuuunu/Boardpj/assets/125693102/080bcdf6-0fe4-4d2f-be9e-1e8eb39246a0)


