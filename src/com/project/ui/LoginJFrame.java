package com.project.ui;

import com.project.domain.User;
import com.project.util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {

    static ArrayList<User> allUsers = new ArrayList<>();
    static {
        allUsers.add(new User("zhangsan","123"));
        allUsers.add(new User("lisi","1234"));
    }


    JButton login = new JButton();
    JButton register = new JButton();

    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    //The right auth code
    JLabel rightCode = new JLabel();
    public LoginJFrame() {
        //init the frame
        initJFrame();
        //add content to the frame
        initView();
        //show
        this.setVisible(true);

    }


    public void initJFrame() {
        this.setSize(470, 420);
        this.setTitle("The Puzzle Game V1.0 Login");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
    }


    public void initView() {
        //1. add user name
        JLabel usernameText = new JLabel(new ImageIcon("image/login/Username.png"));
        usernameText.setBounds(120, 135, 80, 30);
        this.getContentPane().add(usernameText);

        //2.user name textbox

        username.setBounds(200, 135, 200, 30);
        this.getContentPane().add(username);

        //3.add code name
        JLabel passwordText = new JLabel(new ImageIcon("image/login/Password.png"));
        passwordText.setBounds(120, 185, 80, 30);
        this.getContentPane().add(passwordText);

        //4.add password input
        password.setBounds(200, 185, 200, 30);
        this.getContentPane().add(password);



        JLabel codeText = new JLabel(new ImageIcon("image/login/Auth.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //auth code textbox
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);


        String codeStr = CodeUtil.getCode();
        //set content
        rightCode.setText(codeStr);
        //Bind mouse click event
        rightCode.addMouseListener(this);
        //location and weight height
        rightCode.setBounds(300, 256, 50, 30);
        //add to the interface
        this.getContentPane().add(rightCode);


        //5.Add login button添加登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image/login/login.png"));
       
        login.setBorderPainted(false);
     
        login.setContentAreaFilled(false);
        //bind mouse click event to login
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //6.Add register button
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image/login/register.png"));
        //remove border
        register.setBorderPainted(false);
        //remove backgroud
        register.setContentAreaFilled(false);
        //bind mouse click event to register
        register.addMouseListener(this);
        this.getContentPane().add(register);


        //7.add background 
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }


 


    //Click
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == login) {
            System.out.println("Click the login button");
        
            String usernameInput = username.getText();
            String passwordInput = password.getText();
      
            String codeInput = code.getText();

            //create a user class
            User userInfo = new User(usernameInput, passwordInput);
            System.out.println("The input username is " + usernameInput);
            System.out.println("The input password is " + passwordInput);

            if (codeInput.length() == 0) {
                showJDialog("The athu code can't be empty");
            } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                //check if the username or password is empty
                System.out.println("Username or Password is empty");

                //call showJDialog
                showJDialog("Username or Password is empty");


            } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("The auth code is incorrect");
            } else if (contains(userInfo)) {
                System.out.println("Game Starting ....");
                //Close the current login page
                // Open the main page 
                this.setVisible(false);
                new GameJFrame();
            } else {
                System.out.println("incorrect username or password");
                showJDialog("incorrect username or password");
            }
        } else if (e.getSource() == register) {
            System.out.println("click the register button");
            this.setVisible(false);
            new RegisterJFrame();

        } else if (e.getSource() == rightCode) {
            System.out.println("change the auth code");
            //Get a new auth code
            String code = CodeUtil.getCode();
            rightCode.setText(code);
        }
    }


    public void showJDialog(String content) {
        //create a dialog
        JDialog jDialog = new JDialog();

        jDialog.setSize(300, 150);

        jDialog.setAlwaysOnTop(true);
 
        jDialog.setLocationRelativeTo(null);
     
        jDialog.setModal(true);

       
        JLabel warning = new JLabel(content);
        warning.setBounds(50, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        jDialog.setVisible(true);
    }

    // mouse pressed event
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image/login/loginpressed.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image/login/registerpressed.png"));
        }
    }


    // mouse released event
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image/login/login.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image/login/register.png"));
        }
    }

    //mouse enter event
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //mouse move out event
    @Override
    public void mouseExited(MouseEvent e) {

    }

    //check if user is in the set
    public boolean contains(User userInput){
        for (int i = 0; i < allUsers.size(); i++) {
            User rightUser = allUsers.get(i);
            if(userInput.getUsername().equals(rightUser.getUsername()) && userInput.getPassword().equals(rightUser.getPassword())){
    
                return true;
            }
        }
      
        return false;
    }


}
