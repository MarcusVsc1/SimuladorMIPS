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
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
				MipsOperational.bancoRegistradores[32] += 4;
			} else {
				MipsOperational.log +=String.format("Instrução %s: registrador %s modificado. \n"
						+ "Instrução finalizada. \n", ins.createAssembly(),
						MipsOperational.mapa.get(((TypeR)ins).getRd())
						);
			}
			
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] + 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			MipsOperational.log += run;
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e RT para a instrução %s: %s %s \n", ins.createAssembly(), 
					((TypeR)ins).getRs(), 
					((TypeR)ins).getRt()
					);
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			Integer soma = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] + 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			representarALU(ins, soma);
			return true;
		}
		
		@Override
		public boolean register(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
				MipsOperational.bancoRegistradores[32] += 4;
			} else {
				MipsOperational.log +=String.format("Instrução %s: registrador %s modificado. \n"
						+ "Instrução finalizada. \n", ins.createAssembly(),
						MipsOperational.mapa.get(((TypeR)ins).getRd())
						);
			}
			
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] - 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			MipsOperational.log += run;
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e RT para a instrução %s: \n", ((TypeR)ins).getRs(), 
					((TypeR)ins).getRt(), ins.createAssembly());
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			Integer soma = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] - 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			representarALU(ins, soma);
			return true;
		}	
		
		@Override
		public boolean register(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
				MipsOperational.bancoRegistradores[32] += 4;
			} else {
				MipsOperational.log +=String.format("Instrução %s: registrador %s modificado. \n"
						+ "Instrução finalizada. \n", ins.createAssembly(),
						MipsOperational.mapa.get(((TypeR)ins).getRd())
						);
			}
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] & 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			MipsOperational.log += run;
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e RT  para a instrução %s: \n", ((TypeR)ins).getRs(), 
					((TypeR)ins).getRt(), ins.createAssembly());
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			Integer soma = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] & 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			representarALU(ins, soma);
			return true;
		}
		@Override
		public boolean register(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
				MipsOperational.bancoRegistradores[32] += 4;
			} else {
				MipsOperational.log +=String.format("Instrução %s: registrador %s modificado. \n"
						+ "Instrução finalizada. \n", ins.createAssembly(),
						MipsOperational.mapa.get(((TypeR)ins).getRd())
						);
			}
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] | 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			MipsOperational.log += run;
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e RT para a instrução %s: \n", ((TypeR)ins).getRs(), 
					((TypeR)ins).getRt(), ins.createAssembly());
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			Integer soma = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] | 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRt()];
			representarALU(ins, soma);
			return true;
		}
		
		@Override
		public boolean register(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
				MipsOperational.bancoRegistradores[32] += 4;
			} else {
				MipsOperational.log +=String.format("Instrução %s: registrador %s modificado. \n"
						+ "Instrução finalizada. \n", ins.createAssembly(),
						MipsOperational.mapa.get(((TypeR)ins).getRd())
						);
			}
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = 
					MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] <	MipsOperational.bancoRegistradores[((TypeR) ins).getRt()] ?
							1 : 0;
			MipsOperational.log += run;
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e RT para a instrução %s: \n", ((TypeR)ins).getRs(), 
					((TypeR)ins).getRt(), ins.createAssembly());
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			Integer result = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()] <	MipsOperational.bancoRegistradores[((TypeR) ins).getRt()] ?
					1 : 0;
			representarALU(ins, result);
			return true;
		}
		
		@Override
		public boolean register(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			String run = "";
			Integer posicao = (MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] + ((TypeI) ins).getImm());
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run += "Instrução rodada com sucesso! \n";
				MipsOperational.bancoRegistradores[32] += 4;
			} else {
					run += String.format("Instrução %s: Esta se trata de uma operação de escrita."
							+ "\nMemória na posição %s modificada. "
							+ "\nInstrução finalizada. \n", ins.createAssembly(),
							posicao);
			}
			MipsOperational.memoria[posicao / 4] 
					 = MipsOperational.bancoRegistradores[((TypeI) ins).getRt()];
			MipsOperational.log += run;
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e Immediate para a instrução %s: %s %s \n", ins.createAssembly(),
					((TypeI)ins).getRs(), 
					((TypeI)ins).getImm());
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			Integer result = ((TypeI) ins).getImm() + MipsOperational.bancoRegistradores[((TypeI) ins).getRs()];
			representarALU(ins, result);
			return true;
		}	
		
		@Override
		public boolean memo(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run += "Instrução rodada com sucesso! \n";
				MipsOperational.bancoRegistradores[32] += 4;				
			} else {
					MipsOperational.log +=String.format("Instrução %s: esta se trata de uma operação de leitura. "
							+ "\nRegistrador %s modificado. \n"
							+ "Instrução finalizada. \n", ins.createAssembly(),
							MipsOperational.mapa.get(((TypeI)ins).getRt())
							);
				}
			MipsOperational.bancoRegistradores[((TypeI) ins).getRt()]
					 = MipsOperational.memoria[(MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] + 
							 ((TypeI) ins).getImm()) / 4];
			MipsOperational.log += run;
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e Immediate para a instrução %s: \n", ((TypeI)ins).getRs(), 
					((TypeI)ins).getImm(), ins.createAssembly());
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			Integer result = ((TypeI) ins).getImm() + MipsOperational.bancoRegistradores[((TypeI) ins).getRs()];
			representarALU(ins, result);
			return true;
		}
		@Override
		public boolean register(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
		}
		@Override
		public boolean memo(Instruction ins) {
			Integer result = ((TypeI) ins).getImm() + MipsOperational.bancoRegistradores[((TypeI) ins).getRs()];
			MipsOperational.log +=String.format("Instrução %s: Valor contido no endereço de memória %s\n", ins.createAssembly(),
					result,
					MipsOperational.bancoRegistradores[result/4]);
			return true;
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
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
				MipsOperational.bancoRegistradores[32] += 4;
			}
			else {
				MipsOperational.log +=String.format("Instrução %s: registrador %s modificado. \n"
						+ "Instrução finalizada. \n", ins.createAssembly(),
						MipsOperational.mapa.get(((TypeI)ins).getRt())
						);
			}
			MipsOperational.bancoRegistradores[((TypeI) ins).getRt()] = MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] + 
					((TypeI) ins).getImm();
			MipsOperational.log += run;
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e Immediate para a instrução %s: %s %s \n", ins.createAssembly(),
					((TypeI)ins).getRs(), 
					((TypeI)ins).getImm());
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			Integer result = ((TypeI) ins).getImm() + MipsOperational.bancoRegistradores[((TypeI) ins).getRs()];
			representarALU(ins, result);
			return true;
		}
		
		@Override
		public boolean register(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run += "Instrução rodada com sucesso! \n";
				MipsOperational.bancoRegistradores[32] += 4;
			} else {
				MipsOperational.log +=String.format("Instrução %s: registrador %s modificado. \n"
						+ "Instrução finalizada. \n", ins.createAssembly(),
						MipsOperational.mapa.get(((TypeR)ins).getRd())
						);
			}
			MipsOperational.bancoRegistradores[((TypeR) ins).getRd()] = MipsOperational.bancoRegistradores[((TypeR) ins).getRt()] <<
					((TypeR) ins).getShamt();
			MipsOperational.log += run;		
		}
		
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de Rt e SHAMT para a instrução %s: %s %s\n", ins.createAssembly(),
					((TypeR)ins).getRt(), 
					((TypeR)ins).getShamt()
					);
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			Integer result = MipsOperational.bancoRegistradores[((TypeR) ins).getRt()] <<
					((TypeR) ins).getShamt();
			representarALU(ins, result);
			return true;
		}
		
		@Override
		public boolean register(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			Integer branch = 
					MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] == MipsOperational.bancoRegistradores[((TypeI) ins).getRt()] ?
							((TypeI) ins).getImm() : 0;
			MipsOperational.bancoRegistradores[32] += branch;
			String run = "";
			if(!MipsOperational.piped) {
				MipsOperational.bancoRegistradores[32] += 4;
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
			}else {
				run += branch != 0 ? 
						String.format("ALU: Desvio feito. Valor de PC: %s\n. Instrução finalizada no pipeline.\n", 
						MipsOperational.bancoRegistradores[32]) : 
							String.format("ALU instrução %s: Desvio não realizado. \nInstrução finalizada no pipeline.\n", 
									ins.createAssembly(), MipsOperational.bancoRegistradores[32]);				
			}
			MipsOperational.log += run;	
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e RT para a instrução %s: %s %s \n", ins.createAssembly(),
					((TypeI)ins).getRs(), ((TypeI)ins).getRt());
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			Integer branch =
					MipsOperational.bancoRegistradores[((TypeI) ins).getRs()] != MipsOperational.bancoRegistradores[((TypeI) ins).getRt()] ?
							((TypeI) ins).getImm() : 0;
			MipsOperational.bancoRegistradores[32] += branch;
			String run = "";
			if(!MipsOperational.piped) {
				MipsOperational.bancoRegistradores[32] += 4;
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
			}else {
				run += branch != 0 ? 
						String.format("ALU: Desvio feito. Valor de PC: %s\n. Instrução finalizada no pipeline.\n", 
						MipsOperational.bancoRegistradores[32]) : 
							String.format("ALU instrução %s: Desvio não realizado. Valor de PC: %s\n. Instrução finalizada no pipeline.\n", 
									ins.createAssembly(), MipsOperational.bancoRegistradores[32]);				
			}
			MipsOperational.log += run;	
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valores de RS e RT para a instrução %s: \n", ((TypeI)ins).getRs(), ((TypeI)ins).getRt());
			return true;
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
			MipsOperational.bancoRegistradores[32] = ((TypeJ) ins).getAdd();
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
			}else {
				run += String.format("Instrução %s, Valor de PC: %s\n. Instrução finalizada no pipeline.\n", 
						ins.createAssembly(),  MipsOperational.bancoRegistradores[32]);
				MipsOperational.bancoRegistradores[32] -= 4;
			}
			MipsOperational.log += run;		
		}
		
		@Override
		public boolean fetch(Instruction ins) {
			String run = "Fetch da instrução : "+ins.createAssembly()+"\n";
			ins.realizarExecucaoDireta();
			return true;
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
			String run = "";
			ins = (TypeR) ins;
			MipsOperational.bancoRegistradores[32] = MipsOperational.bancoRegistradores[((TypeR) ins).getRs()];
			if(!MipsOperational.piped) {
				run += "Iniciando instrução : "+ins.createAssembly()+"\n";
				run += "Instrução rodada com sucesso! \n";
			}else {
				run += String.format("Instrução %s, Valor de PC: %s\n. Instrução finalizada no pipeline.\n", 
						ins.createAssembly(),  MipsOperational.bancoRegistradores[32]);
				MipsOperational.bancoRegistradores[32] -= 4;
			}
			MipsOperational.log += run;			
		}
		
		@Override
		public boolean read(Instruction ins) {
			boolean hazard = ins.verifyDataHazard();
			if(!hazard) return false;
			MipsOperational.log +=String.format("Valor de RS para a instrução %s: \n", ((TypeR)ins).getRs());
			return true;
		}
		
		@Override
		public boolean alu(Instruction ins) {
			ins.realizarExecucaoDireta();
			return true;
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
			MipsOperational.bancoRegistradores[31] = MipsOperational.bancoRegistradores[32] + 4;
			MipsOperational.bancoRegistradores[32] = ((TypeJ) ins).getAdd();
			String run = "";
			if(!MipsOperational.piped) {
				run = "Iniciando instrução : "+ins.createAssembly()+"\n";
				run = run + "Instrução rodada com sucesso! \n";
			}else {
				run += String.format("Instrução %s, Valor de PC: %s\n Valor de $ra: %s. Instrução finalizada no pipeline.\n", 
						ins.createAssembly(), MipsOperational.bancoRegistradores[32], MipsOperational.bancoRegistradores[31]);
			}
			
			MipsOperational.log += run;		
		}
		
		@Override
		public boolean fetch(Instruction ins) {
			String run = "Fetch da instrução : "+ins.createAssembly()+"\n";
			ins.realizarExecucaoDireta();
			return true;
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
	
	public boolean fetch(Instruction ins) {
		MipsOperational.log += "Fetch da instrução : "+ins.createAssembly()+"\n";
		return true;
	}
	
	public boolean read(Instruction ins) {
		return true;
	}
	
	public boolean alu(Instruction ins) {
		return true;
	}
	
	public void representarALU(Instruction ins, Integer valor) {
		MipsOperational.log +=String.format("Valor calculado pela ALU para a instrução %s: %s \n", ins.createAssembly(), valor);
	}
	
	public boolean register(Instruction ins) {
		return true;
	}
	
	public boolean memo(Instruction ins) {
		return true;
	}
}
