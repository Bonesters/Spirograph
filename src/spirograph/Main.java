package spirograph;

import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Global Variables
public Ellipse2D outerCircle,Gear;//height,width,x,y



public class Main
{

	public static void main(String[] args)
	{
    //System.out.println("HELLOR WORLD");
    JFrame f=new JFrame("Spirograph");
    DrawPanel d=new DrawPanel();
    f.add(d);
	}

  static class DrawPanel extends JPanel
  {
    public void paint(Graphics g)
    {
      Graphics2D g2d=(Graphics2D)g;

    }
  }

//returns the location of the pen in the greater circle
	private Point2D getLocation(double t1,double t2,double pensliderWIP)
	{
		Point2D pp;  //pen position

		//calculation of the point in 2d land
		pp.x = // cx + r cos(a)
		 /*calculate outercircle pos*/ ((outercircle.radius-Gear.radius)*Math.cos(t1)+outercircle.x)
		 /*calculate pen pos*/ + pensliderWIP * Math.cos(t2);
		pp.y = // cy + r sin (a)
		/*calculate outercircle pos*/ ((outercircle.radius-Gear.radius)*Math.sin(t1)+outercircle.x)
		 /*calculate pen pos*/ + pensliderWIP * Math.sin(t2);

		return pp;
	}

}
