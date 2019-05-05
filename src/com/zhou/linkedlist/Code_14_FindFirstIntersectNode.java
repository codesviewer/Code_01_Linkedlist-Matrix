package com.zhou.linkedlist;

public class Code_14_FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		return null;
	}

	public static Node getLoopNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node n1 = head.next; // n1 -> slow
		Node n2 = head.next.next; // n2 -> fast
		while (n1 != n2) {
			if (n2.next == null || n2.next.next == null) {
				return null;
			}
			n2 = n2.next.next;
			n1 = n1.next;
		}
		//等到快节点和慢节点 第一次相遇了，那么慢节点在当前位置不动，快节点重新回到开始头位置，从此，快节点每次走一步，慢节点继续每次走一步
		n2 = head; // n2 -> walk again from head
		//等到快节点和慢节点再次相遇的时候，那么这个点 就是环的起始节点
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}

	public static Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		//先来统计链表1的长度
		while (cur1.next != null) {
			n++;
			cur1 = cur1.next;
		}
		//得到了链表1的长度以后，用这个长度来遍历链表2
		while (cur2.next != null) {
			n--;
			cur2 = cur2.next;
		}
		//如果 链表1 和 链表2 都走到了最后一个节点还不相等，那么肯定不会再相交了
		if (cur1 != cur2) {
			return null;
		}
		// 用链表1的长度遍历完链表2以后，如果他还没减到0，那么肯定是链表1长了
		cur1 = n > 0 ? head1 : head2;
		cur2 = cur1 == head1 ? head2 : head1;
		n = Math.abs(n);
		//那么链表1 就要先走 那些比链表2多出来的长度，然后两个链表再一起走
		while (n != 0) {
			n--;
			cur1 = cur1.next;
		}
		//等到两个链表再一起走到第一次 相遇的位置，那么然会这个节点，就在此地相交了
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		//如果两个链表成环的位置一样 那么 链表1从头开始到loop1这一段与 链表2从头开始到loop2这一段，在这之间会有相交点，而不用考虑进环以后该怎么处理，基本逻辑与 noLoop一致
		if (loop1 == loop2) {
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			// 这里就是要判断成环之间，而不是整个链表的终点
			while (cur1 != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while (cur2 != loop2) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else {
			//从loop1开始 那么将来一定能再回到loop1，要是在回到loop1之前还没遇上loop2，那么这两个链表不会相交，要是遇上了loop2，那么返回loop1与loop2都行
			cur1 = loop1.next;
			while (cur1 != loop1) {
				if (cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println("=========" + getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}

}

