package BackgroundObject;

import GameCore.GameObject;
import GameCore.MiniMap;
import GameCore.StaticVariables;

import javax.swing.*;
import java.awt.*;

public class House extends GameObject {

    private int houseNumber;
    private JLabel houseEntrance;

    public static boolean houseNumber1Open=false,houseNumber2Open=false,houseNumber3Open=false;
    public House(int houseNumber){
        this.houseNumber=houseNumber;
        pickHouse(houseNumber);
        addHouseEntrance();

    }

    private void addHouseEntrance(){
        houseEntrance=new JLabel();
        houseEntrance.setName("houseEntrance");
        houseEntrance.setVisible(false);
        houseEntrance.addMouseListener(StaticVariables.world);

        switch (houseNumber)
        {
            case 0:
            {


                houseEntrance.setIcon(new ImageIcon(StaticVariables.houseEnter.getScaledInstance(200,200,Image.SCALE_SMOOTH)));
                houseEntrance.setBounds(getX()+getWidth()+300,getY()+250,200,200);
                StaticVariables.world.add(houseEntrance);



                break;
            }
            case 1:
            {
                houseEntrance.setIcon(new ImageIcon(StaticVariables.houseEnter.getScaledInstance(200,200,Image.SCALE_SMOOTH)));
                houseEntrance.setBounds(getWidth()+100,getHeight()+100,200,200);
                StaticVariables.world.add(houseEntrance);

               break;
            }
            case 2:
            {
                houseEntrance.setIcon(new ImageIcon(StaticVariables.houseEnter.getScaledInstance(200,200,Image.SCALE_SMOOTH)));
                houseEntrance.setBounds(getX()-getWidth(),getHeight()-100,200,200);
                StaticVariables.world.add(houseEntrance);

                break;
            }
        }
        System.out.println(houseEntrance.getLocation());
    }
    private void pickHouse(int houseNumber) {
        switch (houseNumber)
        {
            case 0:
            {
//                down left
                setName(""+1);
                setIcon(new ImageIcon(StaticVariables.houseNum1));
                setBounds(0, StaticVariables.world.getHeight() - 1200,StaticVariables.houseNum1.getWidth(null),StaticVariables.houseNum1.getHeight(null));
                break;
            }
            case 1:
            {
//                left up
                setName(""+2);
                setIcon(new ImageIcon(StaticVariables.houseNum2));
                setBounds(0, 0,StaticVariables.houseNum2.getWidth(null),StaticVariables.houseNum2.getHeight(null));
                break;
            }
            case 2:
            {
//                right up
                setName(""+3);
                setIcon(new ImageIcon(StaticVariables.houseNum3));
                setBounds(StaticVariables.world.getWidth() - 1000, 0,StaticVariables.houseNum3.getWidth(null),StaticVariables.houseNum3.getHeight(null));
                break;
            }

        }
        MiniMap.house.add(new JLabel(new ImageIcon(StaticVariables.houseIconMiniMap)));
        MiniMap.house.get(MiniMap.house.size()-1).setVisible(false);
        StaticVariables.miniMap.add( MiniMap.house.get(MiniMap.house.size()-1));


    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public JLabel getHouseEntrance() {
        return houseEntrance;
    }

    public void setHouseEntrance(JLabel houseEntrance) {
        this.houseEntrance = houseEntrance;
    }
}
