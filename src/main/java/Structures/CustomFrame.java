/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structures;

import java.awt.*;
import javax.swing.*;


/**
 *
 * @author rehan
 */
public class CustomFrame extends JFrame {


    public CustomFrame(Boolean isGradient) {
        setSize(900, 550);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(ImagePath.path + "icon.png");
        setIconImage(icon.getImage());
        setResizable(false);

        if (isGradient) {
            setContentPane(new GradientPanel());
        }else{
            setContentPane(new JPanel());
        }

    }

    private class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(0x005B5C);
            Color color3 = new Color(0x1A737D);
            GradientPaint gradient = new GradientPaint(0, 0,Colors.getBaseColor().brighter() , getWidth()*0.95f, getHeight(), Colors.getGoldenColor().darker(), false);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
        }
    }
}

//0x1F3A3D
//        0xA3D8E0