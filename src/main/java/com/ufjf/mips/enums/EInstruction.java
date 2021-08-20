package com.ufjf.mips.enums;

import com.ufjf.mips.model.TypeR;
import com.ufjf.mips.model.TypeI;
import com.ufjf.mips.model.TypeJ;
import com.ufjf.mips.interfaces.Instruction;
import com.ufjf.mips.model.MipsOperational;

public enum EInstruction {
	
	ADD(32) {
		@Override
		public String createAssembly(Instruction ins) {
			return String.format("add %s, %s, %s", 
					MipsOperational.mapa.get(((TypeR) ins).getRd()), 
					MipsOperational.mapa.get(((TypeR) ins).getRs()), 
					MipsOperational.mapa.get(((TypeR) ins).getRt())
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeR) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] + 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;
		}
	}, 
	SUB(34) {
		@Override
		public String createAssembly(Instruction ins) {
			return String.format("sub %s, %s, %s", 
					MipsOperational.mapa.get(((TypeR) ins).getRd()), 
					MipsOperational.mapa.get(((TypeR) ins).getRs()), 
					MipsOperational.mapa.get(((TypeR) ins).getRt())
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeR) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] - 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;
		}
	}, 
	AND(36) {
		@Override
		public String createAssembly(Instruction ins) {
			return String.format("and %s, %s, %s", 
					MipsOperational.mapa.get(((TypeR) ins).getRd()), 
					MipsOperational.mapa.get(((TypeR) ins).getRs()), 
					MipsOperational.mapa.get(((TypeR) ins).getRt())
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeR) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] & 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;
		}
	}, 
	OR(37) {
		@Override
		public String createAssembly(Instruction ins) {
			return String.format("or %s, %s, %s", 
					MipsOperational.mapa.get(((TypeR) ins).getRd()), 
					MipsOperational.mapa.get(((TypeR) ins).getRs()), 
					MipsOperational.mapa.get(((TypeR) ins).getRt())
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeR) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] | 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;
		}
	}, 
	SLT(42) {
		@Override
		public String createAssembly(Instruction ins) {
			return String.format("slt %s, %s, %s", 
					MipsOperational.mapa.get(((TypeR) ins).getRd()), 
					MipsOperational.mapa.get(((TypeR) ins).getRs()), 
					MipsOperational.mapa.get(((TypeR) ins).getRt())
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeR) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] <	MipsOperational.bancoRegistradores[((TypeR) ins).getRt()] ?
							1 : 0;
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;
		}
	}, 
	SW(43) {
		@Override
		public String createAssembly(Instruction ins) {
			ins = (TypeI) ins;
			return String.format("sw %s, %s(%s)", 
					MipsOperational.mapa.get(((TypeI) ins).getRt()), 
					((TypeI) ins).getImm(),
					MipsOperational.mapa.get(((TypeI) ins).getRs())
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeI) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.memoria[(MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] + ((TypeI) ins).getImm()) / 4] 
					 = MipsOperational.bancoRegistradores[((TypeI) ins).getRt()];
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;
		}
	}, 
	LW(35) {
		@Override
		public String createAssembly(Instruction ins) {
			ins = (TypeI) ins;
			System.out.println(((TypeI) ins).getRs());
			return String.format("lw %s, %s(%s)", 
					MipsOperational.mapa.get(((TypeI) ins).getRt()), 
					((TypeI) ins).getImm(),
					MipsOperational.mapa.get(((TypeI) ins).getRs())
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeI) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[((TypeI) ins).getRt()]
					 = MipsOperational.memoria[(MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] + ((TypeI) ins).getImm()) / 4];
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;
		}
	}, 
	ADDI(8) {
		@Override
		public String createAssembly(Instruction ins) {
			ins = (TypeI) ins;
			return String.format("addi %s, %s, %s", 
					MipsOperational.mapa.get(((TypeI) ins).getRt()), 
					MipsOperational.mapa.get(((TypeI) ins).getRs()), 
					((TypeI) ins).getImm()
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeI) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[((TypeI) ins).getRt()] = MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] + 
					((TypeI) ins).getImm();
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;
		}
	},
	SLL(0) {
		@Override
		public String createAssembly(Instruction ins) {
			return String.format("sll %s, %s, %s", 
					MipsOperational.mapa.get(((TypeR) ins).getRd()), 
					MipsOperational.mapa.get(((TypeR) ins).getRt()), 
					((TypeR) ins).getShamt()
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeR) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRt()] <<
					((TypeR) ins).getShamt();
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;			
		}
	},
	BEQ(4) {
		@Override
		public String createAssembly(Instruction ins) {
			ins = (TypeI) ins;
			return String.format("beq %s, %s, %s", 
					MipsOperational.mapa.get(((TypeI) ins).getRs()), 
					MipsOperational.mapa.get(((TypeI) ins).getRt()), 
					((TypeI) ins).getImm()
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeI) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[32] += 
					MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] == MipsOperational.bancoRegistradores[((TypeI) ins).getRt()] ?
					((TypeI) ins).getImm() : 0;
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;			
		}
	}, 
	BNE(5) {
		@Override
		public String createAssembly(Instruction ins) {
			ins = (TypeI) ins;
			return String.format("bne %s, %s, %s", 
					MipsOperational.mapa.get(((TypeI) ins).getRs()), 
					MipsOperational.mapa.get(((TypeI) ins).getRt()), 
					((TypeI) ins).getImm()
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeI) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[32] += 
					MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] != MipsOperational.bancoRegistradores[((TypeI) ins).getRt()] ?
					((TypeI) ins).getImm() : 0;
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;
			MipsOperational.bancoRegistradores[32] += 4;
		}
	},
	J(2) {
		@Override
		public String createAssembly(Instruction ins) {
			return String.format("j %s", 
					((TypeJ) ins).getAdd()
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeJ) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[32] = ((TypeJ) ins).getAdd();
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;		
		}
	}, 
	JR(-1) {
		@Override
		public String createAssembly(Instruction ins) {
			return String.format("jr %s", 
					MipsOperational.mapa.get(((TypeR) ins).getRs()) 
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeR) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[32] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()];
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;			
		}
	}, 
	JAL(3) {
		@Override
		public String createAssembly(Instruction ins) {
			return String.format("jal %s", 
					((TypeJ) ins).getAdd()
					);
		}

		@Override
		public void execucaoDireta(Instruction ins) {
			ins = (TypeJ) ins;
			String run = "Iniciando instrução : "+ins.createAssembly()+"\n";
			MipsOperational.bancoRegistradores[31] = MipsOperational.bancoRegistradores[32] + 4;
			MipsOperational.bancoRegistradores[32] = ((TypeJ) ins).getAdd();
			run = run + "Instrução rodada com sucesso! \n";
			MipsOperational.log += run;		
		}
	};
	private final Integer ins;
	
	EInstruction(Integer i) {
		this.ins = i;
	} 
	

	
	public Integer getIns() {
		return ins;
	}



	//public abstract void runFunction();
	public abstract String createAssembly(Instruction ins);
	public abstract void execucaoDireta(Instruction ins);
}
