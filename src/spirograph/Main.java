package spirograph;

import spirograph.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;



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
    private Dimension colorSize=new Dimension(255,510);
    private Dimension initOther=new Dimension(250,255);
    private Dimension initMini=new Dimension(63,125);
    private Dimension initBotSize=new Dimension(505,255);


	public Main()
    {
        outerCircle=new Circle(252,250,250);
        innerCircle=new Circle(252,50,50);
        curve=new Path2D.Double();

        f=new JFrame("Spirograph");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        forceSize(f,mainSize);

        JPanel p=new JPanel();
        forceSize(p,mainSize);

        center=new JPanel();
        forceSize(center,mainDrawSize);

        draw=new JPanel()
        {
            public void paint(Graphics g)
            {
                super.paint(g);
                Graphics2D g2d=(Graphics2D)g;

                g2d.setColor(Color.WHITE);
                g2d.fillRect(0,0,mainDrawSize.width,mainDrawSize.height);
                g2d.setColor(Color.BLACK);
                if(outerCircle!=null)
                {
                    g2d.draw(outerCircle);
                    g2d.fillRect((int)outerCircle.getCenterX(),(int)outerCircle.getCenterY(),1,1);
                }
                if(innerCircle!=null)
                {
                    g2d.draw(innerCircle);
                    g2d.fillRect((int)innerCircle.getCenterX(),(int)innerCircle.getCenterY(),1,1);
                }
                if(curve!=null)
                {
                    g2d.draw(curve);
                }
            }
        };
        forceSize(draw,mainDrawSize);

        init=new JPanel(new GridLayout(2,1));
        forceSize(init,mainDrawSize);

        JPanel initBot=new JPanel(new GridLayout(1,2));
        forceSize(initBot,initBotSize);

        JColorChooser colors=new JColorChooser(Color.BLACK);
        colors.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
                f.repaint();
            }
            public void mouseEntered(MouseEvent e)
            {
                f.repaint();
            }
            public void mouseExited(MouseEvent e)
            {
                f.repaint();
            }
            public void mousePressed(MouseEvent e)
            {
                f.repaint();
            }
            public void mouseReleased(MouseEvent e)
            {
                f.repaint();
            }
        });
        forceSize(colors,colorSize);

        JPanel initLeft=new JPanel(new GridLayout(4,1));
        forceSize(initLeft,initOther);

        JTextArea rText=new JTextArea("Radius of Circle");
        rText.setEditable(false);
        forceSize(rText,initMini);

        JSlider sizePicker=new JSlider(25,200,50);
        sizePicker.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                
            }
        });
        forceSize(sizePicker,initMini);

        JTextArea pText=new JTextArea("Location of Point");
        pText.setEditable(false);
        forceSize(pText,initMini);

        JSlider pointPicker=new JSlider(0,100,50);
        pointPicker.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                
            }
        });
        forceSize(pointPicker,initMini);

        JPanel initRight=new JPanel()
        {
            public void paint(Graphics g)
            {
                super.paint(g);
                Graphics2D g2d=(Graphics2D)g;

                g2d.setColor(Color.WHITE);
                g2d.fillRect(0,0,initOther.width,initOther.height);
                g2d.setColor(Color.BLACK);
                
                if(innerCircle!=null)
                {
                    g2d.drawOval((int)(initOther.width/2-innerCircle.getRadius()),(int)(initOther.height/2-innerCircle.getRadius()),(int)innerCircle.getWidth(),(int)innerCircle.getHeight());
                    if(innerCircle.getPoint()!=null)
                    {
                        System.out.println(colors.getColor().toString());
                        g2d.setColor(colors.getColor());
                        g2d.fillRect((int)(initOther.width/2+innerCircle.getPoint().getX()-innerCircle.getCenterX()),(int)(initOther.height/2+innerCircle.getPoint().getY()-innerCircle.getCenterY()),1,1);
                    }
                }
            }
        };
        forceSize(initRight,initOther);

        JPanel bot=new JPanel(new BorderLayout());
        forceSize(bot,botSize);

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

        initLeft.add(rText);
        initLeft.add(sizePicker);

        initLeft.add(pText);
        initLeft.add(pointPicker);

        initBot.add(initLeft);
        initBot.add(initRight);

        init.add(initBot);
        init.add(colors);

        center.add(init);

        p.add(center);
        p.add(bot);

        f.getContentPane().add(p);

        f.pack();
        f.setVisible(true);

        tm = new Timer(5,new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                outerCircle.changeAngle(0.01);
                innerCircle.changeAngle(0.01);
                innerCircle.setPos((outerCircle.getRadius()-innerCircle.getRadius())*Math.cos(outerCircle.getAngle())+outerCircle.getCenterX(),
                (outerCircle.getRadius()-innerCircle.getRadius())*Math.sin(outerCircle.getAngle())+outerCircle.getCenterY());
                if(innerCircle.getPoint()!=null)
                {
                    Point2D m = getLocation();
                    curve.lineTo(m.getX(),m.getY());
                }
                f.repaint();
            }
        });
    }

    private void forceSize(Component c,Dimension d)
    {
        c.setPreferredSize(d);
        c.setMaximumSize(d);
        c.setMinimumSize(d);
        c.setSize(d);
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

        Point2D pp=innerCircle.getPoint();         //pen position

        //calculation of the point in 2d land
        double ppx = // cx + r cos(a)
        //calculate outercircle pos
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.cos(outerCircle.getAngle())+outerCircle.getCenterX())
        //calculate pen pos
        + slide.getValue() * Math.cos(innerCircle.getAngle());
        double ppy = // cy + r sin (a)
        //calculate outercircle pos
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.sin(outerCircle.getAngle())+outerCircle.getCenterY())
        //calculate pen pos
        + slide.getValue() * Math.sin(innerCircle.getAngle());


        return new Point2D.Double(ppx,ppy);
	}
	
}
