package spirograph;

import spirograph.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Main implements ActionListener
{
    //Global Variables

    public boolean running=false;

    public Circle outerCircle,innerCircle;
    public JFrame f;
    public JSlider slide;
    public Timer tm;
    public Path2D.Double curve;

    private Dimension mainSize=new Dimension(501,601);
    private Dimension botSize=new Dimension(501,100);
    private Dimension sliderSize=new Dimension(301,100);
    private Dimension miscSize=new Dimension(100,100);
    private Dimension botLeftSize=new Dimension(201,100);
    private Dimension mainDrawSize=new Dimension(501,501);


	public Main()
    {
        outerCircle=new Circle(250,250,250);
        innerCircle=new Circle(250,50,50);
        curve=new Path2D.Double();

        f=new JFrame("Spirograph");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(mainSize);
        f.setMaximumSize(mainSize);
        f.setMinimumSize(mainSize);
        f.setSize(mainSize);

        JPanel p=new JPanel();
        p.setPreferredSize(mainSize);
        p.setMaximumSize(mainSize);
        p.setMinimumSize(mainSize);
        p.setSize(mainSize);

        DrawPanel d=new DrawPanel();
        d.setPreferredSize(mainDrawSize);
        d.setMaximumSize(mainDrawSize);
        d.setMinimumSize(mainDrawSize);
        d.setSize(mainDrawSize);

        JPanel bot=new JPanel(new GridLayout(1,2));
        bot.setPreferredSize(botSize);
        bot.setMaximumSize(botSize);
        bot.setMinimumSize(botSize);
        bot.setSize(botSize);

        JPanel botLeft=new JPanel(new GridLayout(1,2));
        botLeft.setPreferredSize(botLeftSize);
        botLeft.setMaximumSize(botLeftSize);
        botLeft.setMinimumSize(botLeftSize);
        botLeft.setSize(botLeftSize);

        slide=new JSlider(0,250,125);
        slide.setPreferredSize(sliderSize);
        slide.setMaximumSize(sliderSize);
        slide.setMinimumSize(sliderSize);
        slide.setSize(sliderSize);

        JPanel mid=new JPanel();
        mid.setPreferredSize(miscSize);
        mid.setMaximumSize(miscSize);
        mid.setMinimumSize(miscSize);
        mid.setSize(miscSize);

        JPanel right=new JPanel();
        right.setPreferredSize(miscSize);
        right.setMaximumSize(miscSize);
        right.setMinimumSize(miscSize);
        right.setSize(miscSize);

        bot.add(slide);
        bot.add(botLeft);

        botLeft.add(mid);
        botLeft.add(right);

        p.add(d);
        p.add(bot);

        f.getContentPane().add(p);

        f.pack();
        f.setVisible(true);

        tm = new Timer(50,this);
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
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.cos(outerCircle.getAngle())+outerCircle.getCenterX())
        //calculate pen pos
        + slide.getValue() * Math.cos(innerCircle.getAngle());
        double ppy = // cy + r sin (a)
        //calculate outercircle pos
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.sin(outerCircle.getAngle())+outerCircle.getCenterX())
        //calculate pen pos
        + slide.getValue() * Math.sin(innerCircle.getAngle());


        return new Point2D.Double(ppx,ppy);
	}

	public void actionPerformed(ActionEvent e)
    {
        switch(e.getSource().toString())
        {
            case "increment":
                outerCircle.changeAngle(0.1);
                innerCircle.changeAngle(0.1);
                f.repaint();
                break;
            case "stop":
                tm.stop();
                break;
            case "start":
                tm = new Timer(50,this);
                tm.start();
                break;

        }
    }

    class DrawPanel extends JPanel
    {
        public void paint(Graphics g)
        {
            super.paint(g);
            Graphics2D g2d=(Graphics2D)g;

            g2d.setColor(Color.WHITE);
            g2d.fillRect(0,0,500,500);
            g2d.setColor(Color.BLACK);
            if(outerCircle!=null)
            {
                //System.out.println(outerCircle.getBounds().toString());
                g2d.draw(outerCircle);
                g2d.fillRect((int)outerCircle.getCenterX(),(int)outerCircle.getCenterY(),1,1);
            }
            if(innerCircle!=null)
            {
                //System.out.println(innerCircle.getBounds().toString());
                g2d.draw(innerCircle);
                g2d.fillRect((int)innerCircle.getCenterX(),(int)innerCircle.getCenterY(),1,1);
            }
            if(curve!=null)
            {
                g2d.draw(curve);
            }
        }
    }

    class Increment extends TimerTask
    {
        public void run()
        {

        }
    }
}
