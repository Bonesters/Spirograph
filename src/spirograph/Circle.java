package spirograph;

import java.lang.Math;
import java.awt.geom.*;
import java.util.ArrayList;

public class Circle extends Ellipse2D.Double
{
    private ArrayList<Point2D.Double> points;
    
    private double angle;
    
    public Circle(double x,double y,double radius,double angle)
    {
        super(x-radius,y-radius,radius,radius);
        this.angle=angle;
        points=new ArrayList<Point2D.Double>();
    }
    
    public Circle(Point2D.Double origin,double radius,double angle)
    {
        this(origin.getX(),origin.getY(),radius,angle);
    }
    
    public Circle(double x,double y,double radius)
    {
        this(x-radius,y-radius,radius,0.0);
    }
    
    public Circle(Point2D.Double origin,double radius)
    {
        this(origin.getX(),origin.getY(),radius,0.0);
    }
    
    public Point2D.Double getCenter()
    {
        return new Point2D.Double(getCenterX(),getCenterY());
    }
    
    public double getRadius()
    {
        return (super.getWidth()/2.0);
    }
    
    public double getX()
    {
        return super.getX()-getRadius();
    }
    
    public double getY()
    {
        return super.getY()-getRadius();
    }
    
    public void setX(double x)
    {
        setPos(x,getY());
    }
    
    public void setY(double y)
    {
        setPos(getX(),y);
    }
    
    public void setPos(Point2D p)
    {
        setPos(p.getX(),p.getY());
    }
    
    public void setPos(double x,double y)
    {
        move(x,y,getRadius());
    }
    
    public void setRadius(double radius)
    {
        move(getCenterX(),getCenterY(),radius);
    }
    
    public double getAngle()
    {
        return angle;
    }
    
    public void setAngle(double angle)
    {
        this.angle=angle;
        adjustPoints();
    }
    
    private void adjustPoints()
    {
        for(Point2D p:points)
        {
            
        }
    }
    
    private void move(double x,double y,double radius)
    {
        super.setFrame(x-radius,y-radius,radius*2f,radius*2f);
    }
}
