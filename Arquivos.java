/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Adam
 */
import java.io.*;
import java.util.ArrayList;

public class Arquivos<T> {

    private String nomeArq;
    private ObjectInputStream arqLeitura;
    private ObjectOutputStream arqGravacao;

    public Arquivos(String nomeArq) throws IOException {
        this.nomeArq = nomeArq;
    }

    public ArrayList<T> lerArqObj(T registro) throws IOException {
        ArrayList<T> lista = new ArrayList<>();
        try {
            do {
                registro = (T) arqLeitura.readObject();
                if (registro != null) {
                    lista.add(registro);
                    System.out.println(registro);
                }
            } while (registro != null);

        } catch (EOFException ex) {
            System.out.println("Fim do arquivo");
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new IOException("Erro na Leitura do arquivo " + e1.toString());

        } catch (ClassNotFoundException cnfEx) {

            throw new IOException("Objeto do arquivo o mesmo do solicitado");
        } finally {
            return lista;
        }
    }

    public void gravaArqObj(T registro) throws IOException {
        try {
            arqGravacao.writeObject(registro);
        } catch (IOException e1) {
            throw new IOException("Erro na gravacao de arquivo " + e1.toString());
        }
    }

    public void abreLerArqObj() throws IOException {
        try {
            arqLeitura = new ObjectInputStream(new FileInputStream(nomeArq));
        } catch (FileNotFoundException e1) {
            throw new IOException(nomeArq + "não encontrado!");
        }
    }

    public void abreGravaArqObj() throws IOException {
        try {
            arqGravacao = new ObjectOutputStream(new FileOutputStream(nomeArq));
        } catch (FileNotFoundException e1) {
            throw new IOException(nomeArq + "não encontrado!");
        }
    }

    public void fechaArqObjGravacao() throws IOException {
        try {
            arqGravacao.close();
        } catch (IOException e1) {
            throw new IOException("Erro no fechamento dos arquivos, \n"
                    + e1.getLocalizedMessage());
        }
    }

    public void fechaArqObjLeitura() throws IOException {
        try {
            arqLeitura.close();
        } catch (IOException e1) {
            throw new IOException("Erro no fechamento dos arquivos, \n"
                    + e1.getLocalizedMessage());
        }
    }

    /**
     * @return the nomeArq
     */
    public String getNomeArq() {
        return nomeArq;
    }

    /**
     * @param nomeArq the nomeArq to set
     */
    public void setNomeArq(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    /**
     * @return the arqLeitura
     */
    public ObjectInputStream getArqLeitura() {
        return arqLeitura;
    }

    /**
     * @param arqLeitura the arqLeitura to set
     */
    public void setArqLeitura(ObjectInputStream arqLeitura) {
        this.arqLeitura = arqLeitura;
    }

    /**
     * @return the arqGravacao
     */
    public ObjectOutputStream getArqGravacao() {
        return arqGravacao;
    }

    /**
     * @param arqGravacao the arqGravacao to set
     */
    public void setArqGravacao(ObjectOutputStream arqGravacao) {
        this.arqGravacao = arqGravacao;
    }

}
