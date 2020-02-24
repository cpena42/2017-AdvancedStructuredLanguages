/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritance;
import java.util.Scanner;       //Required to use the Scanner class

/**
 *
 * @author Cesar Pena
 */
public class Inheritance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);      //Scanner object for keyboard input
       
       //Variables to hold user input
       String n;
       int a, i;
       
       //Boolean variable to run the while loops to validate user data
       Boolean test = true;
       
       //Create an object array from Student class with 3 students
       Student[] p=new Student[3];
       
       //For loop to fill out the p array with user input.
       for(int x = 0; x < 3; x++)
       {
           //Prompt user for user name
           p[x] = new Student();
           System.out.printf("Please enter your name: ", 1);
           n = scan.nextLine();
           p[x].setName(n);
           
           //p[x].name = scan.nextLine();
           
           //Prompt user for user age
           System.out.printf("Please enter your age: ", 1);
           
           //While loop to make sure that the user enters a number not a letter for the age
           while(test){
                try //This is an exception where the user inputs wrong data type
                {
                    a = scan.nextInt();
                    p[x].setAge(a);
                    test = false;
                }
                catch(Exception e)
                {
                    String junk;
                    junk = scan.nextLine();
                    System.out.println(e);
                    System.out.println("You were supposed to enter a number, not " + junk);
                    System.out.printf("Please re-enter your age: ", 1);
                    //scan.nextLine();
                }
           }
           
           test = true;
           
           //Prompt user for user school id
           System.out.printf("Please enter you school id: ", 1);
           
           //While loop to make sure that the user enters a number nto a letter for the ID
           while(test){
                try
                {
                    i = scan.nextInt();
                    p[x].setID(i);
                    test = false;
                }
                catch(Exception e)
                {
                    String junk;
                    junk = scan.nextLine();
                    System.out.println(e);
                    System.out.println("You were supposed to enter a number, not " + junk);
                    System.out.printf("Please re-enter your school id: ", 1);
                }
           }
           
           scan.nextLine();
           test = true;
           System.out.println(); //Blank line
       }
       
       System.out.println();    //Blank line
       
       //Display the data from the 3 students
       for(int x = 0; x < 3; x++)
       {
           System.out.println(p[x].getName());
           System.out.println(p[x].getAge());
           System.out.println(p[x].getID());
           System.out.println();
       }
    }
    
}
