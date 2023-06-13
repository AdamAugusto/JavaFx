/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Adam Augusto
 */
public class CompraIngresso extends Application {
    private Evento obj;
    private ArrayList<Comprador> listaComprador = new ArrayList();
    private ArrayList<Evento> listinha = new ArrayList();
    private Comprador compr;
    public CompraIngresso(Evento obj,Comprador compr){
        this.obj=obj;
        this.compr=compr;
    }
    public CompraIngresso(){
        this.obj= new Evento("Teste","Teste",69.69,10);
        this.compr=new Comprador();
    }
    
    int  numeroIngressos = 0;
    int ingressosInteiros=0;
    double precoTotalzin=0;
    int numeroMeias = 0;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Arquivos arquivo = new Arquivos("eventos.obj");
            arquivo.abreLerArqObj();
            listinha = arquivo.lerArqObj(new Evento());
            arquivo.fechaArqObjLeitura();
            Arquivos compradors = new Arquivos("compradores.obj");
            compradors.abreLerArqObj();
            listaComprador = compradors.lerArqObj(new Comprador());
            compradors.fechaArqObjLeitura();
        } catch (IOException e) {
            System.out.println("Erro na leitura " + e.getMessage());
        }
       
        GridPane compra = new GridPane();
        compra.setPadding(new Insets(15,12,15,12));
        compra.setHgap(5);
        compra.setVgap(5);
        
        Label quantIngressos = new Label(Integer.toString(numeroIngressos));
        Label quantMeias = new Label(Integer.toString(numeroMeias));
        
        Text titulo = new Text("Compre seus ingressos");
        compra.add(titulo,0,0,2,1);
        compra.setHalignment(titulo, HPos.CENTER);
        Label precoIngresso = new Label("Preço ingresso inteiro:");
        compra.add(precoIngresso, 0,1);
        Label precoIngressos = new Label(Double.toString(obj.preco));
        compra.add(precoIngressos,1,1);
        
        Label precoMeia = new Label("Preço meio ingresso:");
        compra.add(precoMeia, 0,2);
        Label precoMeias = new Label(Double.toString(obj.preco/2));
        compra.add(precoMeias,1,2);        
        Label lIngressos = new Label("Quantidade de ingressos:");
        compra.add(lIngressos,0,3);
        compra.add(quantIngressos,1,3);
        Label precoTotal = new Label(Double.toString(precoTotalzin));
        compra.add(precoTotal,1,8);
        Button btn = new Button();
        btn.setText("+");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(numeroIngressos<obj.getInscricoes_disponiveis()){
                    numeroIngressos++;
                    ingressosInteiros=numeroIngressos-numeroMeias;
                    precoTotalzin=numeroMeias*(obj.preco/2);
                    precoTotalzin+=ingressosInteiros*obj.preco;
                    precoTotal.setText(Double.toString(precoTotalzin));
                    quantIngressos.setText(Integer.toString(numeroIngressos));
               }
            }
        });
        Button btn2 = new Button();
        btn2.setText("-");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
              if(numeroIngressos>0){
                   numeroIngressos--;
                   ingressosInteiros=numeroIngressos-numeroMeias;
                   precoTotalzin=numeroMeias*(obj.preco/2);
                   precoTotalzin+=ingressosInteiros*obj.preco;
                   precoTotal.setText(Double.toString(precoTotalzin));
                   quantIngressos.setText(Integer.toString(numeroIngressos));
                   if(numeroIngressos<numeroMeias){
                       numeroMeias--;
                       ingressosInteiros=numeroIngressos-numeroMeias;
                       precoTotalzin=numeroMeias*(obj.preco/2);
                       precoTotalzin+=ingressosInteiros*obj.preco;
                       precoTotal.setText(Double.toString(precoTotalzin));
                       quantMeias.setText(Integer.toString(numeroMeias));
                   }
              }
            }
        });
        HBox boxinha = new HBox();
        boxinha.getChildren().addAll(btn,btn2);
        compra.add(boxinha,2,3);
        Label lMeias = new Label("Quant de meio ingressos:");
        compra.add(lMeias,0,4);
        compra.add(quantMeias,1,4);
        Button btn3 = new Button();
        btn3.setText("+");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {

              if(numeroMeias<numeroIngressos){
                 numeroMeias++;
                 ingressosInteiros=numeroIngressos-numeroMeias;
                 precoTotalzin=numeroMeias*(obj.preco/2);
                 precoTotalzin+=ingressosInteiros*obj.preco;
                 precoTotal.setText(Double.toString(precoTotalzin));
                 quantMeias.setText(Integer.toString(numeroMeias));
              }
            }
        });
        Button btn4 = new Button();
        btn4.setText("-");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
              if(numeroMeias>0){
                  numeroMeias--;
                  ingressosInteiros=numeroIngressos-numeroMeias;
                  precoTotalzin=numeroMeias*(obj.preco/2);
                  precoTotalzin+=ingressosInteiros*obj.preco;
                  precoTotal.setText(Double.toString(precoTotalzin));
                  quantMeias.setText(Integer.toString(numeroMeias));
                  
              }
            }
        });
        HBox boxinha1 = new HBox();
        boxinha1.getChildren().addAll(btn3,btn4);
        compra.add(boxinha1,2,4);
        Label tipoPagamento= new Label("Selecione a forma de pagamento:");
        compra.add(tipoPagamento,0,5);
        ToggleGroup pagamento = new ToggleGroup();
        RadioButton cartaoCredito = new RadioButton("Cartão de Crédito");
        cartaoCredito.setSelected(true);
        cartaoCredito.setToggleGroup(pagamento);
        compra.add(cartaoCredito,1,5);
        RadioButton money = new RadioButton("Dinheiro");
        money.setSelected(false);
        money.setToggleGroup(pagamento);
        compra.add(money,2,5);
        RadioButton boletin = new RadioButton("Boleto");
        boletin.setSelected(false);
        boletin.setToggleGroup(pagamento);
        compra.add(boletin,1,6);
        RadioButton deposito = new RadioButton("Depósito");
        deposito.setSelected(false);
        deposito.setToggleGroup(pagamento);
        compra.add(deposito,2,6);
        RadioButton convenio = new RadioButton("Convenio");
        convenio.setSelected(false);
        convenio.setToggleGroup(pagamento);
        compra.add(convenio,1,7);
        Button btn5 = new Button();
        btn5.setText("Comprar");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            String tipo = pagamento.getSelectedToggle().toString().substring(pagamento.getSelectedToggle().toString().indexOf("'")+1,pagamento.getSelectedToggle().toString().lastIndexOf("'"));

             Comprador oSalvo = new Comprador(compr.getCpf(),compr.getNomeComprador(),tipo);
              try {
                    Arquivos arquivo = new Arquivos("compradores.obj");
                    arquivo.abreGravaArqObj();
                    listaComprador.add(oSalvo);
                    for (Comprador comprador : listaComprador) {
                        arquivo.gravaArqObj(comprador);
                    }
                    arquivo.fechaArqObjGravacao();
                } catch (IOException e) {
                    System.out.println("Erro na gravação");
                }
              try {
                    Arquivos arquivo = new Arquivos("eventos.obj");
                    arquivo.abreGravaArqObj();
                    for (Evento events : listinha) {
                        if(events.getNome().equals(obj.getNome())){
                            events.setInscricoes_disponiveis(events.getInscricoes_disponiveis()-numeroIngressos);
                        }
                        arquivo.gravaArqObj(events);
                    }
                    arquivo.fechaArqObjGravacao();
                } catch (IOException e) {
                    System.out.println("Erro na gravação");
                }
             System.exit(0);
            }
        });
        compra.add(btn5, 0, 9);
        compra.setHalignment(btn5, HPos.LEFT);
        Button btn6 = new Button();
        btn6.setText("Cancelar");
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
             System.out.println("Cancelado!");
             System.exit(0);
            }
        });
        compra.add(btn6, 2, 9);
        compra.setHalignment(btn6,HPos.RIGHT);
        Label total = new Label("Preço total:");
        compra.add(total,0,8);
        
        

        
        Scene scene = new Scene(compra);
        
        primaryStage.setTitle("Compra de ingressos");
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
