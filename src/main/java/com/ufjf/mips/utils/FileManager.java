/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufjf.mips.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Nova
 */
public class FileManager {
    
    
    public static BufferedReader criarBufferedReader(String nomeArquivo) throws FileNotFoundException {
        try {   
            return new BufferedReader(new FileReader(nomeArquivo));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado.");
        }     
    }

    public static String[] criarListaComandos(String localArquivo) throws FileNotFoundException, IOException {
        BufferedReader reader = criarBufferedReader(localArquivo);
        String comandosEmLinha = "";
        String line = reader.readLine();
        while(line != null){
            comandosEmLinha = comandosEmLinha + line + "\n";
            line = reader.readLine();
        }
        reader.close();
        return InputValidator.validarInput(comandosEmLinha);
    }
    
    public static void escreverEmArquivo(String info, String fileName) throws IOException {
    	BufferedWriter writer = new BufferedWriter(new FileWriter("output/"+fileName));
    	writer.write(info);
    	writer.close();
    }
    
    public static String criarStringArrayMemoria(Integer[] memoria) {
    	String memo = "Conteúdo da memória de dados da última execução: \n";
    	for(Integer i = 0; i < memoria.length; i++) {
    		memo += String.format("[%s] [%s]\n", i*4, memoria[i] != -999999 ? memoria[i] : "#LIXO");
    	}
    	return memo;
    }
}
