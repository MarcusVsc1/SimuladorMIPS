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
       Integer[] array = {0,1,1,1,1,1,1,0};
       Integer  decimal = ops.convertBinaryToDecimal(array);
       assertEquals(126, decimal);
    }
    
    @Test
    public void GivenArrayWhenBinaryThenNegativeSucess() {
    	MipsOperational ops = new MipsOperational();
        Integer[] array = {0,0,0,0,0,1,1,0};
        Integer[] negativeArray = ops.negativeBinary(array);
        Integer[] expected = {1,1,1,1,1,0,1,0};
        assertArrayEquals(expected, negativeArray);
    }
}
