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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Spencer Laird
 * EVERYTHING WORKS EXCEPT FOR THE KEYFRAME UPDATING CLOCK/DIGITAL IN REAL TIME.
 * INSTEAD IT DISPLAYS WITH EACH USE OF THE ACTION EVENT
 */
public class CPUMoniterModel {
    @FXML 
    public Label digital;
    
    @FXML
    public Timeline timeline;
  
   
    @FXML
    public KeyFrame keyframe;
    

    @FXML
    public double angleDeltaPerSeconds = 2.7;
    @FXML
    public boolean isRunning = false;
    @FXML
    public Label hey;
    
    @FXML
    public ImageView hand;
    
   
  
    
//    
  
    public CPUMoniterModel() {
       setUpTimer();
       
        
      

   }
    
    
    //hahah gpu
    @FXML
    public static double updateGpu(){
        
        OperatingSystemMXBean operatingSystemMXBean = 
                        ManagementFactory.getOperatingSystemMXBean();
        
        double value = 0;
        for(Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if(method.getName().startsWith("getSystemCpuLoad") && Modifier.isPublic(method.getModifiers())){
                try{
                    value = (double) method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = 0;
                }
                
                return value * 100;
            }
            
        }
        
        return value * 100;
  
    }
    
    @FXML
    public void setUpTimer(){
        
//         if(isRunning()){
//            timeline.stop();
//        }
        
        keyframe = new KeyFrame(Duration.millis(100),(ActionEvent) -> {
            update();
            
        });
        double cpu = updateGpu();
        double rotation = cpu * angleDeltaPerSeconds + 220;
       // hand.setRotate(rotation);
       // digital.setText(cpu + "%");
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        
    }
    
    @FXML
    public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
    
    @FXML
    public void update(){
        double cpu = updateGpu();
       double rotation = cpu * angleDeltaPerSeconds + 220;
       hand.setRotate(rotation);
       digital.setText(cpu + "%");
      // hey.setText(updateGpu() +"%");
       
   
    }
}
