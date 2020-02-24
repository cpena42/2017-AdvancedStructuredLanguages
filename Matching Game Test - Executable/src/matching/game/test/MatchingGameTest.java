/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matching.game.test;
//import java.util.Scanner;
//import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.Random;    //Needed for Random class

/**
 * 
 * @author Cesar Pena
 */
public class MatchingGameTest extends JFrame{
    // Variables for Menu Bar
    private JLabel messageLabel;
    private final int LABEL_WIDTH = 400;
    private final int LABEL_HEIGHT = 200;
    
    // Variables for the menu components
    private JMenuBar menuBar;       // The menu bar
    private JMenu fileMenu;         // The File menu
    private JMenu themeMenu;        // The Theme menu
    private JMenu aboutMenu;        // The About menu
    private JMenuItem startItem;    // An item to start a new game
    private JMenuItem resetItem;    // An item to reset the current game
    private JRadioButtonMenuItem overwatchItem;     // Choose an overwatch theme
    private JRadioButtonMenuItem hotsItem;          // Choose a Heroes of the Storm theme
    private JRadioButtonMenuItem smiteItem;         // Choose a Smite theme
    private JRadioButtonMenuItem smallItem;         // Choose a 3 x 4 game grid
    private JRadioButtonMenuItem mediumItem;        // Choose a 3 x 6 game grid
    private JRadioButtonMenuItem largeItem;         // Choose a 3 x 8 game grid
    private JMenuItem helpItem;              // An item that contains instructions about the game and the options available.
    private JMenuItem aboutItem;                    // An item that contains information about you such as name, subject, date complete, version number, etc.
    
    // Variables for game grid
    private JLabel instructions;
    private JLabel score;
    
    private JPanel gameGrid;
    private int randIndex1;
    private int randIndex2;
    
    private ImageIcon backgroundIcon;
    private JLabel backgroundLabel;
    
    // Variables for size of the game grid
    private int CARDGAME_WIDTH = 4;
    private int CARDGAME_HEIGHT = 3;
    
    private int GAMEGRID_WIDTH = 750;
    private int GAMEGRID_HEIGHT = 700;
    
    // Variable for current theme
    private String currentTheme = "Overwatch";
    
    // Variables for the player scores
    private int whoseTurn = 1;
    private int Player1Score = 0;
    private int Player2Score = 0;
    
    // Array Variables for the cards
    private ImageIcon [] cardFace;
    private JButton [] buttons;
    
    // Variable for selected card that will be used for comparison
    private ImageIcon PickedCard1;
    private ImageIcon PickedCard2;
    
    private int Card1;
    private int Card2;
   
    private Timer timer;
    private final int TIME_DELAY = 1000;
    
    public MatchingGameTest(){
        // Call the Jframe constructor
        super("Matching Game");
        
        // Set window size
        setSize(1400, 900);
        setLocationRelativeTo(null);    //Center the window
        
        // Specify an action for the close button.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a BorderLayout manager for the content pane.
        setLayout(new BorderLayout());
        
        // Create the message label to let the user choose the theme options
        messageLabel = new JLabel ("Use the Theme menu to " +
                "select the game theme and the size.",
                SwingConstants.CENTER);
        messageLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        messageLabel.setForeground(Color.BLACK);
        
        // Add the label to the content pane.
        add(messageLabel);
        
        // Build the menu bar.
        buildMenuBar();
        
        instructions = new JLabel("Player " + whoseTurn + " - Pick two cards", SwingConstants.CENTER);
        instructions.setPreferredSize(new Dimension(1300, 30));
        instructions.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        score = new JLabel("Player 1: " + Player1Score + "     Player 2: " + Player2Score, SwingConstants.CENTER);
        score.setPreferredSize(new Dimension(1300, 30));
        score.setFont(new Font("SansSerif", Font.BOLD, 24));   //Change the font to Bold 48pt and SansSerif
       
        
        // Add gameGrid and Instructions label to the JFrame
        gameGrid = buildGameBoard();
        
        add(instructions);
        add(gameGrid);
        add(score);
        
        //Hide instructions and score
        score.setVisible(true);
        instructions.setVisible(true);
        
        timer = new Timer(TIME_DELAY, new TimerListener());
        
        //Display the JFrame
        setVisible(true);
    }
    
    //**********************************
    // Alternate between player 1 and 2
    //**********************************
    private void whoseTurnIsIt()
    {
        if(whoseTurn == 1)
        {
            whoseTurn = 2;
        }
        else
        {
            whoseTurn = 1;
        }
    }
    
    //***********************************
    // Function to build the Menu Bar
    //***********************************
    private void buildMenuBar()
    {
        // Create the menu bar.
        menuBar = new JMenuBar();
        
        // Create the file, theme, and about menus.
        buildFileMenu();
        buildThemeMenu();
        buildAboutMenu();
        
        // Add the file, theme, and about menus to the menu bar.
        menuBar.add(fileMenu);
        menuBar.add(themeMenu);
        menuBar.add(aboutMenu);
        
        // Set the window's menu bar.
        setJMenuBar(menuBar);
    }
    
    //***************************************
    // Function that builds the File menu
    //***************************************
    private void buildFileMenu()
    {
        // Create a New Game menu item.
        startItem = new JMenuItem("New Game");
        startItem.setMnemonic(KeyEvent.VK_N);
        startItem.addActionListener(new StartListener());
        
        // Create a Reset menu item.
        resetItem = new JMenuItem("Reset");
        resetItem.setMnemonic(KeyEvent.VK_R);
        resetItem.addActionListener(new ResetListener());
        
        // Create a JMenu object for the File Menu.
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        // Add the New Game and Reset to the File menu.
        fileMenu.add(startItem);
        fileMenu.add(resetItem);
    }
    
    
    //*******************************************
    // Function that builds the Theme menu
    //*******************************************
    private void buildThemeMenu()
    {
        // Create the radio button menu items to change the theme
        // of the game.  Add an action listener to each one.
        overwatchItem = new JRadioButtonMenuItem("Overwatch", true);
        overwatchItem.setMnemonic(KeyEvent.VK_O);
        overwatchItem.addActionListener(new ThemeListener());
        
        hotsItem = new JRadioButtonMenuItem("Heroes of the Storm");
        hotsItem.setMnemonic(KeyEvent.VK_E);
        hotsItem.addActionListener(new ThemeListener());
        
        smiteItem = new JRadioButtonMenuItem("Smite");
        smiteItem.setMnemonic(KeyEvent.VK_I);
        smiteItem.addActionListener(new ThemeListener());
        
        // Create a button group for the radio button items for the Theme.
        ButtonGroup group = new ButtonGroup();
        group.add(overwatchItem);
        group.add(hotsItem);
        group.add(smiteItem);
        
        // Create the radio button menu items to change the size
        // of the game board.  Add an action listener to each one.
        smallItem = new JRadioButtonMenuItem("Small 3x4", true);
        smallItem.setMnemonic(KeyEvent.VK_S);
        smallItem.addActionListener(new SizeListener());
        
        mediumItem = new JRadioButtonMenuItem("Medium 3x6");
        mediumItem.setMnemonic(KeyEvent.VK_M);
        mediumItem.addActionListener(new SizeListener());
        
        largeItem = new JRadioButtonMenuItem("Large 3x8");
        largeItem.setMnemonic(KeyEvent.VK_L);
        largeItem.addActionListener(new SizeListener());
        
        // Create a button group for the radio button items for the Size.
        // This is to prevent several buttons to be active at one time.
        ButtonGroup group2 = new ButtonGroup();
        group2.add(smallItem);
        group2.add(mediumItem);
        group2.add(largeItem);
        
        // Create a JMenu object for the Theme menu.
        themeMenu = new JMenu("Theme");
        themeMenu.setMnemonic(KeyEvent.VK_T);
        
        // Add the menu items to the Theme menu.
        themeMenu.add(overwatchItem);
        themeMenu.add(hotsItem);
        themeMenu.add(smiteItem);
        themeMenu.addSeparator();   // Add a separator bar.
        themeMenu.add(smallItem);
        themeMenu.add(mediumItem);
        themeMenu.add(largeItem);
    }
    
    //*******************************************
    // Function used to build the About Menu
    //*******************************************
    private void buildAboutMenu()
    {
        // Create a Help menu item.
        helpItem = new JMenuItem("Help");
        helpItem.setMnemonic(KeyEvent.VK_H);
        helpItem.addActionListener(new HelpListener());
        
        // Create a Reset menu item.
        aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new AboutListener());
        
        // Create a JMenu object for the File Menu.
        aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_F);
        
        // Add the New Game and Reset to the File menu.
        aboutMenu.add(helpItem);
        aboutMenu.add(aboutItem);
    }
    
    //*****************************************************
    // Function that will set the background of the JFrame
    // when the game is opened
    //*****************************************************
    private void buildBackground()
    {
        // Set a background image to the JFrame
        // http://stackoverflow.com/questions/18777893/jframe-background-image
        backgroundIcon = new ImageIcon(currentTheme + ".png");
        backgroundLabel = new JLabel(backgroundIcon);
        setContentPane(backgroundLabel);
        setLayout(new FlowLayout());   
    }
    
    //*************************************************
    // Function that will build the game board when
    // the game first opens.
    //*************************************************
    private JPanel buildGameBoard()
    {
        gameGrid = new JPanel();
        gameGrid.setPreferredSize(new Dimension(GAMEGRID_WIDTH, GAMEGRID_HEIGHT));
        gameGrid.setLayout(new GridLayout(CARDGAME_HEIGHT, CARDGAME_WIDTH, 20, 9));
        gameGrid.setOpaque(false);
        buttons = new JButton[CARDGAME_HEIGHT * CARDGAME_WIDTH];
        
        //Buttons to hold the pictures for the face of the cards
        cardFace = new ImageIcon[CARDGAME_HEIGHT * CARDGAME_WIDTH];

        for (int x = 0; x < (CARDGAME_HEIGHT * CARDGAME_WIDTH); x++)
        {
            ImageIcon cardBack = new ImageIcon( currentTheme + "_Back.png");
            buttons[x] = new JButton(cardBack);          //create instance of a button
            buttons[x].addActionListener(new ButtonSelector());    
            
            // Set the buttons to transparent to allow only the image to display
            buttons[x].setOpaque(false);
            buttons[x].setContentAreaFilled(false);
            buttons[x].setBorderPainted(false);
            gameGrid.add(buttons[x]);
            
        }
        
        // Array of ints used to test if a selected image has been used before.
        int [] selectedImages;
        selectedImages = new int[CARDGAME_HEIGHT *CARDGAME_WIDTH];
        
        for (int x = 0; x < ((CARDGAME_HEIGHT * CARDGAME_WIDTH)); x++)
        {
            //Generate a random number from the number of cards
            Random randomSelection = new Random();

            // Generate the two indexes for the card fronts, randIndex1 will also be used
            // to grab an image from the theme's image folder
            randIndex1 = randomSelection.nextInt(frontCards());
            randIndex2 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
            
            // Grab an image from the current themes image folder
            ImageIcon cardFront = new ImageIcon(currentTheme + "/pic" + randIndex1 + ".png");
            
            // Check to see if the selected image hasn't been used in the buttons before
            for(int y = 0; y < (CARDGAME_HEIGHT * CARDGAME_WIDTH); y++)
            {
                if(selectedImages[0] == randIndex1)
                {
                    randIndex1 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
                    cardFront = new ImageIcon(currentTheme + "/pic" + randIndex1 + ".png");
                }
                while(selectedImages[y] == randIndex1)
                {
                    randIndex1 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
                    cardFront = new ImageIcon(currentTheme + "/pic" + randIndex1 + ".png");
                    y = 0;
                }
            }
            // Save the index of the selected image to the selectedImages array
            // used for future testing to make sure that an image isn't duplicated with
            // more than 1 pair
            selectedImages[x] = randIndex1;
            
            // Re-generate randIndex1 for the location of the card in the game board
            randIndex1 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
            
            // While the chosen location is not null, re-generate a new random number
            while(cardFace[randIndex1] != null)
            {
                randIndex1 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
            }

            cardFace[randIndex1] = cardFront;
            
            while(randIndex1 == randIndex2 || cardFace[randIndex2] != null)
            {
                randIndex2 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
            }
            cardFace[randIndex2] = cardFront;
            x++;
        }
        
        
                
        gameGrid.setVisible(true);
        
        buildBackground();
        
        return gameGrid;
    }
    
    //********************************************************
    // Function that will erase the game board and repaints
    // it onto the JFrame.
    //********************************************************
    private void rebuildGameBoard()
    {        
        // Clear the gameGrid layout
        gameGrid.removeAll();
        
        // Set up the buttons array with the required cards in it
        buttons = new JButton[CARDGAME_HEIGHT * CARDGAME_WIDTH];
        
        // Set the dimensions and layout size for the cards
        gameGrid.setPreferredSize(new Dimension(GAMEGRID_WIDTH, GAMEGRID_HEIGHT));
        gameGrid.setLayout(new GridLayout(CARDGAME_HEIGHT, CARDGAME_WIDTH, 20, 9));
        
        // Buttons to hold the pictures for the face of the cards
        cardFace = new ImageIcon[CARDGAME_HEIGHT * CARDGAME_WIDTH];
        
        // Assign the card back on the buttons based on the current theme
        for (int x = 0; x < (CARDGAME_HEIGHT * CARDGAME_WIDTH); x++)
        {
            ImageIcon cardBack = new ImageIcon( currentTheme + "_Back.png");
            buttons[x] = new JButton(cardBack);          //create instance of a button   
            buttons[x].addActionListener(new ButtonSelector());  
            
            // Make the buttons transparent except the imageIcons so that
            // the background underneath can be seen
            buttons[x].setOpaque(false);
            buttons[x].setContentAreaFilled(false);
            buttons[x].setBorderPainted(false);
            gameGrid.add(buttons[x]);
        }
        
        // Set the front of the cards that will show once the cards are flipped
        // Array of ints used to test if a selected image has been used before.
        int [] selectedImages;
        selectedImages = new int[CARDGAME_HEIGHT *CARDGAME_WIDTH];
        
        for (int x = 0; x < ((CARDGAME_HEIGHT * CARDGAME_WIDTH)); x++)
        {
            //Generate a random number from the number of cards
            Random randomSelection = new Random();

            // Generate the two indexes for the card fronts, randIndex1 will also be used
            // to grab an image from the theme's image folder
            randIndex1 = randomSelection.nextInt(frontCards());
            randIndex2 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
            
            // Grab an image from the current themes image folder
            ImageIcon cardFront = new ImageIcon(currentTheme + "/pic" + randIndex1 + ".png");
            
            // Check to see if the selected image hasn't been used in the buttons before
            for(int y = 0; y < (CARDGAME_HEIGHT * CARDGAME_WIDTH); y++)
            {
                if(selectedImages[0] == randIndex1)
                {
                    randIndex1 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
                    cardFront = new ImageIcon(currentTheme + "/pic" + randIndex1 + ".png");
                }
                while(selectedImages[y] == randIndex1)
                {
                    randIndex1 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
                    cardFront = new ImageIcon(currentTheme + "/pic" + randIndex1 + ".png");
                    y = 0;
                }
            }
            // Save the index of the selected image to the selectedImages array
            // used for future testing to make sure that an image isn't duplicated with
            // more than 1 pair
            selectedImages[x] = randIndex1;
            
            // Re-generate randIndex1 for the location of the card in the game board
            randIndex1 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
            
            // While the chosen location is not null, re-generate a new random number
            while(cardFace[randIndex1] != null)
            {
                randIndex1 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
            }

            cardFace[randIndex1] = cardFront;
            
            while(randIndex1 == randIndex2 || cardFace[randIndex2] != null)
            {
                randIndex2 = randomSelection.nextInt(CARDGAME_HEIGHT * CARDGAME_WIDTH);
            }
            cardFace[randIndex2] = cardFront;
            x++;
        }
        
        // Revalidate the gameGrid and repaint it back to the JFrame
        gameGrid.revalidate();
        gameGrid.repaint();
        
        // Change the current background to the selected theme.
        backgroundIcon = new ImageIcon(currentTheme + ".png");
        backgroundLabel.setIcon(backgroundIcon);

    }
    
    private int frontCards()
    {
        int cardNum;
        
        if(currentTheme == "Overwatch")
        {
            cardNum = 23;
        }
        else if (currentTheme == "HoTS")
        {
            cardNum = 62;
        }
        else
        {
            cardNum = 83;
        }
        return cardNum;
    }
    
    //**************************************************************
    //  Private inner class that handles the event that is generated
    //  when the user selects New Game from the File menu.
    //**************************************************************
    private class StartListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Rebuild the game board
            rebuildGameBoard();
        }
    }
    
    //***************************************************************
    //  Private inner class that handles the event that is generated
    //  when the user selects Reset from the File menu.
    //***************************************************************
    private class ResetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Rebuild the game board
            rebuildGameBoard();
            
            // Reset the scores to 0
            Player1Score = 0;
            Player2Score = 0;
            
            // Set the player back to player 1
            whoseTurn = 1;
            
            // Reset the instructions label
            instructions.setText("Player " + whoseTurn + " - Pick two cards");
            
            // Reset the scores in the score label back to 0
            score.setText("Player 1: " + Player1Score + "     Player 2: " + Player2Score);
        }
    }
    
    //***************************************************************
    // Private inner class that handles the event that is generated
    // when the user selects a theme from the Theme menu.
    //***************************************************************
    private class ThemeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(overwatchItem.isSelected())
            {
                currentTheme = "Overwatch";
                
                // Reset - Rebuild Game Grid
                rebuildGameBoard();
                
                // Change the font color for the instructions and score label
                instructions.setForeground(Color.BLACK);
                score.setForeground(Color.BLACK);
            }
            else if(hotsItem.isSelected())
            {
                currentTheme = "HoTS";
                
                // Reset - Rebuild Game Grid
                rebuildGameBoard();
                
                // Change the font color for the instructions and score label
                instructions.setForeground(Color.WHITE);
                score.setForeground(Color.WHITE);
            }
            else if(smiteItem.isSelected())
            {
                currentTheme = "Smite";
                
                //Reset - Rebuild Game Grid
                rebuildGameBoard();
                
                // Change the font color for the instructions and score label
                instructions.setForeground(Color.BLACK);
                score.setForeground(Color.WHITE);
            }
        }
    }
    
    //***************************************************************
    // Private inner class that handles the event that is generated
    // when the user selects a size from the Theme menu.
    //***************************************************************
    private class SizeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
            // Determine which size is selected and apply the size to the
            // game grid.
            if(smallItem.isSelected())
            {
                CARDGAME_HEIGHT = 3;
                CARDGAME_WIDTH = 4;
                
                GAMEGRID_HEIGHT = 700;
                GAMEGRID_WIDTH = 750;
                
                //Reset - Rebuild Game Grid
                rebuildGameBoard();
                
            }
            else if(mediumItem.isSelected())
            {
                CARDGAME_HEIGHT = 3;
                CARDGAME_WIDTH = 6;
                
                GAMEGRID_HEIGHT = 700;
                GAMEGRID_WIDTH = 1000;
                
                //Reset - Rebuild Game Grid
                rebuildGameBoard();
                
            }
            else if(largeItem.isSelected())
            {
                CARDGAME_HEIGHT = 3;
                CARDGAME_WIDTH = 8;
                
                GAMEGRID_HEIGHT = 700;
                GAMEGRID_WIDTH = 1325;
                
                //Reset - Rebuild Game Grid
                rebuildGameBoard();
                
            }
        }
    }
    
    //***************************************************************
    // Private inner class that handles the event that is generated
    // when the user selects Help from the About menu.
    //***************************************************************
    private class HelpListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "Game Rules:      \n" +
                    "1. Click on a card, and the card will show the \n" +
                    "picture it contains. Click on a second card, which \n" +
                    "will also reveal what picture it contains.  If the two pictures \n" +
                    "match, you will gain 5 points. Otherwise it's the other players' turn. \n\n" +
                    "2. There is exactly 1 pair for each picture or card. \n\n" +
                    "3. Winner is determine by the player with the most matches or highest score.");
        }
    }
    
    //***************************************************************
    // Private inner class that handles the event that is generated
    // when the user selects About from the About menu.
    //***************************************************************
    private class AboutListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "Matching Card Game:      \n" +
                    "Creator: Cesar Pena \n" +
                    "Date Completed: April 7, 2017 \n" +
                    "Version: 1.0");
        }
    }
    
    //****************************************************************
    // Private inner class that handles the event that is generated
    // when the user selects a card from the grid board.
    //****************************************************************
    private class ButtonSelector implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {   
            for(int x = 0; x < (CARDGAME_HEIGHT * CARDGAME_WIDTH); x++)
            {
                if(e.getSource() == buttons[x])
                {
                    // Turn the clicked card over to show the front
                    buttons[x].setIcon(cardFace[x]);
                    
                    // If the variable in PickedCard1 is empty set it to the first card clicked
                    if(PickedCard1 == null)
                    {
                        PickedCard1 = cardFace[x];
                        Card1 = x;
                        buttons[Card1].setDisabledIcon(PickedCard1);
                        buttons[Card1].setEnabled(false);
                    }
                    // Else If the variable of PickedCard1 is not empty and the PickedCard2 is empty
                    // set it to the second card clicked
                    else if(PickedCard1 != null && PickedCard2 == null)
                    {
                        PickedCard2 = cardFace[x];
                        Card2 = x;
                        buttons[Card2].setDisabledIcon(PickedCard2);
                        buttons[Card2].setEnabled(false);
                    }
                    // If there is a value in PickedCard1 and PickedCard2 run this if statement
                    if(PickedCard1 != null && PickedCard2 != null)
                    {
                        // Check if the picked cards matched
                        if(PickedCard1 == PickedCard2)
                        {
                            // Show a message that the cards matched and increment the player's score
                            JOptionPane.showMessageDialog(null, "Match!");
                            if(whoseTurn == 1)
                            {
                                Player1Score += 5;
                            }
                            else
                            {
                                Player2Score += 5;
                            }
                                   
                            // Change the text displayed in the score label to show the incremented value
                            score.setText("Player 1: " + Player1Score + "     Player 2: " + Player2Score);
                        }
                        // If they didn't match flipped them back after 1 second
                        else
                        {
                            // Call timer to allow the 2nd card to be displayed for 1 second before it gets flipped back
                            timer.start();
                            
                            // Call the whoseTurnIsIt function to alternate the current player
                            whoseTurnIsIt();
                            
                            buttons[Card1].setEnabled(true);
                            buttons[Card2].setEnabled(true);
                        }
                        // Changed the picked cards back to null
                        PickedCard1 = null;
                        PickedCard2 = null;
                        
                    }
                }
            }
        }
    }
    
    
    //******************************************************************************
    //**    Timer that flips the cards back if they don't back after 2 seconds    **
    //******************************************************************************
    private class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           
            buttons[Card1].setIcon(new ImageIcon(currentTheme + "_Back.png"));
            buttons[Card2].setIcon(new ImageIcon(currentTheme + "_Back.png"));
            
            instructions.setText("Player " + whoseTurn + " - Pick two cards");
            
            timer.stop();
            
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MatchingGameTest();
    }
    
}
