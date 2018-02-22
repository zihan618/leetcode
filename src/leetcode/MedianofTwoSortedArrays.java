package leetcode;

public class MedianofTwoSortedArrays {

	public static void main(String[] args) {
		System.out.printf("%d", Integer.MAX_VALUE);
	}
	 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int nums1_start = 0, nums1_end = nums1.length;
	        int nums2_start = 0, nums2_end = nums2.length;
	        int median1 = (nums1_start + nums1_end) / 2;
	        int median2 = (nums2_start + nums2_end) / 2;
	        int abandoned = 0;
	        while (abandoned < (nums1.length + nums2.length)/2 -1) {
	        	median1 = (nums1_start + nums1_end) / 2;
		        median2 = (nums2_start + nums2_end) / 2;
		       
	        	if(true) {
	        		
	        	}
				
			}
	        return 0;
	        
	        
	    }

}
