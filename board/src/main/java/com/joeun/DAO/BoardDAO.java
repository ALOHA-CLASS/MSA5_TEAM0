package com.joeun.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joeun.DTO.Board;





/**
 *  데이터 접근 객체
 *  - 게시글 데이터 접근
 */
public class BoardDAO extends JDBConnection {
	
	// 데이터 목록
	public List<Board> list() {
		
		// 게시글 목록을 담을 컬렉션 객체 생성
		List<Board> boardList = new ArrayList<Board>();
		
		// SQL 작성
		String sql = " SELECT * "
				   + " FROM board ";
		try {
			// 쿼리(SQL) 실행 객체 생성 - Statement (stmt)
			stmt = con.createStatement();
			
			// 쿼리(SQL) 실행 -> 결과  - ResultSet (rs)
			rs = stmt.executeQuery(sql);
			
			// 조회 결과를 리스트(boardList)에 추가
			while( rs.next() ) {		// next() : 실행 결과의 다음 데이터로 이동
				Board board = new Board();
				
				// 결과 데이터 가져오기
				// rs.getXXX("컬럼명") --> 해당 컬럼의 데이터를 가져온다
				// * "컬럼명"의 값을 특정 타입으로 변환
				board.setNo( rs.getInt("no") );
				board.setTitle( rs.getString("title") );
				board.setWriter( rs.getString("writer") );
				board.setContent( rs.getString("content") );
				board.setRegDate( rs.getTimestamp("reg_date") );
				board.setUpdDate( rs.getTimestamp("upd_date") );
				
				// 게시글 목록에 추가
				boardList.add(board);
			}
		} catch(SQLException e) {
			System.err.println("게시글 목록 조회 시, 예외 발생");
			e.printStackTrace();
		}
		// 게시글 목록 반환
		return boardList;
	}
	
	// 데이터 조회
	public Board select(int no) {
		
		// 게시글 정보 객체 생성
		Board board = new Board();
		
		// SQL 작성
		String sql = " SELECT * "
				   + " FROM board "
				   + " WHERE no = ? ";
		try {
			// 쿼리(SQL) 실행 객체 생성 - PreparedStatement (psmt)
			psmt = con.prepareStatement(sql);
			
			// psmt.setXXX( 순서번호, 매핑할 값 );
			psmt.setInt( 1, no );		// ?(1) <-- no (글번호)
			
			// 쿼리(SQL) 실행 -> 결과  - ResultSet (rs)
			rs = psmt.executeQuery();
			
			// 조회 결과를 1건 가져오기
			if( rs.next() ) {		// next() : 실행 결과의 다음 데이터로 이동
				// 결과 데이터 가져오기
				// rs.getXXX("컬럼명") --> 해당 컬럼의 데이터를 가져온다
				// * "컬럼명"의 값을 특정 타입으로 변환
				board.setNo( rs.getInt("no") );
				board.setTitle( rs.getString("title") );
				board.setWriter( rs.getString("writer") );
				board.setContent( rs.getString("content") );
				board.setRegDate( rs.getTimestamp("reg_date") );
				board.setUpdDate( rs.getTimestamp("upd_date") );
				
			}
		} catch(SQLException e) {
			System.err.println("게시글 조회 시, 예외 발생");
			e.printStackTrace();
		}
		// 게시글 정보 1건 반환
		return board;
	}
	
	
	// 데이터 등록
	public int insert(Board board) {
		int result = 0;		// 결과 : 적용된 데이터 건수
		
		String sql = " INSERT INTO board (no, title, writer, content) "
				   + " VALUES(SEQ_BOARD.NEXTVAL, ?, ?, ? ) ";
		
		try {
			psmt = con.prepareStatement(sql);			// 쿼리 실행 객체 생성
			psmt.setString( 1, board.getTitle() );		// 1번 ? 에 제목 매핑
			psmt.setString( 2, board.getWriter() );		// 2번 ? 에 작성자 매핑
			psmt.setString( 3, board.getContent() );	// 3번 ? 에 내용을 매핑
			
			result = psmt.executeUpdate();		// SQL 실행 요청, 적용된 데이터 개수를 받아온다.
												// 게시글 1개 적용 성공 시, result : 1
												// 				실패 시, result : 0
			// executeUpdate()
			// : SQL (INSERT, UPDATE, DELETE)을 실행하고 적용된 데이터 개수를 int 타입으로 반환
		} catch (SQLException e) {
			System.err.println("게시글 등록 시, 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 데이터 수정
	public int update(Board board) {
		int result = 0;		// 결과 : 적용된 데이터 건수
		
		String sql = " UPDATE board "
				   + " SET title = ? "
				   + "    ,writer  = ? "
				   + "    ,content = ? "
				   + "	  ,upd_date = sysdate "
				   + " WHERE no = ? ";
		
		try {
			psmt = con.prepareStatement(sql);			// 쿼리 실행 객체 생성
			psmt.setString( 1, board.getTitle() );		// 1번 ? 에 제목 매핑
			psmt.setString( 2, board.getWriter() );		// 2번 ? 에 작성자 매핑
			psmt.setString( 3, board.getContent() );	// 3번 ? 에 내용을 매핑
			psmt.setInt( 4, board.getNo() );			// 4번 ? 에 게시글 번호를 매핑
			
			result = psmt.executeUpdate();		// SQL 실행 요청, 적용된 데이터 개수를 받아온다.
												// 게시글 1개 적용 성공 시, result : 1
												// 				실패 시, result : 0
			// executeUpdate()
			// : SQL (INSERT, UPDATE, DELETE)을 실행하고 적용된 데이터 개수를 int 타입으로 반환
		} catch (SQLException e) {
			System.err.println("게시글 수정 시, 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 데이터 삭제
	public int delete(int no) {
		int result = 0;		// 결과 : 적용된 데이터 건수
		
		String sql = " DELETE FROM board "
				   + " WHERE no = ? ";
		
		try {
			psmt = con.prepareStatement(sql);	// 쿼리 실행 객체 생성
			psmt.setInt( 1, no );				// 1번 ? 에 게시글 번호를 매핑
			
			result = psmt.executeUpdate();		// SQL 실행 요청, 적용된 데이터 개수를 받아온다.
												// 게시글 1개 적용 성공 시, result : 1
												// 				실패 시, result : 0
			// executeUpdate()
			// : SQL (INSERT, UPDATE, DELETE)을 실행하고 적용된 데이터 개수를 int 타입으로 반환
		} catch (SQLException e) {
			System.err.println("게시글 삭제 시, 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	
}


























