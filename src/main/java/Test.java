import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Test extends JFrame {


    private JPanel panel1;
    private JTextArea txtwelcome;

    public static void main(String[] args) {
        new Test();
    }
    Test(){
        super();
        String fileName = "src/main/resources/welcome.txt";

        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName, StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            txtwelcome.setLineWrap(true);
            txtwelcome.setText(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
