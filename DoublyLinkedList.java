import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/*Emma Chiusano
 * PP2
 * Date Created: 4 November 2021
 * Last date modified: 12 November 2021
 * */

public class DoublyLinkedList<E> implements List<E>{
	// Data members
		private Node head, tail;
		int size;

		// Inner class Node
		//MODIFY FOR DLL
		//O(1)
		private class Node {
			E value;
			Node next;
			Node prev;

			Node(E initialValue) {//initialize the node
				value = initialValue;
				next = null;
				prev=null;
			}
		}

		// Constructor
		/*Constructor for doubly linked list
		 * @param: none
		 * @return: none*/
		public DoublyLinkedList() {// O(1)
			head = tail = null;//constructor for our node in doubly linked list
			size = 0;
		}

		// Adding an item to the list
		//MODIFY FOR DLL
		/*method to add to first node
		 * @param: E item
		 * @return: true once added*/
		public boolean addFirst(E item) {// O(n)
			Node newNode = new Node(item);
			if (head == null) { // adding the first node
				head = tail = newNode;
				head.prev=null;
				tail.next=null;
			} else {
				Node temp=head;
				head=newNode;
				newNode.next=head;
				head.prev=null;
				head.next=temp;
				
			}
			size++;
			return true;
		}
		//MODIFY FOR DLL
		/*Method to add to last node
		 * @param: E item
		 * @return: true once added*/
		public boolean addLast(E item) {// O(n)
			Node newNode = new Node(item);
			if (head == null) {
				head = tail = newNode;
				head.prev=null;
				tail.next=null;
			} else {
				Node temp=tail;
				tail.next = newNode;
				tail = newNode;
				tail.prev=temp;
				tail.next=null;
			}
			size++;
			return true;
		}
		/*Method to add
		 * @param: E item
		 * @return: value of the added node*/
		public boolean add(E item) { // O(1)
			return addLast(item);
		}
		// Retrieving an item from the list
		/*Method to retrieve from list
		 * @param: none
		 * @return: head value*/
		public E getFirst() { // O(1)
			if (head == null)
				throw new NoSuchElementException();
			return head.value;
		}
		/*Method to retrieve from list
		 * @param: none
		 * @return: tail value*/
		public E getLast() { // O(1)
			if (head == null)
				throw new NoSuchElementException();
			return tail.value;
		}
		// Removing an item from the list
		//MODIFY FOR DLL
		/*Method to remove from list
		 * @param: none
		 * @return: true once removed*/
		public boolean removeFirst() { // O(n)
			if (head == null)
				throw new NoSuchElementException();
			head = head.next;
			if (head == null)
				tail = null;
			size--;
			return true;
		}
		//MODIFY FOR DLL
		/*Method to remove from list
		 * @param: none
		 * @return: true once removed*/
		public boolean removeLast() { // O(n)
			if (head == null)
				throw new NoSuchElementException();
			if (size == 1)
				return removeFirst();
			Node current = head;
			Node previous = null;
			while (current.next != null) {
				previous = current;
				current = current.next;
			}
			previous.next = null;
			tail = previous;
			size--;
			return true;
		}
		
		// toString() method
		/*Method to print the list as a string
		 * @param: none
		 * @return: output of string*/
		public String toString() { // O(n)
			String output = "[";
			Node node = head;
			while (node != null) {
				output += node.value + " ";
				node = node.next;
			}
			output += "]";
			return output;
		}

		// clear, check if empty, and size of the list
		/*Method to clear the list
		 * @param: none
		 * @return: nonee*/
		public void clear() { // O(1)
			head = tail = null;
			size = 0;
		}
		/*Method to check if list is empty
		 * @param: none
		 * @return: none*/
		public boolean isEmpty() { // O(1)
			return (size == 0);
		}
		/*Method to check size
		 * @param: none
		 * @return: size*/
		public int size() { // O(1)
			return size;
		}
		// Generating an iterator for the list
		/*Method to iterate through teh list
		 * @param: none
		 * @return: iterated list*/
		public Iterator<E> iterator() {
			return new DoublyLinkedListIterator();
		}
/*
		private class LinkedListIterator implements Iterator<E> {
			private Node current = head;

			public boolean hasNext() { // O(1)
				return (current != null);
			}

			public E next() { // O(1)
				if (current == null)
					throw new NoSuchElementException();
				E value = current.value;
				current = current.next;
				return value;
			}
		}*/
		/*Method to check if list contains a node
		 * @param: E item
		 * @return: iterations*/
		public int contains(E item) {
			Iterator<E> iter = iterator();
			int iterations = 0;
			while(iter.hasNext()) {
				iterations++;
				if(item.equals(iter.next())) {
					return iterations;
				}
			}
			return iterations;
		}
		/*Method to remove a node
		 * @param: E item
		 * @return: iterations*/
		public int remove(E item) {
			Node current = head;
			Node previous = null;
			int iterations=0;
			while(current!= null && !item.equals(current.value)) {
				previous = current;
				current = current.next;
				iterations++;
			}
			if(current != null) {
				if(previous == null)
					removeFirst();
				else {
					previous.next = current.next;
					size--;
				}
			}
			return iterations;
		}
		/*Method to add a node
		 * @param: E item, int index
		 * @return: iterations*/
		public int add(int index, E item) {
			if(index < 0 || index > size) {
				throw new ArrayIndexOutOfBoundsException();	
			}
			if(index == 0) {
				addFirst(item);
				return 0;
			}
			if(index == size) {
				addLast(item);
				return 0;
			}
			int i=0;
			int iterations=0;
			Node current = head;
			Node previous = null;
			while(current!=null && i<index) {
				previous = current;
				current = current.next;
				i++;
				iterations++;
			}
			Node newNode = new Node(item);
			previous.next = newNode;
			newNode.next = current;
			size++;
			return iterations;
		}
		//ADD ONS FOR PP2
		/*Method to iterate through the list
		 * @param: none
		 * @return: doublylinkedlistiterator*/
		public ListIterator<E> listIterator(){
			DoublyLinkedListIterator lisIter=new DoublyLinkedListIterator();
			return new DoublyLinkedListIterator(); // invokes the default constructor that initializes current to -1
		}
		/*Method to iterate through the list
		 * @param: int index
		 * @return: doublylinkedlistiterator*/
		public ListIterator<E> listIterator(int index){
			DoublyLinkedListIterator lisIter=new DoublyLinkedListIterator();
			return new DoublyLinkedListIterator(index);
		}
		/*inner class of linkedlistiterator
		 * @param: none
		 * @return: doublylinkedlistiterator*/
		private class DoublyLinkedListIterator implements ListIterator<E>{
			private Node current = head;
			/*Method to iterate through the list
			 * @param: none
			 * @return: none*/
			public DoublyLinkedListIterator() {
				current=head;
			}
			/*Method to iterate through the list
			 * @param: int index
			 * @return: none*/
			public DoublyLinkedListIterator(int index) {
				current=head;
				int i=0;
				if (index==size) {
					index--;
				}
				while (i<index) {
					current=current.next;
					i++;
				}
			}
			/*Method to check if the list has next
			 * @param: none
			 * @return: true if there is a value next*/
			public boolean hasNext() {
				return (current != null);
			}
			/*Method to get next value
			 * @param: none
			 * @return: value for the next node*/
			public E next() {
				if (current == null)
					throw new NoSuchElementException();
				E value = (E) current.value;
				current = current.next;
				return value;
			} 
			/*Method to check if the list has previous
			 * @param: none
			 * @return: true if there is a value previous*/
			public boolean hasPrevious() {
				return (current != null);
			}
			//MODIFY FOR DLL
			//O(n)
			/*Method to get previous value
			 * @param: none
			 * @return: value for the previous node*/
			public E previous() {
				Node temp=head;
				Node prev=null;
				while(temp != current) {
					prev=temp;
					temp=temp.next;
				} 
				E value=(E) current.value;
				current=prev;
				return value;
			}
			public int nextIndex() {
				throw new UnsupportedOperationException();
			}
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}
			public void remove() {
				throw new UnsupportedOperationException();
				
			}
			public void set(E e) {
				throw new UnsupportedOperationException();
				
			}
			public void add(E e) {
				throw new UnsupportedOperationException();
				
		}
	}
}

