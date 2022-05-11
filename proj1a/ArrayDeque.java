public class ArrayDeque<T> {
    private class Items{
        T[] itemArr;
        int num; // number of elements stored in the array.
        int volume; // number of memory blocks preserved by array.

        // constructors
        public Items(){
            itemArr = (T []) new Object[100];
            this.volume = 100;
            this.num = 0;
        }
        public Items(int volume){
            itemArr = (T []) new Object[volume];
            this.volume = volume;
            this.num = 0;
        }
    }

    private Items items;

    // constructors
    public ArrayDeque(){
        this.items = new Items();
    }
    public ArrayDeque(int volume){
        this.items = new Items(volume);
    }

    // methods
    public void addFirst(T item){

    }


}
