/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package houserooms;

/**
 * House class to hold the 2 rooms
 * @author Cesar Pena
 */
public class House {
    //Private Variables
    private Room room1;
    private Room room2;
    
    //Default Constructor
    public House(){
        room1 = new Room();
        room2 = new Room();
    }
    
    //Set Functions - Used to get the length and width of each room
    //Room 1 Data
    public void setRoom1(double length, double width){
        room1.setL(length);
        room1.setW(width);
    }
    
    //Room 2 Data
    public void setRoom2(double length, double width){
        room2.setL(length);
        room2.setW(width);
    }
    
    //Get Functions - Used to return the size of each room
    //Return size of room 1
    public double getRoom1Size(){
        double size;
        
        size = room1.getL() * room1.getW();
        
        return size;
    }
    
    //Return size of room 2
    public double getRoom2Size(){
        double size;
        
        size = room2.getL() * room2.getW();
        
        return size;
    }
}
