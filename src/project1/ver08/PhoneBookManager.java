package project1.ver08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneBookManager {
	
	HashSet<PhoneInfo> list;
	
	// 생성자
	public PhoneBookManager() {
		list = new HashSet<PhoneInfo>();
		roadPhoneBook();
	}
	
	// 메뉴 출력
	public void printMenu() {
		System.out.println("1.데이터 입력");
		System.out.println("2.데이터 검색");
		System.out.println("3.데이터 삭제");
		System.out.println("4.주소록 출력");
		System.out.println("5.저장 옵션");
		System.out.println("6.프로그램 종료");
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
		if (choice < SubMenuItem.BASIC || choice > SubMenuItem.COMPANY) {
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
		
		// HashSet에 객체 추가 성공 여부
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
	
	// 사용자 지정 예외
	public int inputMenu(int choice) throws MenuSelectException {
		
		if (choice < MenuItem.DATA_INPUT || choice > MenuItem.END_PROGRAM) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
		}
		
		return choice;
	}
	
	// 저장
	public void savePhoneBook() {
		try {
			ObjectOutputStream out = 
					new ObjectOutputStream(
							new FileOutputStream(
									"src/project1/ver08/PhoneBook.obj"));
			
			for (PhoneInfo pi : list) {
				out.writeObject(pi);
			}
			out.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("파일 없음!");
		}
		catch (IOException e) {}
		catch (Exception e) {
			System.out.println("아 몰랑 무슨 예외인지");
			e.printStackTrace();
		}
		System.out.println("저장 완료!");
	}
	
	// 로드
	public void roadPhoneBook() {
		try {
			ObjectInputStream in = 
					new ObjectInputStream(
							new FileInputStream(
									"src/project1/ver08/PhoneBook.obj"));
			
			while (true) {
				PhoneInfo pi = (PhoneInfo)in.readObject();
				list.add(pi);
				if (pi == null) break;
			}
			in.close();
		}
		catch (ClassNotFoundException e) {
			System.out.println("클래스 없음!");
		}
		catch (FileNotFoundException e) {
			System.out.println("파일 없음!");
		}
		catch (IOException e) {}
		catch (Exception e) {
			System.out.println("더 이상 읽을 정보가 없습니다.");
		}
		System.out.println("로드 완료!");
	}
	
	// 저장 옵션
	public void saveOption(AutoSaver as) {
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		try {
			System.out.println("\n저장 옵션 선택");
			System.out.println("1.자동 저장 ON");
			System.out.println("2.자동 저장 OFF");
			System.out.print("선택 : ");
			choice = scanner.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println("제발... 좀...");
			return;
		}
		
		if (choice == 1) {
			if (as.isAlive()) {
				System.out.println("이미 자동 저장이 실행중입니다.");
			}
			else {
				as.setDaemon(true);
				as.start();
				System.out.println("자동 저장 ON");
			}
		}
		else if (choice == 2) {
			as.interrupt();
			System.out.println("자동 저장 OFF");
		}
		else {
			System.out.println("입력좀 제발 잘 해줘..");
			return;
		}
	}
	
	public void saveTextInfo() {
		System.out.println("\n자동 저장중...");
		
		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("src/project1/ver08/PhoneBook.txt"));
			
			
			for (PhoneInfo pi : list) {
				out.println(pi.toString());
			}
			out.close();
		}
		catch (IOException e) {
			System.out.println("입출력 예외 발생..");
		}
		catch (Exception e) {
			System.out.println("아 몰랑 무슨 예외야 이건");
		}
	}
}
