package project1.ver03;

import java.util.Scanner;

public class PhoneBookManager {
	
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
		
		System.out.println("데이터 입력을 시작합니다.");
		System.out.print("이름 : ");
		String name = scanner.nextLine();
		System.out.print("전화번호 : ");
		String phone = scanner.nextLine();
		System.out.print("생년월일 : ");
		String birth = scanner.nextLine();
		
		piArr[index++] = new PhoneInfo(name, phone, birth);
		
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
