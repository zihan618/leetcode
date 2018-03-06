package leetcode;

public class RemoveNthNodeFromEndofList {
	
	   public class ListNode { 
	   int val; 
	  ListNode next;
	    ListNode(int x) { val = x; } 
	   }
	
	public static void main(String[] args) {

	}

	/**
	 * 保持两个节点之间的距离差即可 当需要删除的节点是头结点时需要单独处理， 当然也可以在head之前再加一个头结点 这样不用考虑这种特殊情况了
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head.next == null) {
			return null;
		}
		ListNode tmp1 = head, tmp2 = head, tmp3 = head;
		for (int i = 0; i < n - 1; i++) {
			tmp1 = tmp1.next;
		}
		while (tmp1.next != null) {
			tmp3 = tmp2;
			tmp1 = tmp1.next;
			tmp2 = tmp2.next;
		}
		if (tmp2 == head) {
			head = head.next;
		} else {

			tmp3.next = tmp2.next;
		}
		return head;
	}
}
