package com.ufjf.mips.interfaces;

import com.ufjf.mips.enums.EInstruction;

public abstract class Instruction implements Cloneable{
	
	private String command;
	public String[] pipeline;
	public String pipelineType;
	private Integer stage;
	
	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public Instruction(Integer stage) {
		super();
		this.stage = stage;
	}

	public Instruction(int def) {
		definirEnum(def);
		stage = 0;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public abstract String createAssembly();
	
	public abstract void realizarExecucaoDireta();
	
	

	public void definirEnum(Integer def) {
		switch(def) {
		case 8:  command = "ADDI";
				pipeline = new String[] {"FETCH", "READ", "ALU", "REGISTER"};
				break;
		case 32:  command = "ADD";
				pipeline = new String[] {"FETCH", "READ", "ALU", "REGISTER"};
				break;
		case 34:  command = "SUB";
				pipeline = new String[] {"FETCH", "READ", "ALU", "REGISTER"};
				break;
		case 42:  command = "SLT";
				pipeline = new String[] {"FETCH", "READ", "ALU", "REGISTER"};
				break;
		case 36:  command = "AND";
				pipeline = new String[] {"FETCH", "READ", "ALU", "REGISTER"};
				break;
		case 37:  command = "OR";
				pipeline = new String[] {"FETCH", "READ", "ALU", "REGISTER"};
				break;
		case 35:  command = "LW";
				pipeline = new String[] {"FETCH", "READ", "ALU", "MEMO", "REGISTER"};
				break;
		case 43:  command = "SW";
				pipeline = new String[] {"FETCH", "READ", "ALU", "MEMO"};
				break;
		case 0:  command = "SLL";
				pipeline = new String[] {"FETCH", "READ", "ALU", "REGISTER"};
				break;
		case 2:  command = "J";
				pipeline = new String[] {"FETCH", "READ", "ALU"};
				break;
		case 3:  command = "JAL";
				pipeline = new String[] {"FETCH", "READ", "ALU"};
				break;
		case 4:  command = "BEQ";
				pipeline = new String[] {"FETCH", "READ", "ALU"};
				break;
		case 5:  command = "BNE";
				pipeline = new String[] {"FETCH", "READ", "ALU"};
				break;
		case -1: command = "JR";
				pipeline = new String[] {"FETCH", "READ", "ALU"};
				break;
		}
		
		
	}
	public boolean runPipelineStage() {
		boolean retorno = true;
		switch(pipeline[stage]) {
		case "FETCH" -> retorno = EInstruction.valueOf(getCommand()).fetch(this);
		case "READ" -> retorno =  EInstruction.valueOf(getCommand()).read(this);
		case "ALU" -> retorno =  EInstruction.valueOf(getCommand()).alu(this) ;
		case "REGISTER" -> retorno =  EInstruction.valueOf(getCommand()).register(this);
		case "MEMO" -> retorno =  EInstruction.valueOf(getCommand()).memo(this);
		
		}
		return retorno;
		
	}

	public abstract boolean verifyDataHazard(); 
	
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
	
}