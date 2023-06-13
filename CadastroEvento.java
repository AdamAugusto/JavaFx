/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam Augusto
 */
public class CadastroEvento extends Application {

    private ArrayList<Evento> listinha = new ArrayList();

    @Override
    public void start(Stage primaryStage) throws ParseException, ClassNotFoundException {
        try {
            Arquivos arquivo = new Arquivos("eventos.obj");
            arquivo.abreLerArqObj();
            listinha = arquivo.lerArqObj(new Evento());
            arquivo.fechaArqObjLeitura();
        } catch (IOException e) {
            System.out.println("Erro na leitura " + e.getMessage());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        GridPane eventos = new GridPane();
        eventos.setPadding(new Insets(15, 12, 15, 12));
        eventos.setVgap(5);
        eventos.setHgap(5);
        eventos.setGridLinesVisible(false);

        Text titulo = new Text("Crie seu Evento");
        eventos.add(titulo, 0, 0, 2, 1);
        eventos.setHalignment(titulo, HPos.CENTER);
        Label lNomeEvento = new Label("Nome:");
        eventos.add(lNomeEvento, 0, 1);
        TextField tNomeEvento = new TextField();
        tNomeEvento.setMinSize(170, 30);
        eventos.add(tNomeEvento, 1, 1);
        Label lData = new Label("Data:");
        eventos.add(lData, 0, 2);
        TextField tData = new TextField();
        eventos.add(tData, 1, 2);
        Label llData = new Label("(dd/mm/yyyy HH:mm)");
        eventos.add(llData, 2, 2);
        Label lLocal = new Label("Local:");
        eventos.add(lLocal, 0, 3);
        TextField tLocal = new TextField();
        eventos.add(tLocal, 1, 3);
        Label lInscricoes = new Label("Número de ingressos:");
        eventos.add(lInscricoes, 0, 4);
        TextField tInscricoes = new TextField();
        tInscricoes.setMaxSize(60, 30);
        eventos.add(tInscricoes, 1, 4);
        Label obsInscricoes = new Label("(apenas números)");
        eventos.add(obsInscricoes, 1, 4);
        eventos.setHalignment(obsInscricoes, HPos.RIGHT);
        Label lPreco = new Label("Preço por ingresso:");
        eventos.add(lPreco, 0, 5);
        TextField tPreco = new TextField();
        tPreco.setMaxSize(60, 30);
        eventos.add(tPreco, 1, 5);
        Label obsInscricoes1 = new Label("(apenas números)");
        eventos.add(obsInscricoes1, 1, 5);
        eventos.setHalignment(obsInscricoes1, HPos.RIGHT);

        Button btn = new Button();
        btn.setText("Salvar");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    Date dataInicio = sdf.parse(tData.getText());
                    int numeroConvertido = Integer.parseInt(tInscricoes.getText());
                    double doubleConvetido = Double.parseDouble(tPreco.getText());
                    Evento myEvent = new Evento(dataInicio, tLocal.getText(), numeroConvertido, tNomeEvento.getText(), doubleConvetido);
                    boolean salva = true;
                    for (Evento events : listinha) {
                        if (tNomeEvento.getText().equals(events.getNome())) {
                            JOptionPane.showMessageDialog(null,
                                    "Não cadastre dois eventos com o mesmo nome!");
                            salva = false;
                        }
                    }
                    if (salva) {
                        listinha.add(myEvent);
                        tLocal.clear();
                        tNomeEvento.clear();
                        tData.clear();
                        tInscricoes.clear();
                        tPreco.clear();
                        System.out.println("Evento salvo!");
                    }

                } catch (ParseException ex) {
                    System.out.println("Data com formato dd/MM/yyyy !");
                } catch (java.lang.NumberFormatException e) {
                    System.out.println("Apenas número para número de ingressos e/ou preço!");
                }

            }
        });

        Button btn2 = new Button();
        btn2.setText("Cancelar");
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Cancelado!");
                try {
                    Arquivos arquivo = new Arquivos("eventos.obj");
                    arquivo.abreGravaArqObj();
                    for (Evento events : listinha) {
                        arquivo.gravaArqObj(events);
                    }
                    arquivo.fechaArqObjGravacao();
                } catch (IOException e) {
                    System.out.println("Erro na gravação");
                }
                System.exit(0);
            }
        });
        Button btn3 = new Button();
        btn3.setText("Sair");
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Saindo");
                try {
                    Arquivos arquivo = new Arquivos("eventos.obj");
                    arquivo.abreGravaArqObj();
                    for (Evento events : listinha) {
                        arquivo.gravaArqObj(events);
                    }
                    arquivo.fechaArqObjGravacao();
                } catch (IOException e) {
                    System.out.println("Erro na gravação");
                }
                System.exit(0);
            }
        });

        eventos.add(btn, 0, 7);
        eventos.add(btn2, 2, 7);
        eventos.add(btn3, 1, 7);
        eventos.setHalignment(btn3, HPos.RIGHT);
        eventos.setHalignment(btn2, HPos.RIGHT);

        Scene scene = new Scene(eventos);

        primaryStage.setTitle("Cadastro de Evento");
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
