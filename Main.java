import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static String GetInput() throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        return reader.readLine();
    }

    static String IntegerToRomanNumeral(int input) throws Exception {
        if (input < 1 || input > 3999)
            throw new Exception("не удалось привести к римским цифрам");
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }

    static class Data {

        static String Operations = "+|-|*|/";
        static String[] Map = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        private String str_a;
        private String str_b;
        public int operation_id = -1;
        public Integer a = -1;
        public Integer b = -1;
        public Integer c = -1;
        public String output = "";
        private boolean is_find_operations;
        public boolean is_success;
        public Boolean isRomanA;
        public Boolean isRomanB;
        public boolean isRoman;
        private String[] parts;
        private String text;

        public Data(String text) {
            this.text = text;
        }

        static int findIndex(String t) {
            int index = -1;
            for (int i = 0; i < Map.length; i++) {
                if (Map[i].equals(t)) {
                    index = i;
                    break;
                }
            }
            return index;
        }

        public void build() throws Exception {
            text = text.trim();
            text = text.replaceAll("\\s+", "");
            if (!(text.contains("+") || text.contains("-") || text.contains("*") || text.contains("/"))) {
                throw new Exception("строка не является математической операцией " + text);
            }
            if (text.contains("+")) {
                str_a = text.substring(0, text.indexOf("+"));
                str_b = text.substring(text.indexOf("+") + 1, text.length());
                operation_id = 0;
                is_find_operations = true;
            }
            if (text.contains("-")) {
                str_a = text.substring(0, text.indexOf("-"));
                str_b = text.substring(text.indexOf("-") + 1, text.length());
                operation_id = 1;
                is_find_operations = true;
            }
            if (text.contains("*")) {
                str_a = text.substring(0, text.indexOf("*"));
                str_b = text.substring(text.indexOf("*") + 1, text.length());
                operation_id = 2;
                is_find_operations = true;
            }
            if (text.contains("/")) {
                str_a = text.substring(0, text.indexOf("/"));
                str_b = text.substring(text.indexOf("/") + 1, text.length());
                operation_id = 3;
                is_find_operations = true;
            }
            if((str_a.contains("+") || str_a.contains("-") || str_a.contains("*") || str_a.contains("/")) ||
                    (str_b.contains("+") || str_b.contains("-") || str_b.contains("*") || str_b.contains("/"))) {
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            if (!is_find_operations || operation_id == -1) {
                throw new Exception("строка не является математической операцией");
            }

            System.out.println("operation_id " + operation_id);
            System.out.println("a " + str_a);
            System.out.println("b " + str_b);

            if (str_a.contains("I") || str_a.contains("V") || str_a.contains("X")) {
                isRomanA = true;
            } else {
                isRomanA = false;
            }

            if (str_b.contains("I") || str_b.contains("V") || str_b.contains("X")) {
                isRomanB = true;
            } else {
                isRomanB = false;
            }

            System.out.println("is_rimsky_a " + isRomanA);
            System.out.println("is_rimsky_b " + isRomanB);

            if (!isRomanA.equals(isRomanB)) {
                throw new Exception("используются одновременно разные системы счисления");
            }
            if (isRomanA) {
                isRoman = true;
                a = findIndex(str_a);
                b = findIndex(str_b);
                if (a.equals(-1) || b.equals(-1)) {
                    throw new Exception("ошибка на вход числа от 1 до 10 включительно");
                }
            } else {
                a = Integer.valueOf(str_a);
                b = Integer.valueOf(str_b);
            }

            if (a.equals(-1) || b.equals(-1)) {
                throw new Exception("строка не является математической операцией");
            }
            switch (operation_id) {
                case 0:
                default:
                    c = a + b;
                    break;
                case 1:
                    c = a - b;
                    if (c < 0 && isRoman) {
                        throw new Exception("в римской системе нет отрицательных чисел");
                    }
                    break;
                case 2:
                    c = a * b;
                    break;
                case 3:
                    c = a / b;
                    break;
            }
            if (isRoman) {
                output = IntegerToRomanNumeral(c);
            } else {
                output = String.valueOf(c);
                System.out.println("c " + c);
            }
            System.out.println("output " + output);
            is_success = true;
        }
    }

    public static void main(String[] args) {
        try {
            String input = GetInput();
            System.out.println(input);
            Data data = new Data(input);
            data.build();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
