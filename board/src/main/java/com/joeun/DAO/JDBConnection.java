package com.joeun.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBConnection {
	
	public Connection con;				// 연결된 드라이버에 SQL을 요청할 객체를 생성하는 클래스
	public Statement stmt;				// SQL 실행 요청을 하는 클래스
	public PreparedStatement psmt;		// Statement 에서 ? 파라미터 확장기능을 추가로 제공하는 클래스
	public ResultSet rs;				// SQL 실행 결과를 받아오는 클래스
	
	// 기본 생성자
	public JDBConnection() {
		// JDBC 드라이버 로드
		// Oracle
		try {
			// ojdbc8.jar 드라이버의 클래스를 로드한다.
			Class.forName("oracle.jdbc.OracleDriver");		 
			
			// DB에 연결
			// jdbc:oracle:thin  		- JDBC 드라이버 타입 (thin 타입)
			// localhost				- 호스트 주소 (IP 주소),  localhost 또는 127.0.0.1 은 현재 PC의 IP 를 가리킨다.
			// :1521					- 포트번호  (1521은 오라클 DB서버의 기본 포트 번호이다.)
			// :xe  또는 :orcl			- SID 	  (서비스 ID)
			//String url ="jdbc:oracle:thin:@localhost:1521:xe"; 	// 11g
			// String url ="jdbc:oracle:thin:@localhost:1521:orcl"; // 12c이상
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "joeun";
			String pw = "123456";
			
			// 자바 프로그램에서 JDBC 드라이버를 연결시켜주는 클래스
			// getConnection() 메소드로 DB에 연결 요청하고 생성된 Connection 객체를 반환받는다.
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			System.err.println("DB 연결 실패");
			e.printStackTrace();
		}
		
	}
	
	// public static void main(String[] args) {
	// 	JDBConnection jdbc = new JDBConnection();
	// }

}






