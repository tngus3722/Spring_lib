# Fish

프로젝트 개요
전국 낚시터를 검색, 리뷰, 지도 기능을 제공하는 웹 서비스 

<h1>세부 기술</h1>
  <h2>Spring MVC</h2>

  <h2>mybatis를 이용한 CRUD</h2>
    리뷰 글 작성<br>
    리뷰 글 삭제<br>
    리뷰 글 수정<br>
    리뷰 글 조회<br>
    낚시터 위치 검색<br>
    해당 낚시터 정보 조회<br>
![1](https://user-images.githubusercontent.com/32263898/87240292-4d834680-c453-11ea-9738-86f6d1d0836b.PNG)
  
![2](https://user-images.githubusercontent.com/32263898/87240024-971e6200-c450-11ea-95bd-4860d2f3dfc0.PNG)
  <h2>database</h2>
  
   <p>
  *small : int 0~65536, 2bit 사용 -> int보다 적당한 크기, 작은 부하라고 생각해서 사용<br>
  *text : index를 필요하지 않고 비교적 장문이므로 text사용
  </p>
  
![3](https://user-images.githubusercontent.com/32263898/87240025-984f8f00-c450-11ea-960d-66651053b1fc.PNG)
    
   <p>
  *decimal : 정확한 실수 data를 표현하기위해 decimal 사용<br>
  주어진 데이터 양식<br>
  위도 -> xx.(10자리)<br>
  경도 -> xxx.(10자리)<br>
  </p>
  
![4](https://user-images.githubusercontent.com/32263898/87240026-98e82580-c450-11ea-9e9d-5e2645a81878.PNG)

  <h2> 인덱스 화면</h2>
    <p>검색 form과 table로 이루어진 data들이 출력된다</p>
    
![5](https://user-images.githubusercontent.com/32263898/87240132-d7321480-c451-11ea-8c49-455f5d31c27d.PNG)

  <h2> 검색 결과 화면</h2>
    <p>경기도 검색시 주소지에 "경기도"가 포함된 data들을 출력한다</p>
    
![6](https://user-images.githubusercontent.com/32263898/87240134-d7caab00-c451-11ea-958a-1307cf262873.PNG)
   <h2>세부 결과 화면</h2>
    <p>원하는 데이터를 클릭시 해당 데이터의 정보 출력를 출력한다<br>
    kakao map api를 통한 위치 정보를 제공한다<br>
    리뷰 조회, 삭제, 수정, 등록을 할 수 있다
    리뷰를 등록할 때 비밀번호 값으로 수정, 삭제를 할 수 있다.
    </p>
    
![7](https://user-images.githubusercontent.com/32263898/87240135-d8634180-c451-11ea-82f7-6f23198d7f66.PNG)



<h2> 0712 feedback 개선 </h2>
<ul>
  <li>SQL DDL 추가</li>
  <li>directory 이름 수정 DTO -> Domain</li>
  <li>file 이름 수정 ( fishingHoleDTO -> FishingHole, reviewDTO -> Review )</li> 
  <li>Review int id -> Review Long id 수정, FisingHold int id -> Integer id  ( wrapper class) , fishing hold id 는 744개로 고정된 숫자 </li>
  <li>Review id가 long으로 바뀌었으니 mysql SMALLINT id -> BIGINT id로 수정</li>
  <li>date -> created_at, update_at으로 나눔</li>
  <li>myController -> FishingHoleController, ReviewController로 </li>
  <li>user 구현할 예정이므로 review table password 삭제</li>
  <li>ajax와 responsebody를 이용하여 구현 ( modelandview를 걷어냄 ) </li>
  <li>user 구현 --> ongoing</li>
</ul>

<h2>0719 feedback 개선</h2>
<ul>
  <li>통일성을 위해 mysql fishing_hole SMALLINT -> BIGINT, java FishingHold Integer -> Long 변경</li> 
  <li>client side에서 request payload에 비밀번호가 그대로 노출됨을 확인 -> 단방향 hash 추가 </li>
  <li>password bcrypt암호화 구현</li>
  <li>cookie와 jwt token을 이용한 user 기능 구현 <br> view (hashed) -> Server(bcrypt) -> JWT token 생성 -> cookie 발급  <br>  view (cookie) -> server (cookie의 token payload 복호화 한 값이 database 정보와 동일한가?)</li>
  <li>ResponseEntity를 통해 상태정보 </li>
  <li>AWS EC2 hosting</li>
  <li>cookie의 CSRF취약점 개선 -> ongoing</li>
</ul>  
