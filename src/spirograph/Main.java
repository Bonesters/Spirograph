package spirograph;

import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main
{
  
    public Circle outerCircle,innerCircle;
    public Point2D penLoc;  //relative to origin of the circle

    public Main()
    {
        JFrame f=new JFrame("Spirograph");
        JPanel p=new JPanel(new GridLayout(1,2));
        JPanel p1=new JPanel(new GridLayout(3,1));
        DrawPanel d=new DrawPanel();
        
        
        
        p.add(d);
        p.add(p1);
        f.add(p);
    }

    

    public static void main(String[] args)
    {
        //System.out.println("HELLOR WORLD");
        Main m=new Main();
    }

    class DrawPanel extends JPanel
    {
        public void paint(Graphics g)
        {
            Graphics2D g2d=(Graphics2D)g;
            g2d.draw(outerCircle);
            g2d.draw(innerCircle);
        }
    }
}
