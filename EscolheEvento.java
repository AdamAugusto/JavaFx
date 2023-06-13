/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adam Augusto
 */
public class EscolheEvento extends Application {
    ObservableList <Evento> listaEventos;
    TableView<Evento> todosEventos = new TableView<>();
    TableColumn<Evento, String> col1 = new TableColumn<>("Nome");
    TableColumn<Evento, Integer> col2 = new TableColumn<>("Ingressos");
    TableColumn <Evento, Double> col3 = new TableColumn<>("Preço");
    TableColumn <Evento, Double> col4 = new TableColumn<>("Local");
     TableColumn <Evento, Double> col5 = new TableColumn<>("Horário");
    private ArrayList<Evento> listinha = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Arquivos arquivo = new Arquivos("eventos.obj");
            arquivo.abreLerArqObj();
            listinha = arquivo.lerArqObj(new Evento());
            arquivo.fechaArqObjLeitura();
        } catch (IOException e) {
            System.out.println("Erro na leitura " + e.getMessage());
        }
        listaEventos = FXCollections.observableArrayList(listinha);
        todosEventos.setItems(listaEventos);
        col1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col2.setCellValueFactory(new PropertyValueFactory<>("inscricoes_disponiveis"));
        col3.setCellValueFactory(new PropertyValueFactory<>("preco"));
        col4.setCellValueFactory(new PropertyValueFactory<>("local"));
        col5.setCellValueFactory(new PropertyValueFactory<>("datainicio"));
        todosEventos.getColumns().addAll(col1,col2,col3,col4,col5);
        StackPane osEventos = new StackPane(todosEventos);
        GridPane poinel = new GridPane();
        poinel.add(osEventos,0,0);
        VBox caixa = new VBox();
        
        Button btn = new Button();
        btn.setText("Continuar");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Evento selecionado = new Evento();
                selecionado=todosEventos.getSelectionModel().getSelectedItem();
                Compradorfx pane = new Compradorfx(selecionado);
                pane.start(primaryStage);
            }
        });
        Button btn2 = new Button();
        btn2.setText("Sair");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Saindo");
                System.exit(0);
            }
        });
        caixa.getChildren().addAll(btn,btn2);
        poinel.add(caixa,1,0);
        
        
        Scene scene = new Scene(poinel);
        
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
