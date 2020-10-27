/*
 * Author: Kaden Payne
 * Date: 10/26/2020
 * 
 * Loan Client, sent loan info to Server to make Loan object
 */
import java.io.*;
import java.net.Socket;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 *
 * @author kjpay
 */
public class Client extends Application {
    //Getters and senders to and from server
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    //Variables
    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfNumOfYears = new TextField();
    private TextField tfLoanAmount = new TextField();
    private Button btSubmit= new Button("Submit");
    
    private TextArea ta = new TextArea();
    
    @Override
    public void start(Stage primaryStage) {
        ta.setWrapText(true);
        
        //GridPane to hold the textfields and labels for them
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Annual Interest Rate"), 0, 0);
        gridPane.add(new Label("Number Of Years"), 0, 1);
        gridPane.add(new Label("Loan Amount"), 0, 2);
        gridPane.add(tfAnnualInterestRate, 1, 0);
        gridPane.add(tfNumOfYears, 1, 1);
        gridPane.add(tfLoanAmount, 1, 2);
        gridPane.add(btSubmit, 2, 1);
        
        //Aligning textfields
        tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
        tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
        tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
        
        //Setting column count
        tfAnnualInterestRate.setPrefColumnCount(5);
        tfNumOfYears.setPrefColumnCount(5);
        tfLoanAmount.setPrefColumnCount(5);
        
        //BorderPane to hold all other panes
        BorderPane pane = new BorderPane();
        pane.setCenter(new ScrollPane(ta));
        pane.setTop(gridPane);
        
        //Creating Scene to show in stage
        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        
        btSubmit.setOnAction(e -> {
            //Getter values from textfields
            try {
                double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText().trim());
                int numberOfYears = Integer.parseInt(tfNumOfYears.getText().trim());
                double loanAmount = Double.parseDouble(tfLoanAmount.getText().trim());
                
                //Sending Values to server
                toServer.writeDouble(annualInterestRate);
                toServer.writeInt(numberOfYears);
                toServer.writeDouble(loanAmount);
                toServer.flush();
                
                //Getting payments from server
                double monthlyPayment = fromServer.readDouble();
                double totalPayment = fromServer.readDouble();
                
                ta.appendText("Annual Interest Rate: " + annualInterestRate + "\nNumber of Years: " + numberOfYears + 
                        "\nLoan Amount: " + loanAmount + '\n');
                ta.appendText("Monthly Payment from Server: " + monthlyPayment + "\nTotal Payment from Server: " + totalPayment + '\n');
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        });
        
        try {
            //Connecting to server
            Socket socket = new Socket("localhost", 8000);
            
            //Creating the input and output for the client
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
            ta.appendText(ex.toString() + '\n');
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
