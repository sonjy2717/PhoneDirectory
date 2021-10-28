package project1;

import java.util.Scanner;

import project1.ver09.PhoneBookManager;

public class PhoneBookVer09 {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		PhoneBookManager pbMgr = new PhoneBookManager();
		
		while(true) {
			
			System.out.println("1. 데이터 입력");
			System.out.println("2. 데이터 검색");
			System.out.println("3. 데이터 삭제");
			System.out.println("4. 주소록 출력");
			System.out.println("5. 프로그램 종료");
			System.out.print("선택 : ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			if (choice == 5) break;
			
			switch (choice) {
			case 1:
				pbMgr.dataInput();
				break;
			case 2:
				pbMgr.dataSearch();
				break;
			case 3:
				pbMgr.dataDelete();
				break;
			case 4:
				pbMgr.printMenu();
				break;
			default:
				System.out.println("잘못 된 값 입력");
				continue;
			}
			
			System.out.println();
		}
		System.out.println("프로그램을 종료합니다.");
		scanner.close();
		
	}
	
}
