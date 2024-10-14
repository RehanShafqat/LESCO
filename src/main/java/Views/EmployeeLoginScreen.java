package Views;

import Structures.CustomFrame;
import Structures.ImagePath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeLoginScreen extends CustomFrame {

    // Declare buttons as member variables
    private JButton loginButton;
    private JTextField userName;
    private JTextField password;

    public EmployeeLoginScreen() {
        setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(400, 550));
        imagePanel.setLayout(new BorderLayout());
        ImageIcon bgImage = new ImageIcon(ImagePath.path + "image2.png");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(bgImage.getImage().getScaledInstance(400, 550, java.awt.Image.SCALE_SMOOTH)));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);

        addForm();


        setTitle("Employee Login");
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void addForm() {
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("Employee Login");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(headingLabel);

        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel userNamePanel = new JPanel(new BorderLayout());
        userName = new JTextField(15); // Initialize userName
        JLabel userLabel = new JLabel("Enter your name: ");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        userName.setFont(new Font("Arial", Font.PLAIN, 13));
        userNamePanel.add(userLabel, BorderLayout.WEST);
        userNamePanel.add(userName, BorderLayout.EAST);
        userNamePanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        JPanel passwordPanel = new JPanel(new BorderLayout());
        password = new JTextField(15); // Initialize password
        JLabel passwordLabel = new JLabel("Enter your password: ");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        password.setFont(new Font("Arial", Font.PLAIN, 13));
        passwordPanel.add(passwordLabel, BorderLayout.WEST);
        passwordPanel.add(password, BorderLayout.EAST);
        passwordPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        loginButton = new JButton("Login"); // Initialize loginButton
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 13));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(userNamePanel);
        formPanel.add(passwordPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(loginButton);

        wrapperPanel.add(formPanel);
        add(wrapperPanel, BorderLayout.CENTER);
    }

    public String getUserName(){
        return this.userName.getText();
    }
    public String getPassword(){
        return this.password.getText();
    }
    public void addLoginButtonListener(ActionListener e){
        loginButton.addActionListener(e);
    }
    public void SayForCredentials() {
        JOptionPane.showMessageDialog(this,"Please Enter all Credentials");
    }
    public void WrongCredentials() {
        JOptionPane.showMessageDialog(this,"Wrong Credentials");
    }
    public void Confirmation () {
        JOptionPane.showMessageDialog(this,"You have been Logged In");
    }





}
