package GameCore;

import BackgroundObject.House;
import Objects.Ghost;
import Objects.MainPlayer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LoadGame {

    static Scanner in;
    public LoadGame(String path) {



        try {
            in = new Scanner(new FileReader(path));
            StaticVariables.mainPlayer.setLocation(in.nextInt(), in.nextInt());
            StaticVariables.world.setLocation(in.nextInt(), in.nextInt());
            MainPlayer.getLife().getjProgressBar().setValue(in.nextInt());
            MainPlayer.getLife().getjProgressBar().setString(""+MainPlayer.getLife().getjProgressBar().getValue());
            GamePanel.getjProgressBar().setValue(in.nextInt());
            StaticVariables.sumOfMoney=in.nextInt();
            StaticVariables.level=in.nextInt();
            Ghost.notTheFirstGhost=in.nextBoolean();


            Key.key1=in.nextBoolean();
            Key.key2=in.nextBoolean();
            Key.key3=in.nextBoolean();
            if(Key.key1)
                Key.addKeyToBag(1);
            if(Key.key2)
                Key.addKeyToBag(2);
            if(Key.key3)
                Key.addKeyToBag(3);
            World.userInProgressToOpenHouse=in.nextBoolean();
            if( Ghost.notTheFirstGhost) {
                try {
                    for (int i = 0; i < 3; ) {
                        House house = new House(i);
                        StaticVariables.world.getBackGroundObjects().add(house);
                        StaticVariables.world.getHouseArrayList().add(house);
                        house.setVisible(false);
                        StaticVariables.world.add(house);
                        i++;
                    }
                    for (int i = 0; i <StaticVariables.world.getHouseArrayList().size() ; i++) {
                        StaticVariables.world.getHouseArrayList().get(i).setVisible(true);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                try
                {

                    StaticVariables.world.getFirstGhost().setVisible(false);


                }catch (Exception e)
                {

                }


                StaticVariables.mainMenu.setType(in.next());
                Ghost.difficulty=in.nextInt();

                if( !World.userInProgressToOpenHouse)
                {
                    StaticVariables.world.addGhost();
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }




}
