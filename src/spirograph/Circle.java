package spirograph;

import java.lang.Math;
import java.awt.geom.*;
import java.util.ArrayList;

public class Circle extends Ellipse2D.Double
{
    private Point2D.Double point;

    private double angle;

    public Circle(double x,double y,double radius,double angle)
    {
        super(x,y,radius*2,radius*2);
        this.angle=angle;
        point=new Point2D.Double(0,0);
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
        setPos(x,getCenterY());
    }

    public void setY(double y)
    {
        setPos(getCenterX(),y);
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
        double diff=angle-this.angle;
        this.angle=angle;
        double x1=getPointX()-getCenterX();
        double y1=getPointY()-getCenterY();

        double x2=x1*Math.cos(diff)-y1*Math.sin(diff);
        double y2=x1*Math.sin(diff)+y1*Math.cos(diff);

        setPoint(x2+getCenterX(),y2+getCenterY());
    }

    public void changeAngle(double angle)
    {
        setAngle(this.angle+angle);
    }

    public void setPoint(Point2D.Double p)
    {
        point=p;
    }

    public void setPoint(double x,double y)
    {
        setPoint(new Point2D.Double(x,y));
    }

    public Point2D.Double getPoint()
    {
        return point;
    }
    
    public double getPointX()
    {
        return getPoint().getX();
    }
    
    public double getPointY()
    {
        return getPoint().getY();
    }

    private void move(double x,double y,double radius)
    {
        setPoint(getPointX()-getCenterX()+x,getPointY()-getCenterY()+y);
        super.setFrame(x,y,radius*2f,radius*2f);
    }
}
