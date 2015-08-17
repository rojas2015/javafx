/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnt;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author yang
 */
public class HNT extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
 
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(HNT.class.getResource("/hnt/showHNT.fxml"));
        BorderPane bp= loader.load();
        
        ShowHNTController shnt=  loader.getController();
        //shnt.doinitialize();
        
        StackPane root = new StackPane();
        root.getChildren().add(bp);
        
        Scene scene = new Scene(root, 650, 550);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
