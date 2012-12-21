package nulllist;

import java.util.List;

public class NullListInFor {
	
	public static void main(String [] args){
		List list = null;
		for(Object obj : list){
			System.out.println(obj);
		}
	}

}
