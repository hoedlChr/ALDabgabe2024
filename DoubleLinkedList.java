/*Christoph Hödl*/
package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{
    private int counter;
    private Node<T> first;
    private Node<T> current;
    private Node<T> last;

    private int pointer;

    /**
     * Einfügen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {
        Node<T> newNode = new Node<T>(a);
        if(first == null){
            first = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
        }
        last = newNode;
        counter++;
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {
        pointer = 0;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        pointer = counter;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
    	
    	return first;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
    	
    	return last;
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {
        T data = null;
        if(pointer == 0)
            data = first.getData();
        else if(pointer == counter)
            data = last.getData();
        else
            data = current.getData();

        moveNext();
    	return data;
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {
        T data = null;
        if(pointer == 0)
            data = first.getData();
        else if(pointer == counter)
            data = last.getData();
        else
            data = current.getData();

        movePrevious();
        return data;
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
        if(pointer < counter){
            current = current.getNext();
            pointer++;
        }
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if(pointer > 0){
            current = current.getPrevious();
            pointer--;
        }
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {
        if(current == null)
            throw new CurrentNotSetException();
    	return current.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos){
        Node<T> help = first;

        for(int i = 0; i < pos-1; i++){
            help = help.getNext();
        }

        return help.getData();
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) {
        Node<T> toDelete = first;

        for(int i = 0; i < pos-1; i++) {
            toDelete = toDelete.getNext();
        }

        Node<T> prev = toDelete.getPrevious();
        Node<T> next = toDelete.getNext();
        prev.setNext(next);
        next.setPrevious(prev);

        if(pointer == pos){
            current = null;
        }


    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
        Node<T> prev = current.getPrevious();
        Node<T> next = current.getNext();
        prev.setNext(next);
        next.setPrevious(prev);

        if(pointer < counter)
            moveNext();
        else
            movePrevious();
    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

    }
}
