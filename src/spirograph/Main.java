package spirograph;

import spirograph.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;



public class Main
{
    //Global Variables

    public boolean running=false;

    public Circle outerCircle,innerCircle;
    public JFrame f;
    public JSlider slide;
    public Timer tm;
    public Path2D.Double curve;
    public JPanel center,draw,init;

    private Dimension mainSize=new Dimension(505,610);
    private Dimension botSize=new Dimension(505,100);
    private Dimension mainDrawSize=new Dimension(505,510);


	public Main()
    {
        outerCircle=new Circle(252,250,250);
        innerCircle=new Circle(252,50,50);
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

        center=new JPanel();
        center.setPreferredSize(mainDrawSize);
        center.setMaximumSize(mainDrawSize);
        center.setMinimumSize(mainDrawSize);
        center.setSize(mainDrawSize);

        DrawPanel d=new DrawPanel();
        d.setPreferredSize(mainDrawSize);
        d.setMaximumSize(mainDrawSize);
        d.setMinimumSize(mainDrawSize);
        d.setSize(mainDrawSize);

        draw=new DrawPanel();
        draw.setPreferredSize(mainDrawSize);
        draw.setMaximumSize(mainDrawSize);
        draw.setMinimumSize(mainDrawSize);
        draw.setSize(mainDrawSize);

        init=new JPanel();
        init.setPreferredSize(mainDrawSize);
        init.setMaximumSize(mainDrawSize);
        init.setMinimumSize(mainDrawSize);
        init.setSize(mainDrawSize);
        
        JPanel bot=new JPanel(new BorderLayout());
        bot.setPreferredSize(botSize);
        bot.setMaximumSize(botSize);
        bot.setMinimumSize(botSize);
        bot.setSize(botSize);

        JButton button=new JButton("Start");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(running) //stop
                {
                    running=false;
                    center.setVisible(false);
                    center.remove(draw);
                    center.add(init);
                    center.setVisible(true);
                    button.setText("Start");
                    tm.stop();
                }
                else        //start
                {
                    running=true;
                    center.setVisible(false);
                    center.remove(init);
                    center.add(draw);
                    center.setVisible(true);
                    button.setText("Stop");
                    tm.start();
                }
            }
        });

        bot.add(button,BorderLayout.CENTER);

        center.add(init);
        
        p.add(center);
        p.add(bot);

        f.getContentPane().add(p);

        f.pack();
        f.setVisible(true);

        tm = new Timer(50,new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                outerCircle.changeAngle(0.1);
                innerCircle.changeAngle(0.1);
                if(innerCircle.getPoint()!=null)
                {
                    Point2D m = getLocation();
                    curve.lineTo(m.getX(),m.getY());
                }
                f.repaint();
            }
        });
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

    /*
	public void actionPerformed(ActionEvent e)
    {
        switch(e.getSource().toString())
        {
            case "increment":

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
    */

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
}
