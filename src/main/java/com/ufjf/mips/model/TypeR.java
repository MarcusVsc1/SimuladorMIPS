package com.ufjf.mips.model;

import com.ufjf.mips.enums.EInstruction;
import com.ufjf.mips.interfaces.Instruction;

public class TypeR implements Instruction{
	private Integer rs;
	private Integer rt;
	private Integer rd;
	private Integer shamt;
	private Integer funct;
	public EInstruction instruction;
	
	public TypeR(String binary) {
		Integer op = rs = Integer.parseInt(binary.substring(0, 5), 2); 
		rs = Integer.parseInt(binary.substring(6, 10), 2);
		rt = Integer.parseInt(binary.substring(11, 15), 2); 
		rd = Integer.parseInt(binary.substring(16, 20), 2);
		shamt = Integer.parseInt(binary.substring(21, 25), 2); 
		funct = Integer.parseInt(binary.substring(16, 20), 2); 
		this.instruction = definirEnum(funct);
	}
	
	@Override
	public EInstruction definirEnum(Integer def) {
		EInstruction instruction = null;;
		switch(def) {
		case 32 -> instruction = EInstruction.ADD;
		case 34 -> instruction = EInstruction.SUB;
		case 42 -> instruction = EInstruction.SLT;
		case 36 -> instruction = EInstruction.AND;
		case 37 -> instruction = EInstruction.OR;
		}
		return instruction;
	}
}
