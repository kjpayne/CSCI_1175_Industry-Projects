/*
 * Author: Kaden Payne
 * Date: 10/29/2020
 * 
 * Rectangle objects for collision detection
 */
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * This class creates rectangle objects to create an environment in DemoRoom.
 * This objects will be used checking if the character collides with it and if so it
 * stops the character from moving into it. Each object will have a X and Y 
 * coordinate, width and height, and a color. The color is to let the player know
 * if the object is a wall or body of water. The character can't move through walls
 * or water. The arrow can't move through walls too but can move over water. Their 
 * values will be used in the collision method in CharacterPane
 * @author kjpay
 * @version 1.0
 */
public class ObjectPane extends Pane{
    //Variables for rectangle and color
    private Rectangle rect;
    private Color color;
    
    /**
     * This is the constructor for making environmental objects for the character 
     * to move around. A rectangle is made with a set X and Y, width and height, 
     * and color and then is added to the pane.
     */
    //Constructors
    public ObjectPane() {
        this(0, 0, 60, 60, Color.BROWN);
    }
    public ObjectPane(double x, double y, double width, double height, Color color) {
        rect = new Rectangle(x, y, width, height);
        rect.setFill(color);
        this.getChildren().add(rect);
    }
    
    //Getters and setters
    /**
     * This is getter for the rectangle's X coordinate
     * @return X coordinate of the rectangle
     */
    public double getRectX() {
        return rect.getX();
    }
    /**
     * This is the setter for the rectangle's X coordinate. It changes the old 
     * X value with a new X value
     * @param x The new X value for the rectangle's X coordinates
     */
    public void setRectX(double x) {
        this.rect.setX(x);
    }
    /**
     * This is getter for the rectangle's Y coordinate
     * @return Y coordinate of the rectangle
     */
    public double getRectY() {
        return rect.getY();
    }
    /**
     * This is the setter for the rectangle's Y coordinate. It changes the old 
     * Y value with a new Y value
     * @param y The new Y value for the rectangle's Y coordinates
     */
    public void setRectY(double y) {
        this.rect.setY(y);
    }
    /**
     * This is the getter for the rectangle's width
     * @return Width of the rectangle
     */
    public double getRectWidth() {
        return rect.getWidth();
    }
    /**
     * This is the setter for the rectangle's width. It changes the old 
     * width value with a new width value
     * @param width The new width value for the rectangle's width
     */
    public void setRectWidth(double width) {
        this.rect.setWidth(width);
    }
    /**
     * This is the getter for the rectangle's height 
     * @return Height of the rectangle
     */
    public double getRectHeight() {
        return rect.getHeight();
    }
    /**
     * This is the setter for the rectangle's height. It changes the old 
     * height value with a new height value
     * @param height The new height value for the rectangle's height
     */
    public void setRectHeight(double height) {
        this.rect.setHeight(height);
    }
    
    /**
     * This method if an object is being collided by a point. This one checks if 
     * the point is going to collide at the bottom of the object. If so, it returns
     * true. If not, it returns false.
     * @param x X coordinate of point
     * @param y Y coordinate of point
     * @return True if colliding, false if not
     */
    public boolean isCollidingBottom(double x, double y) {
        if (x > getRectX() && x < getRectX() + getRectWidth() && y > getRectY() && y + 1 < getRectY() + getRectHeight()) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method if an object is being collided by a point. This one checks if 
     * the point is going to collide at the top of the object. If so, it returns
     * true. If not, it returns false.
     * @param x X coordinate of point
     * @param y Y coordinate of point
     * @return True if colliding, false if not
     */
    public boolean isCollidingTop(double x, double y) {
        if (x > getRectX() && x < getRectX() + getRectWidth() && y + 1 > getRectY() && y < getRectY() + getRectHeight()) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method if an object is being collided by a point. This one checks if 
     * the point is going to collide at the left of the object. If so, it returns
     * true. If not, it returns false.
     * @param x X coordinate of point
     * @param y Y coordinate of point
     * @return True if colliding, false if not
     */
    public boolean isCollidingLeft(double x, double y) {
        if (x + 1 > getRectX() && x < getRectX() + getRectWidth() && y > getRectY() && y < getRectY() + getRectHeight()) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method if an object is being collided by a point. This one checks if 
     * the point is going to collide at the right of the object. If so, it returns
     * true. If not, it returns false.
     * @param x X coordinate of point
     * @param y Y coordinate of point
     * @return True if colliding, false if not
     */
    public boolean isCollidingRight(double x, double y) {
        if (x > getRectX() && x + 1 < getRectX() + getRectWidth() && y > getRectY() && y < getRectY() + getRectHeight()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * This method checks if two line are intersecting. If they are, it returns true.
     * If not, it returns false.
     * @param x1 X coordinate of the beginning of the first line
     * @param y1 Y coordinate of the beginning of the first line
     * @param x2 X coordinate of the ending of the first line
     * @param y2 Y coordinate of the ending of the first line
     * @param x3 X coordinate of the beginning of the second line
     * @param y3 Y coordinate of the beginning of the second line
     * @param x4 X coordinate of the ending of the second line
     * @param y4 Y coordinate of the ending of the second line
     * @return 
     */
    public boolean isCollidingLine(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
        double uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
        
        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method checks if a line is colliding with bottom of an object using 
     * the isCollidingLine method in the ObjectPane class. If the line is colliding,
     * it returns true. If not, it return false.
     * @param x1 X coordinate of the beginning of the line
     * @param y1 Y coordinate of the beginning of the line
     * @param x2 X coordinate of the ending of the line
     * @param y2 Y coordinate of the ending of the line
     * @param rx X coordinate of the object
     * @param ry Y coordinate of the object
     * @param rw Width of the object
     * @param rh Height of the object
     * @return True if line is colliding, false if not
     */
    public boolean isCollidingLineBottom(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {
        if (isCollidingLine(x1, y1, x2, y2, rx, ry + rh, rx + rw, ry + rh)) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method checks if a line is colliding with top of an object using 
     * the isCollidingLine method in the ObjectPane class. If the line is colliding,
     * it returns true. If not, it return false.
     * @param x1 X coordinate of the beginning of the line
     * @param y1 Y coordinate of the beginning of the line
     * @param x2 X coordinate of the ending of the line
     * @param y2 Y coordinate of the ending of the line
     * @param rx X coordinate of the object
     * @param ry Y coordinate of the object
     * @param rw Width of the object
     * @param rh Height of the object
     * @return True if line is colliding, false if not
     */
    public boolean isCollidingLineTop(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {
        if (isCollidingLine(x1, y1, x2, y2, rx, ry, rx + rw, ry)) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method checks if a line is colliding with right of an object using 
     * the isCollidingLine method in the ObjectPane class. If the line is colliding,
     * it returns true. If not, it return false.
     * @param x1 X coordinate of the beginning of the line
     * @param y1 Y coordinate of the beginning of the line
     * @param x2 X coordinate of the ending of the line
     * @param y2 Y coordinate of the ending of the line
     * @param rx X coordinate of the object
     * @param ry Y coordinate of the object
     * @param rw Width of the object
     * @param rh Height of the object
     * @return True if line is colliding, false if not
     */
    public boolean isCollidingLineRight(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {
        if (isCollidingLine(x1, y1, x2, y2, rx + rw, ry, rx + rw, ry + rh)) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method checks if a line is colliding with left of an object using 
     * the isCollidingLine method in the ObjectPane class. If the line is colliding,
     * it returns true. If not, it return false.
     * @param x1 X coordinate of the beginning of the line
     * @param y1 Y coordinate of the beginning of the line
     * @param x2 X coordinate of the ending of the line
     * @param y2 Y coordinate of the ending of the line
     * @param rx X coordinate of the object
     * @param ry Y coordinate of the object
     * @param rw Width of the object
     * @param rh Height of the object
     * @return True if line is colliding, false if not
     */
    public boolean isCollidingLineLeft(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {
        if (isCollidingLine(x1, y1, x2, y2, rx, ry, rx, ry + rh)) {
            return true;
        }
        else {
            return false;
        }
    }
}