import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String[] array = line.split(" ");
        int len = array.length;

        int number = scanner.nextInt();

        for (int i = 0; i < number % len; i++) {
            array = rotate(array);
        }

        for (String x : array) {
            System.out.print(x + " ");
        }
    }

    private static String[] rotate(String[] strings) {
        String temp = strings[strings.length - 1];
        String[] result = new String[strings.length];  // creating an array with the specified length
        result[0] = temp;
        System.arraycopy(strings, 0, result, 1, strings.length - 1);
        return result;
    }
}