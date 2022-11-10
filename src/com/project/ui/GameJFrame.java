package com.project.ui;

import com.project.ui.LoginJFrame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener,ActionListener{
    //GameJFrame is the mian frame of this game
    //Create a two-dimensional array for data storage
    //When loading a picture, it will be loaded according to the data in the two-dimensional array
    int[][] data = new int[4][4];

    //Record the position of the blank square in a 2D array
    int x = 0;
    int y = 0;

    //Define a variable to record the path of the currently displayed image
    String path = "image/animal/animal7/";

    //Define a two-dimensional array that stores the correct data
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    //Define variables to count steps
    int step = 0;


    //Create an object for dropdown menu options
    JMenuItem replayItem = new JMenuItem("Restart Game");
    JMenuItem reLoginItem = new JMenuItem("Change A User");
    JMenuItem closeItem = new JMenuItem("Close Game");

    JMenuItem accountItem = new JMenuItem("Donate");


    public GameJFrame() {
        //Initialize the interface
        initJFrame();

        //Initialize the menu
        initJMenuBar();


        //Initialize data (shuffle)
        initData();

        //Initialize the picture (load the picture according to the result of the shuffle)
        initImage();

        //Display the interface
        this.setVisible(true);

    }


    //Initialize data (shuffle)
    private void initData() {
        //1.Define a one-dimensional array
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //2.Shuffle the order of data in this array
        //Traverse the array, get each element
        //and exchange each element with the data on the machine index
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //get random index
            int index = r.nextInt(tempArr.length);
            //Take each data traversed and exchange it with the data on the machine index
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //4.Add data to a two-dimensional array
        //Traverse the one-dimensional array tempArr to get each element,
        //and add each element to the two-dimensional array in turn
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    //initialize the image
    //When adding pictures, you need to add pictures according to the data managed in the two-dimensional array
    private void initImage() {

        //Clear all pictures that have already appeared
        this.getContentPane().removeAll();

        if (victory()) {
            //Showing victory icon
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203,283,400,200);
            this.getContentPane().add(winJLabel);
        }


        JLabel stepCount = new JLabel("steps" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //The image loaded first is at the top, and the image loaded later is tucked below.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //Get the serial number of the current image to be loaded
                int num = data[i][j];
                //Create a JLabel object (manage container)
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //add a border to the image
                //0: Indicates that the raised picture
                //1: Indicates that the concave picture
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }


        //Add background picture
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);


        //Refresh the interface
        this.getContentPane().repaint();


    }

    private void initJMenuBar() {
        //Create the entire menu object
        JMenuBar jMenuBar = new JMenuBar();
        //Create two options on the menu
        JMenu functionJMenu = new JMenu("Functions");
        JMenu aboutJMenu = new JMenu("Support Us");

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //Add events to items
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //Add function menu and about menu
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //Set the width and height of the interface
        this.setSize(603, 680);
        //Set the title of the interface
        this.setTitle("Puzzle Game ™️️");
        //Set the interface to the top
        this.setAlwaysOnTop(true);
        //Center the interface
        this.setLocationRelativeTo(null);
        //Set close mode
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        //Add keyboard listener events to the entire interface
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //Hot key
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65){
            //Delete all pictures in the interface
            this.getContentPane().removeAll();
            //Load the correct picture
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            //Refresh
            this.getContentPane().repaint();


        }
    }

    //Check victory
    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()){
            return;
        }
        //Check up, down, left, right
        //left：37 up：38 right：39 down：40
        int code = e.getKeyCode();
        System.out.println(code);
        if (code == 37) {
            System.out.println("Left");
            if(y == 3){
                return;
            }
            //Move the number to the right of the blank square to the left
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            //The counter is incremented every time it moves
            step++;
            initImage();

        } else if (code == 38) {
            System.out.println("Up");
            if(x == 3){
                //Indicates that the blank square is already at the bottom,
                //and there is no picture below him that can be moved.
                return;
            }
            //Move the number below the blank square up
            //x, y represent blank squares
            //x + 1, y is the number below the blank square
            //Assign the number below the blank square to the blank square
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            //The counter is incremented every time it moves
            step++;
            initImage();
        } else if (code == 39) {
            System.out.println("Right");
            if(y == 0){
                return;
            }
            //逻辑：
            //Move the number on the left of the blank square to the right
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            //The counter is incremented every time it moves
            step++;
            initImage();
        } else if (code == 40) {
            System.out.println("Down");
            if(x == 0){
                return;
            }

            //Move the number above the blank square to down
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            //The counter is incremented every time it moves
            step++;
            initImage();
        }else if(code == 65){
            initImage();
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }


    // Check whether the data in the data array is the same as the win array
    //If all are the same, return true. else return false
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get the currently clicked item object
        Object obj = e.getSource();

        if(obj == replayItem){
            System.out.println("Restart the Game");
            //Reset step
            step = 0;
            //Shuffle the data in the 2D array again
            initData();
            //reload figure
            initImage();
        }else if(obj == reLoginItem){
            System.out.println("Change User");
            //Close the current game interface
            this.setVisible(false);
            //Open the login interface
            new LoginJFrame();
        }else if(obj == closeItem){
            System.out.println("Close Game");
            //Shut down the virtual machine directly
            System.exit(0);
        }else if(obj == accountItem){
            System.out.println("Donate");

            //Create a dialog object
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/about.png"));
            jLabel.setBounds(0,0,258,258);
            //Add the QR code to the dialog
            jDialog.getContentPane().add(jLabel);
            //Set size
            jDialog.setSize(344,344);
            //Top Dialog
            jDialog.setAlwaysOnTop(true);
            //Center Dialog
            jDialog.setLocationRelativeTo(null);
            //If the pop-up box is not closed,
            // the following opreations are banned
            jDialog.setModal(true);
            //Show dialog
            jDialog.setVisible(true);
        }
    }
}
