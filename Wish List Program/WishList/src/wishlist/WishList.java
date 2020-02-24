/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wishlist;

import java.util.Scanner;  //Needed for the Scanner Class
import java.text.DecimalFormat;     //Required to format a decimal

/**
 * You are to read chapters 7 and 8 (arrays and text operations).  *finally a challenge!*
 * You should write a freedom program which includes arrays, but you should do text operations such as looking at the third character of a string to sort.
 * 
 * An acceptable program would be something like having a one dimensional array of characters. If you recall, a string is an array of characters, 
 * holding several strings in an array is kind of like a 2 dimensional array of characters.
 * 
 * Writing a program which will sort the strings of characters based on 2nd letter. If the 2nd letter is the same, use the 4th letter, etc. 
 * The rules are up to you but this program basically uses both chapter 7 and 8 material and would work.
 * 
 * Think of something fun and make sure to read the chapters incase there is material which you havnt already learned in previous C++ classes
 * @author Cesar Pena
 */
public class WishList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);      //Scanner object for keyboard input
        
        //Variable to hold the size of the array
        int SIZE;
        double totalCost = 0.0;   //Accumulator
        
        //Variables to hold display data
        String userName;
        StringBuilder helloMessage = new StringBuilder();
        
        //Display program title
        System.out.println("This program will accept a number of wish items and " +
                "calculate the total cost of them.");
        System.out.println("Note: Items will be sorted by 2nd letter then 4th");
        
        //Prompt user for their name
        System.out.printf("Enter your name: ", 1);
        userName = Scan.nextLine();
        
        System.out.println();   //Print blank line
        
        helloMessage.append("Hello ");
        helloMessage.append(userName);
        helloMessage.append(" please provide the following:");
        
        //Display message
        System.out.println(helloMessage);
        
        //Prompt user for input - Number of items to be included in the wish list
        System.out.printf("How many wish items would you like to include? ", 1);
        SIZE = Scan.nextInt();
        
        Scan.nextLine();
        System.out.println();   //Print blank line
        
        //Create arrays to hold the data
        String[] wishList = new String[SIZE];
        double[] prices = new double[SIZE];
        
        //Display subtitle
        System.out.println("Enter item data: ");
        
        //Loop to ask for user input for each item
        for(int x = 0; x < SIZE; x++)
        {
            //Ask for wish item title
            System.out.printf("Item #%d: ", (x+1));
            wishList[x] = Scan.nextLine();
            //Ask for wish item price
            System.out.printf("Price: $", (x+1));
            prices[x] = Scan.nextDouble();
            
            Scan.nextLine();
            
            System.out.println();   //Print blank line
        }
        
        //Display subtitle
        System.out.println("Unsorted wish list items:");
        
        //Loop to display unsorted items
        for(int x = 0; x < SIZE; x++)
        {
            //Display item
            System.out.println(wishList[x] + " " + prices[x]);
            totalCost += prices[x];    
        }
        
        //Sort items by their even letter
        Sort.selectionSort(wishList, prices);
        
        System.out.println();   //Print blank line
        System.out.println("Sorted wish list items:");
        //Loop to display sorted items
        for(int x = 0; x < SIZE; x++)
        {
            //Display item
            System.out.println(wishList[x] + " " + prices[x]);   
        }
        
        //Create a DecimalFormat object. Used to display only 2 decimal places
        DecimalFormat formatter = new DecimalFormat("#0.00");
        
        //Display total cost of all items
        System.out.println("The total cost of all items is: $" + formatter.format(totalCost));
    }
    
}
