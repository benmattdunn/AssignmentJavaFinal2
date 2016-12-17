package GUI;

import DB.DBConnection;
import Session.Session;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    DBConnection dbConnectionHandler;  //session holder, was not static. 
    
    //areas that need identity for login. 
    JTextField txtUserName;
    JTextField txtPassword;
    JButton btnLogin;
    ActionListener loginButtonActionListener; 
    Session session; 
    
    
    public LoginGUI() {
        
        dbConnectionHandler = new DBConnection();
        // basic login frame
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setLayout(new FlowLayout());
        
        // create login panel
        JPanel loginPanel = new JPanel();
        // create labels and text fields
        JLabel lblUserName = new JLabel("Login: ");
        JLabel lblPassword = new JLabel("Password: ");
        txtUserName = new JTextField(10);
        txtPassword = new JTextField(10);
        // create buttons
        btnLogin = new JButton("Login");
        
        //inline to prevent static errors, not a reference object.
        loginButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                session = dbConnectionHandler.loginRequest(txtUserName.getText(), txtPassword.getText());
                validateSession(session); 
            }
        }; 
        btnLogin.addActionListener(loginButtonActionListener);
        
        JButton btnExit = new JButton("Exit");
        
        // add components to login panel
        loginPanel.add(lblUserName);
        loginPanel.add(txtUserName);
        loginPanel.add(lblPassword);
        loginPanel.add(txtPassword);
        loginPanel.add(btnLogin);
        loginPanel.add(btnExit);
        
        add(loginPanel);        
    }
    
    public void validateSession (Session session) {
        if (session.isSuccess()) {
            GUI mainSession = new GUI(session); 
            this.setVisible(false);
            //while blocking due to code, it will ultimately end. 
            mainSession.setVisible(true);
            this.dispose(); 
        } else {
            String message = "Something has gone wrong with the login! \n ?" + session.getFailureStatusMessage()
                    + "\n Would you like to quit?";
            String title = "Login Error";
            // display the JOptionPane showConfirmDialog
            int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
    }
    
    
    
    
    
    
}
