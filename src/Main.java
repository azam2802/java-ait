import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        arrStat();

        isPalindrome();

    }

    public static void arrStat() {
        // Array statistics
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the size of the array: ");
        int count = scan.nextInt();

        if (count <= 0) {
            System.out.println("Invalid input");
            return;
        }

        int[] arr = new int[count];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < count; i++) {
            System.out.printf("Enter %d element%n", i + 1);
            arr[i] = scan.nextInt();
        }
        Arrays.sort(arr);

        // Sum of array
        System.out.println("Sum " + Arrays.stream(arr).sum());

        // Average of array
        System.out.println("Average " + Arrays.stream(arr).average().getAsDouble());

        // Min of array
        System.out.println("Min " + Arrays.stream(arr).min().getAsInt());

        // Max of array
        System.out.println("Max " + Arrays.stream(arr).max().getAsInt());

        // Second smallest
        if (arr.length > 1) {
            System.out.println("Second Smallest " + arr[1]);
        } else {
            System.out.println("Second Smallest not available");
        }

        // Second largest
        if (arr.length > 1) {
            System.out.println("Second Largest " + arr[count - 2]);
        } else {
            System.out.println("Second Largest not available");
        }

    }

    public static void isPalindrome() {
        // Palindrome checker

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter something to check if it's a palindrome:");
        String input = scanner.nextLine();

        String cleanedInput = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        boolean isPalindrome = true;
        int length = cleanedInput.length();

        for (int i = 0; i < length / 2; i++) {
            if (cleanedInput.charAt(i) != cleanedInput.charAt(length - i - 1)) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println("The input is a palindrome.");
        } else {
            System.out.println("The input is not a palindrome.");
        }


    }
}
