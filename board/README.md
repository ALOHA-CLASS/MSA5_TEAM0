# 게시판 프로그램
0. DB 구축
    - ORACLE 설치
    - 계정 생성
    - 테이블 생성 (board)
    ```
        CREATE TABLE BOARD(
            NO NUMBER NOT NULL,
            TITLE VARCHAR2(100) NOT NULL,
            WRITER VARCHAR2(50) NOT NULL,
            CONTENT VARCHAR2(1000),
            UPD_DATE DATE DEFAULT SYSDATE,
            REG_DATE DATE DEFAULT SYSDATE,
            PRIMARY KEY(NO)
        );
    ```
1. JavaFx 프로젝트 생성 
2. pom.xml - ojdbc 의존성(라이브러리) 추가
```
<   !-- ojdbc driver -->
    <dependency>
        <groupId>com.oracle</groupId>
        <artifactId>ojdbc8</artifactId>
        <version>21.5</version>
        <scope>system</scope>
        <systemPath>E:\JOEUN\SETUP\WINDOWS.X64_193000_db_home\jdbc\lib\ojdbc8.jar</systemPath>
    </dependency>
```
3. module-info.java 에 java.sql 모듈 추가
```
    requires java.sql;
```
4. JDBC 연동 - JDBConnection.java
5. 게시판 프로그램의 Model 재사용 
   - (DAO,DTO, Service)
6. View (fxml) 
  - 게시글 목록 : TableView
7. Controller