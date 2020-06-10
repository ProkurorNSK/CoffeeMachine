import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int len = scanner.nextInt(); // reading a length
        int[] a = new int[len];  // creating an array with the specified length

        for (int i = 0; i < len; i++) {
            a[i] = scanner.nextInt(); // read the next number of the array
        }

        int[] b = rotate(a);

        for (int x : b) {
            System.out.print(x + " ");
        }
    }

    private static int[] rotate(int[] a) {
        int temp = a[a.length - 1];
        int[] b = new int[a.length];  // creating an array with the specified length
        b[0] = temp;
        System.arraycopy(a, 0, b, 1, a.length - 1);
        return b;
    }
}