/*
 * Author: Kaden Payne
 * Date: 10/27/2020
 * 
 * Client side of a chat room
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author kjpay
 */
public class ChatClient extends Application {
    //Getters and senders to and from server
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    
    //Connversation of chat in TextAreas
    private TextArea taServer = new TextArea();
    private TextArea taClient = new TextArea();
    
    @Override
    public void start(Stage primaryStage) {
        taServer.setWrapText(true);
        taClient.setWrapText(true);
        //taServer.setDisable(true);
        
        //BorderPanes to hold history of chat and current chat
        BorderPane pane1 = new BorderPane();
        pane1.setTop(new Label("History"));
        pane1.setCenter(new ScrollPane(taServer));
        BorderPane pane2 = new BorderPane();
        pane2.setTop(new Label("New Message"));
        pane2.setCenter(new ScrollPane(taClient));
        
        //VBox to hold above panes
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(pane1, pane2);
        
        //Creating scene with vbox in it and then showing it on stage
        Scene scene = new Scene(vBox, 400, 400);
        primaryStage.setTitle("ChatClient"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        
        //Sending text to server
        taClient.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    String textFromClient = taClient.getText().trim();

                    toServer.writeUTF(textFromClient);

                    //adding text to History
                    Platform.runLater( () -> {
                        taServer.appendText("Client: " + textFromClient + '\n');
                    });

                    toServer.flush();
                    taClient.clear();
                }
                catch (IOException ex) {
                    taServer.appendText(ex.toString() + '\n');
                }
            }
        });
        
        new Thread( () -> {
            try {
                //Connecting to server
                Socket socket = new Socket("localhost", 800);
                Platform.runLater( () -> {
                    taServer.appendText("Connected to Server." + '\n');
                });

                //Creating the input and output for the client
                fromServer = new DataInputStream(socket.getInputStream());
                toServer = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    //Gets text from server and displays in History
                    String textFromServer = fromServer.readUTF().trim();

                    Platform.runLater( () -> {
                        taServer.appendText("Server: " + textFromServer + '\n');
                    });
                }
            }
            catch (IOException ex) {
                taServer.appendText(ex.toString() + '\n');
            }
        }).start();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
