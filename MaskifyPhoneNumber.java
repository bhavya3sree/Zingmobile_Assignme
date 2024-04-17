import java.util.Scanner;

public class MaskifyPhoneNumber {

    // method to maskify user mobile number except last 3 digits
    public static String maskify(String phoneNumber) {
        StringBuilder maskedNumber = new StringBuilder();
        int length = phoneNumber.length();
        
        // mask all digits except last 3
        for (int i = 0; i < length - 3; i++) {
            maskedNumber.append('#');
        }
        
        // append last 3 digits
        maskedNumber.append(phoneNumber.substring(length - 3));
        
        return maskedNumber.toString();
    }

    public static void main(String[] args) {
       Scanner str = new Scanner(System.in);

        // getting user input
        System.out.print("Enter phonenumber : ");
        String phoneNumber = str.nextLine();
        String maskedNumber = maskify(phoneNumber);
        System.out.println(maskedNumber);
    }
}
