import java.util.Scanner;
public class linkedUse{

	public static class LinkedListNode<T>{
		T data;
		LinkedListNode<T> next;
		public LinkedListNode(T data){
			this.data = data;
		}
	}

	public static LinkedListNode<Integer> takeInput(){
		LinkedListNode<Integer> head = null;
		LinkedListNode<Integer> tail = null;
		Scanner s = new Scanner(System.in);
		int data = s.nextInt();
		while(data != -1) {
			LinkedListNode<Integer> newLinkedListNode = new LinkedListNode<Integer>(data);
			if(head == null) {
				head = newLinkedListNode;
				tail = newLinkedListNode;
			} else {
				tail.next = newLinkedListNode;
				tail = newLinkedListNode;
			}
			data = s.nextInt();
		}
		return head;
	}

	public static void print(LinkedListNode<Integer> head){
		if(head == null)
			return;
		LinkedListNode<Integer> temp = head;
		while(temp != null){
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	public static LinkedListNode<Integer> insert(LinkedListNode<Integer> head, int data, int pos){
		LinkedListNode<Integer> newLinkedListNode = new LinkedListNode<Integer>(data);
		if(pos == 0){
			newLinkedListNode.next = head;
			head = newLinkedListNode;
			return head;
		}
		int count = 0;
		LinkedListNode<Integer> temp = head;
		while(count < pos - 1){
			count ++;
			temp = temp.next;
		}
		newLinkedListNode.next = temp.next;
		temp.next = newLinkedListNode;
		return head;
	}

	public static LinkedListNode<Integer> deleteLinkedListNode(LinkedListNode<Integer> head, int pos){
		if(pos == 0){
			head = head.next;
			return head;
		}
		LinkedListNode<Integer> temp = head;
		int count = 0;
		while(count < pos - 1 && temp.next != null){
			count ++;
			temp = temp.next;
		}
		if(temp.next != null){
			temp.next = temp.next.next;
		}
		return head;
	}

	public static void printReverse(LinkedListNode<Integer> root){
		if(root == null)
			return;
		printReverse(root.next);
		System.out.print(root.data + " ");
	}

	public static LinkedListNode<Integer> reverseList(LinkedListNode<Integer> head){
		if(head == null || head.next == null)
			return head;
		LinkedListNode<Integer> curr = head;
		LinkedListNode<Integer> prev = null;
		LinkedListNode<Integer> fwd = null;
		while(curr != null){
			fwd = curr.next;
			curr.next = prev;
			prev = curr;
			curr = fwd;
		}
		return prev;
	}

	public static boolean isPalindrome(LinkedListNode<Integer> head){
		if(head == null || head.next == null)
			return true;
		LinkedListNode<Integer> fast = head;
		LinkedListNode<Integer> slow = head;
		while(fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		LinkedListNode<Integer> secondHead = slow.next;
		slow.next = null;
		secondHead = reverseList(secondHead);
		LinkedListNode<Integer> firstSubList = secondHead;
		LinkedListNode<Integer> secondSubList = head;
		while(firstSubList != null){
			if(firstSubList.data != secondSubList.data)
				return false;
			firstSubList = firstSubList.next;
			secondSubList = secondSubList.next;
		}
		secondHead = reverseList(secondHead);
		secondSubList = head;
		while(secondSubList.next != null)
			secondSubList = secondSubList.next;
		secondSubList.next = secondHead;
		return true;
	}

	public static LinkedListNode<Integer> mergeSortedLists(LinkedListNode<Integer> head1, LinkedListNode<Integer> head2){
		LinkedListNode<Integer> resultList = new LinkedListNode<Integer>(0);
		LinkedListNode<Integer> curr = resultList;
		while(head1 != null && head2 != null){
			if(head1.data < head2.data){
				curr.next = new LinkedListNode<Integer>(head1.data);
				head1 = head1.next;
			} else {
				curr.next = new LinkedListNode<Integer>(head2.data);
				head2 = head2.next;
			}
			curr = curr.next;
		}
		while(head1 != null){
			curr.next = new LinkedListNode<Integer>(head1.data);
			head1 = head1.next;
			curr = curr.next;
		}
		while(head2 != null){
			curr.next = new LinkedListNode<Integer>(head2.data);
			head2 = head2.next;
			curr = curr.next;
		}
		return resultList.next;
	}

	public static LinkedListNode<Integer> midPoint(LinkedListNode<Integer> head){
		LinkedListNode<Integer> slow = head;
		LinkedListNode<Integer> fast = head;
		while(fast.next != null && fast.next.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static LinkedListNode<Integer> mergeSort(LinkedListNode<Integer> head){
		if(head == null || head.next == null)
			return head;
		LinkedListNode<Integer> mid = midPoint(head);
		LinkedListNode<Integer> head1 = head;
		LinkedListNode<Integer> head2 = mid.next;
		mid.next = null;
		head1 = mergeSort(head1);
		head2 = mergeSort(head2);
		LinkedListNode<Integer> resultList = mergeSortedLists(head1, head2);
		return resultList; 
	}

	public static LinkedListNode<Integer> reverseListRec(LinkedListNode<Integer> head){
		if(head.next == null)
			return head; 
		LinkedListNode<Integer> finalHead = reverseListRec(head.next);
		head.next.next = head;
		head.next = null;
		return finalHead;
	}

	public static LinkedListNode<Integer> deleteithNodeRec(LinkedListNode<Integer> head, int i) {
		if(head == null)
			return head;
		if(i == 0) {
			LinkedListNode<Integer> resultNode = head.next;
			return resultNode;
		} 
		head.next = deleteithNodeRec(head.next, i - 1);
		return head;
	}

	public static LinkedListNode<Integer> swapNodes(LinkedListNode<Integer> head, int i, int j) {
		int pos = 0;
		LinkedListNode<Integer> temp = head;
		LinkedListNode<Integer> prev = null;
		LinkedListNode<Integer> prev1 = null;
		LinkedListNode<Integer> curr1 = null;
		LinkedListNode<Integer> prev2 = null;
		LinkedListNode<Integer> curr2 = null;
		while(temp != null) {
			if(pos == i) {
				prev1 = prev;
				curr1 = temp;
			} else if(pos == j) {
				prev2 = prev;
				curr2 = temp;
			}
			prev = temp;
			temp = temp.next;
			pos ++;
		}

		if(prev1 != null)
			prev1.next = curr2;
		else
			head = curr2;

		if(prev2 != null)
			prev2.next = curr1;
		else
			head = curr1;

		LinkedListNode<Integer> tempNew = curr2.next;
		curr2.next = curr1.next;
		curr1.next = tempNew;
		return head;
	}

	public static LinkedListNode<Integer> kReverse(LinkedListNode<Integer> head, int k){
		if(k == 0)
			return head;
		int count = 0;
		LinkedListNode<Integer> curr = head;
		LinkedListNode<Integer> prev = null;
		LinkedListNode<Integer> fwd = null;
		while(curr != null && count < k){
			fwd = curr.next;
			curr.next = prev;
			prev = curr;
			curr = fwd;
			count ++;
		}
		if(fwd != null)
			head.next = kReverse(fwd, k);
		return prev;
	}

	public static void main(String[] args){
		LinkedListNode<Integer> head = takeInput();
		print(head);
		printReverse(head);
		LinkedListNode<Integer> head1 = reverseList(head);
		print(head1);	
	}
}