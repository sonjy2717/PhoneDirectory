package project1;

import java.util.Scanner;

import project1.ver02.PhoneInfo;

public class PhoneBookVer02 {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("1. 데이터 입력");
			System.out.println("2. 프로그램 종료");
			System.out.print("선택 : ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			if (choice == 2) break;
			
			System.out.print("이름 : ");
			String name = scanner.nextLine();
			System.out.print("전화번호 : ");
			String phone = scanner.nextLine();
			System.out.print("생년월일 : ");
			String birth = scanner.nextLine();
			
			System.out.println();
			
			PhoneInfo pi = new PhoneInfo(name, phone, birth);
			pi.showPhoneInfo();
			
			System.out.println();
		}
		System.out.println("프로그램을 종료합니다.");
		scanner.close();
		
	}
	
}
