/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaeaenergy.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

/**
 *
 * @author Jean
 */
public class WriteToFile {

    public WriteToFile() {

    }

    public void escrever(String mensagem, String logFile) throws IOException {
        //O segundo parametro "true" indica append para o arquivo em quest�o.
        FileOutputStream escritorArquivos = new FileOutputStream(logFile, true);
        int tamanho = 0;
        //String data = (new java.util.Date()).toString();
        String msg = mensagem;
        while (tamanho < msg.length()) {
            escritorArquivos.write((int) msg.charAt(tamanho++));
        }
        escritorArquivos.flush();
        escritorArquivos.close();
    }

    public void geraLog(String mensagem) throws IOException {
        FileOutputStream escritorArquivos = new FileOutputStream("log.txt", true);
        int tamanho = 0;
        String data = (new java.util.Date()).toString();
        String msg = "\n:: " + data + " :: " + mensagem + "\n";

        while (tamanho < msg.length()) {
            escritorArquivos.write((int) msg.charAt(tamanho++));
        }

        escritorArquivos.flush();
        escritorArquivos.close();
    }

    public String recuperaLogin() throws IOException {
        String linha = null;

        if (arquivoExiste()) {
            FileReader fileReader = new FileReader("login.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                linha = bufferedReader.readLine();
                System.out.println(linha);
            }

            bufferedReader.close();
            return linha;
        } else {
            return "";
        }

    }

    private boolean arquivoExiste() {
        File file = new File("login.txt");

        if (file.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean jogaPilhaNoArquivo(Throwable t, boolean append) {
        File file = new File("log.txt");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy aa hh:mm:ss");
        String text = "\r\n\r\n" + "\r\n" + sw.toString();
        pw.close();
        try {
            sw.close();
        } catch (Exception e) {
        }
        return setFileContentAsText(file, text, append);
    }

    private boolean setFileContentAsText(File file, String text, boolean append) {
        try {
            if (file == null || !file.exists() || !file.isFile()) {
                throw new FileNotFoundException(file.getAbsolutePath());
            }
            if (text == null) {
                text = "";
            }
            FileWriter fw = new FileWriter(file, append);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void limparLog() {
        File file = new File("login.txt");
        file.delete();
    }

//    public static void main( String[] args ) {
//
//        try {
//            WriteToFile.escrever("Testeeeeeeeeeee", "login.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
