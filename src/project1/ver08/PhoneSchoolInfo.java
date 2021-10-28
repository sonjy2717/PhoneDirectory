package project1.ver08;

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
	
	@Override
	public String toString() {
		return "이름 : " + name + "\n전화번호 : " + phoneNumber 
				+ "\n전공 : " + subject + "\n학년 : " + grade + "\n";
	}
}
