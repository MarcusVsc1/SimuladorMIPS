/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufjf.mips.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.io.TextStreamsKt;

/**
 *
 * @author Nova
 */
public class InputValidator {
    
    public static List<Integer[]> validarInput(String input) throws IOException{
        String [] inputPorLinha = input.split("\n");
        String caracteres="01";
        List<Integer[]> toInt = new ArrayList<Integer[]>();
        for (String linha : inputPorLinha){
            int contador = 0;
            Integer[] linhaInt = new Integer[32];
            if(linha.length() != 32){
                throw new IOException("Falha ao carregar os inputs!" +
                " (MIPS aceita cada linha com exatamente 32 caracteres compostos de 0 e 1).");
            }
            for (char c : linha.toCharArray()) {
                
                
		if(!caracteres.contains(String.valueOf(c))){
                    throw new IOException("Falha ao carregar os inputs!" +
                " (MIPS aceita cada linha com exatamente 32 caracteres compostos de 0 e 1).");
		}
                linhaInt[contador] = Integer.parseInt(String.valueOf(c));
                contador++;
            }
            toInt.add(linhaInt);
        }
        
        return toInt;
    }
}
