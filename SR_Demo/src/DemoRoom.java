/*
 * Author: Kaden Payne
 * Date: 8/25/2020; Update Date: 10/30/2020
 * 
 * Room where the action happens.
 */
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.animation.PathTransition;
import javafx.util.Duration;
/**
 * This class runs the panel that the character will be tested on. It makes a pane 
 * that hold the CharacterPane object, a circle, and two lines. The CharacterPane 
 * object is a triangle that is the test character. The circle follows the cursor 
 * as a way to "aim". One line is an "arrow" and the other is the path that the 
 * arrow travels. The character is moved using WASD and the arrow is "shot" with 
 * the click of the mouse. The character rotates by the movement of the cursor and 
 * when it moves around it. The arrow is placed off-screen until the mouse is clicked.
 * @author kjpay
 * @version 1.1
 */
public class DemoRoom extends Application{
    /**
     * This method makes a pane, a CharacterPane object, a circle, and two lines. 
     * The circle, one line called arrow, and CharacterPane is added to the pane 
     * in that order. The CharacterPane called character has a KeyEvent added to it.
     * The event is triggered when W, A, S, or D is pressed. W calls the setAllYUp 
     * method in the CharacterPane class. A calls the setAllXLeft method. S calls 
     * the setAllYDown method. D calls the setAllXRight method. All key press rotate 
     * the triangle to face the cursor and set the StartX and StartY of the line 
     * called pathLine respectively. The pane has two MouseEvent's added to it. 
     * The first event is triggered when the cursor moves. Then triggered, the 
     * circle has its center X and Y values changed to that of the cursor. The 
     * character also rotates with the rotateTriangle method(the key press trigger 
     * this method too). The EndX and EndY of pathLine is changed to that of the 
     * cursor. When the mouse is clicked, it triggers an event. The event rotates 
     * the arrow to match the rotation of the character and a PathTransition is played. 
     * The PathTransistion, or pt, has the Duration of 500 milliseconds and the 
     * path is pathLine with the Node being the arrow. Last but not less, a scene is 
     * made with the pane, the scene is added to the primaryStage, and the primaryStage 
     * gets the title of "SR Demo". The character request focus so that it can read 
     * the keys being pressed. Nine ObjectPane objects are made to be obstacle for 
     * the character to move around. While the character, the isCollidingBottom/Top/Left/Right 
     * methods are called to check if the character is colliding with the objects. 
     * If so, the character is stopped at the edge of the object. If not, the character moves normal.
     * The pathLine also checks if it's colliding with an object. If so, the pathLine 
     * ends at the edge of the object. If not, it continues to the mouse cursor.
     * @param primaryStage Where the character, circle, and arrow is displayed
     */
    @Override
    public void start(Stage primaryStage) {
        final int WIDTH = 2000, HEIGHT = 1000;
        Pane pane = new Pane();
        CharacterPane character = new CharacterPane();
        
        ObjectPane water = new ObjectPane(725, 650, 500, 150, Color.CYAN);
        pane.getChildren().add(water);
        
        //Path for arrow and arrow
        Line arrow = new Line(1940, 100, 1940, 140);
        arrow.setStroke(Color.SILVER);
        arrow.setStrokeWidth(4);
        pane.getChildren().add(arrow);
        
        //Objects to move around
        ObjectPane wallA = new ObjectPane(0, 0, 1930, 50, Color.BURLYWOOD);
        ObjectPane wallB = new ObjectPane(0, 50, 50, 1000, Color.BURLYWOOD);
        ObjectPane wallC = new ObjectPane(50, 950, 1920, 50, Color.BURLYWOOD);
        ObjectPane wallD = new ObjectPane(1873, 50, 50, 1000, Color.BURLYWOOD);
        ObjectPane box = new ObjectPane(300, 225, 150, 150, Color.SADDLEBROWN);
        ObjectPane box1 = new ObjectPane(300, 650, 150, 150, Color.SADDLEBROWN);
        ObjectPane box2 = new ObjectPane(1500, 225, 150, 150, Color.SADDLEBROWN);
        ObjectPane box3 = new ObjectPane(1500, 650, 150, 150, Color.SADDLEBROWN);
        pane.getChildren().addAll(wallA, wallB, wallC, wallD, box, box1, box2, box3);
        
        pane.getChildren().add(character);
        
        //Circle to follow cursor
        Circle circle = new Circle(1940, 200, 20);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.RED);
        circle.setOpacity(0.5);
        Line pathLine = new Line(character.getP1X(), character.getP1Y() + 30, circle.getCenterX(), circle.getCenterY());
        pane.getChildren().add(circle);
        
        //KeyEvents for moving the character
        character.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    if (!character.isCollidingBottom(wallA.getRectX(), wallA.getRectY(), wallA.getRectWidth(), wallA.getRectHeight()) && 
                            !character.isCollidingBottom(box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight()) && 
                            !character.isCollidingBottom(box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight()) && 
                            !character.isCollidingBottom(box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight()) && 
                            !character.isCollidingBottom(box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight()) && 
                            !character.isCollidingBottom(water.getRectX(), water.getRectY(), water.getRectWidth(), water.getRectHeight())) {
                        character.setAllYUp();
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingBottom(wallA.getRectX(), wallA.getRectY(), wallA.getRectWidth(), wallA.getRectHeight())) {
                        character.repositionAllYDown(wallA.getRectY(), wallA.getRectHeight());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingBottom(box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight())) {
                        character.repositionAllYDown(box.getRectY(), box.getRectHeight());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingBottom(box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight())) {
                        character.repositionAllYDown(box1.getRectY(), box1.getRectHeight());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingBottom(box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight())) {
                        character.repositionAllYDown(box2.getRectY(), box2.getRectHeight());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingBottom(box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight())) {
                        character.repositionAllYDown(box3.getRectY(), box3.getRectHeight());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingBottom(water.getRectX(), water.getRectY(), water.getRectWidth(), water.getRectHeight())) {
                        character.repositionAllYDown(water.getRectY(), water.getRectHeight());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                case S:
                    if (!character.isCollidingTop(wallC.getRectX(), wallC.getRectY(), wallC.getRectWidth(), wallC.getRectHeight()) &&
                            !character.isCollidingTop(box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight()) && 
                            !character.isCollidingTop(box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight()) && 
                            !character.isCollidingTop(box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight()) && 
                            !character.isCollidingTop(box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight()) && 
                            !character.isCollidingTop(water.getRectX(), water.getRectY(), water.getRectWidth(), water.getRectHeight())) {
                        character.setAllYDown();
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingTop(wallC.getRectX(), wallC.getRectY(), wallC.getRectWidth(), wallC.getRectHeight())) {
                        character.repositionAllYUp(wallC.getRectY());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingTop(box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight())) {
                        character.repositionAllYUp(box.getRectY());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingTop(box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight())) {
                        character.repositionAllYUp(box1.getRectY());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingTop(box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight())) {
                        character.repositionAllYUp(box2.getRectY());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingTop(box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight())) {
                        character.repositionAllYUp(box3.getRectY());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                    else if (character.isCollidingTop(water.getRectX(), water.getRectY(), water.getRectWidth(), water.getRectHeight())) {
                        character.repositionAllYUp(water.getRectY());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartY(character.getP1Y() + 30);
                        break;
                    }
                case A:
                    if (!character.isCollidingRight(wallB.getRectX(), wallB.getRectY(), wallB.getRectWidth(), wallB.getRectHeight()) && 
                            !character.isCollidingRight(box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight()) && 
                            !character.isCollidingRight(box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight()) && 
                            !character.isCollidingRight(box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight()) && 
                            !character.isCollidingRight(box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight()) && 
                            !character.isCollidingRight(water.getRectX(), water.getRectY(), water.getRectWidth(), water.getRectHeight())) {
                        character.setAllXLeft();
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingRight(wallB.getRectX(), wallB.getRectY(), wallB.getRectWidth(), wallB.getRectHeight())) {
                        character.repositionAllXRight(wallB.getRectX(), wallB.getRectWidth());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingRight(box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight())) {
                        character.repositionAllXRight(box.getRectX(), box.getRectWidth());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingRight(box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight())) {
                        character.repositionAllXRight(box1.getRectX(), box1.getRectWidth());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingRight(box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight())) {
                        character.repositionAllXRight(box2.getRectX(), box2.getRectWidth());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingRight(box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight())) {
                        character.repositionAllXRight(box3.getRectX(), box3.getRectWidth());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingRight(water.getRectX(), water.getRectY(), water.getRectWidth(), water.getRectHeight())) {
                        character.repositionAllXRight(water.getRectX(), water.getRectWidth());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                case D:
                    if (!character.isCollidingLeft(wallD.getRectX(), wallD.getRectY(), wallD.getRectWidth(), wallD.getRectHeight()) && 
                            !character.isCollidingLeft(box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight()) && 
                            !character.isCollidingLeft(box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight()) && 
                            !character.isCollidingLeft(box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight()) && 
                            !character.isCollidingLeft(box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight()) && 
                            !character.isCollidingLeft(water.getRectX(), water.getRectY(), water.getRectWidth(), water.getRectHeight())) {
                        character.setAllXRight();
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingLeft(wallD.getRectX(), wallD.getRectY(), wallD.getRectWidth(), wallD.getRectHeight())) {
                        character.repositionAllXLeft(wallD.getRectX());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingLeft(box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight())) {
                        character.repositionAllXLeft(box.getRectX());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingLeft(box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight())) {
                        character.repositionAllXLeft(box1.getRectX());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingLeft(box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight())) {
                        character.repositionAllXLeft(box2.getRectX());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingLeft(box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight())) {
                        character.repositionAllXLeft(box3.getRectX());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                    else if (character.isCollidingLeft(water.getRectX(), water.getRectY(), water.getRectWidth(), water.getRectHeight())) {
                        character.repositionAllXLeft(water.getRectX());
                        character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
                        pathLine.setStartX(character.getP1X());
                        break;
                    }
                default:
                    break;
            }
        });
        
        //Move circle with cursor and rotate character
        pane.setOnMouseMoved(e -> {
            circle.setCenterX(e.getX());
            circle.setCenterY(e.getY());
            character.rotateTriangle(circle.getCenterX(), circle.getCenterY());
            pathLine.setEndX(circle.getCenterX());
            pathLine.setEndY(circle.getCenterY());
            
            if (wallA.isCollidingBottom(pathLine.getEndX(), pathLine.getEndY())) {
                pathLine.setEndY(wallA.getRectY() + wallA.getRectHeight());
            }
            else if (wallB.isCollidingRight(pathLine.getEndX(), pathLine.getEndY())) {
                pathLine.setEndX(wallB.getRectX() + wallB.getRectWidth());
            }
            else if (wallC.isCollidingTop(pathLine.getEndX(), pathLine.getEndY())) {
                pathLine.setEndY(wallC.getRectY());
            }
            else if (wallD.isCollidingLeft(pathLine.getEndX(), pathLine.getEndY())) {
                pathLine.setEndX(wallD.getRectX());
            }
            
            if (box.isCollidingLineBottom(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight())) {
                pathLine.setEndY(box.getRectY() + box.getRectHeight());
            }
            else if (box.isCollidingLineRight(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight())) {
                pathLine.setEndX(box.getRectX() + box.getRectWidth());
            }
            
            if (box.isCollidingLineTop(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight())) {
                pathLine.setEndY(box.getRectY());
            }
            else if (box.isCollidingLineLeft(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box.getRectX(), box.getRectY(), box.getRectWidth(), box.getRectHeight())) {
                pathLine.setEndX(box.getRectX());
            }
            
            if (box1.isCollidingLineBottom(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight())) {
                pathLine.setEndY(box1.getRectY() + box1.getRectHeight());
            }
            else if (box1.isCollidingLineRight(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight())) {
                pathLine.setEndX(box1.getRectX() + box1.getRectWidth());
            }
            
            if (box1.isCollidingLineTop(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight())) {
                pathLine.setEndY(box1.getRectY());
            }
            else if (box1.isCollidingLineLeft(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box1.getRectX(), box1.getRectY(), box1.getRectWidth(), box1.getRectHeight())) {
                pathLine.setEndX(box1.getRectX());
            }
            
            if (box2.isCollidingLineBottom(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight())) {
                pathLine.setEndY(box2.getRectY() + box2.getRectHeight());
            }
            else if (box2.isCollidingLineRight(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight())) {
                pathLine.setEndX(box2.getRectX() + box2.getRectWidth());
            }
            
            if (box2.isCollidingLineTop(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight())) {
                pathLine.setEndY(box2.getRectY());
            }
            else if (box2.isCollidingLineLeft(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box2.getRectX(), box2.getRectY(), box2.getRectWidth(), box2.getRectHeight())) {
                pathLine.setEndX(box2.getRectX());
            }
            
            if (box3.isCollidingLineBottom(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight())) {
                pathLine.setEndY(box3.getRectY() + box3.getRectHeight());
            }
            else if (box3.isCollidingLineRight(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight())) {
                pathLine.setEndX(box3.getRectX() + box3.getRectWidth());
            }
            
            if (box3.isCollidingLineTop(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight())) {
                pathLine.setEndY(box3.getRectY());
            }
            else if (box3.isCollidingLineLeft(pathLine.getEndX(), pathLine.getEndY(), pathLine.getStartX(), pathLine.getStartY(), 
                    box3.getRectX(), box3.getRectY(), box3.getRectWidth(), box3.getRectHeight())) {
                pathLine.setEndX(box3.getRectX());
            }
        });
        
        //Making arrow animation
        PathTransition pt = new PathTransition(Duration.millis(500), pathLine, arrow);
        pt.setCycleCount(1);
        
        pane.setOnMouseClicked(e -> {
            arrow.setRotate(character.getDegrees());
            pt.play();
        });
        
        //Creating scene and showing stage
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setTitle("SR Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        character.requestFocus();
    }
    
    /**
     * This method launches the primaryStage.
     * @param args What is launched
     */
    //To lanch the stage
    public static void main(String[] args) {
        launch(args);
    }
}
