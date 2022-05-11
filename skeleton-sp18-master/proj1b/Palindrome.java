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

    public boolean isPalindrome(String word){
        Deque<Character> dq = wordToDeque(word);

        // Boundary Conditions
        if(dq.size() == 0 || dq.size() == 1){
            return true;
        }

        // double pointer method to check
        Character lBuf, rBuf;
        while(dq.size()>1){
            lBuf = dq.removeFirst();
            rBuf = dq.removeLast();
            if(!lBuf.equals(rBuf)){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeRec(String word){
        Deque<Character> dq = wordToDeque(word);
        return isPalindromeRecHelper(dq);
    }

    private boolean isPalindromeRecHelper(Deque<Character> dq){
        if(dq.size() == 0 || dq.size() == 1){
            return true;
        } else{
            Character lBuf, rBuf;
            lBuf = dq.removeFirst();
            rBuf = dq.removeLast();
            return (lBuf.equals(rBuf)) && (isPalindromeRecHelper(dq));
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> dq = wordToDeque(word);
        return isPalindromeRecHelper(dq, cc);
    }

    private boolean isPalindromeRecHelper(Deque<Character> dq, CharacterComparator cc){
        if(dq.size() == 0 || dq.size() == 1){
            return true;
        } else{
            return (cc.equalChars(dq.removeFirst(), dq.removeLast())) && (isPalindromeRecHelper(dq,cc));
        }
    }
}
