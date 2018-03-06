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
	 * ���������ڵ�֮��ľ����� ����Ҫɾ���Ľڵ���ͷ���ʱ��Ҫ�������� ��ȻҲ������head֮ǰ�ټ�һ��ͷ��� �������ÿ����������������
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
