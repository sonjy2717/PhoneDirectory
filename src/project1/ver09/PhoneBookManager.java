package project1.ver09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PhoneBookManager {
	
	PreparedStatement ps;
	Statement st;
	Connection con;
	ResultSet rs;
	PhoneInfo[] piArr;
	int index;
	
	// 생성자
	public PhoneBookManager() {
		piArr = new PhoneInfo[100];
		index = 0;
	}
	
	// 메뉴 출력
	public void printMenu() {
		
		for (int i = 0; i < index; i++) {
			piArr[i].showPhoneInfo();
			System.out.println();
		}
	}
	
	// 입력
	public void dataInput() {
		
		Scanner scanner = new Scanner(System.in);
		String name = "";
		String phone = "";
		String birth = "";
		
		try {
			connect();
			
			String query = "INSERT INTO phonebook_tb VALUES "
					+ " (seq_phonebook.nextval, ?, ?, ?)";
			
			ps = con.prepareStatement(query);
			
			System.out.println("데이터 입력을 시작합니다.");
			System.out.print("이름 : ");
			name = scanner.nextLine();
			System.out.print("전화번호 : ");
			phone = scanner.nextLine();
			System.out.print("생년월일 : ");
			birth = scanner.nextLine();
			
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, birth);
			
			piArr[index++] = new PhoneInfo(name, phone, birth);
			int data = ps.executeUpdate();
			System.out.println(data + "행 DB 입력 완료");
		}
		catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알 수 없는 예외발생");
		}
		finally {
			close();
		}
		System.out.println("자바 데이터 입력 완료");
	}
	
	// 검색
	public void dataSearch() {
		try {
			connect();
			
			st = con.createStatement();
			
			System.out.println("데이터 검색을 시작합니다.");
			Scanner scanner = new Scanner(System.in);
			System.out.print("이름 : ");
			String search = scanner.nextLine();
			boolean flag = true;
			
			String query = "SELECT idx, name, phone_number, "
					+ " to_char(birthday, 'yyyy-mm-dd') FROM "
					+ " phonebook_tb WHERE name LIKE '%" + search + "%'";
			
			rs = st.executeQuery(query);
			System.out.println("\nDB검색 결과");
			while (rs.next()) {
				int idx = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String date = rs.getString(4);
				
				System.out.printf("%d %s %s %s\n",idx, name, phone, date);
			}
			
			for (int i = 0; i < index; i++) {
				if (search.equals(piArr[i].name)) {
					piArr[i].showPhoneInfo();
					flag = false;
				}
			}
			
			if (flag) {
				System.out.println("자바 검색 결과가 없습니다.");
			}
		}
		catch (Exception e) {
			System.out.println("알 수 없는 예외발생");
		}
		finally {
			close();
		}
	}
	
	// 삭제
	public void dataDelete() {
		
		try {
			connect();
			
			String query = "DELETE FROM phonebook_tb WHERE name = ?";
			
			ps = con.prepareStatement(query);
			
			System.out.println("데이터 삭제를 시작합니다.");
			Scanner scanner = new Scanner(System.in);
			System.out.print("이름 : ");
			String search = scanner.nextLine();
			int delIndex = -1;
			
			ps.setString(1, search);
			
			int data = ps.executeUpdate();
			System.out.println(data + "행 DB 삭제완료");
			
			for (int i = 0; i < index; i++) {
				if (search.equals(piArr[i].name)) {
					piArr[i] = null;
					delIndex = i;
					index--;
					break;
				}
			}
			
			if (delIndex == -1) {
				System.out.println("삭제할 자바 데이터가 없습니다.");
			}
			else {
				for (int i = delIndex; i < index; i++) {
					piArr[i] = piArr[i + 1];
				}
				System.out.println("자바 데이터 삭제가 완료되었습니다.");
			}
		}
		catch (Exception e) {
			System.out.println("알 수 없는 예외발생");
		}
		finally {
			close();
		}
	}
	
	// 오라클 DB연결
	public void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin://@localhost:1521:xe", "kosmo", "1234");
			
			if (con != null) {
				System.out.println("DB 연결 성공");
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알 수 없는 예외발생");
		}
	}
	
	// 스트림 닫아주기
	public void close() {
		try {
			if (ps != null) ps.close();
			if (con != null) con.close();
			if (st != null) st.close();
			if (rs != null) rs.close();
		}
		catch (SQLException e) {
			System.out.println("자원 반납시 예외 발생");
		}
	}
}
