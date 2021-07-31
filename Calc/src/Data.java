public class Data {


    static String Operations = "+|-|*|/";

    public Integer a = -1;
    public Integer b = -1;
    public Integer c = -1;
    public int operation_id = -1;
    public boolean isRoman;

    public void calc() throws Exception {

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
    }
}
