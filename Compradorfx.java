/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Adam Augusto
 */
public class Compradorfx extends Application {
    Evento evento;
    
    public Compradorfx(Evento evento){
        this.evento=evento;
    }
    @Override
    public void start(Stage primaryStage) {
        
        GridPane comprador = new GridPane();
        comprador.setPadding(new Insets(15,12,15,12));
        comprador.setVgap(5);
        comprador.setHgap(5);
        
        
        Text compradorzin = new Text("Insira seus dados");
        comprador.add(compradorzin, 0, 0,3,1);
        comprador.setHalignment(compradorzin, HPos.CENTER);
        Label lNome = new Label("Nome:");
        comprador.add(lNome,0,1);
        TextField tNome = new TextField();
        comprador.add(tNome,1,1);
        Label lCPF = new Label ("CPF:");
        comprador.add(lCPF,0,2);
        TextField tCPF = new TextField();
        comprador.add(tCPF,1,2);
        Label numeros= new Label("(Somento números)");
        comprador.add(numeros,2,2);
        tNome.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>() {
           String alfabeto = "abcdefghijklmnopqrstuvwxyzABDEFGHIJKLMNOPQRSTUVWXYZ ";
           
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
               
                if(!(KeyCode.BACK_SPACE==event.getCode())){
                    if((alfabeto.indexOf(event.getCharacter().charAt(0))<0)){
                        System.out.println("Não é letra");
                        event.consume();
                    }
                }
            }
        });
        
        Button btn = new Button();
        btn.setText("Continuar");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                long numeroConvertido = Long.parseLong(tCPF.getText());
                Comprador pessoa = new Comprador(tNome.getText(), tCPF.getText());
                CompraIngresso pane = new CompraIngresso(evento,pessoa);
                pane.start(primaryStage);
            }
        });
        comprador.add(btn,0,3);
        
        Button btn1 = new Button();
        btn1.setText("Cancelar");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               System.out.println("Cancelado!");
               System.exit(0);
            }
        });
        comprador.add(btn1,2,3);
        comprador.setHalignment(btn1, HPos.RIGHT);
       
        Scene scene = new Scene(comprador);
        
        primaryStage.setTitle("Dados pessoais");
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
