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
    public void GivenBinaryWhenConversionThenInt() {
    	MipsOperational ops = new MipsOperational();
    	String number = "1111000";
    	Integer intNum = ops.strToInt(number);
    	assertEquals(120, intNum);
    }
}
