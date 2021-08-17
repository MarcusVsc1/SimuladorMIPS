/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufjf.mips.model;

import java.util.Arrays;

/**
 *
 * @author Nova
 */
public class MipsOperational {
    
    
    public Integer convertBinaryToDecimal(Integer[] binary){
        Integer decimal = 0;
        for(int i = binary.length - 1; i >= 0 ; i--){
            decimal += binary[i] == 0 ? 0 : (int) Math.pow(2, binary.length -1 - i);
        }      
        return decimal;
    }
    
    public Integer[] createNegativeBinary(Integer[] binary) {
    	boolean somaDois = true;
    	Integer[] negBinary = binary.clone();
    	for(int i = negBinary.length - 1; i >= 0; i--) {
    		negBinary[i] = (negBinary[i] == 0 ? 1 : 0);
    		if(somaDois) {
    			negBinary[i] = negBinary[i] + 1 == 2 ? 0 : 1;
    			somaDois = negBinary[i] == 0;
    		}
    	}
    	return negBinary;   	
    }
    
    public Integer[] andInstruction(Integer[] bin1, Integer[] bin2 ) {
    	Integer[] andBin = new Integer[bin1.length];
    	for(int i = bin1.length - 1; i >= 0; i--) {
    		andBin[i] = (bin1[i] == 1 && bin2[i] == 1) ? 1 : 0;
    	}
    	return andBin;
    }
    
    public Integer[] orInstruction(Integer[] bin1, Integer[] bin2 ) {
    	Integer[] orBin = new Integer[bin1.length];
    	for(int i = bin1.length - 1; i >= 0; i--) {
    		orBin[i] = (bin1[i] == 1 || bin2[i] == 1) ? 1 : 0;
    	}
    	return orBin;
    }
    
    public Integer setOnLessThenInstruction(Integer[] num1, Integer[] num2) {
    	return (convertBinaryToDecimal(num1) < convertBinaryToDecimal(num2)) ? 1 : 0;
    }
}
