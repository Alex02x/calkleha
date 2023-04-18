import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Что решаем? (Например: 1 + 1)");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        System.out.println(calc(text));
    }
    public static String calc(String input) throws Exception {
        String res;
        String [] newText = input.split(" ");
        if (newText.length < 3) {
            throw new Exception("Строка не является математической операцией");
        }
        if (newText.length != 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        // two
        String two = newText[1];
        // two
        if (newText[0].contains("IIII")) {
            throw new Exception("Одного из введеных вами чисел не существует: " + newText[0]);
        }
        if (newText[2].contains("IIII")) {
            throw new Exception("Одного из введеных вами чисел не существует: " + newText[2]);
        }
        // first
        int first;
        boolean firstRoman = false;
        try {
            first = Integer.parseInt(newText[0]);
        } catch (NumberFormatException e) {
            first = ArabicToRoman.romanToInt(newText[0]);
            firstRoman = true;
        }
        // first

        // three
        int three;
        boolean threeRoman = false;
        try {
            three = Integer.parseInt(newText[2]);
        } catch (NumberFormatException e) {
            three = ArabicToRoman.romanToInt(newText[2]);
            threeRoman = true;
        }
        // three
        if ((firstRoman && !threeRoman) || (!firstRoman && threeRoman)) {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        if (first > 10 || three > 10) {
            throw new Exception("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
        }

        res = switch (two) {
            case "+" -> String.valueOf(first + three);
            case "-" -> String.valueOf(first - three);
            case "/" -> String.valueOf(first / three);
            case "*" -> String.valueOf(first * three);
            default -> throw new Exception("Не найдено такой логической операции (" + two + ")");
        };
        if (firstRoman) {
            int a = Integer.parseInt(res);
            if (a < 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            return ArabicToRoman.intToRoman(a);
        } else {
            return res;
        }
    }
}
class ArabicToRoman {
    static int romanToInt(String s) {
        int sum = 0;
        char chs[] = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == 'I') {
                sum += 1;
            }
            if (chs[i] == 'V') {
                if (i != 0 && chs[i - 1] == 'I') {
                    sum += 4 - 1;
                } else {
                    sum += 5;
                }
            }
            if (chs[i] == 'X') {
                if (i != 0 && chs[i - 1] == 'I') {
                    sum += 9 - 1;
                } else {
                    sum += 10;
                }
            }
        }
        return sum;
    }

    static String intToRoman(int a) {
        String[] romans = {"I", "IV", "V", "IX", "X", "XL", "L", "LX", "C", "D"};
        int[] arabics = {1, 4, 5, 9, 10, 40, 50, 60, 100, 500};
        String res = "";
        int n = a;
        while (n > 0) {
            for (int i = 0; i < arabics.length; i++) {
                if (n < arabics[i]) {
                    n -= arabics[i - 1];
                    res += romans[i - 1];
                    break;
                }
            }
        }
        return res;
    }
}
