package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver08.MenuItem;
import project1.ver08.MenuSelectException;
import project1.ver08.PhoneBookManager;

public class PhoneBookVer08 {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		PhoneBookManager pbMgr = new PhoneBookManager();
		
		while (true) {
			
			int choice = 0;
			
			try {
				pbMgr.printMenu();
	 			choice = scanner.nextInt();
				inputMenu(choice);
			}
			catch (InputMismatchException e) {
				System.out.println("제발... 좀...");
			}
			catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			catch (Exception e) {
				System.out.println("알 수 없는 예외 발생");
				e.printStackTrace();
			}
			
			if (choice == MenuItem.END_PROGRAM) break;
			
			switch (choice) {
			case MenuItem.DATA_INPUT:
				try {
					pbMgr.dataInput();
					break;
				}
				catch (MenuSelectException e) {
					scanner.nextLine();
					System.out.println(e.getMessage());
					break;
				}
			case MenuItem.DATA_SEARCH:
				pbMgr.dataSearch();
				break;
			case MenuItem.DATA_DELETE:
				pbMgr.dataDelete();
				break;
			case MenuItem.DATA_ALL_SHOW:
				pbMgr.dataAllShow();
				break;
			}
			
			System.out.println();
		}
		
		System.out.println("프로그램을 종료합니다.");
		scanner.close();
	}
	
	// 사용자 지정 예외
	public static int inputMenu(int choice) throws MenuSelectException {
		
		if (choice < 1 || choice > 5) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
		}
		
		return choice;
	}
}
