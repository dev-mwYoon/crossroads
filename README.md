# crossroads
## 국비 첫 프로젝트입니다
* 프로젝트 주제는 베테랑 운전자가 초보 운전자를 연수시켜주는 것입니다.
* 화면은 클론코딩으로 제작하고 문구는 프로젝트 주제에 맞춰서 만들었습니다.
* 클론 코딩 사이트는 위시켓(https://www.wishket.com/) 입니다.

제가 작업한 퍼블리싱 부분은 로그인(웹, 반응형), 회원가입(웹, 반응형), 비밀번호 찾기(웹, 반응형), 연수자 찾기(반응형), 환전하기(반응형)
마이페이지-비밀번호 변경(반응형), 마이페이지-회원탈퇴(반응형), 마이페이지-포인트내역(반응형), 마이페이지-연수내역(반응형) 입니다.

#### 백엔드 담당 분야
- 관리자 페이지를 담당했습니다.
- MyBatis를 이용하여 Mapper를 통해 DBMS에 접근하여 정보를 조회하고 화면에 전달했습니다.
- Javascript, jQuery를 이용하여 ajax를 통해 페이지 이동 없이 목록을 조회하고, 회원 삭제 및 페이징 처리를 구현하였습니다.

## 데이터베이스 테이블(DB ERD)
![crossroads-erd](https://user-images.githubusercontent.com/122762471/233522845-0ac26ded-111b-4a1a-8af9-7102ad2939ec.png)


## 😃 느낀점

&nbsp;프로젝트 처음 시작을 페이지 이동으로만 처리하다가 중간에 다 ajax로 변경하여 REST로 대부분 작업을 했는데 확실히 작업이 완성되고 난 후 왜 ajax와 REST를 써야
불필요한 데이터를 가져올 필요가 없어지고, 사용자 편의성이 증가하는지 몸소 체험할 수 있었습니다. 또한 다른 팀원이 작업한 프론트 부분을 백엔드를 맡게 되었는데, 
주석이 부족하여 코드를 이해하는데 시간이 조금 걸리면서 주석에 필요성을 느꼈고, 반복되는 javascript와 jquery 그리고 정리되어 있지 않은 코드는 
제가 재사용하는데 어려움을 겪었고 제가 퍼블리싱과 프론트를 담당했을 때 주석을 달지 않고 모듈화하지 않았던 저를 되돌아보게 되었습니다.

## 😥 아쉬운점

&nbsp;처음 프로젝트를 진행하면서 처음 기획했던 내용들과 너무나 많은 것들이 달라지게 되었습니다. 팀원과 소통의 부재로 중간에 테이블이 변경되어 Mapper 쿼리를 다 변경해야하는 일이 발생했고 기획의 중요성과 팀원과의 소통의 중요성을 아주 절실히 깨달을 수 있었습니다. 또한 기술이라는 것이 머리로 아는 것과
실제 기술로써 적용하는 것이 다르다는 것도 깨달았습니다. AOP를 사용하여 분리하고 싶었지만 일단 프로젝트 완성에 급급하여 사용할 수 없었고, 
OAuth를 사용하여 카카오, 네이버 로그인을 해보고 싶었지만 해당 업무를 얻어내지 못했습니다. 또한 REST와 ajax의 사용으로 javascript와 jquery를
잘 활용할 수 있게 되었지만 타임리프 문법을 사용할 기회가 적어서 아쉬웠습니다.
