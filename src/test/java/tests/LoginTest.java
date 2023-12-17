package tests;

import login.LoginForm;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class LoginTest {
private ArrayList<Component> components = new ArrayList<>();
    @Test
    void tesztTextfieldAvailable() {
        boolean textFieldAvailable = false;
        listComponents();
        for(Component c:components){
            if (c instanceof JTextField) {
                textFieldAvailable = true;
                break;
            }
        }

        assertTrue(textFieldAvailable);
    }

    @Test
    void tesztSize() {
        LoginForm loginForm = new LoginForm();
        assertEquals(new Dimension(600,400),loginForm.getSize());
    }

    @Test
    void tesztPasswordfieldAvailable() {
        boolean passwordFieldAvailable = false;
        listComponents();
        for(Component c:components){
            if (c instanceof JTextField) {
                passwordFieldAvailable = true;
                break;
            }
        }

        assertTrue(passwordFieldAvailable);
    }

    @Test
    void tesztBtnLoginAvailable() {
        boolean btnLoginAvailable = false;
        listComponents();
        int i=0;
        while(i<components.size()){
            if (components.get(i) instanceof JButton && components.get(i).getName().equals("btnLogin")) {
                btnLoginAvailable = true;
            }
            i++;
        }
        assertTrue(btnLoginAvailable);
    }
    private void listComponents(){
        LoginForm loginForm = new LoginForm();
        for (int i = 0; i < loginForm.getContentPane().getComponentCount(); i++) {
            components.add(loginForm.getContentPane().getComponent(i));

        }
    }
}