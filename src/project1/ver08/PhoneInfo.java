package project1.ver08;

import java.io.Serializable;
import java.util.Objects;

public class PhoneInfo implements Serializable {
	
	String name; // 이름
	String phoneNumber; // 전화번호
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PhoneInfo)) {
			return false;
		}
		PhoneInfo other = (PhoneInfo) obj;
		return Objects.equals(name, other.name);
	}
	
	public PhoneInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public void showPhoneInfo() {
		System.out.println("이름 : " + name);
		System.out.println("전화번호 : " + phoneNumber);
	}
	
	@Override
	public String toString() {
		return "이름 : " + name + "\n" + "전화번호 : " + phoneNumber + "\n";
	}
}
