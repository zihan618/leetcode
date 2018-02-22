package leetcode;

public class MedianofTwoSortedArrays {

	public static void main(String[] args) {
		System.out.printf("%d", Integer.MAX_VALUE);
	}
	/**
	 * 在数组1里面二分法查找，  数组2 里面根据数组1 的下表确定下标
	 * https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn))-solution-with-explanation
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 /*	if( nums1.length > nums2.length) {
		 		return findMedianSortedArrays(nums2, nums1);
		 	}
		 	final int half = ( nums1.length + nums2.length + 1) /2;
		 	int nums1_start = 0;
		 	int nums1_last = nums1.length - 1;
		 	int median1 = 0, median2=0;
		 	int left_median , right_median;
		 	while( nums1_start <= nums1_last) {
		 		median1 = (nums1_start + nums1_last) / 2;
		 		median2 = half - median1;
		 		//median1 太大了，需要小一点
		 		if( median1 > 0 && nums1[median1 - 1] > nums2[median2] ) {
		 			nums1_last = median1 - 1;
		 		}//median1 太小了
		 		else if( median1 < nums1.length && nums2[median2 - 1] > nums1[median1]) {
		 			nums1_last = median1 + 1;
		 		}else {
		 			
					break;
				}
		 	}
		 	
		 	if( median1 == 0) {
		 		left_median = nums2[median2 - 1];
		 	}else if( median2 == 0) {
				left_median = nums1[median1 - 1];
			}else {
				left_median = Math.max(nums2[median2 - 1], nums1[median1 - 1])
			}
		 	
		 	if((nums1.length + nums2.length)%2 != 0 ) {
		 		return left_median;
		 	}
		 	
		 	if( nums1_start = )
		 
					
	        */
		 int m = nums1.length;
		    int n = nums2.length;
		    if(m>n) return findMedianSortedArrays(nums2,nums1);
		    //for all the following, we assumed m<=n;
		    int imin = 0;
		    int imax = m;
		    int max_of_left;
		    int min_of_right;
		    int i=(imin+imax)/2, j=(m+n+1)/2 -i;
		    //
		    while(imin <= imax){
		        i=(imin+imax)/2;
		        j = (m+n+1)/2 -i;
		        if(i>0 && nums1[i-1] > nums2[j]){
		            //it means i is too large, so decrease i 
		            //m <= n(we have assumed), i < m ==> j = (m+n+1)/2 - i > (m+n+1)/2 - m >= (2*m+1)/2 - m >= 0  
		            imax = i-1;
		        }else if(i < m  && nums2[j-1] > nums1[i]){
		            //it means i is too smore.
		            //m <= n(we have assumed), i > 0 ==> j = (m+n+1)/2 - i < (m+n+1)/2 <= (2*n+1)/2 <= n
		            
		            imin = i+1;
		        }else break;
		        //i is perfect
		    }
		           //find left maximum value and find right maximum value
		           if(i == 0) max_of_left = nums2[j-1];    
		           else if(j == 0) max_of_left = nums1[i-1];
		           else  max_of_left = Math.max(nums1[i-1],nums2[j-1]);
		           if((m + n) % 2 == 1) return max_of_left;
		           
		           if(i == m) min_of_right = nums2[j];
		           else if( j == n) min_of_right = nums1[i];
		           else min_of_right = Math.min(nums1[i],nums2[j]);
		           
		           return (max_of_left+min_of_right)/2.0;
		 

	    }

}
