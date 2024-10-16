package Views.Customer;

import Structures.Colors;
import Structures.CustomField;
import Structures.CustomFrame;
import Structures.ImagePath;
import Views.CustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerLoginScreen extends CustomFrame {

    // Declare member variables for the UI components
    private CustomButton loginButton;
    private CustomField idField;
    private CustomField cnicField;
    private CustomButton backButton;
    public CustomerLoginScreen() {
        super(false);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(400, 550));
        imagePanel.setLayout(new BorderLayout());
        ImageIcon bgImage = new ImageIcon(ImagePath.path + "Hero.jpg");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(bgImage.getImage().getScaledInstance(400, 580, java.awt.Image.SCALE_SMOOTH)));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);

        addForm();

        setTitle("Customer Login");
        setSize(800, 600);
        setVisible(true);
    }

    private void addForm() {
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setOpaque(false);
        wrapperPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("Customer Login");
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headingLabel.setForeground(Colors.getBaseColor());
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(headingLabel);

        formPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // ID panel
        JPanel idPanel = new JPanel(new BorderLayout());
        idPanel.setBorder(null);
        idPanel.setOpaque(false);

        //ID field
        idField = new CustomField("text", 23 , 30);

        JLabel idLabel = new JLabel("Enter your ID: ");
        idLabel.setForeground(Colors.getBaseColor());
        idLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        idLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        idField.setFont(new Font("Arial", Font.PLAIN, 13));
        idPanel.add(idLabel, BorderLayout.NORTH);
        idPanel.add(idField, BorderLayout.WEST);
        idPanel.setBorder(new EmptyBorder(0, 0, 15, 0));



        // CNIC panel
        JPanel cnicPanel = new JPanel(new BorderLayout());
        cnicPanel.setBorder(null);
        cnicPanel.setOpaque(false);
        cnicField = new CustomField("text",23,30);
        JLabel cnicLabel = new JLabel("Enter your CNIC: ");
        cnicLabel.setForeground(Colors.getBaseColor());
        cnicLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        cnicLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        cnicPanel.add(cnicLabel, BorderLayout.NORTH);
        cnicPanel.add(cnicField, BorderLayout.CENTER);
        cnicPanel.setBorder(new EmptyBorder(0, 0, 20, 0));


        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        //  Login button
         loginButton = new CustomButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));

        //  Back button
        backButton = new CustomButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));


        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalGlue());








        formPanel.add(idPanel);
        formPanel.add(cnicPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(buttonPanel);
//        formPanel.add(BackbuttonPanel);
        wrapperPanel.add(formPanel);
        add(wrapperPanel, BorderLayout.CENTER);
    }

    public void SayForCredentials() {
        JOptionPane.showMessageDialog(this, "Please Enter all Credentials");
    }

    public void WrongCredentials() {
        JOptionPane.showMessageDialog(this, "Wrong Credentials");
    }

    public void Confirmation() {
        JOptionPane.showMessageDialog(this, "You have been Logged In");
    }

    public void addLoginButtonListener(ActionListener e) {
        loginButton.addActionListener(e);
    }

    public String getCNIC() {
        return this.cnicField.getText();
    }

    public String getID() {
        return this.idField.getText();
    }
    public void addBackButtonListener(ActionListener e) {
        backButton.addActionListener(e);
    }
    public void ExitScreen() {
        this.dispose();
    }



}
