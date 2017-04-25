package spirograph;

import spirograph.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Main
{
    //Global Variables
    public Circle outerCircle,innerCircle;
    public Point2D penLoc;  //relative to origin of the circle

	public Main()
    {
        JFrame f=new JFrame("Spirograph");
        JPanel p=new JPanel(new GridLayout(1,2));
        JPanel bot=new JPanel(new GridLayout(3,1));
        DrawPanel d=new DrawPanel();
        JSlider slide=new JSlider(50,250,125);
        
        
        
        bot.add(slide);
        p.add(d);
        p.add(bot);
        f.add(p);
    }

    

    public static void main(String[] args)
    {
        System.out.println("HELLO WORLD");
        Main m=new Main();
    }


    /**
        returns the location of the pen in the greater circle
    */
	private Point2D getLocation(double t1,double t2,float pensliderWIP)
	{
	    
        Point2D pp=new Point2D.Double(0.0,0.0);  //pen position
        
        /*
        //calculation of the point in 2d land
        pp.x = // cx + r cos(a)
        //calculate outercircle pos
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.cos(t1)+outerCircle.getX())
        //calculate pen pos
        + pensliderWIP * Math.cos(t2);
        pp.y = // cy + r sin (a)
        //calculate outercircle pos
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.sin(t1)+outerCircle.getX())
        //calculate pen pos
        + pensliderWIP * Math.sin(t2);
        */

        return pp;
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
