package com.ufjf.mips.interfaces;

import com.ufjf.mips.enums.EInstruction;

public abstract class Instruction {
	
	private String command;
	
	public Instruction(int def) {
		definirEnum(def);
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
		case 8 -> command = "ADDI";
		case 32 -> command = "ADD";
		case 34 -> command = "SUB";
		case 42 -> command = "SLT";
		case 36 -> command = "AND";
		case 37 -> command = "OR";
		case 35 -> command = "LW";
		case 43 -> command = "SW";
		case 0 -> command = "SLL";
		case 2 -> command = "J";
		case 3 -> command = "JAL";
		case 4 -> command = "BEQ";
		case 5 -> command = "BNE";
		case -1 -> command = "JR";
		}
	}
}