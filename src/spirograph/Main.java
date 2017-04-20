package spirograph;

import java.awt.geom.Ellipse2D;
import javax.swing.*;
import java.awt.*;

//global variables



public class Main
{

	public static void main(String[] args)
	{
    //System.out.println("HELLOR WORLD");
    JFrame f=new JFrame("Spirograph");
    DrawPanel d=new DrawPanel();
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
