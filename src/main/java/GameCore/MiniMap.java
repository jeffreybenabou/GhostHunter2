package GameCore;


import ImageHandel.SpriteSheet;
import Objects.Ghost;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MiniMap extends JLabel {

    private SpriteSheet pickHouse;
    private int pickHouseCounter=0;
    private boolean changeLevel = false;
    public static JLabel mainPlayerLabel;
    private boolean loadFuncation=false;
    private  Border blackline;
    private Key key;
    public static ArrayList<JLabel> aghost=new ArrayList<JLabel>();
    public static ArrayList<JLabel> house=new ArrayList<JLabel>();

    public MiniMap() {
        pickHouse = new SpriteSheet((BufferedImage)StaticVariables.houseIconChooseMiniMap);
        setBounds(10, StaticVariables.mainClass.getHeight() - 170, 250, 160);

        setIcon(new ImageIcon(StaticVariables.worldBackGroundScaled.getScaledInstance(getWidth(), getHeight(), 4)));


        setBackground(Color.black);


    }

    public static void setTheMainPlayerLabel(int x,int y){
         mainPlayerLabel=new JLabel();
        mainPlayerLabel.setBounds(x,y,5,5);
        mainPlayerLabel.setBackground(Color.blue);
        mainPlayerLabel.setOpaque(true);

    }

    public void addActionOfMiniMap() {


        new Thread(new Runnable() {
            public void run() {
                setLoadFuncation(false);
                setChangeLevel(false);

                while (!isLoadFuncation()) {

                    addTheHouseLocationToMap();


                    try {
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    if (Ghost.notTheFirstGhost)
                        for (Ghost ghost : StaticVariables.world.getGhostArrayList()) {
                            if (ghost.isVisible()) {
                                setChangeLevel(false);
                                break;
                            } else
                                setChangeLevel(true);


                        }


                    if (isChangeLevel())

                    {
                        levelUp();
                        changeLevel=false;
                    }



                }



            }
        }).start();
    }



        public static JLabel  addGhostToMiniMap(int x,int y) {
            JLabel label=new JLabel();
            label.setBounds(x,y,5,5);
            label.setBackground(Color.red);
            label.setOpaque(true);
            return label;





    }

    private void addTheHouseLocationToMap() {
        if(Ghost.notTheFirstGhost)
        for (int i = 0; i < house.size(); i++) {
            try {
                if(i==StaticVariables.level-1)
                {
                    if(pickHouseCounter*25<pickHouse.getSheet().getRaster().getWidth())
                    house.get(i).setIcon(new ImageIcon(pickHouse.crop(pickHouseCounter*24,0,24,24)));
                    else
                        pickHouseCounter=0;
                    pickHouseCounter++;
                }
                else
                    house.get(i).setIcon(new ImageIcon(StaticVariables.houseIconMiniMap));

                    house.get(i).setVisible(true);
                    house.get(i).setBounds(StaticVariables.world.getHouseArrayList().get(i).getX() / 19+10, StaticVariables.world.getHouseArrayList().get(i).getY() / 32+5,house.get(i).getIcon().getIconWidth(),house.get(i).getIcon().getIconHeight());



            } catch (IndexOutOfBoundsException e) {

                break;

            } catch (NullPointerException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private void levelUp() {

        if (!StaticVariables.world.isGhostAreBeingAdd()) {

            if (isChangeLevel() && Ghost.notTheFirstGhost) {
                new Thread(new Runnable() {
                    public void run() {
                        setKey(new Key());
                        getKey().setVisible(true);
                        StaticVariables.gamePanel.add(getKey());
                        for (JLabel label:getAghost()) {
                            label.setVisible(false);
                        }
                        getAghost().clear();


                        getKey().moveTheKey();
                        Ghost.numberOfDeadGhost = 0;
                    }
                }).start();


            }


        }
    }

    public static void addTheMainPlayerLocationToMap(int xPlayer,int yPlayer){



                    int x;
                    int y;
                    try{
                        y=yPlayer/32+3;
                        x=xPlayer/20+5;

                        mainPlayerLabel.setLocation(x,y);

                    }catch (NullPointerException e)
                    {

                        e.printStackTrace();

                    }






    }

    public void addTheGhostLocationToMap(int position,Point location) {

            JLabel jLabel=new JLabel();
            jLabel.setBounds(location.x,location.y,5,5);
            jLabel.setBackground(Color.RED);
            jLabel.setOpaque(true);
        jLabel.setName(""+position);
            add(jLabel);
            getAghost().add(jLabel);







    }

    public boolean isChangeLevel() {
        return changeLevel;
    }

    public void setChangeLevel(boolean changeLevel) {
        this.changeLevel = changeLevel;
    }

    public boolean isLoadFuncation() {
        return loadFuncation;
    }

    public void setLoadFuncation(boolean loadFuncation) {
        this.loadFuncation = loadFuncation;
    }

    public Border getBlackline() {
        return blackline;
    }

    public void setBlackline(Border blackline) {
        this.blackline = blackline;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public static ArrayList<JLabel> getAghost() {
        return aghost;
    }

    public static void setAghost(ArrayList<JLabel> aghost) {
        MiniMap.aghost = aghost;
    }
}
