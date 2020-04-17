/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termpulse;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.TabPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


/**
 *
 * @author kroni
 */
public class TermPulse extends Application {
    private static Stage primaryStage;
    private ScrollPane rootLayout;
    public static double version=2.0;
    public static String title="TermPulse";
    public static String logFile = "rt_log";
    //public static String dbPropertiesFile="db.properties";
    private static TermPulse aInstance;
    private static TermPulseViewController theMainController;
    
   
    public void updateTitle(String newTitle){
        primaryStage.setTitle(newTitle);
    }
    public static TermPulse getInstance(){
        return aInstance;
    }
        
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage=stage;
        this.primaryStage.setTitle(title);
        this.primaryStage.getIcons().add(new Image(TermPulse.class.getResourceAsStream("/icon.gif")));
        this.primaryStage.setWidth(1200);
        this.primaryStage.setHeight(900);
        //this.primaryStage.setMaximized(true);
        
        initRootLayout();
     
    }
    
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TermPulseViewRoot.fxml"));
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(TermPulse.class.getResource("TermPulseViewRoot.fxml"));
            //loader.setController(new TermPulseViewController());
            rootLayout = (ScrollPane) fxmlLoader.load();
            theMainController = (TermPulseViewController)fxmlLoader.getController();
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static TermPulseViewController getMainController() {
        return theMainController;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
