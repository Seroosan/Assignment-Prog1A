/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_1_semester_1_prog5121;

/**
 *
 * @author Seroosan
 */
import static com.sun.javafx.util.Utils.contains;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LoginPage implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel messageLabel = new JLabel();
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	LoginPage(HashMap<String,String> loginInfoOriginal){
		
		logininfo = loginInfoOriginal;
		
		userIDLabel.setBounds(50,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);
		
		messageLabel.setBounds(125,250,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,12));
		
		userIDField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		
		loginButton.setBounds(125,200,100,25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		resetButton.setBounds(225,200,100,25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
            if(e.getSource()==resetButton) {
                userIDField.setText("");
                userPasswordField.setText("");
            }

            if(e.getSource()==loginButton) {
                String userID = userIDField.getText();
                String password = String.valueOf(userPasswordField.getPassword());
                
                loginUser(userID, password);
                
                 WelcomePage welcomePage = new WelcomePage(userID, returnLoginStatus(loginUser(userID, password)));
            }
	}
        public String returnLoginStatus(boolean state){
            if(state){
                return "A successful login";
            }
            return "A Failed login";            
        }
        public boolean loginUser(String userID, String password){
            

            if(checkUserName(userID)){
                if(checkPasswordComplexity(password)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText(registerUser(userID, password));
                    frame.dispose();
                }else{
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText(registerUser(userID, password));

                }
                return true;
            }else{
                messageLabel.setForeground(Color.red);
                messageLabel.setText(registerUser(userID, password));

            }
            return  false;
        }
       public String registerUser(String userID, String password){
             String message = "";
             if(checkUserName(userID)){
                    if(checkPasswordComplexity(password)){
                        message = "The two above conditions have been met and the user has been registered successfully.";
             
                    }else{
                        message = "The password does not meet the complexity requirements.";

                    }
                }else{
                    message ="The username is incorrectly formatted";
                }
             return message;
        }
        public boolean checkUserName(String username){ 
            return username.contains("_");
        }
        public boolean checkPasswordComplexity(String password){
            int counter = 0; 
            int correctComplexity = 4; 
                                    

            if(password.length() >=8){
                counter ++;
            }
            if(containsUpperCaseLetter(password)){
                counter ++;

            }
           
            if(containsSpecialCharater(password)){
                counter ++; 

            }
            if(containsNumber(password)){
                counter ++;

            }
            return counter == 4;
        }
        
    public boolean containsUpperCaseLetter(String s){
        for(int i=0;i<s.length();i++){
            if(Character.isUpperCase(s.charAt(i))){
                    return true;
            }
        }
        return false;
    }
    public boolean containsSpecialCharater(String s){
        char[] myArray = {'!', '@', '#','$','%','^','&','*','(',')'};

        for(int i=0;i<s.length();i++){
           for(int j=0;j < myArray.length ; j++){
               if( s.charAt(i) == myArray[j]){
                     return true;

               }
           }
        }
        return false;
    }
    public boolean containsNumber(String s){
        char[] myArray = {'1', '2', '3','4','5','6','7','8','9','0'};

        for(int i=0;i<s.length();i++){
           for(int j=0;j < myArray.length ; j++){
               if( s.charAt(i) == myArray[j]){
                     return true;

               }
           }
        }
        return false;
    }
}