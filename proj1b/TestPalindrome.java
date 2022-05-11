import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } // Uncomment this class once you've created your Palindrome class.


    @Test
    public void testPalindrome(){
        String pstr1 = "asdfghjkllkjhgfdsa";
        String str2 = "asdfghjklkjhnbvfdsa";

        assertTrue(palindrome.isPalindrome(pstr1));
        assertFalse(palindrome.isPalindrome(str2));
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindromeRec(pstr1));
        assertFalse(palindrome.isPalindromeRec(str2));
        assertFalse(palindrome.isPalindromeRec("cat"));

        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake",obo));
        assertTrue(palindrome.isPalindrome("acegyzhfdb",obo));
        assertFalse(palindrome.isPalindrome("qowieuryt",obo));
    }
}
