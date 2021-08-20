/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufjf.mips.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.ufjf.mips.interfaces.Instruction;

/**
 *
 * @author Nova
 */
public class MipsOperational {
	
	private String[] binaryInstructions;
	private List<String> assemblyList = new ArrayList<String>();
	private List<Instruction> instructions = new ArrayList<Instruction>();
	private Integer clock;
	public static final Integer[] typeIOpCode = {8, 4, 5, 35, 43};
	public static final Integer[] typeJOpCode = {2, 3};
	public static HashMap<Integer, String> mapa = new HashMap<Integer, String>();
	public static Integer[] bancoRegistradores;
	public static Integer[] memoria;
	public static String log;
	

	public MipsOperational(String[] binaryInstructions) {
		inicializarMapDeRegistradores();
		this.binaryInstructions = binaryInstructions;
		instructions =  createInstructionList();
		clock = 0;
		bancoRegistradores = new Integer[33];
		memoria = new Integer[128];
		inicializarRegistradoresEMemoria();
		log = "Log de execução: \n";
	}
	
	public void inicializarRegistradoresEMemoria() {
		for(int i = 1; i < MipsOperational.bancoRegistradores.length - 1; i++) {
			MipsOperational.bancoRegistradores[i] = -999999;
		}
		MipsOperational.bancoRegistradores[0] = 0;
		MipsOperational.bancoRegistradores[32] = 0;
		for(int i = 0; i < memoria.length; i++) {
			MipsOperational.memoria[i] = -999999;
		}
	}
	
	public void execucaoSequencial() {
		Instruction instrucao = instructions.get(bancoRegistradores[32]/4);
		instrucao.realizarExecucaoDireta();
		clock++;
		log +="Ciclo de clock atual: "+clock+"\n";
	}

	public Integer getClock() {
		return clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

	public MipsOperational() {
		inicializarMapDeRegistradores();
	}
	
	public List<String> getAssemblyList() {
		return assemblyList;
	}

	public void setAssemblyList(List<String> assemblyList) {
		this.assemblyList = assemblyList;
	}
	
    private List<Instruction> createInstructionList() {
		for(String instruction : binaryInstructions) {
			Instruction toAdd = verificarOpCode(instruction);
			String command = toAdd.createAssembly();
			assemblyList.add(command);
			instructions.add(toAdd);
		}
		return instructions;
	}



	private Instruction verificarOpCode(String instruction) {
		Integer opCode = strToInt(instruction.substring(0, 6));
		Instruction newInstruction = null;
		
		if(opCode == 0)
			newInstruction = new TypeR(instruction);

		if(Arrays.stream(typeIOpCode).anyMatch(x -> x == opCode)) 
			newInstruction = new TypeI(instruction);
		
		if(Arrays.stream(typeJOpCode).anyMatch(x -> x == opCode)) 
			newInstruction = new TypeJ(instruction);

		return newInstruction;
		
	}

	public Integer strToInt(String binary) {
    	return Integer.parseInt(binary, 2);    	   		
    }

	
	
    
//    
//    public Integer convertBinaryToDecimal(Integer[] binary){
//        Integer decimal = 0;
//        for(int i = binary.length - 1; i >= 0 ; i--){
//            decimal += binary[i] == 0 ? 0 : (int) Math.pow(2, binary.length -1 - i);
//        }      
//        return decimal;
//    }
//    
//
//    
//    public Integer[] createNegativeBinary(Integer[] binary) {
//    	boolean somaDois = true;
//    	Integer[] negBinary = binary.clone();
//    	for(int i = negBinary.length - 1; i >= 0; i--) {
//    		negBinary[i] = (negBinary[i] == 0 ? 1 : 0);
//    		if(somaDois) {
//    			negBinary[i] = negBinary[i] + 1 == 2 ? 0 : 1;
//    			somaDois = negBinary[i] == 0;
//    		}
//    	}
//    	return negBinary;   	
//    }
//    
//    public Integer[] andInstruction(Integer[] bin1, Integer[] bin2 ) {
//    	Integer[] andBin = new Integer[bin1.length];
//    	for(int i = bin1.length - 1; i >= 0; i--) {
//    		andBin[i] = (bin1[i] == 1 && bin2[i] == 1) ? 1 : 0;
//    	}
//    	return andBin;
//    }
//    
//    public Integer[] orInstruction(Integer[] bin1, Integer[] bin2 ) {
//    	Integer[] orBin = new Integer[bin1.length];
//    	for(int i = bin1.length - 1; i >= 0; i--) {
//    		orBin[i] = (bin1[i] == 1 || bin2[i] == 1) ? 1 : 0;
//    	}
//    	return orBin;
//    }
//    
//    public Integer setOnLessThenInstruction(Integer[] num1, Integer[] num2) {
//    	return (convertBinaryToDecimal(num1) < convertBinaryToDecimal(num2)) ? 1 : 0;
//    }
	
	private void inicializarMapDeRegistradores() {
		mapa.put(0, "$zero");
		mapa.put(1, "$at");
		mapa.put(2, "$v0");
		mapa.put(3, "$v1");
		mapa.put(4, "$a0");
		mapa.put(5, "$a1");
		mapa.put(6, "$a2");
		mapa.put(7, "$a3");
		mapa.put(8, "$t0");
		mapa.put(9, "$t1");
		mapa.put(10, "$t2");
		mapa.put(11, "$t3");
		mapa.put(12, "$t4");
		mapa.put(13, "$t5");
		mapa.put(14, "$t6");
		mapa.put(15, "$t7");
		mapa.put(16, "$s0");
		mapa.put(17, "$s1");
		mapa.put(18, "$s2");
		mapa.put(19, "$s3");
		mapa.put(20, "$s4");
		mapa.put(21, "$s5");
		mapa.put(22, "$s6");
		mapa.put(23, "$s7");
		mapa.put(24, "$t8");
		mapa.put(25, "$t9");
		mapa.put(26, "$k0");
		mapa.put(27, "$k1");
		mapa.put(28, "$gp");
		mapa.put(29, "$sp");
		mapa.put(30, "$fp");
		mapa.put(31, "$ra");
	}
}
