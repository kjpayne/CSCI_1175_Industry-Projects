/*
 * Author: Kaden Payne
 * Date: 10/23/2020
 * 
 * Changing the animation to use a thread
 */
import javafx.animation.PathTransition; 
import javafx.application.Application; 
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author kjpay
 */
public class FlagRisingAnimation extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        
        ImageView imageView = new ImageView("image/us.gif");
        pane.getChildren().add(imageView);
        
        PathTransition pt = new PathTransition(Duration.millis(10000), new Line(100, 200, 100, 0), imageView);
        pt.setCycleCount(5);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            pt.play();
                        }
                    });
                    
                    Thread.sleep(200);
                }
                catch (InterruptedException ex) {
                    System.out.println("Thread was interrupted");
                }
            }
        }).start();
        
        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setTitle("Flag Rising");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
