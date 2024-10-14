package Views.Customer;

import Structures.CustomFrame;
import Structures.ImagePath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerLoginScreen extends CustomFrame {


    // Declare member variables for the UI components
    private JButton loginButton;
    private JTextField idField;
    private JTextField cnicField;

    public CustomerLoginScreen() {
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


        setTitle("Customer Login");
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void addForm() {
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel headingLabel = new JLabel("Customer Login");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(headingLabel);

        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ID panel
        JPanel idPanel = new JPanel(new BorderLayout());
        idField = new JTextField(15); // Initialize idField
        JLabel idLabel = new JLabel("Enter your ID: ");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        idField.setFont(new Font("Arial", Font.PLAIN, 13));
        idPanel.add(idLabel, BorderLayout.WEST);
        idPanel.add(idField, BorderLayout.EAST);
        idPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        //CNIC panel
        JPanel cnicPanel = new JPanel(new BorderLayout());
        cnicField = new JTextField(15); // Initialize cnicField
        JLabel cnicLabel = new JLabel("Enter your CNIC: ");
        cnicLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        cnicField.setFont(new Font("Arial", Font.PLAIN, 13));
        cnicPanel.add(cnicLabel, BorderLayout.WEST);
        cnicPanel.add(cnicField, BorderLayout.EAST);
        cnicPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        //loginButton
        loginButton = new JButton("Login");
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 13));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the form panel
        formPanel.add(idPanel);
        formPanel.add(cnicPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(loginButton);

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



}
