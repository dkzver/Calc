import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        try {
            Input input = new Input();
            Program program = new Program(input.read());
            program.run();
            /*List<String> stringList = new ArrayList<>();
            stringList.add("II + I");
            stringList.add("VI / I");
//            stringList.add("II - II");
            stringList.add("1 + 2 + 3");
            for (int i = 0; i < stringList.size(); i++) {
                Program program = new Program(stringList.get(i));
                program.run();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
