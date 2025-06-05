# 📈 JDBC 기반 주식 시스템 프로젝트

> Java + MySQL 기반의 콘솔형 CRUD 주식 관리 시스템  
> 4인 협업 프로젝트 | **2025.05.26 ~ 2025.05.30**

---

## 🔧 기술 스택

- Java 17
- JDBC (MySQL)
- MySQL 8
- IntelliJ IDEA
- Gradle
- Lombok

---

## 🗂️ 프로젝트 구조

```
📁 src
└── main
    ├── java
    │   └── org.stock
    │       ├── common          # JDBCUtil (DB 연결 공통 유틸)
    │       │   └── JDBCUtil.java
    │       ├── dao             # DAO 인터페이스 및 구현체
    │       │   ├── StockDao.java
    │       │   └── StockDaoImpl.java
    │       ├── domain          # VO 클래스
    │       │   └── StockVO.java
    │       └── StockMain.java  # 콘솔 프로그램 진입점
    └── resources
        └── application.properties  # DB 연결 설정 위치

📄 stock_db.sql  # 테이블 생성 SQL

```

---

## ✅ 기능 소개 (CRUD)

| 기능 | 설명 | 담당자 |
|------|------|--------|
| **C - Create** | 주식 정보 등록 | 정혜빈 |
| **R - Read**   | 주식 정보 조회 | 송용욱 |
| **U - Update** | 주식 정보 수정 | 이승예 |
| **D - Delete** | 주식 정보 삭제 | 이규리 |

---

## 🧑‍💻 작업 일정

### 2025.05.26 (월)
- JDBC 전체 흐름 복습
- DB 구축 (MySQL)
- `build.gradle` 의존성 설정
- Lombok 설치 및 테스트
- `JDBCUtil.java` 작성, DB 연동 확인
- `application.properties`에 DB 정보 작성

### 2025.05.27 (화)
- `StockDao` 인터페이스 및 메서드 정의
- `StockVO.java` 생성 및 getter/setter 작성
- Dao <-> VO 연동 테스트

### 2025.05.28 (수)
- `StockDaoImpl` 구현
- `StockMain` 작성 (콘솔 입출력 기능 분기)

### 2025.05.29 (목)
- 전체 코드 리팩토링
- 최종 테스트
- Pull Request

### 2025.05.30 (금)
- 최종 Merge 완료

---

## 🗃️ DB 스키마 (MySQL)

```sql
CREATE TABLE stocks (
  id INT PRIMARY KEY AUTO_INCREMENT,
  stock_name VARCHAR(50) NOT NULL,
  ticker VARCHAR(10) NOT NULL,
  price INT NOT NULL,
  holding_qty INT NOT NULL
);
```
---

## 🛠 협업 및 Git 관리 방식

본 프로젝트는 GitHub를 기반으로 다음과 같은 협업 프로세스를 적용하여 진행했습니다:

- GitHub Issue를 통한 작업 분담 및 기능 정의
- 기능별 브랜치 전략 사용: 각 팀원이 `feature/{stock_이름}` 브랜치에서 개별 작업
- Pull Request(PR)를 통해 코드 리뷰 및 변경사항 반영
- PR 리뷰 승인 후 `develop` 브랜치에 merge 후, 최종 코드 `main` 브랜치에 merge
- 충돌 해결, 코드 리팩토링, 주석 보완 등을 PR 리뷰 과정에서 적극 논의

