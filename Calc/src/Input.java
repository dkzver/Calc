import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input {
    public String read() {
        try {
            return GetInput();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String GetInput() throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        return reader.readLine();
    }
}
