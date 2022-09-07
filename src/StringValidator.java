import java.util.Scanner;

public class StringValidator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter biz lic: ");

        String myLic = input.nextLine();

        String typeOf = validateLic(myLic);

        System.out.println("My lic: " + myLic);
    }

    private static String validateLic(String lic) {
        /*Logic goes here*/
        return "A";
    }
}
