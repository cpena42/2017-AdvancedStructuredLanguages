/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wishlist;

/**
 *
 * @author Cesar Pena
 */
public class Sort {
    
    //Function to sort the array using Selection Sort page 501
    public static void selectionSort(String[] array, double[] price)
    {
        //Declare variables required for sorting
        int startScan, index, minIndex;
        double tempPrice;   //Used to sort the prices
        String minValue, indexValue;
        char a, b;      //Used to hold the char in a string
        
        //For loop to begin testing
        for(startScan = 0; startScan < (array.length-1); startScan++)
        {
            minIndex = startScan;
            minValue = array[startScan];
            tempPrice = price[startScan];
            a = minValue.charAt(1); //Assign the 2nd character of the first string in the array to variable a
            
            //Loop to begin comparing against all other strings in the array
            for(index = startScan + 1; index < array.length; index++)
            {
                indexValue = array[index];
                b = indexValue.charAt(1);   //Assign the 2nd character of the next string in the array to variable b
                
                //Compare chars a and b, if b is smaller than a run this if statement
                if (a > b)
                {
                    //Assign the new minimum string to the variables minValue and minIndex
                    minValue = array[index];
                    minIndex = index;
                    tempPrice = price[index];
                    //System.out.println("Pass: ");     Testing if code runs
                }
                
            }
            
            //Swap the new minimum string
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
            
            //Swap the prices
            price[minIndex] = price[startScan];
            price[startScan] = tempPrice;
            
            //Reassign the beginning of the string array to begin testing for strings with the same 2nd letter
            minIndex = startScan;
            minValue = array[startScan];
            tempPrice = price[startScan];
            a = minValue.charAt(1); //Assign the char in the first string to variable a
            
            //Loop to begin testing against the 2nd string
            for(index = startScan + 1; index < array.length; index++)
            {   
                indexValue = array[index];
                b = indexValue.charAt(1);   //Assign the char in the second string to variable b
                
                //If both a and b and equal run this if statement
                if (a == b)
                {
                    //Assign the char values of string 1 and string 2 to a and b
                    a = minValue.charAt(3);
                    b = indexValue.charAt(3);
                    
                    //System.out.println("A: " + a + " B: " + b);  Testing if code runs
                    
                    //If b is smaller than a run this if statement
                    if (a > b)
                    {
                        //Assign the new minimum to the minValue and minIndex
                        minValue = array[index];
                        minIndex = index;
                        tempPrice = price[index];
                    }
                }
            }
            //Swap the strings
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
            
            //Swap the prices
            price[minIndex] = price[startScan];
            price[startScan] = tempPrice;
        }
    }
}
