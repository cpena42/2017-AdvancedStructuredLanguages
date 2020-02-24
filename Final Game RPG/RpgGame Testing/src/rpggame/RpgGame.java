/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Cesar Pena
 */
public class RpgGame extends JPanel implements ActionListener, KeyListener {

    Timer t = new Timer(5, this);
    int x=0, y = 0, velx=0, vely = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f = new JFrame();
        RpgGame s = new RpgGame();
        f.add(s);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 400);
        f.setVisible(true);
    }
    
    public RpgGame() {
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(x<0)
        {
            velx = 0;
            x = 0;
        }
        
        if (x > 530)
        {
            velx = 0;
            x = 530;
        }
        
        if (y < 0)
        {
            vely = 0;
            y = 0;
        }
        
        x += velx;
        y += vely;
        repaint();
    }
    
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_S){
            vely = 1;
            velx = 0;
        }
        if(code == KeyEvent.VK_W) {
            vely = -1;
            velx = 0;
        }
        if(code == KeyEvent.VK_A) {
            vely = 0;
            velx = -1;
        }
        if(code == KeyEvent.VK_D) {
            vely = 0;
            velx = 1;
        }
    }
    
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e) {
        velx = 0;
        vely = 0;
    }
    
}
