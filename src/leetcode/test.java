package leetcode;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> arrayList = new ArrayList<>();
		for(int i=0; i< 10000; i++) {
			arrayList.add(1);
		}
		long ta1 = System.currentTimeMillis();
		for(int j=0; j<1000; j++) {
			for(int i=0; i<arrayList.size(); i++) {
				int a = 0;
				a ++;
			}
		}
			
		long ta2 = System.currentTimeMillis();
		
		long ta3 = System.currentTimeMillis();
		for(int j=0; j<1000; j++) {
			for(int i=0, size = arrayList.size(); i<size; i++) {
				int a = 0;
				a ++;
			}
		}
			
		long ta4 = System.currentTimeMillis();
		
		System.out.println(ta4-ta3-ta2+ ta1);
	}

}
