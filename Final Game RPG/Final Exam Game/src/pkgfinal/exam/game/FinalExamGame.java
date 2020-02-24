/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.exam.game;
import java.applet.*;   // Added from site
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author Cesar Pena
 */
public class FinalExamGame extends JApplet implements KeyListener {

    private int x = 0;
    private final int WIDTH = 40;
    private final int HEIGHT = 40;
    private final int TIME_DELAY = 10;
    private final int MOVE = 5;
    private final int MinY = 0;
    private final int MaxY = 500 - HEIGHT;
    private final int MinX = 0;
    private final int MaxX = 500 - WIDTH;
    
    private int y = 0;
    private Timer timer;
    
    // Character
    private Image myChar;
    private boolean goingUp = false;
    private boolean goingDown = false;
    private boolean goingLeft = false;
    private boolean goingRight = false;
    private int speedUp = 0;
    
    private int leftCount = 0;
    private int rightCount = 0;
    private int downCount = 0;
    private int upCount = 0;
    
    // Character Info
    private int charLevel = 1;
    
    private Label displayLvl;
    
    // Items
    private int totalItems = 12;    // Count all items except the book
    private Image boots;
    private Image pants;
    private Image chest;
    private Image helm;
    private Image sign;
    private Image book;
    private Image boat;
    private Image crystal;
    private Image fishingRod;
    private Image goldenApple;
    private Image sword;
    private Image wand;
    private Image key;
    
    
    // Items grabbed
    private boolean hasBoots = false;
    private boolean foundBook = false;
    private boolean hasPants = false;
    private boolean hasChest = false;
    private boolean hasHelm = false;
    private boolean hasBoat = false;
    private boolean hasCrystal = false;
    private boolean hasFishingRod = false;
    private boolean hasGoldenApple = false;
    private boolean hasSword = false;
    private boolean hasWand = false;
    private boolean hasKey = false;
    private boolean hasShield = false;
   
    // Touch Items
    private boolean touchSign = false;
    private boolean touchBox = false;
    
    // House variables
    private boolean touchHouse = false;
    private boolean enteredHouse = false;
    private boolean touchExit = false;
    private Image houseBack;
    
    // Fishing Pier
    private boolean touchPier = false;
    
    // Cave variables
    private boolean touchCave = false;
    private boolean enteredCave = false;
    private boolean touchCaveExit = false;
    private Image caveBack;

    // Portal variables
    private Image portal;
    private boolean touchPortal = false;
    private boolean portalVisible = false;
    
    // Sound variables
    private AudioClip sound;
    private boolean soundPlaying = false;
    
    // Background
    int grid = 3;
    int gridA = 1;
    int gridB = 1;
    
    private Image background[][];
    
    // Added from site
    Graphics bufferGraphics;
    Image offscreen;
    Dimension dim;
    
    int curX, curY;
    int keyX, keyY;
    
    private final int WW = 800;
    private final int WH = 650;
    /**
     * @param args the command line arguments
     */
    public void init() {
        super.setSize(WW,WH);
        dim = getSize();
        
        // Detect the mouse
        //addMouseMotionListener(this);
        
        // Detect key input
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        Toolkit tool = Toolkit.getDefaultToolkit();
        myChar = tool.getImage("myChar.png");

        background = new Image[grid][grid];
        
        // Fill the array with world map 
        background[0][0] = tool.getImage("images/background/Grid_Map-3x3_01.gif");
        background[1][0] = tool.getImage("images/background/Grid_Map-3x3_02.png");
        background[2][0] = tool.getImage("images/background/Grid_Map-3x3_03.gif");
        background[0][1] = tool.getImage("images/background/Grid_Map-3x3_04.gif");
        background[1][1] = tool.getImage("images/background/Grid_Map-3x3_05.gif");
        background[2][1] = tool.getImage("images/background/Grid_Map-3x3_06.gif");
        background[0][2] = tool.getImage("images/background/Grid_Map-3x3_07.gif");
        background[1][2] = tool.getImage("images/background/Grid_Map-3x3_08.gif");
        background[2][2] = tool.getImage("images/background/Grid_Map-3x3_09.png");
 
       
        // TextField to display character's level
        displayLvl = new Label("Level: " + charLevel);
        displayLvl.setBounds(WW - 60, 0, 60, 30);

        // Get the AudioClip object for the sound file
        sound = getAudioClip(getCodeBase(), "sound/music.wav");
        sound.play();
        
        // Center the character on the screen
        x = (WW / 2) - 39;
        y = (WH / 2) - 48;
        
        //background = tool.getImage("images/grass.png");
        offscreen = createImage(dim.width,dim.height);
        bufferGraphics = offscreen.getGraphics();
        timer = new Timer(TIME_DELAY, new TimerListener());
        timer.start();
        
        
    }
 
    
    public void paint(Graphics g) {
        dim = getSize();    // In case window is resized
        
        // Clear the screen using clearRect
        bufferGraphics.clearRect(0, 0, dim.width, dim.height);
  
        add(displayLvl);
        
        Toolkit tool = Toolkit.getDefaultToolkit();
        
        // Draw the background
        if(enteredHouse == true)
        {
            // Inside of the house
            houseBack = tool.getImage("images/background/house.png");
            bufferGraphics.drawImage(houseBack, 0, 0, this);
            
            // Find out if the user has found the boat
            if(hasBoat != true) {
                // Draw the boat
                boat = tool.getImage("images/boat.png");
                bufferGraphics.drawImage(boat, 600, 200, this);
            }
            
            // Find out if the user has found the chest armor
            if(hasChest != true) {
                // Draw the chest armor
                chest = tool.getImage("images/chestarmor.png");
                bufferGraphics.drawImage(chest, 400, 200, this);
            }
        }
        else if(enteredCave == true)
        {
            // Inside of the cave
            caveBack = tool.getImage("images/background/cave.png");
            bufferGraphics.drawImage(caveBack, 0, 0, this);
            
            // Find out if the user has found the crystal
            if(hasCrystal != true) {
                // Draw the crystal
                crystal = tool.getImage("images/crystal.png");
                bufferGraphics.drawImage(crystal, 150, 200, this);
            }
            
            // Find out if the user has found the leg armor
            if(hasPants != true) {
                // Draw the pants
                pants = tool.getImage("images/pantarmor.png");
                bufferGraphics.drawImage(pants, 700, 300, this);
            }
        }
        else
        {
            // Draw the map
            bufferGraphics.drawImage(background[gridA][gridB], 0,0 , this);
        }    
      
        
        
        if(goingUp == true)
        {
            if(upCount % 2 == 0)
                myChar = tool.getImage("myCharUp.png");
            else if (upCount % 3 == 0)
                myChar = tool.getImage("myCharUp2.png");
            else
                myChar = tool.getImage("myCharUp3.png");
            goingUp = false;
        }
        
        if(goingDown == true)
        {
            if(downCount % 2 == 0)
                myChar = tool.getImage("myChar.png");
            else if (downCount % 3 == 0)
                myChar = tool.getImage("myChar2.png");
            else
                myChar = tool.getImage("myChar3.png");
            goingDown = false;
        }
        
        if(goingLeft == true)
        {
            if(leftCount % 2 == 0)
                myChar = tool.getImage("myCharLeft.png");
            else if (leftCount % 3 == 0)
                myChar = tool.getImage("myCharLeft2.png");
            else
                myChar = tool.getImage("myCharLeft3.png");
            goingLeft = false;
        }
        
        if(goingRight == true)
        {
            if(rightCount % 2 == 0)
                myChar = tool.getImage("myCharRight.png");
            else if (rightCount % 3 == 0)
                myChar = tool.getImage("myCharRight2.png");
            else
                myChar = tool.getImage("myCharRight3.png");
 
            goingRight = false;
        }
        
        // Display items if the background is in the top center of the array = [1][0]
        if(background[gridA][gridB] == background[1][0])
        {
            // Paint the sign if the map image displayed in array [1][1]
            sign = tool.getImage("images/sign.png");
            bufferGraphics.drawImage(sign, 475, 275, this);
            
            if(totalItems == 0)
            {
                portal = tool.getImage("images/portal.png");
                bufferGraphics.drawImage(portal, 392, 135, this);
                portalVisible = true;
            }
        }
        
        // Display items if the background is in the center of the array = [1][1]
        if(background[gridA][gridB] == background[1][1])
        {
        
            if(foundBook != true)
            {
                book = tool.getImage("images/book.png");
                bufferGraphics.drawImage(book, (WW / 2) + 39, (WH/2) + 48, this);
            }
            
            if(hasBoots != true)
            {
                boots = tool.getImage("images/boots.png");
                bufferGraphics.drawImage(boots, 650, 300, this);
            }
        }
        
        // Display items if the background is in the top right corner = [2][0]
        if(background[gridA][gridB] == background[2][0])
        {
            // Draw the fishing rod
            if(hasFishingRod != true)
            {
                fishingRod = tool.getImage("images/fishingrod.png");
                bufferGraphics.drawImage(fishingRod, 250, 200, this);
            }
            
            // Draw the wand
            if(hasWand != true)
            {
                wand = tool.getImage("images/wand.png");
                bufferGraphics.drawImage(wand, 450, 400, this);
            }
        }
        
        // Display items if the background is in the right middle section of the array = [0][1]
        if(background[gridA][gridB] == background[0][1])
        {
            // Draw the helm
            if(hasHelm != true)
            {
                helm = tool.getImage("images/helmarmor.png");
                bufferGraphics.drawImage(helm, 400, 350, this);
            }
        }
        
        // Display items if the background is in the bottom middle section of the array = [1][2]
        if(background[gridA][gridB] == background[1][2])
        {
            // Draw the sword
            if(hasSword != true)
            {
                sword = tool.getImage("images/sword.png");
                bufferGraphics.drawImage(sword, 550, 500, this);
            }
        }
        
        // Display items if the background is in the bottom right section of the array = [2][2]
        if(background[gridA][gridB] == background[2][2])
        {
            // Draw the apple
            if(hasGoldenApple != true)
            {
                goldenApple = tool.getImage("images/goldenapple.png");
                bufferGraphics.drawImage(goldenApple, 700, 400, this);
            }
        }
        
        bufferGraphics.drawImage(myChar, x, y, this);
        
        g.drawImage(offscreen, 0, 0, this);
        
        
    
    }
    
    public void update(Graphics g)
    {
        paint(g);
    }
    
    
    private class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
            {
                
                // Collisions
                // Boots
                Rectangle boots = new Rectangle(650, 300, WIDTH, HEIGHT);
               
                // Sign
                Rectangle sign = new Rectangle(475, 275, WIDTH, HEIGHT);
               
                // Book
                Rectangle book = new Rectangle((WW / 2) + 39, (WH/2) + 48, WIDTH, HEIGHT);
                
                // House Entrance
                Rectangle house = new Rectangle(600, 75, 200, 200);
                
                // House Exit
                Rectangle houseExit = new Rectangle(150, 500, 150, 500);
                
                // Boat
                Rectangle boat = new Rectangle(600, 200, WIDTH, HEIGHT);
                
                // Fishing Rod
                Rectangle fishingRod = new Rectangle(250, 200, WIDTH, HEIGHT);
                
                // Fishing Pier
                Rectangle fishingPier = new Rectangle(475, 400, 75, 50);
                
                // Character
                Rectangle charBody = new Rectangle(x, y, 39, 48);
                
                // Cave Entrance
                Rectangle cave = new Rectangle(300, 0, 40, 25);
                
                // Cave Exit
                Rectangle caveExit = new Rectangle(125, 525, 150, 100);
                
                // Box
                Rectangle box = new Rectangle(425, 60, 25, 25);
                
                // Crystal
                Rectangle crystal = new Rectangle(150, 200, WIDTH, HEIGHT);
                
                // Pant Armor
                Rectangle pants = new Rectangle(700, 300, WIDTH, HEIGHT);
                
                // Chest Armor
                Rectangle chest = new Rectangle(400, 200, WIDTH, HEIGHT);
                
                // Helm Armor
                Rectangle helm = new Rectangle(400, 350, WIDTH, HEIGHT);
                
                // Wand
                Rectangle wand = new Rectangle(450, 400, WIDTH, HEIGHT);
                
                // Sword
                Rectangle sword = new Rectangle(550, 500, WIDTH, HEIGHT);
                
                // Golden Apple
                Rectangle apple = new Rectangle(700, 400, WIDTH, HEIGHT);
                
                // Portal
                Rectangle portal = new Rectangle(392, 135, 150, 150);
                
                // Travel left - check if another panel exists
                if(x < 0)
                {
                    if(gridA != 0 && enteredHouse != true && enteredCave != true)
                    {
                        gridA -= 1;
                        keyX = 0;
                        x = dim.width - 39;
                    }
                    else
                    {
                        keyX = 0;
                        x = 0;
                    }
                }

                // Travel right - check if another panel exists
                if (x > dim.width - 39)
                {
                    if(gridA != 2 && enteredHouse != true && enteredCave != true)
                    {
                        gridA += 1;
                        keyX = 0;
                        x = 0;
                    }
                    else
                    {
                        keyX = 0;
                        x = dim.width - 39;
                    }
                    
                }
                
                // Travel up - check if another panel exists
                if (y < 0)
                {
                    if(gridB != 0 && enteredHouse != true && enteredCave != true)
                    {
                        gridB -= 1;
                        keyY = 0;
                        y = dim.height - 48;
                    }
                    else
                    {
                        keyY = 0;
                        y = 0;
                    }
                }
                
                // Travel down - check if another panel exists
                if (y > dim.height - 48)
                {
                    if(gridB != 2 && enteredHouse != true && enteredCave != true)
                    {
                        gridB += 1;
                        keyY = 0;
                        y = 0;
                    }
                    else
                    {
                        keyY = 0;
                        y = dim.height - 48;
                    }

                }
                
                // Collisions in the [1][1] section
                if(charBody.intersects(boots) && background[gridA][gridB] == background[1][1] && hasBoots == false)
                {
                    JOptionPane.showMessageDialog(null,"Golden Boots Found! \n" +
                            "Press F to speed up and C to slow back down.");
                    hasBoots = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                if(charBody.intersects(sign) && background[gridA][gridB] == background[1][0])
                {
                    touchSign = true;
                }
                else
                {
                    touchSign = false;
                }
                
                if(charBody.intersects(book) && background[gridA][gridB] == background[1][1] && foundBook == false)
                {
                    JOptionPane.showMessageDialog(null,"Welcome to Item Hunt RPG \n" +
                        "Find all the items to beat the game \n" +
                        "and escape the island. \n" +
                        "A total of " + totalItems + " remain to be found! \n" +
                        "Interact with items by pressing E \n" +
                        "Press I at any time to open read this book again \n");
                    foundBook = true;
                }
                
                // Collisions in the [2][1]
                if(charBody.intersects(house) && background[gridA][gridB] == background[2][1] && touchHouse == false && enteredHouse == false)
                {
                    touchHouse = true;
                }
                else
                {
                    touchHouse = false;
                }
                
                // Collisions if in the house
                if(charBody.intersects(houseExit) && enteredHouse == true)
                {
                    touchExit = true;
                }
                else
                {
                    touchExit = false;
                }
                
                // Collisions for boat in the house
                if(charBody.intersects(boat) && enteredHouse == true && hasBoat == false)
                {
                    JOptionPane.showMessageDialog(null,"Found Boat!");
                    hasBoat = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                // Collisions for the chest armor in the house
                if(charBody.intersects(chest) && enteredHouse == true && hasChest == false)
                {
                    JOptionPane.showMessageDialog(null, "Found Chest Armor!");
                    hasChest = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                // Collisions for fishing rod
                if(charBody.intersects(fishingRod) && background[gridA][gridB] == background[2][0] && hasFishingRod == false)
                {
                    JOptionPane.showMessageDialog(null,"Found Fishing Rod!");
                    hasFishingRod = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                // Collisions for standing in the fishing pier
                if(charBody.intersects(fishingPier) && background[gridA][gridB] == background[0][2])
                {
                    touchPier = true;
                }
                else
                {
                    touchPier = false;
                }
                
                // Collisions for touching cave entrance
                if(charBody.intersects(cave) && background[gridA][gridB] == background[0][0])
                {
                    touchCave = true;
                }
                else
                {
                    touchCave = false;
                }
                
                
                // Collisions if in the cave
                if(charBody.intersects(caveExit) && enteredCave == true)
                {
                    touchCaveExit = true;
                }
                else
                {
                    touchCaveExit = false;
                }
                
                // Collisions with the box/chest in [0][0]
                if(charBody.intersects(box) && background[gridA][gridB] == background[0][0])
                {
                    touchBox = true;
                }
                else
                {
                    touchBox = false;
                }
                
                // Collisions with the crystal in the cave
                if(charBody.intersects(crystal) && enteredCave == true && hasCrystal == false)
                {
                    JOptionPane.showMessageDialog(null,"Found Crystal!");
                    hasCrystal = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                // Collisions with the pants in the cave
                if(charBody.intersects(pants) && enteredCave == true && hasPants == false)
                {
                    JOptionPane.showMessageDialog(null, "Found Pant Armor!");
                    hasPants = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                // Collisions with the helm in [0][1]
                if(charBody.intersects(helm) && background[gridA][gridB] == background[0][1] && hasHelm == false)
                {
                    JOptionPane.showMessageDialog(null, "Found Helm Armor!");
                    hasHelm = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                // Collisions with the wand in [2][0]
                if(charBody.intersects(wand) && background[gridA][gridB] == background[2][0] && hasWand == false)
                {
                    JOptionPane.showMessageDialog(null, "Found a Wand!");
                    hasWand = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                // Collisions with the sword in [1][2]
                if(charBody.intersects(sword) && background[gridA][gridB] == background[1][2] && hasSword == false)
                {
                    JOptionPane.showMessageDialog(null, "Found a Sword!");
                    hasSword = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                // Collisions with the golden apple in [2][2]
                if(charBody.intersects(apple) && background[gridA][gridB] == background[2][2] && hasGoldenApple == false)
                {
                    JOptionPane.showMessageDialog(null, "Found a Golden Apple!");
                    hasGoldenApple = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                
                // Collisions with the portal in [1][0]
                if(charBody.intersects(portal) && background[gridA][gridB] == background[1][0] && portalVisible == true)
                {
                    touchPortal = true;
                }
                else
                {
                    touchPortal = false;
                }
                
                x += keyX;
                y += keyY;
                repaint();
            }
    }
    
    // Move the ball with a key press
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        // Move Down
        if(code == KeyEvent.VK_S){
            keyY = 1 + speedUp;
            keyX = 0;
            
            downCount += 1;
            goingDown = true;
            
            leftCount = 0;
            rightCount = 0;
            upCount = 0;
        }
        // Move Up
        if(code == KeyEvent.VK_W) {
            keyY = -1 - speedUp;
            keyX = 0;
            
            upCount += 1;
            goingUp = true;
            
            leftCount = 0;
            rightCount = 0;
            downCount = 0;
        }
        // Move Left
        if(code == KeyEvent.VK_A) {
            keyY = 0;
            keyX = -1 - speedUp;
  
            leftCount += 1;
            goingLeft = true;
  
            upCount = 0;
            rightCount = 0;
            downCount = 0;
        }
        // Move Right
        if(code == KeyEvent.VK_D) {
            keyY = 0;
            keyX = 1 + speedUp;

            rightCount += 1;
            goingRight = true;
            
            upCount = 0;
            downCount = 0;
            leftCount = 0;
        }
        
 
        if(code == KeyEvent.VK_F) {
            if(hasBoots == true){
                speedUp = 2;
            }     
        }

        if(code == KeyEvent.VK_C) {
            if(speedUp > 0 && hasBoots == true){
                speedUp = 0;
            }
        }
        
        if(code == KeyEvent.VK_E) {
            // Interactions with the sign
            if(touchSign == true) {
                JOptionPane.showMessageDialog(null,"The Path of Salvation will only open once all items are collected!");
            }
            
            // Interactions with the house
            if(touchHouse == true) {
                if(hasKey == true){
                    JOptionPane.showMessageDialog(null,"Entered the House!");
                    enteredHouse = true;
                    x = 150;
                    y = 500;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Door is locked!");
                }
            }
            
            // Interactions with the cave entrance
            if(touchCave == true) {
                if(hasBoat == true)
                {
                    JOptionPane.showMessageDialog(null, "Entered the Cave!");
                    enteredCave = true;
                    x = 150;
                    y = 500;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You need to have crossed the water successfully \n" +
                            "to enter this cave!");
                }
            }
            
            // Interactions with the house exit 
            if(touchExit == true) {
                JOptionPane.showMessageDialog(null, "Exit the house!");
                x = 600;
                y = 250;
                enteredHouse = false;
            }
            
            // Interactions with the cave exit
            if(touchCaveExit == true) {
                JOptionPane.showMessageDialog(null, "Exit the cave!");
                x = 300;
                y = 50;
                enteredCave = false;
            }
            // Interactions with the fishing pier
            if(touchPier == true) {
                if(hasKey == false && hasFishingRod == true) 
                {
                    JOptionPane.showMessageDialog(null, "Fished up a Golden Key!");
                    hasKey = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                else if(hasFishingRod != true)
                {
                    JOptionPane.showMessageDialog(null, "You need a fishing rod to fish!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Nothing Caught!");
                }
            }
            
            // Interactions with the chest/box
            if(touchBox == true) {
                if(hasBoat == true && hasShield == false)
                {
                    JOptionPane.showMessageDialog(null, "Opened the Chest! \n" +
                            "Found a Shield!");
                    hasShield = true;
                    charLevel = charLevel + 1;
                    totalItems = totalItems - 1;
                    
                    displayLvl.setText("Level: " + charLevel);
                }
                else if (hasShield != true)
                {
                    JOptionPane.showMessageDialog(null, "You need to have crossed the water successfully \n" +
                            "to open this chest!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Box is empty!");
                }
            }
            
            if(touchPortal == true) {
                // Beat the game!
                JOptionPane.showMessageDialog(null,"Congratulations you escaped the island \n" +
                        "and beat the game!");
                System.exit(0);
            }
        }
        
        if(code == KeyEvent.VK_I) {
            if(foundBook == true) {
                JOptionPane.showMessageDialog(null,"Welcome to Item Hunt RPG \n" +
                        "Find all the items to beat the game \n" +
                        "and escape the island. \n" +
                        "A total of " + totalItems + " remain to be found! \n" +
                        "Interact with the environment by pressing E \n" +
                        "Press I at any time to read this book again \n");
            }
        }
    }
    
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e) {
        keyY = 0;
        keyX = 0;
    }
}
