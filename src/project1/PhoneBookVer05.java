package project1;

import java.util.Scanner;

import project1.ver05.MenuItem;
import project1.ver05.PhoneBookManager;

public class PhoneBookVer05 implements MenuItem {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		PhoneBookManager pbMgr = new PhoneBookManager();
		
		while(true) {
			
			System.out.println("1.데이터 입력");
			System.out.println("2.데이터 검색");
			System.out.println("3.데이터 삭제");
			System.out.println("4.주소록 출력");
			System.out.println("5.프로그램 종료");
			System.out.print("선택 : ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			if (choice == END_PROGRAM) break;
			
			switch (choice) {
			case DATA_INPUT:
				pbMgr.dataInput();
				break;
			case DATA_SEARCH:
				pbMgr.dataSearch();
				break;
			case DATA_DELETE:
				pbMgr.dataDelete();
				break;
			case PRINT_MENU:
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
