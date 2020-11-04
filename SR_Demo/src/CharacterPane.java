/*
 * Author/: Kaden Payne
 * Date: 8/25/2020; Update Date: 10/29/2020
 * 
 * Pane that will draw the character, character is a triangle.
 */
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
/**
 * This class creates a pane that makes a triangle. The triangle is created using
 * the Point2D class for the three points of the triangle. There is a getter and 
 * setter for the X and Y of each point. There's also a setter that sets all the 
 * X's together and all the Y's together. This is because the triangle will be 
 * moving up, down, left, and right. The triangle will also rotate by the movement 
 * of the cursor and when it moves around it. There's also a getter for the degrees 
 * because another object needs to be at the same rotation as the triangle. This
 * class also has a hit box for collision detection with objects. There are getters 
 * and setters for the X and Y coordinates and getters for the width and height.
 * The collision method takes the X and Y, and width and height of an object and 
 * checks if the character is colliding with the object. 
 * @author kjpay
 * @version 1.1
 */
public class CharacterPane extends Pane{
    //Points for the triangle, triangle, and dergees for rotation
    private Point2D p1 = new Point2D(1000, 470);
    private Point2D p2 = new Point2D(970, 530);
    private Point2D p3 = new Point2D(1030, 530);
    private Polygon triangle;
    private double degrees;
    private Rectangle hitbox;
    
    /**
     * This is the constructor for making an object for this class. It makes the 
     * triangle a new Polygon and adds the X and Y coordinates for p1, p2, and p3. 
     * The triangle's stroke is set to black and the fill is set to green. The 
     * triangle is then added to the pane.
     */
    //Constuctor
    public CharacterPane() {
        this.triangle = new Polygon();
        triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
        triangle.setStroke(Color.BLACK);
        triangle.setFill(Color.GREEN);
        this.hitbox = new Rectangle(970, 470, 60, 60);
        hitbox.setStroke(Color.BLACK);
        //this.getChildren().add(hitbox);
        this.getChildren().add(triangle);
    }
    
    /**
     * This is the getter for p1's X value.
     * @return X value of p1
     */
    //Getters and Setters
    public double getP1X() {
        return p1.getX();
    }
    /**
     * This is the getter for p1's Y value.
     * @return Y value of p1
     */
    public double getP1Y() {
        return p1.getY();
    }
    /**
     * This is the setter for p1's X value. It gives p1 a new X coordinate while 
     * keeping its Y coordinate. It then clears all the old points of the triangle 
     * and adds the new points to the triangle. This helps the triangle to move.
     * @param x The new X value for p1
     */
    public void setP1X(double x) {
        this.p1 = new Point2D(x, p1.getY());
        triangle.getPoints().clear();
        triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }
    /**
     * This is the setter for p1's Y value. It gives p1 a new Y coordinate while 
     * keeping its X coordinate. It then clears all the old points of the triangle 
     * and adds the new points to the triangle. This helps the triangle to move.
     * @param y The new Y value for p1
     */
    public void setP1Y(double y) {
        this.p1 = new Point2D(p1.getX(), y);
        triangle.getPoints().clear();
        triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }
    /**
     * This is the getter for p2's X value
     * @return X value of p2
     */
    public double getP2X() {
        return p2.getX();
    }
    /**
     * This is the getter for p2's Y value
     * @return Y value of p2
     */
    public double getP2Y() {
        return p2.getY();
    }
    /**
     * This is the setter for p2's X value. It gives p1 a new X coordinate while 
     * keeping its Y coordinate. It then clears all the old points of the triangle 
     * and adds the new points to the triangle. This helps the triangle to move.
     * @param x The new X value for p2
     */
    public void setP2X(double x) {
        this.p2 = new Point2D(x, p2.getY());
        triangle.getPoints().clear();
        triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }
    /**
     * This is the setter for p2's Y value. It gives p1 a new Y coordinate while 
     * keeping its X coordinate. It then clears all the old points of the triangle 
     * and adds the new points to the triangle. This helps the triangle to move.
     * @param y The new Y value for p2
     */
    public void setP2Y(double y) {
        this.p2 = new Point2D(p2.getX(), y);
        triangle.getPoints().clear();
        triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }
    /**
     * This the getter for p3's X value.
     * @return X value of p3
     */
    public double getP3X() {
        return p3.getX();
    }
    /**
     * This is getter for p3's Y value
     * @return Y value of p3
     */
    public double getP3Y() {
        return p3.getY();
    }
    /**
     * This is the setter for p3's X value. It gives p1 a new X coordinate while 
     * keeping its Y coordinate. It then clears all the old points of the triangle 
     * and adds the new points to the triangle. This helps the triangle to move.
     * @param x The new X value for p3
     */
    public void setP3X(double x) {
        this.p3 = new Point2D(x, p3.getY());
        triangle.getPoints().clear();
        triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }
    /**
     * This is the setter for p3's Y value. It gives p1 a new Y coordinate while 
     * keeping its X coordinate. It then clears all the old points of the triangle 
     * and adds the new points to the triangle. This helps the triangle to move.
     * @param y The new Y value for p3
     */
    public void setP3Y(double y) {
        this.p3 = new Point2D(p3.getX(), y);
        triangle.getPoints().clear();
        triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }
    
    /**
     * This is the getter for the X coordinate of the hit box.
     * @return X coordinate of the hit box
     */
    public double getHitboxX() {
        return hitbox.getX();
    }
    /**
     * This is the setter for the X coordinate of the hit box. It changes the
     * old X value to the new X value in the method.
     * @param x The new X coordinate for the hit box
     */
    public void setHitboxX(double x) {
        this.hitbox.setX(x);
    }
    /**
     * This is the getter for the Y coordinate of the hit box.
     * @return Y coordinate of the hit box
     */
    public double getHitboxY() {
        return hitbox.getY();
    }
    /**
     * This is the setter for the Y coordinate of the hit box. It changes the
     * old Y value to the new Y value in the method.
     * @param y The new Y coordinate for the hit box
     */
    public void setHitboxY(double y) {
        this.hitbox.setY(y);
    }
    /**
     * This is the getter for the width of the hit box.
     * @return Width of the hit box
     */
    public double getHitboxWidth() {
        return hitbox.getWidth();
    }
    /**
     * This is the getter for the height of the hit box.
     * @return Height of the hit box
     */
    public double getHitboxHeight() {
        return hitbox.getHeight();
    }
    
    /**
     * This method calls the Y setters of all the points and set the Y value to 
     * 5 minus its current value if the value is greater than 0 or 60 respectively. 
     * If they are less than those values, they are made those values instead. This 
     * moves the triangle up. This also moves the hit box so that it stays with
     * the character as they move.
     */
    //Moving the Character
    public void setAllYUp() {
        setP1Y(getP1Y() > 0 ? getP1Y() - 5 : 0);
        setP2Y(getP2Y() > 60 ? getP2Y() - 5 : 60);
        setP3Y(getP3Y() > 60 ? getP3Y() - 5 : 60);
        setHitboxY(getHitboxY() > 0 ? getHitboxY() - 5 : 0);
    }
    /**
     * This method calls the Y setters of all the points and set the Y value to 
     * 5 plus its current value if the value is less than 940 or 1000 respectively. 
     * If they are greater than those values, they are made those values instead. This 
     * moves the triangle down. This also moves the hit box so that it stays with
     * the character as they move.
     */
    public void setAllYDown() {
        setP1Y(getP1Y() < 940 ? getP1Y() + 5 : 940);
        setP2Y(getP2Y() < 1000 ? getP2Y() + 5 : 1000);
        setP3Y(getP3Y() < 1000 ? getP3Y() + 5 : 1000);
        setHitboxY(getHitboxY() < 940 ? getHitboxY() + 5 : 940);
    }
    /**
     * This method calls the X setters of all the points and set the X value to 
     * 5 minus its current value if the value is greater than 30, 0, or 60 respectively. 
     * If they are less than those values, they are made those values instead. This 
     * moves the triangle to the left. This also moves the hit box so that it stays with
     * the character as they move.
     */
    public void setAllXLeft() {
        setP1X(getP1X() > 30 ? getP1X() - 5 : 30);
        setP2X(getP2X() > 0 ? getP2X() - 5 : 0);
        setP3X(getP3X() > 60 ? getP3X() - 5 : 60);
        setHitboxX(getHitboxX() > 0 ? getHitboxX() - 5 : 0);
    }
    /**
     * This method calls the X setters of all the points and set the X value to 
     * 5 plus its current value if the value is less than 1890, 1960, or 1920 respectively. 
     * If they are greater than those values, they are made those values instead. 
     * This moves the triangle to the right. This also moves the hit box so that it stays with
     * the character as they move.
     */
    public void setAllXRight() {
        setP1X(getP1X() < 1890 ? getP1X() + 5 : 1890);
        setP2X(getP2X() < 1860 ? getP2X() + 5 : 1860);
        setP3X(getP3X() < 1920 ? getP3X() + 5 : 1920);
        setHitboxX(getHitboxX() < 1860 ? getHitboxX() + 5 : 1860);
    }
    
    /**
     * This rotates the triangle. It takes the center of a circle(cX and cY) and 
     * minus the X and Y of the circle with the X and Y of p1 respectively. The 
     * total distance between the two is calculated by squaring the distance between
     * the X's time itself plus the distance between the Y's time itself. A move 
     * distance is made for the X and Y by dividing the distance of the X and Y 
     * with the total distance. A total move distance is made the same way as the 
     * total distance with using the move distance respectively. A new move distance 
     * is make the same way as the original move distance using the original with 
     * the total. An angle is found between the new move distance and is then converted 
     * to degrees. The triangle's rotation is set to those degrees.
     * @param cX The centerX of a circle
     * @param cY The centerY of a circle
     */
    //Rotating the character
    public void rotateTriangle(double cX, double cY) {
        double distanceX = cX - getP1X();
        double distanceY = cY - getP1Y();
        double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        double moveDistanceX = distanceX / distanceTotal;
        double moveDistanceY = distanceY / distanceTotal;
        double totalMove = Math.sqrt(moveDistanceX * moveDistanceX + moveDistanceY * moveDistanceY);
        double newMoveDX = moveDistanceX / totalMove;
        double newMoveDY = moveDistanceY / totalMove;
        double angle = Math.atan2(newMoveDY, newMoveDX);
        degrees = Math.toDegrees(angle) + 90;
        triangle.setRotate(degrees);
    }
    /**
     * The getter for the degrees. It's used to rotate a line in the main class.
     * @return The degrees from the rotateTriangle method
     */
    public double getDegrees() {
        return degrees;
    }
    
    //May not use this collision method
    /**
     * This method checks if the character/hit box is colliding with an object. 
     * If it's colliding with an object, it returns true which will stop the 
     * character from moving. If it's not, it returns false ane the character can 
     * still move.
     * @param x The X coordinate of the object
     * @param y The Y coordinate of the object
     * @param width The width of the object
     * @param height The height of the object
     * @return True if character is colliding, false if not.
     */
    //Collision detection
    public boolean isColliding(double x, double y, double width, double height) {
        if (getHitboxX() + getHitboxWidth() + 5 > x && getHitboxX() - 5 < x + width 
                && getHitboxY() + getHitboxHeight() + 5 > y && getHitboxY() - 5 < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * This method repositions the character if it's colliding with an object. 
     * This one repositions the character down if the character is colliding at 
     * the bottom of the object
     * @param y The Y coordinate of the object
     * @param height The height of the object
     */
    //Collision reposition
    public void repositionAllYDown(double y, double height) {
        setP1Y(y + height);
        setP2Y(y + height + 60);
        setP3Y(y + height + 60);
        setHitboxY(y + height);
    }
    /**
     * This method repositions the character if it's colliding with an object. 
     * This one repositions the character up if the character is colliding at 
     * the top of the object
     * @param y The Y coordinate of the object
     */
    public void repositionAllYUp(double y) {
        setP1Y(y - 60);
        setP2Y(y);
        setP3Y(y);
        setHitboxY(y - 60);
    }
    /**
     * This method repositions the character if it's colliding with an object. 
     * This one repositions the character to the right if the character is colliding at 
     * the left of the object
     * @param x The X coordinate of the object
     * @param width The width of the object
     */
    public void repositionAllXRight(double x, double width) {
        setP1X(x + width + 30);
        setP2X(x + width);
        setP3X(x + width + 60);
        setHitboxX(x + width);
    }
    /**
     * This method repositions the character if it's colliding with an object. 
     * This one repositions the character to the left if the character is colliding at 
     * the right of the object
     * @param x 
     */
    public void repositionAllXLeft(double x) {
        setP1X(x - 30);
        setP2X(x - 60);
        setP3X(x);
        setHitboxX(x - 60);
    }
    
    /**
     * This method checks if the character is colliding with the left of an object.
     * It checks if the x plus the width of the hit box at the moving speed of 
     * the character is greater than the X coordinate of the object while also 
     * checking if the character is inside the object. If the character is colliding 
     * with the left, it returns true. If not, it returns false.
     * @param x X coordinate of the object
     * @param y Y coordinate of the object
     * @param width Width of the object
     * @param height Height of the object
     * @return True if hit box is in object, false if not
     */
    public boolean isCollidingLeft(double x, double y, double width, double height) {
        if (getHitboxX() + getHitboxWidth() + 5 > x && getHitboxX() < x + width && 
                getHitboxY() + getHitboxHeight() > y && getHitboxY() < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method checks if the character is colliding with the right of an object.
     * It checks if the x of the hit box at the moving speed of the character is 
     * greater than the X coordinate plus the width of the object while also 
     * checking if the character is inside the object. If the character is colliding 
     * with the right, it returns true. If not, it returns false.
     * @param x X coordinate of the object
     * @param y Y coordinate of the object
     * @param width Width of the object
     * @param height Height of the object
     * @return True if hit box is in object, false if not
     */
    public boolean isCollidingRight(double x, double y, double width, double height) {
        if (getHitboxX() + getHitboxWidth() > x && getHitboxX() - 5 < x + width && 
                getHitboxY() + getHitboxHeight() > y && getHitboxY() < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method checks if the character is colliding with the top of an object.
     * It checks if the y plus the height of the hit box at the moving speed of 
     * the character is greater than the Y coordinate of the object while also 
     * checking if the character is inside the object. If the character is colliding 
     * with the top, it returns true. If not, it returns false.
     * @param x X coordinate of the object
     * @param y Y coordinate of the object
     * @param width Width of the object
     * @param height Height of the object
     * @return True if hit box is in object, false if not
     */
    public boolean isCollidingTop(double x, double y, double width, double height) {
        if (getHitboxX() + getHitboxWidth() > x && getHitboxX() < x + width && 
                getHitboxY() + getHitboxHeight() + 5 > y && getHitboxY() < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method checks if the character is colliding with the bottom of an object.
     * It checks if the y of the hit box at the moving speed of the character is 
     * greater than the Y coordinate plus the height of the object while also 
     * checking if the character is inside the object. If the character is colliding 
     * with the bottom, it returns true. If not, it returns false.
     * @param x X coordinate of the object
     * @param y Y coordinate of the object
     * @param width Width of the object
     * @param height Height of the object
     * @return True if hit box is in object, false if not
     */
    public boolean isCollidingBottom(double x, double y, double width, double height) {
        if (getHitboxX() + getHitboxWidth() > x && getHitboxX() < x + width && 
                getHitboxY() + getHitboxHeight() > y && getHitboxY() - 5 < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
}