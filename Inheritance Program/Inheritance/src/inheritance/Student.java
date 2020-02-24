/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritance;

/**
 *
 * @author Cesar Pena
 */
public class Student extends Person{
    //Declare private variables
    private int id;
    
    //Default Constructor
    Student(){
        id = 1111;
    }
    
    //Overloaded Constructor
    Student(int i){
        id = i;
    }
    
    //Set Functions
    void setID(int i){
        id = i;
    }
    
    //Get Functions
    int getID(){
        return id;
    }
}
