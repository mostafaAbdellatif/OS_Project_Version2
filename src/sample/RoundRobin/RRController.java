package sample.RoundRobin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.publicVariables;

import java.io.IOException;
import java.util.ArrayList;

public class RRController {

    publicVariables publicVariablesObject = new publicVariables();

    @FXML
    private TextField noProcesses;
    @FXML
    private TextField quantum;
    @FXML
    private Button okButton;
    @FXML
    private Button beginButton;
    @FXML
    private VBox processId;
    @FXML
    private VBox startTime;
    @FXML
    private VBox burstTime;


    public ArrayList<TextField> starts = new ArrayList(100);
    public ArrayList<TextField> bursts = new ArrayList(100);


    public void okEntred(ActionEvent event) throws Exception {

        processId.getChildren().clear();
        startTime.getChildren().clear();
        burstTime.getChildren().clear();


        if (!noProcesses.getText().isEmpty() && !quantum.getText().isEmpty()) {


            int inputNumber = Integer.parseInt(noProcesses.getText());
            publicVariablesObject.quantum = Integer.parseInt(quantum.getText());


            for (int x = 1; x <= inputNumber; x++) {
                Label y = new Label("Process" + x);
                y.setFont(new Font("Regular", 18));
                y.setAlignment(Pos.CENTER);
                y.setTextAlignment(TextAlignment.CENTER);
                y.setContentDisplay(ContentDisplay.CENTER);
                processId.getChildren().addAll(y);
                processId.setFillWidth(true);

                TextField start = new TextField();
                start.setFont(new Font("Regular", 12));
                start.setPromptText("Process "+ x + " Start");
                start.setAlignment(Pos.CENTER);
                start.setMinWidth(100);
                start.setMinHeight(27);
                starts.add(x-1,start);
                startTime.getChildren().addAll(start);
                startTime.setFillWidth(true);

                TextField burst = new TextField();
                burst.setFont(new Font("Regular", 12));
                burst.setPromptText("Process "+ x + " Burst");
                burst.setAlignment(Pos.CENTER);
                burst.setMinWidth(100);
                burst.setMinHeight(27);
                bursts.add(x-1,burst);
                burstTime.getChildren().addAll(burst);
                burstTime.setFillWidth(true);
            }
        }
    }


    public void beginEntered (ActionEvent event) throws Exception {
        boolean x = true;
        boolean y = true;
        //to delete all avalues in array and add the new written
        publicVariablesObject.starts.clear();
        publicVariablesObject.bursts.clear();

        for (int i = 0; i < Integer.parseInt(noProcesses.getText()); i++) {

            if (!starts.get(i).getText().isEmpty()) {
                int f = Integer.parseInt(starts.get(i).getText());
                publicVariablesObject.starts.add(f);
            } else {
                starts.get(i).setPromptText("Fill all");
                x = false;
            }
        }
        for (int i = 0; i < Integer.parseInt(noProcesses.getText()); i++) {

            if (!bursts.get(i).getText().isEmpty()) {
                int k = Integer.parseInt(bursts.get(i).getText());
                publicVariablesObject.bursts.add(k);
            } else {
                bursts.get(i).setPromptText("Fill all");
                y = false;
            }
        }
        //we have to come out with true x and y to make sure that all fields are filled;
        if((x && y) == true){
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("RRChart.fxml"));
                Parent root = (Parent) loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("GanttChart");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }




}