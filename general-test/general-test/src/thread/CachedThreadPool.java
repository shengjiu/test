package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
	
	public static void main(String [] args){
		ExecutorService service1 = Executors.newCachedThreadPool();
		System.out.println(service1);
		ExecutorService service2 = Executors.newCachedThreadPool();
		System.out.println(service2);
	}

}
