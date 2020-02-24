/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package houserooms;

import java.util.Scanner;  //Needed for the Scanner Class

/**
 * Homework #3
 * Create a house with 2 rooms using 2 classes
 * Prompt user for the L and W of each room.  Assign the bigger room to the parents and smaller to the children.
 * State:  "parents get room #_ of size __", "children get room #_ of size __".
 * @author Cesar Pena
 * Date: February 17, 2017
 */
public class HouseRooms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);
        double length;
        double width;
        
        House house = new House();
        
        //Get user input for Room 1
        System.out.println("What is the length of Room 1?");
        length = Scan.nextDouble();
        
        System.out.println("What is the width of Room 1?");
        width = Scan.nextDouble();
        
        house.setRoom1(length, width);  //Assign the length and width to room 1
        
        //Get user input for Room 2
        System.out.println("What is the length of Room 2?");
        length = Scan.nextDouble();
        
        System.out.println("What is the width of Room 2?");
        width = Scan.nextDouble();
        
        house.setRoom2(length, width);  //Assign the length and width to room 2
        
        if(house.getRoom1Size() > house.getRoom2Size()){
            System.out.println("Parents get room #1 of size " + house.getRoom1Size());
            System.out.println("Children get room #2 of size " + house.getRoom2Size());
        }
        else{
            System.out.println("Parents get room #2 of size " + house.getRoom2Size());
            System.out.println("Children get room #1 of size " + house.getRoom1Size());
        }
    }
}
