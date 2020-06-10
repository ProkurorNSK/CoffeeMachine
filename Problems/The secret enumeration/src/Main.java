public class Main {

    public static void main(String[] args) {
        int result = 0;
        for (Secret element : Secret.values()) {
            if (element.name().startsWith("STAR")) {
                result++;
            }
        }
        System.out.println(result);
    }
}

/*
enum Secret {
    STAR, CRASH, START
}*/
