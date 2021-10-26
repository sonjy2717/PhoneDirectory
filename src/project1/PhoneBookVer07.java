package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver07.MenuItem;
import project1.ver07.MenuSelectException;
import project1.ver07.PhoneBookManager;

public class PhoneBookVer07 implements MenuItem {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		PhoneBookManager pbMgr = new PhoneBookManager();
		
		while (true) {
			
			int choice = 0;
			
			try {
				System.out.println("1.데이터 입력");
				System.out.println("2.데이터 검색");
				System.out.println("3.데이터 삭제");
				System.out.println("4.주소록 출력");
				System.out.println("5.프로그램 종료");
				System.out.print("선택 : ");
	 			choice = scanner.nextInt();
				inputMenu(choice);
			}
			catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("제발... 좀...");
			}
			catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			catch (Exception e) {
				System.out.println("알 수 없는 예외 발생");
				e.printStackTrace();
			}
			
			if (choice == END_PROGRAM) break;
			
			switch (choice) {
			case DATA_INPUT:
				try {
					pbMgr.dataInput();
					break;
				}
				catch (MenuSelectException e) {
					System.out.println(e.getMessage());
					break;
				}
			case DATA_SEARCH:
				pbMgr.dataSearch();
				break;
			case DATA_DELETE:
				pbMgr.dataDelete();
				break;
			case PRINT_MENU:
				pbMgr.printMenu();
				break;
			}
			
			System.out.println();
		}
		
		System.out.println("프로그램을 종료합니다.");
		scanner.close();
	}
	
	public static int inputMenu(int choice) throws MenuSelectException {
		
		if (choice < 1 || choice > 5) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
		}
		
		return choice;
	}
}
