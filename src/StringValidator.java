import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

public class StringValidator {
    private static final int currentYear = 2022;
    private static final Map<Character, Integer> yearMap = Map.ofEntries(entry('T',20),
            entry('S',19),
            entry('R',18));


    public static void main(String[] args) {

        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Quit to Exit");
            System.out.print("Enter UEN: ");

            String myUEN = input.nextLine();

            if (myUEN.equals("Quit")) {
                break;
            }

            UEN validatedUEN = validateLic(myUEN);

            if (validatedUEN.isValid) {
                System.out.println("Valid UEN with format: " + validatedUEN.format);
            } else {
                System.out.println("Invalid UEN");
            }
        }
    }

    private static UEN validateLic(String uen) {
        /*Logic goes here*/
        UEN myUEN = new UEN();
        int length = uen.length();
        if(length == 9) {
            myUEN.format = 'A';
            int data = 0;
            for (int i = 0; i < length-1;i++) {
                if (Character.isLetter(uen.charAt(i))) {
                    return myUEN;
                } else {
                    data = 10 * data + Character.getNumericValue(uen.charAt(i));
                }
            }
            myUEN.data = data;
            if (Character.isLetter(uen.charAt(length-1))) {
                myUEN.check = uen.charAt(length-1);
                myUEN.isValid = true;
            }
        } else if (length == 10) {
            int year = 0;
            int data = 0;
            if (Character.isDigit(uen.charAt(0))) {
                myUEN.format = 'B';
                for (int i = 0; i < length-1;i++) {
                    if (Character.isLetter(uen.charAt(i))) {
                        return myUEN;
                    } else {
                        if (i<4) {
                           year = 10 * year + Character.getNumericValue(uen.charAt(i));
                        } else {
                            data = 10 * data + Character.getNumericValue(uen.charAt(i));
                        }
                    }
                }
                myUEN.year = year;
                myUEN.data = data;
                if (Character.isLetter(uen.charAt(length-1))) {
                    myUEN.check = uen.charAt(length-1);
                    myUEN.isValid = true;
                }
            } else {
                myUEN.format = 'C';
                char[] entity = new char[2];
                int entityCount = 0;
                if (yearMap.containsKey(uen.charAt(0))) {
                    year = yearMap.get(uen.charAt(0));
                    for (int i = 1; i < length-1;i++) {
                        if (i < 3) {
                            if (Character.isLetter(uen.charAt(i))) {
                                return myUEN;
                            } else {
                                year = 10 * year + Character.getNumericValue(uen.charAt(i));
                            }
                        } else if (i < 5) {
                            if (i == 3 && Character.isDigit(uen.charAt(i))) {
                                return myUEN;
                            } else {
                                entity[entityCount] = uen.charAt(i);
                                entityCount = entityCount+1;
                            }
                        } else {
                            if (Character.isLetter(uen.charAt(i))) {
                                return myUEN;
                            } else {
                                data = 10 * data + Character.getNumericValue(uen.charAt(i));
                            }
                        }
                    }
                    myUEN.year = year;
                    myUEN.data = data;
                    myUEN.entity = entity;
                    if (Character.isLetter(uen.charAt(length-1))) {
                        myUEN.check = uen.charAt(length-1);
                        myUEN.isValid = true;
                    }
                } else {
                    return myUEN;
                }
            }
        }
        if (myUEN.year > currentYear) {
            myUEN.isValid = false;
        }
        return myUEN;
    }
}
