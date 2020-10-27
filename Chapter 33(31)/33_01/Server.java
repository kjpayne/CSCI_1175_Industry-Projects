/*
 * Author: Kaden Payne
 * Date: 10/26/2020
 * 
 * Loan Server, makes the Loan objects
 */
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/**
 *
 * @author kjpay
 */
public class Server extends Application {
    private TextArea ta = new TextArea();
    
    @Override
    public void start(Stage primaryStage) {
        ta.setWrapText(true);
        
        Scene scene = new Scene(new ScrollPane(ta), 400, 200);
        primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        
        new Thread( () -> {
            try {
                //Creating server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater( () -> {
                    ta.appendText("Server started at " + new Date() + '\n');
                });
                
                //Listen for a client request
                Socket socket = serverSocket.accept();
                Platform.runLater( () -> {
                    ta.appendText("Connected to Client" + '\n');
                });
                
                //Getters of info for client and senders of info to client
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                
                while (true) {
                    //Creating Loan objects
                    double annualInterestRate = inputFromClient.readDouble();
                    int numberOfYears = inputFromClient.readInt();
                    double loanAmount = inputFromClient.readDouble();
                    
                    Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);
                    
                    //Find monthly and total payments
                    double monthlyPayment = loan.getMonthlyPayment();
                    double totalPayment = loan.getTotalPayment();
                    
                    //Sending payments to client
                    outputToClient.writeDouble(monthlyPayment);
                    outputToClient.writeDouble(totalPayment);
                    
                    Platform.runLater( () -> {
                        ta.appendText("Annual Interest Rate from Client: " + annualInterestRate + 
                                "\nNumber Of Years from Client: " + numberOfYears + "\nLoan Amount from Client: " + loanAmount + '\n');
                        ta.appendText("Monthly Payment to Client: " + monthlyPayment + "\nTotalPayment to Client: " + totalPayment + '\n');
                    });
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
