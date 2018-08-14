package GameCore;

import ImageHandel.ImageLoader;
import Objects.MainPlayer;
import sound.Sound;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class MainMenu extends JLabel implements MouseListener {

    public static LoadGame loadGame=null;
    public static String pathToFile,pathToImage;
    public static boolean wantToLoadGame=false;
    int optionChoose;
    private JLabel loadingIcon;
    private ImageLoader imageLoader;
    private Sound backgroundSound;
    private JLabel watingLabel;
    private TextField textField;
    private JButton confirm,start,exit;
    private String type="";
    private boolean hover=false;
    private ArrayList<ImageIcon> aMale,aFemale;
    private  JLabel male,female;
    private Border border;
    private JLabel chooseThePlayer;
    private int index=0;





    public MainMenu(){
        imageLoader=new ImageLoader();
        addSound();
        try
        {
            setIcon(new ImageIcon(StaticVariables.mainMenuBackGround.getScaledInstance(MainClass.dimension.width,MainClass.dimension.height,Image.SCALE_SMOOTH)));
            setBounds(0, 0, MainClass.dimension.width, MainClass.dimension.height);
            ChooseThePlayer();
            for (int i = 0; i <2 ; i++) {
                createButton(i);
            }
            setVisible(false);


        }catch (Exception e)
        {

            e.printStackTrace();

        }




    }

    private void addSound() {
        backgroundSound=new Sound();
        backgroundSound.playSound(Sound.path.get(2));
    }


    public void ChooseThePlayer(){
        chooseThePlayer=new JLabel();
        chooseThePlayer.setVisible(false);
        chooseThePlayer.addMouseListener(this);
        add(chooseThePlayer);
        new Thread(new Runnable() {
            public void run() {
                aMale=new ArrayList<ImageIcon>();
                aFemale=new ArrayList<ImageIcon>();
                female=new JLabel();
                male=new JLabel();
                addPlayerLabel();
                new Thread(new Runnable() {
                    public void run() {

                        new Thread(new Runnable() {
                            public void run() {
// TODO: 21/07/2018 change the image to main player attack

                                imageLoader.addImageOfObject(32,"Photos/character/male/attack/down/",aMale);

                                    male.setIcon(aMale.get(0));




                            }
                        }).start();
                        new Thread(new Runnable() {
                            public void run() {

                                imageLoader.addImageOfObject(43,"Photos/character/female/attack/down/",aFemale);
                                    female.setIcon(aFemale.get(0));




                            }
                        }).start();
                    }
                }).start();




                chooseThePlayer.setBounds(250,100,getWidth()/2+200,getHeight()/2+200);
                chooseThePlayer.setOpaque(true);
                chooseThePlayer.setBackground(new Color(0, 0, 0, 254));
                border  = BorderFactory.createLineBorder(Color.red);

                chooseThePlayer.setBorder(border);
                chooseThePlayer.setForeground(Color.red);
                setBorder(null);

            }
        }).start();

    }

    private void addPlayerLabel() {

        chooseThePlayer.add(female);
        chooseThePlayer.add(male);
        female.addMouseListener(this);
        male.addMouseListener(this);
        male.setBounds(480,100,380,400);
        male.setName("male");
        male.setHorizontalAlignment(JLabel.CENTER);
        female.setBounds(20,100,380 ,400);
        female.setHorizontalAlignment(JLabel.RIGHT);
        female.setName("female");

        female.setOpaque(true);

        male.setOpaque(true);
        getPlayerName();
    }

    public void moveTheCharacter(final JLabel label, final ArrayList<ImageIcon> vector) {

        new Thread(new Runnable() {
            public void run() {
                while (hover) {
                    try {


                        index++;
                        if (index >= vector.size())
                            index = 0;
                        label.setIcon((vector.get(index)));
                        Thread.sleep(40);

                    } catch (NullPointerException e) {


                        break;

                    } catch (ArrayIndexOutOfBoundsException e) {


                        break;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }


    private void getPlayerName() {

        textField=new TextField();
         confirm=new JButton();
        confirm.setBounds(390,540,90,30);
        confirm.setText("confirm");
        confirm.setName("confirm");
        confirm.addMouseListener(this);
        chooseThePlayer.add(confirm);

        textField.setName("choose");

        textField.setBounds(340,50,200,20);
        JLabel label=new JLabel(" enter your character name here :");
        label.setForeground(Color.red);
        label.setBounds(340,20,200,20);
        chooseThePlayer.add(label);

        chooseThePlayer.add(textField);



    }

    private void createButton(int i) {
        JButton button=null;
        try
        {
            switch (i)
            {
                case 0:{

                    start=new JButton(new ImageIcon(StaticVariables.startButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                    start.setBounds(550-MainClass.differenceX, 100-MainClass.differenceY, start.getIcon().getIconWidth(), start.getIcon().getIconHeight());
                    start.setName("start");
                    add(start);
                    button=start;
                    break;
                }
                case 1:{
                    exit=new JButton(new ImageIcon(StaticVariables.exitButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                    exit.setBounds(550-MainClass.differenceX, 550-MainClass.differenceY, exit.getIcon().getIconWidth(), exit.getIcon().getIconHeight());
                    exit.setName("exit");
                    add(exit);
                    button=exit;
                    break;
                }


            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }



        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addMouseListener(this);


    }



    private void addTheWaitingLabel(){






        File file = new File(""+ImageLoader.class.getClassLoader().getResource("save/").getPath(),""+textField.getText()+".txt");
        pathToFile=ImageLoader.class.getClassLoader().getResource("save/").getPath()+textField.getText()+".txt";
        pathToImage=ImageLoader.class.getClassLoader().getResource("save/").getPath()+textField.getText()+".png";
        try {
            if(!file.createNewFile()){
                askToLoadAGame();
                if(optionChoose==2)
                    return;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }











        new Thread(new Runnable() {
            public void run() {
                try
                {




                    loadingIcon=new JLabel("loading.....");
                    loadingIcon.setHorizontalTextPosition(JLabel.CENTER);
                    loadingIcon.setFont(new Font("Serif", Font.PLAIN, 45));
                    loadingIcon.setForeground(Color.red);
                    loadingIcon.setBounds(getWidth()-250-MainClass.differenceX,getHeight()-80-MainClass.differenceY,400-MainClass.differenceX,80-MainClass.differenceY);
                    watingLabel =new JLabel(new ImageIcon(StaticVariables.watingLabel.getScaledInstance(MainClass.dimension.width,MainClass.dimension.height,Image.SCALE_SMOOTH)));
                    watingLabel.setBounds(0,0,MainClass.dimension.width,MainClass.dimension.height);
                    watingLabel.add(loadingIcon);
                    StaticVariables.mainClass.add(watingLabel);
                    MainClass.removeTheMainMenu();
                }catch (Exception e)
                {
                    e.printStackTrace();

                }

                new Thread(new Runnable() {

                    public void run() {
                        MainPlayer.makeNewElements(type);
                        while (true)
                        {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(StaticVariables.worldBackGround==null)
                            {
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {

                                try
                                {
                                    StaticVariables.mainPlayer=new MainPlayer();
                                    StaticVariables.world=new World();
                                    StaticVariables.world.setIcon(new ImageIcon(StaticVariables.worldBackGround));

                                    StaticVariables.miniMap=new MiniMap();
                                    StaticVariables.gamePanel=new GamePanel();
                                    StaticVariables.gamePanel.add(StaticVariables.miniMap);
                                    MainClass.addTheWorld();
                                    watingLabel.setVisible(false);
                                    StaticVariables.miniMap.addActionOfMiniMap();
                                    if(wantToLoadGame)
                                    loadGame=new LoadGame(pathToFile);
                                }catch (Exception e)
                                {

e.printStackTrace();
                                }



                                break;
                            }
                        }

                    }
                }).start();










    }
}).start();


    }

    private void askToLoadAGame() {

        try
        {
            optionChoose= JOptionPane.showOptionDialog(this,null,"                                                     load game?",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,new ImageIcon(StaticVariables.imageLoader.loadImage("save/"+textField.getText()+".png").getScaledInstance(400,400,4)),null,null);

        }catch (Exception e)
        {

        }
        switch (optionChoose)
        {
            case 0:
            {
                wantToLoadGame=true;


                break;
            }
            case 1:
            {
                wantToLoadGame=false;
                break;
            }


        }

    }


    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        if(e.getComponent().equals(confirm))
        {

            if(!type.equals("")&&!textField.getText().equals(""))
            {
                addTheWaitingLabel();
            }
            else
                JOptionPane.showMessageDialog(this,"pick user name and character");






        }
        if(e.getComponent().equals(male))
        {
            male.setBorder(border);
            male.setBorder( BorderFactory.createMatteBorder(
                    3, 3, 3, 3, Color.red));

            female.setBorder(null);
            type="male";
        }
        if(e.getComponent().equals(female))
        {
            female.setBorder(border);
            female.setBorder( BorderFactory.createMatteBorder(
                    3, 3, 3, 3, Color.red));
            male.setBorder(null);
            type="female";
        }


        if(e.getComponent().equals(start))
        {


            chooseThePlayer.setVisible(true);


        }
        if(e.getComponent().equals(exit))
        {

        }


    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {



            if(e.getComponent().equals(male))
            {
                hover=true;
                moveTheCharacter(male,aMale);
            }
            else if(e.getComponent().equals(female))
            {
                hover=true;
                moveTheCharacter(female,aFemale);
            }





    }

    public void mouseExited(MouseEvent e) {
        hover=false;
    }


    public Sound getBackgroundSound() {
        return backgroundSound;
    }

    public void setBackgroundSound(Sound backgroundSound) {
        this.backgroundSound = backgroundSound;
    }

    public JLabel getWatingLabel() {
        return watingLabel;
    }

    public void setWatingLabel(JLabel watingLabel) {
        this.watingLabel = watingLabel;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public JButton getConfirm() {
        return confirm;
    }

    public void setConfirm(JButton confirm) {
        this.confirm = confirm;
    }

    public JButton getStart() {
        return start;
    }

    public void setStart(JButton start) {
        this.start = start;
    }



    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public ArrayList<ImageIcon> getaMale() {
        return aMale;
    }

    public void setaMale(ArrayList<ImageIcon> aMale) {
        this.aMale = aMale;
    }

    public ArrayList<ImageIcon> getaFemale() {
        return aFemale;
    }

    public void setaFemale(ArrayList<ImageIcon> aFemale) {
        this.aFemale = aFemale;
    }

    public JLabel getMale() {
        return male;
    }

    public void setMale(JLabel male) {
        this.male = male;
    }

    public JLabel getFemale() {
        return female;
    }

    public void setFemale(JLabel female) {
        this.female = female;
    }

    @Override
    public Border getBorder() {
        return border;
    }

    @Override
    public void setBorder(Border border) {
        this.border = border;
    }

    public JLabel getChooseThePlayer() {
        return chooseThePlayer;
    }

    public void setChooseThePlayer(JLabel chooseThePlayer) {
        this.chooseThePlayer = chooseThePlayer;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
