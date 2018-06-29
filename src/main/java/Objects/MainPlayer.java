package Objects;

import BackgroundObject.FootStep;
import GameCore.GameObject;
import GameCore.Life;
import GameCore.StaticVariables;
import ImageHandel.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import java.util.Vector;

public class MainPlayer extends GameObject {

    public static int sumOfLife=35000;
    public static Life life;

    public static int imageFrameRate=0,addLifeToMainPlayer=0;
    public static boolean intersect=false;
    private int xSpriteSheet=350,ySprtieSheet=320,index=0,imageSpeed=3,damgeToGhost;
    public static String nameOfPlayer;
    private  boolean attacking;
    public static boolean walking=false;
    public static boolean stand=true;


    public static Point point;


    private boolean leftFromTheGhost, rightFromTheGhost, upFromTheGhost, downFromTheGhost,

    is_stand_left_up, is_stand_left_down, is_stand_right_dowb, is_stand_right_up;
    private File DIR_1;

    private Vector<Image>
            up, down, left, right,
             standDown,
            attackLeft,attackRight,attackDown,attackUp, die;
    private double angle=0;
    private double distanceFromPoint;
    private long speedOfMove=20;
    private FootStep footStep;


    public MainPlayer(){
        setBounds(550,250,xSpriteSheet,ySprtieSheet);
        point=new Point(getX(),getY());
        makeNewElements();
        addMainPlayerPosition();


    }


    public  void calculateTheAngle(int x, int y) {
        float xDistance = getX()-x ;
        float yDistance =  getY()-y ;

        angle= (360 + Math.toDegrees(Math.atan2(yDistance, xDistance)))%360 ;
/*
* calculate the angle of main player position in order to
 * know what is the diriction that the main player wants to move
* */
    }

    private void addMainPlayerPosition() {

        new Thread(new Runnable() {
            public void run() {

                Dimension dimension=new Dimension(400,380);
                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/stand/");
                ImageLoader.addImageOfObject(DIR_1, standDown,dimension);
                setTheUserAction();

                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/walk/up");
                        ImageLoader.addImageOfObject(DIR_1, up,dimension);

                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/walk/down");
                        ImageLoader.addImageOfObject(DIR_1, down,dimension);

                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/walk/left");
                        ImageLoader.addImageOfObject(DIR_1, left,dimension);

                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/walk/right");
                        ImageLoader.addImageOfObject(DIR_1, right,dimension);

                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/die");
                        ImageLoader.addImageOfObject(DIR_1, die,dimension);



                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/attack/right");
                        ImageLoader.addImageOfObject(DIR_1, attackRight,dimension);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/attack/down");
                ImageLoader.addImageOfObject(DIR_1, attackDown,dimension);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/attack/up");
                ImageLoader.addImageOfObject(DIR_1, attackUp,dimension);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/female/attack/left");
                ImageLoader.addImageOfObject(DIR_1, attackLeft,dimension);


            }
        }).start();











    }

    private void makeNewElements() {
        up = new Vector<Image>();
        down =  new Vector<Image>();
        left =  new Vector<Image>();
        right =  new Vector<Image>();

        standDown =  new Vector<Image>();






        attackLeft =  new Vector<Image>();
        attackDown =  new Vector<Image>();
        attackRight =  new Vector<Image>();
        attackUp =  new Vector<Image>();
        die =  new Vector<Image>();


    }

    private void setTheUserAction() {
        new Thread(new Runnable() {
            public void run() {

                while (true) {


                    imageFrameRate++;
                    setPlace();
                    checkIfMainPlayerOutOfBound();
                    checkifMainPlayerIntercetWithMonster();
                    setLocationOfMainPlayerWhenFightTheGhost();
                    addLifeToPlayer();


                    try {
                        Thread.sleep(speedOfMove);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        setTheUserAction();
                        break;
                    }
                }


            }
        }).start();
    }

    private void addLifeToPlayer() {

        if(!attacking&&walking)
        {
            addLifeToMainPlayer++;
            if(addLifeToMainPlayer==15&&life.getjProgressBar().getValue()<life.getjProgressBar().getMaximum())
            {
                addLifeToMainPlayer=0;
                life.getjProgressBar().setValue(life.getjProgressBar().getValue()+1);
                life.getjProgressBar().setString(""+life.getjProgressBar().getValue());
            }
            if(addLifeToMainPlayer>15)
                addLifeToMainPlayer=0;
        }
    }

    private void checkifMainPlayerIntercetWithMonster() {


        for (Ghost g : StaticVariables.world.getGhostArrayList()) {

            if(g.isStopMoving())
            intersect = g.isStopMoving();
            else
                intersect=false;
            if (intersect)
                break;

        }

    }

    private void setLocationOfMainPlayerWhenFightTheGhost(){
        if(attacking)
        {
            if (angle < 164 && angle >= 52) {
                downFromTheGhost = true;
                leftFromTheGhost = false;
                rightFromTheGhost = false;
                upFromTheGhost = false;
            }
            if (angle < 183 && angle >= 164) {
                leftFromTheGhost = true;
                downFromTheGhost = false;
                rightFromTheGhost = false;
                upFromTheGhost = false;
            }
            if (angle < 205 && angle >= 183) {
                leftFromTheGhost = true;
                downFromTheGhost = false;
                rightFromTheGhost = false;
                upFromTheGhost = false;
            }
            if (angle < 231 && angle >= 205) {
                leftFromTheGhost = true;
                downFromTheGhost = false;
                rightFromTheGhost = false;
                upFromTheGhost = false;
            }
            if (angle < 281 && angle >= 231) {
//                    down
                upFromTheGhost = true;
                leftFromTheGhost = false;
                downFromTheGhost = false;
                rightFromTheGhost = false;
            }
            if (angle < 318 && angle >= 281) {
//                    left up
                rightFromTheGhost = true;
                upFromTheGhost = false;
                leftFromTheGhost = false;
                downFromTheGhost = false;
            }
            if (angle >= 318) {
//                    left
                rightFromTheGhost = true;
                upFromTheGhost = false;
                leftFromTheGhost = false;
                downFromTheGhost = false;
            }
            if (angle < 52 && angle >= 0) {
//                    left down
                rightFromTheGhost = true;
                upFromTheGhost = false;
                leftFromTheGhost = false;
                downFromTheGhost = false;
            }


        }

    }
    private void setPlace() {
        try {

            if(imageFrameRate%20==0)
             footStep = new FootStep();
            if (walking) {
                if (angle < 164 && angle >= 52) {
                    StaticVariables.world.setLocation(StaticVariables.world.getX(), StaticVariables.world.getY() + imageSpeed);
                    setLocation(getX(), getY() - imageSpeed);
                    setIcon(new ImageIcon(up.get(index)));
                    if(imageFrameRate%20==0)
                    footStep.setTheImage(3);
// up

                }
                if (angle < 183 && angle >= 164) {
                    StaticVariables.world.setLocation(StaticVariables.world.getX() - imageSpeed, StaticVariables.world.getY() + imageSpeed);
                    setLocation(getX() + imageSpeed, getY() - imageSpeed);
                    setIcon(new ImageIcon(right.get(index)));
                    if(imageFrameRate%20==0)
                    footStep.setTheImage(4);
//                    rightup
                }
                if (angle < 205 && angle >= 183) {

                    StaticVariables.world.setLocation(StaticVariables.world.getX() - imageSpeed, StaticVariables.world.getY());
                    setLocation(getX() + imageSpeed, getY());
                    setIcon(new ImageIcon(right.get(index)));
                    if(imageFrameRate%20==0)
                    footStep.setTheImage(5);
//                    right

                }
                if (angle < 231 && angle >= 205) {

                    StaticVariables.world.setLocation(StaticVariables.world.getX() - imageSpeed, StaticVariables.world.getY() - imageSpeed);
                    setLocation(getX() + imageSpeed, getY() + imageSpeed);
                    setIcon(new ImageIcon(right.get(index)));
                    if(imageFrameRate%20==0)
                    footStep.setTheImage(6);
//right down
                }
                if (angle < 281 && angle >= 231) {
//                    down


                    StaticVariables.world.setLocation(StaticVariables.world.getX(), StaticVariables.world.getY() - imageSpeed);
                    setLocation(getX(), getY() + imageSpeed);
                    setIcon(new ImageIcon(down.get(index)));
                    if(imageFrameRate%20==0)
                    footStep.setTheImage(7);
                }
                if (angle < 318 && angle >= 281) {
//                    left up


                    StaticVariables.world.setLocation(StaticVariables.world.getX() + imageSpeed, StaticVariables.world.getY() - imageSpeed);
                    setLocation(getX() - imageSpeed, getY() + imageSpeed);
                    setIcon(new ImageIcon(left.get(index)));
                    if(imageFrameRate%20==0)
                    footStep.setTheImage(2);

                }

                if (angle >= 318) {
//                    left

                    StaticVariables.world.setLocation(StaticVariables.world.getX() + imageSpeed, StaticVariables.world.getY());
                    setLocation(getX() - imageSpeed, getY());
                    setIcon(new ImageIcon(left.get(index)));
                    if(imageFrameRate%20==0)
                    footStep.setTheImage(1);

                }
                if (angle < 52 && angle >= 0) {
//                    left down

                    StaticVariables.world.setLocation(StaticVariables.world.getX() + imageSpeed, StaticVariables.world.getY() + imageSpeed);
                    setLocation(getX() - imageSpeed, getY() - imageSpeed);
                    setIcon(new ImageIcon(left.get(index)));
                    if(imageFrameRate%20==0)
                    footStep.setTheImage(8);
                }
                index++;
                if (index == left.size())
                    index = 0;
                attacking=false;
                stand=false;

            } else if (stand) {
                index++;
                if (index == standDown.size())
                    index = 0;
                setIcon(new ImageIcon(standDown.get(index)));
                attacking=false;
                walking=false;
            }

            else if(attacking)
            {
                index++;


                if(leftFromTheGhost)
                {
                    checkIndexOutOfBound(attackRight);
                    setIcon(new ImageIcon(attackRight.get(index)));
                }
                else if (rightFromTheGhost)
                {
                    checkIndexOutOfBound(attackLeft);
                    setIcon(new ImageIcon(attackLeft.get(index)));

                }
                else if (downFromTheGhost)
                {
                    checkIndexOutOfBound(attackUp);
                    setIcon(new ImageIcon(attackUp.get(index)));

                }
                else if (upFromTheGhost)
                {
                    checkIndexOutOfBound(attackDown);
                    setIcon(new ImageIcon(attackDown.get(index)));

                }


            }
        } catch (NullPointerException e) {


        } catch (IndexOutOfBoundsException ea) {




        }


    }

    private void checkIndexOutOfBound(Vector<Image> arralist) {


        if (index == arralist.size()-1)
        {
            attacking=false;
            stand=true;
            walking=false;
            index = 0;
        }
    }

    private void checkIfMainPlayerOutOfBound(){
        try
        {
            if(getX()<=0||getY()<=0||getX()>=StaticVariables.world.getWidth()||getY()>=StaticVariables.world.getHeight())
            {
                stand=true;
                walking=false;

            }
        }catch (NullPointerException e)
        {

        }

    }




    public static int getSumOfLife() {
        return sumOfLife;
    }

    public static void setSumOfLife(int sumOfLife) {
        MainPlayer.sumOfLife = sumOfLife;
    }

    public static Life getLife() {
        return life;
    }

    public static void setLife(Life life) {
        MainPlayer.life = life;
    }

    public int getxSpriteSheet() {
        return xSpriteSheet;
    }

    public void setxSpriteSheet(int xSpriteSheet) {
        this.xSpriteSheet = xSpriteSheet;
    }

    public int getySprtieSheet() {
        return ySprtieSheet;
    }

    public void setySprtieSheet(int ySprtieSheet) {
        this.ySprtieSheet = ySprtieSheet;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getImageFrameRate() {
        return imageFrameRate;
    }

    public void setImageFrameRate(int imageFrameRate) {
        this.imageFrameRate = imageFrameRate;
    }

    public int getImageSpeed() {
        return imageSpeed;
    }

    public void setImageSpeed(int imageSpeed) {
        this.imageSpeed = imageSpeed;
    }

    public static String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public static void setNameOfPlayer(String nameOfPlayer) {
        MainPlayer.nameOfPlayer = nameOfPlayer;
    }



    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public static boolean isWalking() {
        return walking;
    }

    public static void setWalking(boolean walking) {
        MainPlayer.walking = walking;
    }

    public static Point getPoint() {
        return point;
    }

    public static void setPoint(Point point) {
        MainPlayer.point = point;
    }

    public boolean isLeftFromTheGhost() {
        return leftFromTheGhost;
    }

    public int getDamgeToGhost() {
        return damgeToGhost;
    }

    public void setDamgeToGhost(int damgeToGhost) {
        this.damgeToGhost = damgeToGhost;
    }

    public Vector<Image> getUp() {
        return up;
    }

    public void setUp(Vector<Image> up) {
        this.up = up;
    }

    public Vector<Image> getDown() {
        return down;
    }

    public void setDown(Vector<Image> down) {
        this.down = down;
    }

    public Vector<Image> getLeft() {
        return left;
    }

    public void setLeft(Vector<Image> left) {
        this.left = left;
    }

    public Vector<Image> getRight() {
        return right;
    }

    public void setRight(Vector<Image> right) {
        this.right = right;
    }



    public Vector<Image> getStandDown() {
        return standDown;
    }

    public void setStandDown(Vector<Image> standDown) {
        this.standDown = standDown;
    }



    public Vector<Image> getAttackLeft() {
        return attackLeft;
    }

    public void setAttackLeft(Vector<Image> attackLeft) {
        this.attackLeft = attackLeft;
    }

    public Vector<Image> getDie() {
        return die;
    }

    public void setDie(Vector<Image> die) {
        this.die = die;
    }

    public void setLeftFromTheGhost(boolean leftFromTheGhost) {
        this.leftFromTheGhost = leftFromTheGhost;
    }

    public boolean isRightFromTheGhost() {
        return rightFromTheGhost;
    }

    public void setRightFromTheGhost(boolean rightFromTheGhost) {
        this.rightFromTheGhost = rightFromTheGhost;
    }

    public boolean isUpFromTheGhost() {
        return upFromTheGhost;
    }

    public void setUpFromTheGhost(boolean upFromTheGhost) {
        this.upFromTheGhost = upFromTheGhost;
    }

    public boolean isDownFromTheGhost() {
        return downFromTheGhost;
    }

    public void setDownFromTheGhost(boolean downFromTheGhost) {
        this.downFromTheGhost = downFromTheGhost;
    }

    public boolean isIs_stand_left_up() {
        return is_stand_left_up;
    }

    public void setIs_stand_left_up(boolean is_stand_left_up) {
        this.is_stand_left_up = is_stand_left_up;
    }

    public boolean isIs_stand_left_down() {
        return is_stand_left_down;
    }

    public void setIs_stand_left_down(boolean is_stand_left_down) {
        this.is_stand_left_down = is_stand_left_down;
    }

    public boolean isIs_stand_right_dowb() {
        return is_stand_right_dowb;
    }

    public void setIs_stand_right_dowb(boolean is_stand_right_dowb) {
        this.is_stand_right_dowb = is_stand_right_dowb;
    }

    public boolean isIs_stand_right_up() {
        return is_stand_right_up;
    }

    public void setIs_stand_right_up(boolean is_stand_right_up) {
        this.is_stand_right_up = is_stand_right_up;
    }

    public File getDIR_1() {
        return DIR_1;
    }

    public void setDIR_1(File DIR_1) {
        this.DIR_1 = DIR_1;
    }



    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getDistanceFromPoint() {
        return distanceFromPoint;
    }

    public void setDistanceFromPoint(double distanceFromPoint) {
        this.distanceFromPoint = distanceFromPoint;
    }
}
