package com.ufjf.mips.model;

import com.ufjf.mips.enums.EInstruction;
import com.ufjf.mips.interfaces.Instruction;


public class TypeJ extends Instruction{
	private Integer op;
	private Integer add;
	
	public TypeJ(String binary) {
		super(Integer.parseInt(binary.substring(0, 6), 2));
		op = Integer.parseInt(binary.substring(0, 6), 2); 
		add = Integer.parseInt(binary.substring(6, 32), 2); 
	}

	public Integer getOp() {
		return op;
	}

	public void setOp(Integer op) {
		this.op = op;
	}

	public Integer getAdd() {
		return add;
	}

	public void setAdd(Integer add) {
		this.add = add;
	}

	@Override
	public String createAssembly() {
		return EInstruction.valueOf(super.getCommand()).createAssembly(this);
	}

	@Override
	public void realizarExecucaoDireta() {
		EInstruction.valueOf(super.getCommand()).execucaoDireta(this);;
		
	}

}
