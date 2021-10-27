package project1.ver08;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneBookManager {
	
	HashSet<PhoneInfo> list;
	
	// 생성자
	public PhoneBookManager() {
		list = new HashSet<PhoneInfo>();
	}
	
	// 메뉴 출력
	public void printMenu() {
		System.out.println("1.데이터 입력");
		System.out.println("2.데이터 검색");
		System.out.println("3.데이터 삭제");
		System.out.println("4.주소록 출력");
		System.out.println("5.프로그램 종료");
		System.out.print("선택 : ");
	}
	
	// 주소록 출력
	public void dataAllShow() {
		
		for (PhoneInfo pi : list) {
			pi.showPhoneInfo();
			System.out.println();
		}
		
		// 저장된 객체가 하나도 없을 때
		if (list.size() == 0) {
			System.out.println("저장된 주소록이 없습니다.");
		}
	}
	
	// 입력
	public void dataInput() throws MenuSelectException {
		
		Scanner scanner = new Scanner(System.in);
		
		String name = "";
		String phone = "";
		int choice = 0;
		
		System.out.println("데이터 입력을 시작합니다.");
		
		try {
			System.out.println("1.일반, 2.동창, 3.회사");
			System.out.print("선택>> ");
			choice = scanner.nextInt();
			scanner.nextLine();
		}
		catch (InputMismatchException e) {
			System.out.println("제발... 좀...");
			return;
		}
		
		// 정해진 숫자가 아닌 다른 숫자를 넣었을 때
		if (choice < 1 || choice > 3) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
		}
		
		System.out.print("이름 : ");
		name = scanner.nextLine();
		System.out.print("전화번호 : ");
		phone = scanner.nextLine();
		
		PhoneInfo pi = null;
		
		switch (choice) {
		case SubMenuItem.BASIC:
			pi = new PhoneInfo(name, phone);
			break;
		case SubMenuItem.SCHOOL:
			try {
				System.out.print("전공 : ");
				String subject = scanner.nextLine();
				System.out.print("학년 : ");
				int grade = scanner.nextInt();
				
				pi = new PhoneSchoolInfo(name, phone, subject, grade);
				break;
			}
			catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("잘못 입력!");
				return;
			}
		case SubMenuItem.COMPANY:
			System.out.print("회사 : ");
			String companyName = scanner.nextLine();
			
			pi = new PhoneCompanyInfo(name, phone, companyName);
			break;
		}
		
		if (list.add(pi)) {
			System.out.println("데이터 입력 완료");
		}
		else {
			System.out.println("이미 저장된 데이터 입니다.");
			System.out.print("덮어쓸까요? Y(y) / N(n) >> ");
			char check = scanner.next().charAt(0);
			if (check == 'y' || check == 'Y') {
				list.remove(pi);
				list.add(pi);
				System.out.println("덮어쓰기 완료");
			}
			else if (check == 'n' || check == 'N'){
				System.out.println("기존 데이터를 유지합니다.");
			}
			else {
				System.out.println("잘못 입력!");
				return;
			}
		}
	}
	
	// 검색
	public void dataSearch() {
		
		System.out.println("데이터 검색을 시작합니다.");
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름 : ");
		String search = scanner.nextLine();
		boolean flag = true;
		
		for (PhoneInfo pi : list) {
			if (pi.name.equals(search)) {
				pi.showPhoneInfo();
				flag = false;
			}
		}
		
		if (flag) {
			System.out.println("검색 결과가 없습니다.");
		}
	}
	
	// 삭제
	public void dataDelete() {
		
		System.out.println("데이터 삭제를 시작합니다.");
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름 : ");
		String search = scanner.nextLine();
		boolean delete = true;
		
		for (PhoneInfo pi : list) {
			if (pi.name.equals(search)) {
				list.remove(pi);
				delete = false;
				break;
			}
		}
		
		if (delete) {
			System.out.println("삭제할 데이터가 없습니다.");
		}
		else {
			System.out.println("삭제 완료");
		}
	}
}
