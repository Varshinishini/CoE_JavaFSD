package task_5;

public class StringProcessor {
	public String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public int countOccurrences(String text, String sub) {
        int count = 0, index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }

    public String splitAndCapitalize(String str) {
        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1))
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        StringProcessor processor = new StringProcessor();

        String input = "hello world this is java";
        System.out.println("Reversed: " + processor.reverseString(input));

        String text = "abc abc abc";
        String sub = "abc";
        System.out.println("Occurrences of '" + sub + "': " + processor.countOccurrences(text, sub));

        System.out.println("Capitalized: " + processor.splitAndCapitalize(input));
    }

}
