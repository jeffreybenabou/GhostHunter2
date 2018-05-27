package GameCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainClass extends JFrame  {

    public static Dimension dimension= Toolkit.getDefaultToolkit().getScreenSize();


    public MainClass(){


        setTheJFrame();
        addMouseListener(StaticVariables.world);
        setVisible(true);
    }

    public static void addTheMainMenu() {
        StaticVariables.mainClass.add(StaticVariables.mainMenu);
        StaticVariables.mainMenu.setVisible(true);

        removeTheWorld();

    }
    public static void removeTheMainMenu(){
        StaticVariables.mainMenu.setVisible(false);
        StaticVariables.mainClass.repaint();

    }
    public static void addTheWorld(){
        StaticVariables.mainClass.add(StaticVariables.gamePanel);
        StaticVariables.mainClass.add(StaticVariables.world);
        StaticVariables.world.setVisible(true);
        StaticVariables.gamePanel.setVisible(true);
        removeTheMainMenu();
    }

    public static void removeTheWorld(){
        if(StaticVariables.world!=null)
        StaticVariables.world.setVisible(false);
        StaticVariables.gamePanel.setVisible(false);
        StaticVariables.mainClass.repaint();
    }

    private void setTheJFrame() {
        setBounds(0,0,dimension.width,dimension.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);

    }



}