package com.project.ui;

import javax.swing.*;

import com.project.domain.User;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterJFrame extends JFrame implements MouseListener {

    JButton reset = new JButton();
    JButton register = new JButton();
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField reenterPassword = new JPasswordField();

    // code with register
    public RegisterJFrame() {

        // init the frame
        initJFrame();

        // add content to the frame
        initView();

 
        this.setVisible(true);
    }

    public void initJFrame() {
        this.setSize(488, 500);
        this.setTitle("Register");
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void initView() {
        // 1. add user name
        JLabel usernameText = new JLabel(new ImageIcon("image/register/username.png"));
        usernameText.setBounds(120, 155, 80, 30);
        this.getContentPane().add(usernameText);

        // 2.user name textbox
        username.setBounds(200, 155, 200, 30);
        this.getContentPane().add(username);

        // 3.add code name
        JLabel passwordText = new JLabel(new ImageIcon("image/register/password.png"));
        passwordText.setBounds(120, 205, 80, 30);
        this.getContentPane().add(passwordText);

        // 4.add password input
        password.setBounds(200, 205, 200, 30);
        this.getContentPane().add(password);

        // 5.reenterpassword input
        JLabel reenterPasswordText = new JLabel(new ImageIcon("image/register/reenter.png"));
        reenterPasswordText.setBounds(120, 255, 80, 30);
        this.getContentPane().add(reenterPasswordText);

        reenterPassword.setBounds(200, 255, 200, 30);
        this.getContentPane().add(reenterPassword);

        // 5.Add register button
        register.setBounds(123, 330, 128, 47);
        register.setIcon(new ImageIcon("image/register/register.png"));
        // remove border
        register.setBorderPainted(false);
        // remove backgroud
        register.setContentAreaFilled(false);
        // bind mouse click event to register
        register.addMouseListener(this);
        this.getContentPane().add(register);

        // 6.Add register button
        reset.setBounds(256, 330, 128, 47);
        reset.setIcon(new ImageIcon("image/register/reset.png"));

        reset.setBorderPainted(false);

        reset.setContentAreaFilled(false);
        // bind mouse click event to reset
        reset.addMouseListener(this);
        this.getContentPane().add(reset);

        // 7.add background
        JLabel background = new JLabel(new ImageIcon("image/register/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }

    // Click
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == register) {
            System.out.println("Click the register button");

            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String reenterPasswordInput = reenterPassword.getText();
          
            // create a user class
            User userInfo = new User(usernameInput, passwordInput);
            System.out.println("The input username is " + usernameInput);
            System.out.println("The input password is " + passwordInput);
            
            
            if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                // check if the username or password is empty
                System.out.println("Username or Password is empty");

                // call showJDialog
                showJDialog("Username or Password is empty");

            }else if(reenterPasswordInput.length()==0){
         
                System.out.println("Please enter the password again");

                // call showJDialog
                showJDialog("Please enter the password again");
            }else if (!reenterPasswordInput.equals(passwordInput)) {
                showJDialog("The password are not matched");
                System.out.println(reenterPasswordInput);
                System.out.println(passwordInput);

            } else if (contains(userInfo)) {
                System.out.println("The user is already exist ");
                // Close the current page
                // Open the main page
                this.setVisible(false);
                new LoginJFrame();
            } else {
                LoginJFrame.allUsers.add(userInfo);
                System.out.println("Register sucessful!");
                showJDialog("Register sucessful!");
                this.setVisible(false);
                new LoginJFrame();
            }
        } else if (e.getSource() == reset) {
            username.setText("");
            password.setText("");
            reenterPassword.setText("");

        } 
    }

    public void showJDialog(String content) {
        // create a dialog
        JDialog jDialog = new JDialog();

        jDialog.setSize(300, 150);

        jDialog.setAlwaysOnTop(true);

        jDialog.setLocationRelativeTo(null);

        jDialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setBounds(50, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jDialog.setVisible(true);
    }

    // mouse pressed event
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image/register/registerpressed.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("image/register/resetpressed.png"));
        }
    }

    // mouse released event
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image/register/register.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("image/register/reset.png"));
        }
    }

    // mouse enter event
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    // mouse move out event
    @Override
    public void mouseExited(MouseEvent e) {

    }

    // check if user is in the set
    public boolean contains(User userInput) {
        for (int i = 0; i < LoginJFrame.allUsers.size(); i++) {
            User rightUser = LoginJFrame.allUsers.get(i);
            if (userInput.getUsername().equals(rightUser.getUsername())
                    && userInput.getPassword().equals(rightUser.getPassword())) {

                return true;
            }
        }

        return false;
    }

}
