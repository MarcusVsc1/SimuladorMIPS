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
	
//    @Test
//    public void GivenBinaryWhenConversionThenInt() {
//    	MipsOperational ops = new MipsOperational();
//    	String number = "00100001001000000000000001100100";
//    	number = number.substring(0,5);
//    	Integer intNum = ops.strToInt(number);
//    	assertEquals("001000",number);
//    	//assertEquals(8, intNum);
//    }
//    
    @Test
    public void GivenMapWhenKeyThenValue() {
    	MipsOperational ops = new MipsOperational();
    	String number = ops.mapa.get(8);
    	assertEquals("$t0", number);
    }
}
