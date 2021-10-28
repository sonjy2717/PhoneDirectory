package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver08.AutoSaver;
import project1.ver08.MenuItem;
import project1.ver08.MenuSelectException;
import project1.ver08.PhoneBookManager;

public class PhoneBookVer08 {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		PhoneBookManager pbMgr = new PhoneBookManager();
		AutoSaver as = new AutoSaver(pbMgr);
		
		while (true) {
			
			int choice = 0;
			
			try {
				pbMgr.printMenu();
	 			choice = scanner.nextInt();
				pbMgr.inputMenu(choice);
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
			
			if (choice == MenuItem.END_PROGRAM) {
				// 프로그램 종료시 입력됐던 정보 저장
				pbMgr.savePhoneBook();
				break;
			}
			
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
			case MenuItem.SAVE_OPTION:
				if (!as.isAlive()) {
					as = new AutoSaver(pbMgr);
					pbMgr.saveOption(as);
				}
				else {
					pbMgr.saveOption(as);
				}
				break;
			}
			
			System.out.println();
		}
		
		System.out.println("프로그램을 종료합니다.");
		scanner.close();
	}
	
}
