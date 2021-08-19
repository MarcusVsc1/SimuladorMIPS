package com.ufjf.mips.model;

import java.util.Arrays;

import com.ufjf.mips.enums.EInstruction;
import com.ufjf.mips.interfaces.Instruction;

public class TypeR extends Instruction{
	private Integer op;
	private Integer rs;
	private Integer rt;
	private Integer rd;
	private Integer shamt;
	private Integer funct;
	
	
	public TypeR(String binary) {
		super(
				Integer.parseInt(binary.substring(26), 2) != 8 ? Integer.parseInt(binary.substring(26), 2) : -1
		);
		
		
		op = Integer.parseInt(binary.substring(0, 6), 2); 
		rs = Integer.parseInt(binary.substring(6, 11), 2);
		rt = Integer.parseInt(binary.substring(11, 16), 2); 
		rd = Integer.parseInt(binary.substring(16, 21), 2);
		shamt = Integer.parseInt(binary.substring(21, 26), 2); 
		funct = Integer.parseInt(binary.substring(26), 2); 
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

	public Integer getRd() {
		return rd;
	}

	public void setRd(Integer rd) {
		this.rd = rd;
	}

	public Integer getShamt() {
		return shamt;
	}

	public void setShamt(Integer shamt) {
		this.shamt = shamt;
	}

	public Integer getFunct() {
		return funct;
	}

	public void setFunct(Integer funct) {
		this.funct = funct;
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
