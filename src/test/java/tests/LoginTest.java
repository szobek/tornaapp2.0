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
    private void listComponents(){
        LoginForm loginForm = new LoginForm();
        for (int i = 0; i < loginForm.getContentPane().getComponentCount(); i++) {
            components.add(loginForm.getContentPane().getComponent(i));

        }
    }
}