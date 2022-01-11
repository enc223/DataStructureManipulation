import java.util.ListIterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/*Emma Chiusano
 * PP2
 * Date Created: 4 November 2021
 * Last date modified: 12 November 2021
 * */
public class Test<E>{

	public static void main(String[]args) {
		ArrayList<String> al=new ArrayList<>();
		LinkedList<String> ll=new LinkedList<>();
		DoublyLinkedList<String> dll=new DoublyLinkedList<>();
		readFromFile(al, "countries.txt");
		readFromFile(ll, "countries.txt");
		readFromFile(dll, "countries.txt");
		System.out.println("Array List (Forward):");
		printListForward(al);
		System.out.println();
		System.out.println("Linked List (Forward):");
		printListForward(ll);
		System.out.println();
		System.out.println("Doubly Linked List (Forward):");
		printListForward(dll);
		System.out.println();
		System.out.println("Array List (Backward):");
		printListBackward(al);
		System.out.println();
		System.out.println("Linked List (Backward):");
		printListBackward(ll);
		System.out.println();
		System.out.println("Doubly Linked List (Backward):");
		printListBackward(dll);
	}
	//O(n)
	private static <E> void printListForward(ArrayList<String> al) {
		System.out.print(al);
		System.out.println();
	}
//O(n)
	private static <E> void printListBackward(ArrayList<String> al) {
		ArrayList<String> reverseArray = new ArrayList<>();
		for (int i=al.size()-1; i>=0; i--) {
			reverseArray.add(al.get(i));
		}
		System.out.println(reverseArray);
		System.out.println();
	}
	//O(n^2)
	private static <E> void printListForward(List<E> forwardList) {
		System.out.print("[");
		ListIterator<E> iter=forwardList.listIterator();
		while (iter.hasNext()) {
			System.out.print(iter.next()+", ");
		} 
		System.out.print("]"); 
		System.out.println();
	} 
//O(n^2)
	private static <E> void printListBackward(List<E> backList) {
		System.out.print("[");
		ListIterator<E> iter=backList.listIterator(backList.size());
		while (iter.hasPrevious()) {
			System.out.print(iter.previous()+", ");
		}
		System.out.print("]");
		System.out.println();
	}
	public static void readFromFile(ArrayList<String> readList, String filename) {
		File file=new File(filename);
		try {
			Scanner readFile=new Scanner(file);
			while (readFile.hasNext()) {
				String line=readFile.nextLine();
				readList.add(line);
			}
			readFile.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}
	}
	public static void readFromFile(List<String> readList, String filename) {
		File file=new File(filename);
		try {
			Scanner readFile=new Scanner(file);
			while (readFile.hasNext()) {
				String line=readFile.nextLine();
				readList.add(line);
			}
			readFile.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}
	}
}
