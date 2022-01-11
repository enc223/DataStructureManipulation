import java.util.ListIterator;
/*Emma Chiusano
 * PP2
 * Date Created: 4 November 2021
 * Last date modified: 12 November 2021
 * */
public interface List<E> {
	public abstract boolean add(E item);
	public abstract int size();
	public abstract ListIterator<E> listIterator();
	public abstract ListIterator<E> listIterator(int index);
	
}
