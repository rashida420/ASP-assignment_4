public class Main {
    public static void main(String[] args) {
        if (checkArguments(args) == false) {
            return;
        }
    }
    private static boolean checkArguments(String[] args) {
        if (args.length == 3) {
            return true;
        }

        reportError("The program requires exactly 3 arguments.");
        return false;
    }
    private static void reportError(String message) {
        System.err.println("Error: " + message);
        System.err.println("Usage: java -cp out Main <file.jpg> <square_size> <S|M>");
    }
}