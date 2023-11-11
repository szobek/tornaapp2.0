    import javax.swing.*;
public class Login {
   private JFrame loginFrame;
    Login(){
        loginFrame=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);

        loginFrame.add(b);//adding button in JFrame

        loginFrame.setSize(400,500);//400 width and 500 height
        loginFrame.setLayout(null);//using no layout managers
        loginFrame.setVisible(true);//making the frame visible
    }

    public static void main(String[] args) {
        new Login();
    }
}