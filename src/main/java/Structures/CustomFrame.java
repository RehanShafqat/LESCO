/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structures;

import java.awt.MediaTracker;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**
 *
 * @author rehan
 */
public class CustomFrame extends JFrame {
    
    
   public CustomFrame(){
        setSize(900,550);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(ImagePath.path+"icon.png");
        setIconImage(icon.getImage());
        setResizable(false);
    }
}
