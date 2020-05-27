/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salf22cpumoniterfxml;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import static salf22cpumoniterfxml.CPUMoniterModel.updateGpu;

/**
 *
 * @author Spencer Laird
 * 
 * EVERYTHING WORKS EXCEPT FOR THE KEYFRAME UPDATING CLOCK/DIGITAL IN REAL TIME.
 * INSTEAD IT DISPLAYS WITH EACH USE OF THE ACTION EVENT
 * 
 * 
 * 
 */
public class FXMLDocumentController implements Initializable {
    
    CPUMoniterModel model = new CPUMoniterModel();
    
    @FXML
    private Label label;
    
    @FXML 
    private Label gpu;
    
    @FXML
    private Button startStop;
    
    @FXML
    private Button record;
    
    @FXML
    private Label recordOne;
    @FXML
    private Label recordTwo;
    @FXML
    private Label recordThree;
    @FXML
    private ImageView hand;
    
    @FXML
    public Timeline timeline;
    
    @FXML
    public double angleDeltaPerSeconds = 2.7;
    @FXML
    public KeyFrame keyframe;
    
     private int data;
    
    //new variables
   
    
   
    
    
    
    @FXML
    private void handleStartButtonAction(ActionEvent event) {
      
        
        //model.setUpTimer();
        if(model.isRunning() == true){
            //model.digital.setText("hey");
            model.timeline.stop();
            record.setText("Reset");
            startStop.setText("Start");
           
             
            
        }
        else{ 
           
            model.timeline.play();
            startStop.setText("Stop");
            record.setText("Record");
           // double cpu = model.updateGpu();
            NumberFormat formatter = new DecimalFormat("#0.00");
           // gpu.setText(formatter.format(cpu)+ "%");
            
           // timeline = new Timeline(new KeyFrame(Duration.millis(100), (ActionEvent) -> {
           
             double cpu = model.updateGpu();
           double rotation = cpu * angleDeltaPerSeconds + 220;
          
          
           hand.setRotate(rotation);
           gpu.setText(formatter.format(cpu)+ "%");
           timeline.play();
           timeline.setCycleCount(Animation.INDEFINITE);
            
    
           
           
             
            
        //}));
        }
  
    }
    
    @FXML
    private void record(ActionEvent event) {
        
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        
       
        NumberFormat formatter = new DecimalFormat("#0.00");
        double cpu = model.updateGpu();
        if(model.isRunning() == true){
            data++;
            if(data == 1){
                recordOne.setText("Record: " + data + ": " + formatter.format(cpu)+ " at " + format.format(date));
                double rotation = cpu * angleDeltaPerSeconds + 220;
                hand.setRotate(rotation);
                gpu.setText(formatter.format(cpu)+ "%");
            }
           
            if(data == 2){
                recordTwo.setText("Record: " + data + ": " + formatter.format(cpu) + " at " + format.format(date));
                double rotation = cpu * angleDeltaPerSeconds + 220;
           hand.setRotate(rotation);
           gpu.setText(formatter.format(cpu)+ "%");
            }
            if(data == 3){
                recordThree.setText("Record: " + data + ": " + formatter.format(cpu) + " at " + format.format(date));
                double rotation = cpu * angleDeltaPerSeconds + 220;
           hand.setRotate(rotation);
           gpu.setText(formatter.format(cpu)+ "%");
            }
            if(data == 4){
                recordOne.setText("Record: " + data + ": " + formatter.format(cpu) + " at " + format.format(date));
                double rotation = cpu * angleDeltaPerSeconds + 220;
           hand.setRotate(rotation);
           gpu.setText(formatter.format(cpu)+ "%");
            }
            if(data == 5){
                recordTwo.setText(recordOne.getText());
                recordOne.setText("Record: " + data + ": " + formatter.format(cpu) + " at " + format.format(date));
                double rotation = cpu * angleDeltaPerSeconds + 220;
           hand.setRotate(rotation);
           gpu.setText(formatter.format(cpu)+ "%");
            }
            if(data >=6){
                recordThree.setText(recordTwo.getText());
                recordTwo.setText(recordOne.getText());
                recordOne.setText("Record: " + data + ": " + formatter.format(cpu) + " at " + format.format(date));
                double rotation = cpu * angleDeltaPerSeconds + 220;
           hand.setRotate(rotation);
           gpu.setText(formatter.format(cpu)+ "%");
            }
        }
        else if(model.isRunning() == false){
            recordOne.setText("--:--.--");
            recordTwo.setText("--:--.--");
            recordThree.setText("--:--.--");
            data = 0;
            gpu.setText("0.00%");
            hand.setRotate(-135);
        }
        
    }
    
 
   
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model.setUpTimer();
    }    
    
}
