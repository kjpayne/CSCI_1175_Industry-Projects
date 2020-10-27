/*
 * Author: Kaden Payne
 * Date: 10/27/2020
 * 
 * The server for a chat room
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javafx.application.Application;
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
public class ChatServer extends Application {
    DataInputStream inputFromClient = null;
    DataOutputStream outputToClient = null;
    //Text box for connversation
    private TextArea taServer = new TextArea();
    private TextArea taClient = new TextArea();
    
    @Override
    public void start(Stage primaryStage) {
        taServer.setWrapText(true);
        taClient.setWrapText(true);
        taServer.setEditable(false);
        //taClient.setDisable(true);
        
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
        primaryStage.setTitle("ChatServer"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        
        //Sending text to client
        taClient.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    String textFromServer = taClient.getText().trim();

                    outputToClient.writeUTF(textFromServer);

                    //adding text to History
                    Platform.runLater( () -> {
                        taServer.appendText("Server: " + textFromServer + '\n');
                    });

                    outputToClient.flush();
                    taClient.clear();
                }
                catch (IOException ex) {
                    taServer.appendText(ex.toString() + '\n');
                }
            }
        });
        
        new Thread( () -> {
            try {
                //Creating server socket
                ServerSocket serverSocket = new ServerSocket(800);
                Platform.runLater( () -> {
                    taServer.appendText("Server started at " + new Date() + '\n');
                });
                
                //Listen for a client request
                Socket socket = serverSocket.accept();
                Platform.runLater( () -> {
                    taServer.appendText("Connected to Client" + '\n');
                });
                
                //Getters of info for client and senders of info to client
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());
                
                while (true) {
                    //Gets text from client and displays in History
                    String textFromClient = inputFromClient.readUTF().trim();
                    
                    Platform.runLater( () -> {
                        taServer.appendText("Client: " + textFromClient + '\n');
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
