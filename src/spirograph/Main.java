package spirograph;

import spirograph.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


Timer tm;

public class Main
{
    //Global Variables
    public Circle outerCircle,innerCircle;
    public Point2D penLoc;  //relative to origin of the circle
    public JSlider slide;

	public Main()
    {
        outerCircle=new Circle(250,250,250);
        innerCircle=new Circle(250,0,50);

        JFrame f=new JFrame("Spirograph");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p=new JPanel(new GridLayout(1,2));
        JPanel bot=new JPanel(new GridLayout(3,1));
        DrawPanel d=new DrawPanel();
        slide=new JSlider(50,250,125);


        bot.add(slide);

        p.add(d);
        p.add(bot);

        f.getContentPane().add(p);

        f.pack();
        f.setVisible(true);
    }



    public static void main(String[] args)
    {
        System.out.println("HELLO WORLD");
        Main m=new Main();
    }


    /**
        returns the location of the pen in the greater circle
    */
	private Point2D getLocation() // for a circle
	{

        //Point2D pp=new Point2D.Double(0.0,0.0);  //pen position
        Point2D pp=innerCircle.getPoint();         //pen position

        //calculation of the point in 2d land
        double ppx = // cx + r cos(a)
        //calculate outercircle pos
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.cos(outerCircle.getAngle())+outerCircle.getX())
        //calculate pen pos
        + slide.getValue() * Math.cos(innerCircle.getAngle());
        double ppy = // cy + r sin (a)
        //calculate outercircle pos
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.sin(outerCircle.getAngle())+outerCircle.getX())
        //calculate pen pos
        + slide.getValue() * Math.sin(innerCircle.getAngle());


        return new Point2D.Double(ppx,ppy);
	}



	class DrawPanel extends JPanel
    {
        public void paint(Graphics g)
        {
            Graphics2D g2d=(Graphics2D)g;
            if(outerCircle!=null)
                g2d.draw(outerCircle);
            if(innerCircle!=null)
                g2d.draw(innerCircle);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
      switch(e.getSource())
      {
        case "tm": f.rePaint();
          break;
        case "slider" : slide.setva

      }
    }
}
