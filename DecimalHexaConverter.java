import java.util.Scanner;

public class DecimalHexaConverter {

    // function to validate if input is in decimal format
    public static boolean isValidDecimal(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // function to convert decimal to hexadecimal
    public static String decimalToHexadecimal(String decimal) {
        int num = Integer.parseInt(decimal);
        StringBuilder hex = new StringBuilder();
        while (num != 0) {
            int rem = num % 16;
            if (rem < 10) {
                hex.insert(0, (char) (rem + '0'));
            } else {
                hex.insert(0, (char) (rem - 10 + 'A'));
            }
            num = num / 16;
        }
        return hex.toString();
    }

    // function to convert hexadecimal to decimal
    public static String hexadecimalToDecimal(String hexadecimal) {
        int decimal = 0;
        for (int i = 0; i < hexadecimal.length(); i++) {
            char digit = hexadecimal.charAt(i);
            int value;
            if (digit >= '0' && digit <= '9') {
                value = digit - '0';
            } else {
                value = digit - 'A' + 10;
            }
            decimal = decimal * 16 + value;
        }
        return Integer.toString(decimal);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // getting user input
        System.out.print("Enter a number: ");
        String input = scanner.nextLine();

        // validating input and performing conversions
        if (isValidDecimal(input)) {
            // Converting decimal to hexadecimal
            String hexadecimal = decimalToHexadecimal(input);
            System.out.println("Hexadecimal: " + hexadecimal);
        } else {
            System.out.println("Invalid input.");
        }

        // getting user input for hexadecimal conversion
        System.out.print("Enter a hexadecimal number: ");
        input = scanner.nextLine();

        // validating input and performing conversions
        if (input.matches("[0-9A-F]+")) {
            // converting hexadecimal to decimal
            String decimal = hexadecimalToDecimal(input);
            System.out.println("Decimal: " + decimal);
        } else {
            System.out.println("Invalid input.");
        }

        scanner.close();
    }
}