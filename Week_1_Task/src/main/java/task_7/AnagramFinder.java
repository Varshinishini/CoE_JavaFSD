package task_7;

import java.util.*;


public class AnagramFinder {
	public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int pLen = p.length();

        
        for (int i = 0; i < s.length(); i++) {
            
            sCount[s.charAt(i) - 'a']++;

            
            if (i >= pLen) {
                sCount[s.charAt(i - pLen) - 'a']--;
            }

            
            if (Arrays.equals(sCount, pCount)) {
                result.add(i - pLen + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        AnagramFinder finder = new AnagramFinder();
        String s = "cbaebabacd";
        String p = "abc";

        System.out.println("Anagram start indices: " + finder.findAnagrams(s, p));
    }

}
