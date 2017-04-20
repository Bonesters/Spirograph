package spirograph;

import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public Ellipse2D outerCircle,innerCircle;

//global variables



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

private point2d getLocation()
{
	
}

}
