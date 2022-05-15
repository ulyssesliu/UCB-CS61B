public class OffByN implements CharacterComparator{
    private int offSet;
    public OffByN(int N){
        offSet = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        return (x-y== offSet) || (y-x==offSet);
    }
}

