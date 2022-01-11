import java.util.Iterator;
import java.util.ListIterator;
/*Emma Chiusano
 * PP2
 * Date Created: 4 November 2021
 * Last date modified: 12 November 2021
 * */
public class ArrayList<E> {
	// data members
	private E[] elements;
	private int size;

	// Constructors 
	public ArrayList() { // O(1)
		elements = (E[]) new Object[10];
		size = 0;
	}

	public ArrayList(int capacity) { // O(1)
		elements = (E[]) new Object[capacity];
		size = 0;
	}
	// Adding an item to the list (2 methods)
	public boolean add(E item) { // O(1) - O(n)
		return add(size, item);
	}
	public boolean add(int index, E item) { // O(n)
		if (index > size || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		ensureCapacity(); // O(n)
		for (int i = size-1; i>= index; i--) { // O(n)
			elements[i + 1] = elements[i];
		}
		elements[index] = item;
		size++;
		return true;
	}
	/*
	public int add(int index, E item) { // O(n)
		int iterations = 0;
		if (index > size || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		iterations = ensureCapacity(); // O(n)
		for (int i = size-1; i>= index; i--) { // O(n)
			iterations++;
			elements[i + 1] = elements[i];
		}
		elements[index] = item;
		size++;
		return iterations;
	}*/
	// Getter and Setter
	public E get(int index) { // O(1)
		checkIndex(index);
		return elements[index];
	}

	public E set(int index, E newItem) { // O(1)
		checkIndex(index);
		E oldItem = elements[index];
		elements[index] = newItem;
		return oldItem;
	}

	// Size of the list
	public int size() {//O(1)
		return size;
	}

	// Clear the list
	public void clear() {//O(1)
		size = 0;
	}

	// Check if the list is empty
	public boolean isEmpty() {//O(1)
		return (size == 0);
	}
	/*
	// Removing an object from the list
	public boolean remove(Object o) { // O(n)
		E item = (E) o;
		for (int i = 0; i < size; i++) { // O(n)
			if (elements[i].equals(item)) {
				remove(i); // O(n)
				return true;
			}
		}
		return false;
	}
	 */
	// Removing the item at index from the list
	public E remove(int index) { // O(n)
	     checkIndex(index);
	     E item = elements[index];
	     for(int i=index; i<size-1; i++) { // O(n)
				elements[i] = elements[i+1];
	     }
	     size--;
	     return item;
	}

	// Shrink the list to size
	public void trimToSize() { // O(n)
		if (size != elements.length) {
			E[] newElements = (E[]) new Object[size];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[i];
			}
			elements = newElements;
		}
	}

	// Grow the list if needed
	private int ensureCapacity() { // O(n)
		int iterations=0;
		if (size >= elements.length) {
			int newCap = (int) (elements.length * 1.5);
			E[] newElements = (E[]) new Object[newCap];
			for (int i = 0; i < size; i++) {
				newElements[i] = elements[i];
				iterations++;
			}
			elements = newElements;
		}
		return iterations;
	}
	
	// Check if the index is valid
	private void checkIndex(int index) { // O(1)
		if (index < 0 || index >= size)
			throw new ArrayIndexOutOfBoundsException("Index out of bounds. Must be between 0 and " + (size - 1));
	}

	// toString() method
	public String toString() {// O(n)
		String output = "[";
		for (int i = 0; i < size - 1; i++) {
			output += elements[i] + ", ";
		}
		output += elements[size - 1] + "]";
		return output;
	}
	// Iterator for the list
	public Iterator<E> iterator(){
			  return new ArrayIterator();
	}
	// Inner class that implements Iterator<E>
	private class ArrayIterator implements Iterator<E>{
		private int current = -1;

		public boolean hasNext() { 
			return current < size-1; 
		}

		public E next() { 
			return elements[++current]; 
		}
	}
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
	public int remove(E item) {
		int iterations = 0;
		for(int i=0; i<size; i++) {// searching
			iterations++;
			if(elements[i].equals(item)) {
				for(int j=i; j<size-1; j++) {// shifting
					iterations++;
					elements[j] = elements[j+1];
				}
				size--;
				return iterations;
			}
		}
		return iterations;
	}
	//ADD ONS FOR PP2
	public ListIterator<E> listIterator(){
		ArrayListIterator lisIter=new ArrayListIterator();
		return new ArrayListIterator(); // invokes the default constructor that initializes current to -1
	}
	public ListIterator<E> listIterator(int index){
		ArrayListIterator lisIter=new ArrayListIterator();
		E filler=null;
		if(index==elements.length) {
			for(int i=0; i<elements.length-1; i++) {
				filler=(E)lisIter.next();
			} 
			return new ArrayListIterator(); // invokes the second constructor that initializes current to index 
		}else if (index>elements.length) {
			throw new ArrayIndexOutOfBoundsException();
		}else {
			for(int i=0; i<index-1;i++) {
				filler=(E)lisIter.next();
			}
			return new ArrayListIterator(); // invokes the second constructor that initializes current to index 
		}
	}
	private class ArrayListIterator implements ListIterator<E>{
		private int current = -1;
		public boolean hasNext() {
			return current < size-1; 
		}
		public E next() {
			return (E) elements[++current]; 
		}
		public boolean hasPrevious() {
			return current > size; 
		}
		public E previous() {
			return (E) elements[--current]; 
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