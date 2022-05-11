public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        int wSize = word.length();
        Deque<Character> dq = new LinkedListDeque();
        for(int i = 0; i < wSize; i++){
            Character newChar = word.charAt(i);
            dq.addLast(newChar);
        }
        return dq;
    }
}
