
public class MyThread implements Runnable{
	int start;
	int end ; 

	public void run() {
		boolean flag = false ;
	for(;start<=end;start++) {
		for(int i =2 ;i < start;i++ ) {
			if(start%i == 0) {
				flag = true ; 
				break;
			}
		}
		if(flag) {
			System.out.println(start+"是合数");
			flag = false;
		}else {
			System.out.println(start+"是素数");
		}
	}	
		
	}
	public static void main(String[] args) {
		MyThread m1 = new MyThread();
		m1.start = 2 ;
		m1.end = 10 ;
		MyThread m2 = new MyThread();
		m2.start = 11 ;
		m2.end = 17 ; 
		Thread t1 = new Thread(m1);
		Thread t2 = new Thread(m2);
		t1.start();t2.start();
		
	}
	
}
