package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;

public class House extends GameObject {

    public House(int i){

        switch (i)
        {
            case 0:
            {
                setIcon(new ImageIcon(StaticVariables.houseNum1));
                setBounds(0, StaticVariables.world.getHeight() - 1200,StaticVariables.houseNum1.getWidth(null),StaticVariables.houseNum1.getHeight(null));
                break;
            }
            case 1:
            {
                setIcon(new ImageIcon(StaticVariables.houseNum2));
                setBounds(0, 0,StaticVariables.houseNum2.getWidth(null),StaticVariables.houseNum2.getHeight(null));
                break;
            }
            case 2:
            {
                setIcon(new ImageIcon(StaticVariables.houseNum3));
                setBounds(StaticVariables.world.getWidth() - 1000, 0,StaticVariables.houseNum3.getWidth(null),StaticVariables.houseNum3.getHeight(null));
                break;
            }

        }
    }
}