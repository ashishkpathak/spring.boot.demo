package au.com.pathak.springbootdemo;

public class SolutionTest {

  public static String RunLengthEncoding(String input) {
    // Your code goes here

    if (input == null || input.length() == 0)
      return input;

    char[] charArray = input.toCharArray();
    int count = 1;
    StringBuffer builder = new StringBuffer();

    for (int i = 0; i < charArray.length - 1; ++i) {
      if (charArray[i] == charArray[i + 1]) {
        count++;
      } else {
        builder.append(count == 1 ? "" : count).append(charArray[i]);
        count = 1;
      }

    }
    builder.append(count == 1 ? "" : count).append(charArray[charArray.length-1]);
    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(RunLengthEncoding("aaabbcdddd"));
    System.out.println(RunLengthEncoding("aaabbcd"));
    System.out.println(RunLengthEncoding("aabcd"));
    System.out.println(RunLengthEncoding("55555"));

  }
}
