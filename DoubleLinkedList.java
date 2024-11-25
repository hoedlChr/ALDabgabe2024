/* 24.11.2024 Christoph Hödl*/
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
        current = getFirst();
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        pointer = counter;
        current = getLast();
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
        if(current == null){
            return null;
        }
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
        if(current == null){
            return null;
        }
        data = current.getData();

        movePrevious();
        return data;
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
        if(current == null) {

        } else if(pointer < counter){
            current = current.getNext();
        } else {
            current = null;
        }
        pointer++;
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if(current == null) {

        } else if(pointer > 0){
            current = current.getPrevious();
        } else {
            current = null;
        }
        pointer--;
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
    public void remove(int pos) throws CurrentNotSetException {
        Node<T> toDelete = first;
        int toDeletePointer = 1;
        for(int i = 0; i < pos-1; i++) {
            if(toDelete == null){
                break;
            }
            toDelete = toDelete.getNext();
            toDeletePointer++;
        }

        if(toDelete == null){
            return;
        }

        int pointerToSave;
        if(current == null){
            pointerToSave = Integer.valueOf(0);
        } else {
            pointerToSave = Integer.valueOf(pointer);
        }

        current = toDelete;
        pointer = toDeletePointer;

        removeCurrent();

        pointer = pointerToSave;

        if(pos == pointer){
            current = null;
        } else {
            current = first;
            for(int i = 0; i < pointer-1; i++) {
                if(current == null){
                    break;
                }
                current = current.getNext();
            }
        }
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
        if (current == null)
            throw new CurrentNotSetException();
        Node<T> prev = current.getPrevious();
        Node<T> next = current.getNext();

        if(prev == null&& next == null){
            current = null;
            first = null;
            last = null;
            counter =0;
            pointer=0;
        } else if(prev == null){
            first = next;
            first.setPrevious(null);
            moveNext();
        } else if (next == null){
            last = last.getPrevious();
            last.setNext(null);
            movePrevious();
        } else {
            prev.setNext(next);
            next.setPrevious(prev);
            moveNext();
        }
        pointer--;
        counter--;

    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {
        if(current == null){
            throw new CurrentNotSetException();
        }
        Node<T> newNode = new Node<T>(a);
        Node<T> nodeAfterCurrent = current.getNext();
        if(nodeAfterCurrent == null){
            add(a);
        } else {
            counter++;
            current.setNext(newNode);
            newNode.setPrevious(current);
            newNode.setNext(nodeAfterCurrent);
            nodeAfterCurrent.setPrevious(newNode);
        }
        moveNext();
    }
}
