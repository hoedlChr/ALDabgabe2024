/*Christoph Hödl*/
package A02_Queue;

public class Queue<T>
{
    private Node<T> first;
    private int counter;
    private Node<T> last;
    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws QueueEmptyException 
     */
    public T dequeue() throws QueueEmptyException {
        if(counter == 0)
            throw new QueueEmptyException();
        T data  = first.getData();
        first = first.getNext();
        counter--;
    	return data;
    }

    
    /**
     * Übergebenen Integer am Ende der Queue anhängen.
     * @param i Zahl
     */
    public void enqueue(T i) {
        Node<T> newNode = new Node<T>(i);
        if(first == null){
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        counter++;
    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
    	return counter;
    }
}
