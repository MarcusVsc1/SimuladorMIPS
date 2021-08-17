/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufjf.mips.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        return InputValidator.validarInput(comandosEmLinha);
    }
}
