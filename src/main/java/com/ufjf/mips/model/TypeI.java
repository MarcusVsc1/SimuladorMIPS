package com.ufjf.mips.model;

import com.ufjf.mips.enums.EInstruction;
import com.ufjf.mips.interfaces.Instruction;


public class TypeI extends Instruction{
	private Integer op;
	private Integer rs;
	private Integer rt;
	private Integer imm; 
	
	public TypeI(String binary) {
		super(Integer.parseInt(binary.substring(0, 6), 2));
		op = Integer.parseInt(binary.substring(0, 6), 2); 
		rs = Integer.parseInt(binary.substring(6, 11), 2);
		rt = Integer.parseInt(binary.substring(11, 16), 2);
		imm = Integer.parseInt(binary.substring(16), 2); 
	}

	public Integer getOp() {
		return op;
	}

	public void setOp(Integer op) {
		this.op = op;
	}

	public Integer getRs() {
		return rs;
	}

	public void setRs(Integer rs) {
		this.rs = rs;
	}

	public Integer getRt() {
		return rt;
	}

	public void setRt(Integer rt) {
		this.rt = rt;
	}

	public Integer getImm() {
		return imm;
	}

	public void setImm(Integer imm) {
		this.imm = imm;
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
