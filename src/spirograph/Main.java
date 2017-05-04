package spirograph;

import spirograph.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.ArrayList;



public class Main
{
    //Global Variables

    public boolean running=false;

    public Circle outerCircle,innerCircle;
    public JFrame f;
    public Timer tm;
    public Path2D.Double curve;
    public JPanel center,draw,init;
    public JColorChooser colors;
    public BufferedImage screenshot;
    
    private ArrayList<Path2D.Double> spirals;
    private ArrayList<Color> spiralColors;

    private Dimension mainSize=new Dimension(505,610);
    private Dimension botSize=new Dimension(505,100);
    private Dimension mainDrawSize=new Dimension(505,510);
    private Dimension colorSize=new Dimension(255,510);
    private Dimension initOther=new Dimension(250,255);
    private Dimension initMini=new Dimension(63,125);
    private Dimension initBotSize=new Dimension(505,255);


	public Main()
    {
        spirals=new ArrayList<Path2D.Double>();
        spiralColors=new ArrayList<Color>();
        outerCircle=new Circle(252,250,250);
        innerCircle=new Circle(252,50,50);
        innerCircle.setPoint(innerCircle.getCenterX(),innerCircle.getCenterY());
        curve=new Path2D.Double();
        f=new JFrame("Spirograph");
        JPanel p=new JPanel();
        center=new JPanel();
        colors=new JColorChooser(Color.BLACK);
        draw=new JPanel()
        {
            public void paint(Graphics g)
            {
                super.paint(g);
                paintSpirograph(g);
            }
        };

        init=new JPanel(new GridLayout(2,1));
        JPanel initBot=new JPanel(new GridLayout(1,2));
        JPanel initLeft=new JPanel(new GridLayout(4,1));
        JTextArea rText=new JTextArea("Radius of Circle");
        JSlider sizePicker=new JSlider(25,125,50);
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
                        g2d.setColor(colors.getColor());
                        g2d.fillRect((int)(initOther.width/2+innerCircle.getPointX()-innerCircle.getCenterX())-1,(int)(initOther.height/2)-1,3,3);
                    }
                }
            }
        };

        JPanel bot=new JPanel(new BorderLayout());
        JPanel buttonHolder=new JPanel(new GridLayout(1,3));
        JButton button=new JButton("Start");
        JButton reset=new JButton("Reset");
        JButton save=new JButton("Save image");

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
                    outerCircle.setAngle(0);
                    innerCircle.setAngle(0);
                    sizePicker.setValue(50);
                    pointPicker.setValue(50);
                    innerCircle.setRadius(sizePicker.getValue());
                    innerCircle.setY(innerCircle.getRadius());
                    innerCircle.setX(252);
                    innerCircle.setPoint((int)(252.0+((pointPicker.getValue()/100.0)*innerCircle.getRadius())),innerCircle.getCenterY());
                    curve=new Path2D.Double();
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
                    spirals.add(curve);
                    spiralColors.add(colors.getColor());
                    curve.moveTo(innerCircle.getPointX(),innerCircle.getPointY());
                    tm.start();
                    initRight.repaint();
                    draw.repaint();
                }
            }
        });
        reset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                running=false;
                center.setVisible(false);
                center.remove(draw);
                center.add(init);
                center.setVisible(true);
                button.setText("Start");
                tm.stop();
                outerCircle.setAngle(0);
                innerCircle.setAngle(0);
                sizePicker.setValue(50);
                pointPicker.setValue(50);
                innerCircle.setRadius(sizePicker.getValue());
                innerCircle.setY(innerCircle.getRadius());
                innerCircle.setX(252);
                innerCircle.setPoint((int)(252.0+((pointPicker.getValue()/100.0)*innerCircle.getRadius())),innerCircle.getCenterY());
                curve=new Path2D.Double();
                spirals=new ArrayList<Path2D.Double>();
                spiralColors=new ArrayList<Color>();
                colors.setColor(Color.BLACK);
                initRight.repaint();
                draw.repaint();
            }
        });
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
            }
        });
        innerCircle.setPoint((int)(252.0+((pointPicker.getValue()/100.0)*innerCircle.getRadius())),innerCircle.getCenterY());
        pText.setBorder(new MatteBorder(23,72,23,72,f.getBackground()));
        rText.setBorder(new MatteBorder(23,78,23,78,f.getBackground()));
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
        
        buttonHolder.add(save);
        buttonHolder.add(button);
        buttonHolder.add(reset);

        bot.add(buttonHolder,BorderLayout.CENTER);

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
                innerCircle.setX((outerCircle.getRadius()-innerCircle.getRadius())*-Math.sin(outerCircle.getAngle())+outerCircle.getCenterX());
                innerCircle.setY((outerCircle.getRadius()-innerCircle.getRadius())*-Math.cos(outerCircle.getAngle())+outerCircle.getCenterY());
                innerCircle.changeAngle(0.01*(outerCircle.getRadius()/innerCircle.getRadius()));
                if(innerCircle.getPoint()!=null)
                {
                    Point2D m=innerCircle.getPoint();
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

    public boolean save(String name);
    {
        Graphics current_layer
        ImageIo.write(current_layer,"PNG",new File(name+".png"));
    }

    public void paintSpirograph(Graphics g)
    {
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
            if(innerCircle.getPoint()!=null)
            {
                g2d.setColor(colors.getColor());
                g2d.fillOval((int)(innerCircle.getPointX()-2),(int)(innerCircle.getPointY()-2),5,5);
                g2d.setColor(Color.BLACK);
                g2d.drawOval((int)(innerCircle.getPointX()-2),(int)(innerCircle.getPointY()-2),5,5);
            }
        }
        for(int i=0;i<spirals.size();i++)
        {
            g2d.setColor(spiralColors.get(i));
            g2d.draw(spirals.get(i));
        }
    }
}
