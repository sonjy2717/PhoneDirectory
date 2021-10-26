package project1.ver06;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneBookManager implements SubMenuItem {
	
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
		
		if (index == 0) {
			System.out.println("저장된 주소록이 없습니다.");
		}
	}
	
	// 입력
	public void dataInput() throws MenuSelectException {
		
		Scanner scanner = new Scanner(System.in);
		
		String name;
		String phone;
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
		
		if (choice < 1 || choice > 3) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
		}
		
		switch (choice) {
		case BASIC:
			System.out.print("이름 : ");
			name = scanner.nextLine();
			System.out.print("전화번호 : ");
			phone = scanner.nextLine();
			
			piArr[index++] = new PhoneInfo(name, phone);
			break;
		case SCHOOL:
			try {
				System.out.print("이름 : ");
				name = scanner.nextLine();
				System.out.print("전화번호 : ");
				phone = scanner.nextLine();
				System.out.print("전공 : ");
				String subject = scanner.nextLine();
				System.out.print("학년 : ");
				int grade = scanner.nextInt();
				
				piArr[index++] = new PhoneSchoolInfo(name, phone, subject, grade);
				break;
			}
			catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("잘못 입력!");
				return;
			}
		case COMPANY:
			System.out.print("이름 : ");
			name = scanner.nextLine();
			System.out.print("전화번호 : ");
			phone = scanner.nextLine();
			System.out.print("회사 : ");
			String companyName = scanner.nextLine();
			
			piArr[index++] = new PhoneCompanyInfo(name, phone, companyName);
			break;
		}
		
		System.out.println("데이터 입력 완료");
	}
	
	// 검색
	public void dataSearch() {
		
		System.out.println("데이터 검색을 시작합니다.");
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름 : ");
		String search = scanner.nextLine();
		boolean flag = true;
		
		for (int i = 0; i < index; i++) {
			if (search.equals(piArr[i].name)) {
				piArr[i].showPhoneInfo();
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
		int delIndex = -1;
		
		for (int i = 0; i < index; i++) {
			if (search.equals(piArr[i].name)) {
				piArr[i] = null;
				delIndex = i;
				index--;
				break;
			}
		}
		
		if (delIndex == -1) {
			System.out.println("삭제할 데이터가 없습니다.");
		}
		else {
			for (int i = delIndex; i < index; i++) {
				piArr[i] = piArr[i + 1];
			}
			System.out.println("데이터 삭제가 완료되었습니다.");
		}
	}
}
