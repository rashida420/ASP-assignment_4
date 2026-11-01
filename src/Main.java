public class Main {
    public static void main(String[] args) {
        if (checkArguments(args) == false) {
            return;
        }

        int size = squareSize(args[1]);
        if (size == -1) {
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

    private static int squareSize(String value) {
        try {
            int size = Integer.parseInt(value);

            if (size > 0) {
                return size;
            }
            reportError("size must be greater than zero.");
        }
        catch (NumberFormatException e) {
            reportError("size must be a valid integer.");
        }
        return -1;
    }
    private static void reportError(String message) {
        System.err.println("Error: " + message);
        System.err.println("Usage: java -cp out Main <file.jpg> <square_size> <S|M>");
    }
}
