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
        innerCircle.setPoint(innerCircle.getCenterX(),innerCircle.getCenterY());
        curve=new Path2D.Double();
        f=new JFrame("Spirograph");
        JPanel p=new JPanel();
        center=new JPanel();
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

        init=new JPanel(new GridLayout(2,1));
        JPanel initBot=new JPanel(new GridLayout(1,2));
        JColorChooser colors=new JColorChooser(Color.BLACK);
        JPanel initLeft=new JPanel(new GridLayout(4,1));
        JTextArea rText=new JTextArea("Radius of Circle");
        JSlider sizePicker=new JSlider(25,200,50);
        JTextArea pText=new JTextArea("Location of Point");
        JSlider pointPicker=new JSlider(0,100,50);
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
                        //System.out.println();
                        g2d.setColor(colors.getColor());
                        g2d.fillRect((int)(initOther.width/2+innerCircle.getPointX()-innerCircle.getCenterX())-1,(int)(initOther.height/2)-1,3,3);
                    }
                }
            }
        };

        JPanel bot=new JPanel(new BorderLayout());
        JButton button=new JButton("Start");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        colors.getSelectionModel().addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                initRight.repaint();
            }
        });
        rText.setEditable(false);
        sizePicker.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                innerCircle.setRadius(sizePicker.getValue());
                System.out.println(innerCircle.getPoint().toString());
                innerCircle.setY(innerCircle.getRadius());
                innerCircle.setPoint((int)(252.0+((pointPicker.getValue()/100.0)*innerCircle.getRadius())),innerCircle.getCenterY());
                initRight.repaint();
            }
        });
        pText.setEditable(false);
        pointPicker.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                innerCircle.setPoint((int)(252.0+((pointPicker.getValue()/100.0)*innerCircle.getRadius())),innerCircle.getCenterY());
                initRight.repaint();
            }
        });
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
                    initRight.repaint();
                    draw.repaint();
                }
                else        //start
                {
                    running=true;
                    center.setVisible(false);
                    center.remove(init);
                    center.add(draw);
                    center.setVisible(true);
                    button.setText("Stop");
                    curve.moveTo(innerCircle.getPointX(),innerCircle.getPointY());
                    tm.start();
                    initRight.repaint();
                    draw.repaint();
                }
            }
        });
        forceSize(f,mainSize);
        forceSize(p,mainSize);
        forceSize(center,mainDrawSize);
        forceSize(draw,mainDrawSize);
        forceSize(init,mainDrawSize);
        forceSize(initBot,initBotSize);
        forceSize(colors,colorSize);
        forceSize(initLeft,initOther);
        forceSize(rText,initMini);
        forceSize(sizePicker,initMini);
        forceSize(pText,initMini);
        forceSize(pointPicker,initMini);
        forceSize(initRight,initOther);
        forceSize(bot,botSize);

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
                initRight.repaint();
                draw.repaint();
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
        double pr = Math.sqrt(Math.pow(pp.getX()-innerCircle.getX(),2) + Math.pow(pp.getY() - innerCircle.getY(),2));

        //calculation of the point in 2d land
        double ppx = // cx + r cos(a)
        //calculate outercircle pos
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.cos(outerCircle.getAngle())+outerCircle.getCenterX())
        //calculate pen pos
        + pr * Math.cos(innerCircle.getAngle());
        double ppy = // cy + r sin (a)
        //calculate outercircle pos
        ((outerCircle.getRadius()-innerCircle.getRadius())*Math.sin(outerCircle.getAngle())+outerCircle.getCenterY())
        //calculate pen pos
        + pr* Math.sin(innerCircle.getAngle());


        return new Point2D.Double(ppx,ppy);
	}
}
