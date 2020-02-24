/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package houserooms;

/**
 * Room class used to get the L and W of each room
 * @author Cesar Pena
 */
public class Room {
    //Private Variables
    private double L;
    private double W;
    
    //Constructor
    public Room(){
        L = 0;
        W = 0;
    }
    
    //Overloaded Construtor
    public Room(double length, double width){
        L = length;
        W = width;
    }
    
    //Set Functions - used to set the length and width on the private variables
    //Set L variable
    public void setL(double length){
        L = length;
    }
    
    //Set W variable
    public void setW(double width){
        W = width;
    }
    
    //Get Functions - used to return the value of the private variables
    //Get the amount in the L variable
    public double getL(){
        return L;
    }
    
    //Get the amount in the W variable
    public double getW(){
        return W;
    }
}
