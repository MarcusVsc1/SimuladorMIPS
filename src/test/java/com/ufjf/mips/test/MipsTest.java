/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufjf.mips.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ufjf.mips.model.MipsOperational;

/**
 *
 * @author Nova
 */
public class MipsTest {
	
    @Test
    public void GivenArrayWhenBinaryThenDecimalSuccess(){
       MipsOperational ops = new MipsOperational();
       Integer[] array = {0,1,1,1,1,1,1,0}; //126
       Integer  decimal = ops.convertBinaryToDecimal(array);
       assertEquals(126, decimal);
    }
    
    @Test
    public void GivenArrayWhenBinaryThenNegativeSucess() {
    	MipsOperational ops = new MipsOperational();
        Integer[] array = {0,0,1,1,0}; //6
        Integer[] negativeArray = ops.createNegativeBinary(array); //-6
        Integer[] expected = {1,1,0,1,0};
        assertArrayEquals(expected, negativeArray);
    }
    
    @Test
    public void GivenTwoBinsWhenAndInstructionThenCorrectAndArray() {
    	MipsOperational ops = new MipsOperational();
    	Integer[] array1 = {0,0,1,1,0};
    	Integer[] array2 = {1,1,0,1,0};
    	Integer[] expected = {0,0,0,1,0};
    	Integer[] result = ops.andInstruction(array1, array2);
    	assertArrayEquals(expected, result);
    }
    
    @Test
    public void GivenTwoBinsWhenOrInstructionThenCorrectOrArray() {
    	MipsOperational ops = new MipsOperational();
    	Integer[] array1 = {0,0,1,1,0};
    	Integer[] array2 = {1,1,0,1,0};
    	Integer[] expected = {1,1,1,1,0};
    	Integer[] result = ops.orInstruction(array1, array2);
    	assertArrayEquals(expected, result);
    }
    
    @Test
    public void GivenTwoBinsWhenStlInstructionThenTrue() {
    	MipsOperational ops = new MipsOperational();
    	Integer[] array1 = {0,0,1,1,0}; //6
    	Integer[] array2 = {0,1,0,0,1}; //7
    	Integer result = ops.setOnLessThenInstruction(array1, array2);
    	assertEquals(1, result);
    }
    
    @Test
    public void GivenTwoBinsWhenStlInstructionThenFalse() {
    	MipsOperational ops = new MipsOperational();
    	Integer[] array1 = {1,0,1,0,0}; //20
    	Integer[] array2 = {0,1,1,1,1}; //15
    	Integer result = ops.setOnLessThenInstruction(array1, array2);
    	assertEquals(0, result);
    }
}
