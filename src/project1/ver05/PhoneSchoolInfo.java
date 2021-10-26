package project1.ver05;

public class PhoneSchoolInfo extends PhoneInfo {
	
	String subject; // 전공
	int grade; // 학년
	
	public PhoneSchoolInfo(String name, String phoneNumber, String subject, int grade) {
		super(name, phoneNumber);
		this.subject = subject;
		this.grade = grade;
	}
	
	@Override
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("전공 : " + subject);
		System.out.println("학년 : " + grade);
	}
}
