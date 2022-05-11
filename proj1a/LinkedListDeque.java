/* The LinkedListDeque below is implemented with
* an one sentinel and circular linked list. */

public class LinkedListDeque<T> {
    // members
    private class Node{
        T item;
        Node next;
        Node prev;
        public Node(T item, Node prev, Node next){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private int size = 0;
    private Node sentinel = new Node(null, null, null);

    // constructors
    public LinkedListDeque(){
        sentinel.prev = sentinel.next = sentinel; //
    } // Default values are given in declarations

    // methods
    public int size(){return size;}
    public void addFirst(T item){
        sentinel.next = new Node(item, sentinel, sentinel.next);
        size++;
    }

    public void addLast(T item){
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        size++;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        if(size == 0)
            return;

        Node ptr = sentinel;
        do{
            System.out.println(ptr.item);
        }while(ptr != sentinel);
    }

    public T removeFirst(){
        if(size <= 0)
            return null;

        T buf = sentinel.next.item;
        Node nodeBuf = sentinel.next; // need to set it as null for GC
        sentinel.next = sentinel.next.next;
        nodeBuf = null;
        size--;
        return buf;
    }

    public T removeLast(){
        if(size <= 0)
            return null;

        T buf = sentinel.prev.item;
        Node nodeBuf = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        nodeBuf = null;
        size--;
        return buf;
    }

    public T get(int index){
        if(index >= size || index < 0)
            return null;
        else{
            int cnt = index;
            Node ptr = sentinel.next;
            while(cnt != 0){
                ptr = ptr.next;
                cnt--;
            }
            return ptr.item;
        }
    }

    public T getRecursive(int index){
        return getRecursiveImplement(index, sentinel.next);
    }

    private T getRecursiveImplement(int index, Node ptr){
        if(index >= size || index < 0)
            return null;

        if(index == 0)
            return ptr.item;
        else
            return getRecursiveImplement(index-1, ptr.next);
    }
}
