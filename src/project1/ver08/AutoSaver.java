package project1.ver08;

public class AutoSaver extends Thread {
	
	PhoneBookManager pbMgr;
	
	public AutoSaver(PhoneBookManager pbMgr) {
		this.pbMgr = pbMgr;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				pbMgr.saveTextInfo();
				sleep(5000);
			}
		}
		catch (InterruptedException e) {
			System.out.println("\n쓰레드 die");
		}
	}
	
}
