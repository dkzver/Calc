public class Program {
    private final Output output = new Output();
    private final Data data = new Data();
    private String text;
    private String str_a;
    private String str_b;
    private boolean is_find_operations;
    public Boolean isRomanA;
    public Boolean isRomanB;
    public boolean is_success;


    public Program(String text) {
        this.text = text;
        clearText();
    }

    private void clearText() {

        text = text.trim();
        text = text.replaceAll("\\s+", "");
    }

    public void run() throws Exception {
        validateOperation(true);
        findOperation();
        validateOperation(false);
        checkRoman();
        checkValues();
        data.calc();
        if (data.isRoman) {
            output.write(Helper.IntegerToRomanNumeral(data.c));
        } else {
            output.write(String.valueOf(data.c));
        }
        is_success = true;
    }

    private void validateOperation(Boolean isFirst) throws Exception {
        if (isFirst) {
            if (!(text.contains("+") || text.contains("-") || text.contains("*") || text.contains("/"))) {
                throw new Exception("строка не является математической операцией " + text);
            }
        } else {
            if ((str_a.contains("+") || str_a.contains("-") || str_a.contains("*") || str_a.contains("/")) ||
                    (str_b.contains("+") || str_b.contains("-") || str_b.contains("*") || str_b.contains("/"))) {
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            if (!is_find_operations || data.operation_id == -1) {
                throw new Exception("строка не является математической операцией");
            }
        }
    }

    private void findOperation() {

        if (text.contains("+")) {
            str_a = text.substring(0, text.indexOf("+"));
            str_b = text.substring(text.indexOf("+") + 1, text.length());
            data.operation_id = 0;
            is_find_operations = true;
        }
        if (text.contains("-")) {
            str_a = text.substring(0, text.indexOf("-"));
            str_b = text.substring(text.indexOf("-") + 1, text.length());
            data.operation_id = 1;
            is_find_operations = true;
        }
        if (text.contains("*")) {
            str_a = text.substring(0, text.indexOf("*"));
            str_b = text.substring(text.indexOf("*") + 1, text.length());
            data.operation_id = 2;
            is_find_operations = true;
        }
        if (text.contains("/")) {
            str_a = text.substring(0, text.indexOf("/"));
            str_b = text.substring(text.indexOf("/") + 1, text.length());
            data.operation_id = 3;
            is_find_operations = true;
        }
    }

    private void checkRoman() throws Exception {
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
        if (!isRomanA.equals(isRomanB)) {
            throw new Exception("используются одновременно разные системы счисления");
        }
    }

    private void checkValues() throws Exception {
        if (isRomanA) {
            data.isRoman = true;
            data.a = Helper.findIndex(str_a);
            data.b = Helper.findIndex(str_b);
            if (data.a.equals(-1) || data.b.equals(-1)) {
                throw new Exception("ошибка на вход числа от 1 до 10 включительно");
            }
        } else {
            data.a = Integer.valueOf(str_a);
            data.b = Integer.valueOf(str_b);
            if(data.a > 10 || data.b > 10) {
                throw new Exception("ошибка на вход числа от 1 до 10 включительно");
            }
        }

        if (data.a.equals(-1) || data.b.equals(-1)) {
            throw new Exception("строка не является математической операцией");
        }
    }
}
