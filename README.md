# 📝 매장 테이블 예약 서비스 구현

## 1️⃣ Tech Stack
- Language : `java`
- Build : `gradle`
- DataBase : `MySQL`
- JDK : `JDK 11`
- Framework : `SpringBoot`
- library : `Spring Data JPA`, `Lombok`, `Swagger`, `Jwt`

## 2️⃣ 개발 기간
- 2023.11.13 ~ 2023.12.04

## 3️⃣ ERD
![image](https://github.com/kdmin0706/store-reservation/assets/124044861/c7a89ca5-e891-4d05-bb8d-cd941490d6d0)

## 4️⃣ 시나리오
- 매장 점장은 예약 서비스 앱에 상점을 등록한다(매장명, 상점 위치, 상점 설명)
- 매장을 등록하기 위해서는 파트너 회원가입이 되어야한다.(따로 승인 조건은 없으며 가입 후 바로 사용 가능)
- 매장 이용자는 앱을 통해서 매장을 검색하고 상세 정보를 확인한다.
- 매장 상세 정보를 보고, 예약을 진행한다. (예약을 진행하기 위해서는 회원 가입이 필수적이다.)
- 서비스를 통해서 예약한 이후에, 예약 10분 전에 도착하여 키오스크를 통해 방문 확인을 진행한다.
- 점장은 예약이 들어오면 예약 승인/거절을 할 수 있는 기능이 있다.
- 예약 및 사용 이후에 리뷰를 작성할 수 있다.
- 리뷰의 경우, 수정은 리뷰 작성자만, 삭제 권한은 리뷰를 작성한 사람과 매장의 관리자(점장) 만 삭제할 수 있다.

## 5️⃣ API 명세서

### ✅ 1. 인증 API
#### 1-1) 사용자 생성 (매니저 회원 가입)
<details>
<summary> 경로/ 파라미터 / 결과 </summary>

경로 : [POST] http://localhost:8080/api/register/manager

파라미터

~~~
{
  "email": "manager@manager.com",
  "password": "12345",
  "phoneNumber": "010-1111-2222",
  "username": "매니저"
}
~~~

결과
~~~
{
  "username": "매니저",
  "email": "manager@manager.com",
  "password": "$2a$10$goYbnGXH9fVx.Xa9XiLuPuzIqiEAh92yd0yPCSDstnJsUWvLNR6pi",
  "phoneNumber": "010-1111-2222"
}
~~~
</details>

#### 1-2) 사용자 생성 (매니저 로그인)
<details>
<summary> 경로/ 파라미터 / 결과 </summary>

경로 : [POST] http://localhost:8080/api/login/manager

파라미터

~~~
{
  "email": "manager@manager.com",
  "password": "12345"
}
~~~

결과
- 성공
~~~
{
  eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1hbmFnZXIuY29tIiwianRpIjoiTitsbEl3UHRmNFVDcGxEZlRPOFR2cjloS0toWUlJbDRyN0xZU3Job0djST0iLCJyb2xlcyI6IlBBUlRORVIiLCJpYXQiOjE3MDEwNzc0NjMsImV4cCI6MTcwMTA4MTA2M30.h2EHzDXB_3r7_8kCjLQ--5znX37K80nqAmhEhhRp9DE
}
~~~
- 실패
~~~
{
  "errorCode": "PASSWORD_NOT_MATCH",
  "errorMessage": "비밀번호가 일치하지 않습니다"
}
~~~

</details>

#### 1-3) 사용자 생성 (방문객 회원 가입)
<details>
<summary> 경로/ 파라미터 / 결과 </summary>

경로 : [POST] http://localhost:8080/api/register/customer

파라미터

~~~
{
  "email": "user@user.com",
  "password": "12345",
  "phoneNumber": "010-1111-2222",
  "username": "방문객"
}
~~~

결과
~~~
{
  "username": "방문객",
  "email": "user@user.com",
  "password": "$2a$10$p5KNpz0BfU3v2CXCMzaMeOL6aX3LK8seAk7Av8z4QmmvOY06woMLu",
  "phoneNumber": "010-1111-2222"
}
~~~
</details>

#### 1-4) 사용자 생성 (방문객 로그인)
<details>
<summary> 경로/ 파라미터 / 결과 </summary>

경로 : [POST] http://localhost:8080/api/login/customer

파라미터

~~~
{
  "email": "user@user.com",
  "password": "12345"
}
~~~

결과
- 성공
~~~
{
  eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQHVzZXIuY29tIiwianRpIjoiRlpobzhrejNtTjVmK29qNFVvY1BMM05wbkEzdXBHcG5uMGcvMWdsSlZjOD0iLCJyb2xlcyI6IlVTRVIiLCJpYXQiOjE3MDEwNzc2MzUsImV4cCI6MTcwMTA4MTIzNX0.tsit6o7fH1Fr3BssnQYXWaevDjyJMSSeKdZ-x_wO8XU
}
~~~
- 실패
~~~
{
  "errorCode": "PASSWORD_NOT_MATCH",
  "errorMessage": "비밀번호가 일치하지 않습니다"
}
~~~
</details>

### ✅ 2. 매장 API
#### 2-1) 매장 등록
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [POST] http://localhost:8080/api/store/partner/create

파라미터

~~~
{
  "location": "대한민국 어딘가",
  "managerId": 1,
  "storeName": "store00",
  "phoneNumber": "02-1234-5670"
}
~~~

결과
- 성공
~~~
{
  "storeName": "store00"
}
~~~

- 실패
~~~
{
  "errorCode": "JWT_TOKEN_WRONG_TYPE",
  "errorMessage": "유효하지 않은 구성의 JWT 토큰입니다."
}
~~~
</details>

#### 2-2) 매장 수정
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [PUT] http://localhost:8080/api/store/partner/update/{id}

파라미터

~~~
{
  "storeName": "store11",
  "location": "미국 어딘가"
}
~~~

결과
- 성공
~~~
{
  "storeName": "store11",
  "location": "미국 어딘가"
}
~~~

- 실패
~~~
{
  "errorCode": "STORE_NOT_FOUND",
  "errorMessage": "매장을 찾을 수 없습니다."
}
~~~

</details>

#### 2-3) 매장 삭제
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [DELETE] http://localhost:8080/api/store/partner/delete/{id}

결과
- 성공
~~~
  매장 삭제가 완료되었습니다.
~~~
- 실패
~~~
{
  "errorCode": "STORE_NOT_FOUND",
  "errorMessage": "매장을 찾을 수 없습니다."
}
~~~
or
~~~
{
  "errorCode": "JWT_TOKEN_WRONG_TYPE",
  "errorMessage": "유효하지 않은 구성의 JWT 토큰입니다."
}
~~~
</details>

#### 2-4) 매장 상세 정보
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [GET] http://localhost:8080/api/store/detail/{name}

결과
~~~
{
  "manager": {
    "createdAt": "2023-11-27T20:44:50.472155",
    "updatedAt": "2023-11-27T20:44:50.472155",
    "id": 1,
    "username": "매니저",
    "memberType": "PARTNER",
    "email": "manager@manager.com",
    "password": "$2a$10$dGhYjxVviE06t3VZfyPMqeLRChqh3sPHl0lit.Zanf3L/LR20bwom",
    "phoneNumber": "010-1111-2222",
    "enabled": false,
    "authorities": [
      {
        "authority": "ROLE_PARTNER"
      }
    ],
    "accountNonLocked": false,
    "accountNonExpired": false,
    "credentialsNonExpired": false
  },
  "storeName": "store00",
  "location": "대한민국 어딘가",
  "phoneNumber": "02-1234-5670"
}
~~~
</details>

#### 2-5) 매니저 본인 매장 조회
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [GET] http://localhost:8080/api/partner/info?id={id}

결과
~~~
[
  {
    "manager": {
      "createdAt": "2023-11-27T19:19:32.508381",
      "updatedAt": "2023-11-27T19:19:32.508381",
      "id": 1,
      "username": "매니저",
      "memberType": "PARTNER",
      "email": "manager@manager.com",
      "password": "$2a$10$RfxttdA2rzdB.3tuwFPgju5ZduFHro/1BGN/hjkr2QfF6aXUdcOYS",
      "phoneNumber": "010-1111-2222",
      "enabled": false,
      "authorities": [
        {
          "authority": "ROLE_PARTNER"
        }
      ],
      "credentialsNonExpired": false,
      "accountNonExpired": false,
      "accountNonLocked": false
    },
    "storeName": "store00",
    "location": "대한민국 어딘가",
    "phoneNumber": "02-1234-5670"
  }
]
~~~
</details>

### ✅ 3. 예약 API
#### 3-1) 예약 생성
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [POST] http://localhost:8080/api/reservation/create

파라미터
~~~
{
  "userId": 1,
  "storeId": 1,
  "reservationDate": "2023-11-19",
  "reservationTime": "16:00:00"
}
~~~

결과
~~~
{
  "username": "방문객",
  "userPhoneNumber": "010-1111-2222",
  "storeName": "store00",
  "reservationStatus": "STANDBY",
  "reservationDate": "2023-11-19",
  "reservationTime": "16:00:00"
}
~~~

</details>

#### 3-2) 예약 삭제
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [PUT] http://localhost:8080/api/reservation/cancel?reservationid={id}

결과
~~~
{
  "reservationId": 1,
  "username": "방문객",
  "userPhoneNumber": "010-1111-2222",
  "storeName": "store00",
  "reservationStatus": "CANCELED",
  "arrivalStatus": "READY",
  "reservationDate": "2023-11-19",
  "reservationTime": "16:00:00"
}
~~~

</details>

#### 3-3) 예약 조회
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [GET] http://localhost:8080/api/reservation/partner/reservation-list/{id}

결과
~~~
{
  "reservationList": [
    {
      "reservationId": 1,
      "username": "방문객",
      "userPhoneNumber": "010-1111-2222",
      "storeName": "store00",
      "reservationStatus": "STANDBY",
      "arrivalStatus": "READY",
      "reservationDate": "2023-11-19",
      "reservationTime": "16:00:00"
    }
  ]
}
~~~
</details>

#### 3-4) 예약 승인 변경
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [PUT] http://localhost:8080/api/reservation/partner/approval/{id}

파라미터
~~~
{
  "reservationStatus": "APPROVAL"
}
~~~

결과
- 성공
~~~
{
  "reservationId": 1,
  "username": "방문객",
  "storeName": "store00",
  "reservationStatus": "APPROVAL",
  "reservationDate": "2023-11-19",
  "reservationTime": "16:00:00"
}
~~~
- 실패
~~~
{
  "errorCode": "RESERVATION_NOT_FOUND",
  "errorMessage": "예약을 찾을 수 없습니다."
}
~~~
</details>

#### 3-5) 도착 조회
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [PUT] http://localhost:8080/api/reservation/kiosk/{id}

파라미터
~~~
{
  "username": "방문객",
  "phoneNumber": "010-1111-2222",
  "arrivalTime": "2023-11-19T14:50:00.000Z"
}
~~~

결과
- 성공
~~~
{
  "reservationId": 1,
  "username": "방문객",
  "storeName": "store00",
  "reservationStatus": "USE_COMPLETED",
  "arrivalStatus": "ARRIVED"
}
~~~
- 실패
~~~
{
  "errorCode": "RESERVATION_STATUS_CHECK_ERROR",
  "errorMessage": "예약 상태 코드에 문제가 있습니다. 가게에 문의하세요."
}
~~~

</details>

### ✅ 4. 리뷰 API
#### 4-1) 리뷰 작성
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [POST] http://localhost:8080/api/review/create?userid={id}&storeid={id}&reservationid={id}


파라미터
~~~
{
  "content": "다음에 또 구매할게요!!",
  "rating": 3.5
}
~~~

결과
~~~
{
  "reviewId": 1,
  "content": "다음에 또 구매할게요!!",
  "rating": 3.5,
  "username": "방문객",
  "storeName": "store00"
}
~~~

</details>

#### 4-2) 리뷰 수정
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [PUT] http://localhost:8080/api/review/update/{id}

파라미터
~~~
{
  "content": "다음에 또 구매할게요!!",
  "rating": 2.5
}
~~~

결과
~~~
{
  "reviewId": 1,
  "content": "다음에 또 구매할게요!!",
  "rating": 2.5
}
~~~

</details>

#### 4-3) 리뷰 삭제
<details>
<summary> 경로 / 파라미터 / 결과 </summary>

경로 : [DELETE] http://localhost:8080/api/review/delete/{id}

결과
~~~
  리뷰 삭제가 완료되었습니다.
~~~
</details>