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
        super(x,y,radius*2,radius*2);
        //System.out.println("x:"+(x-radius)+"\ny:"+(y-radius)+"\nh:"+(radius*2.0)+"\nw:"+(radius*2.0)+"\na:"+angle);
        this.angle=angle;
        points=new ArrayList<Point2D.Double>();
    }

    public Circle(double x,double y,double radius)
    {
        this(x,y,radius,0.0);
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
    }

    public void changeAngle(double angle)
    {
      this.angle+=angle;
    }

    public void addPoint(Point2D.Double p)
    {
        points.add(p);
    }

    public void addPoint(double x,double y)
    {
        addPoint(new Point2D.Double(x,y));
    }

    public void removePoint(Point2D.Double p)
    {
        points.remove(p);
    }

    public void removePoint(double x,double y)
    {
        removePoint(new Point2D.Double(x,y));
    }

    public boolean hasPoint(Point2D.Double p)
    {
        return points.contains(p);
    }

    public boolean hasPoint(double x,double y)
    {
        return points.contains(new Point2D.Double(x,y));
    }

    public Point2D.Double getPoint()
    {
        if(points.size()>0)
            return points.get(0);
        else
            return null;
    }

    private void move(double x,double y,double radius)
    {
        super.setFrame(x-radius,y-radius,radius*2f,radius*2f);
    }
}
