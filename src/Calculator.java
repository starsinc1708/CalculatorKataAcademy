import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {

    private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<>() {{
        put("I", 1);
        put("II", 2);
        put("III", 3);
        put("IV", 4);
        put("V", 5);
        put("VI", 6);
        put("VII", 7);
        put("VIII", 8);
        put("IX", 9);
        put("X", 10);
        put("L", 50);
        put("C", 100);
    }};

    private static boolean isRomanNumeral(String s) {
        return ROMAN_NUMERALS.containsKey(s);
    }

    public static String intToRoman(int num) {

        if (num <= 0 || num > 100) {
            throw new IllegalArgumentException("Input must be between 1 and 100");
        }

        StringBuilder sb = new StringBuilder();

        while (num >= 100) {
            sb.append("C");
            num -= 100;
        }

        if (num >= 90) {
            sb.append("XC");
            num -= 90;
        } else if (num >= 50) {
            sb.append("L");
            num -= 50;
        } else if (num >= 40) {
            sb.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            sb.append("X");
            num -= 10;
        }

        if (num >= 9) {
            sb.append("IX");
            num -= 9;
        } else if (num >= 5) {
            sb.append("V");
            num -= 5;
        } else if (num >= 4) {
            sb.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            sb.append("I");
            num -= 1;
        }

        return sb.toString();
    }

    public static String calc(String input) {
        String[] parts = input.split("\\s+");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid input format. Please provide two numbers and an operator separated by spaces.");
        }

        String num1 = parts[0];
        String operator = parts[1];
        String num2 = parts[2];

        boolean isRoman = false;
        int n1;
        int n2;

        if (isRomanNumeral(num1)) {
            if (!isRomanNumeral(num2)) {
                throw new IllegalArgumentException("Invalid input format. Arabic and Roman numerals cannot be used in the same expression.");
            }
            isRoman = true;
        } else if (isRomanNumeral(num2)) {
            throw new IllegalArgumentException("Invalid input format. Arabic and Roman numerals cannot be used in the same expression.");
        }

        if(!isRoman) {
            n1 = Integer.parseInt(num1);
            n2 = Integer.parseInt(num2);
            if (n1 < 1 || n1 > 10 || n2 < 1 || n2 > 10) {
                throw new IllegalArgumentException("Invalid input. The calculator only accepts numbers from 1 to 10 inclusive.");
            }
        } else {
            n1 = ROMAN_NUMERALS.get(num1);
            n2 = ROMAN_NUMERALS.get(num2);
        }

        int result;

        switch (operator) {
            case "+":
                result = n1 + n2;
                break;
            case "-":
                result = n1 - n2;
                break;
            case "*":
                result = n1 * n2;
                break;
            case "/":
                result = n1 / n2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator. The calculator only accepts +, -, *, and / operators.");
        }

        if (isRoman) {
            if (result < 1) {
                throw new IllegalArgumentException("Invalid input. The result of the calculator with Roman numerals can only be positive numbers.");
            }
            return intToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");
        String expression = String.valueOf(in.nextLine());
        //System.out.print(expression + " = ");
        System.out.println(calc(expression));
    }
}
