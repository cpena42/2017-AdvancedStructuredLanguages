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
public class Person {
    //Declare private variables
    private String name;
    private int age;
    
    //Default Constructor
    Person(){
        name = "Null";
        age = 0;
    }
    
    //Overloaded Constructor
    Person(String n, int a){
        name = n;
        age = a;
    }
    
    //*************
    //Set Functions
    //*************
    void setName(String n){
        name = n;
    }
    
    void setAge(int a){
        age = a;
    }
    
    //*************
    //Get Functions
    //*************
    String getName(){
        return name;
    }
    
    int getAge(){
        return age;
    }
}
