/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufjf.mips.ui;

import java.awt.Color;
import java.awt.Font;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

import javax.naming.TimeLimitExceededException;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.ufjf.mips.model.MipsOperational;
import com.ufjf.mips.utils.FileManager;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Nova
 */
public class MipsForm extends javax.swing.JFrame {
	
	MipsOperational ops;
	private Integer numeroInstrucoes;
	JTextArea log;
	
    /**
     * Creates new form MipsForm
     */
    public MipsForm(String[] comandos) {
    	setAlwaysOnTop(true);
    	numeroInstrucoes = comandos.length;
        ops = new MipsOperational(comandos);
    	initComponents();
        configurarUI();
    } 

	private void adicionarInstrucoesAoTable(MipsOperational mipsOp) {
		List<String> lista = mipsOp.getAssemblyList();
		DefaultTableModel model = (DefaultTableModel) instrucoes.getModel();
		Integer cont = 0;
		for(String item : lista) {
			model.addRow(new String[] {cont.toString(), item});
			cont += 4;
		}
	}

	private void configurarUI() {
		iniciarLog();
		iniciarTableMemoria();
		adicionarInstrucoesAoTable(ops);
		this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setExtendedState(MipsForm.MAXIMIZED_BOTH);
	}

	private void iniciarTableMemoria() {
		memoria.setFocusable(false);
		memoria.getTableHeader().setReorderingAllowed(false);
		DefaultTableModel model = (DefaultTableModel) memoria.getModel();
		for(Integer i = 0; i < 128; i++) {
			Integer num = (i*4);
			model.addRow(new String[] {num.toString(), "###"});
		}	
	}

	private void iniciarLog() {
		log = new JTextArea();
        log.setText("Log de execução:");
        log.setWrapStyleWord(false);
        log.setEditable(false);
        scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(log);
        
	}
	
	private void iniciarNovaInstrução() {
		ops.piped =  false;
		instrucoes.clearSelection();
    	instrucoes.setRowSelectionInterval(ops.bancoRegistradores[32]/4, ops.bancoRegistradores[32]/4);
    	ops.execucaoSequencial();
    	atualizarUI();
	}
	
	private void executarTodasAsInstrucoes() {
		ops.piped =  false;
		Date start = new Date();
		try {
			while(ops.bancoRegistradores[32]/4 < ops.getAssemblyList().size()) {
				iniciarNovaInstrução();
				if(getTime(start) > 4 && (ops.bancoRegistradores[32]/4 < ops.getAssemblyList().size())) 
					throw new TimeLimitExceededException("Execução direta entrou em loop infinito e será terminada.");
			}
			finalizarPrograma();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage(),
                    "Erro!",0);
			this.setVisible(false);
		} 
	}
	
	private void pipelineSequencial() throws CloneNotSupportedException {
		ops.pipelineSequencial();
		atualizarUI();
		ops.piped = true;
		if(ops.bancoRegistradores[32]/4 >= ops.getAssemblyList().size()) {
        	finalizarPrograma();
        }
	}
	
	private void executarTodoPipeline() {
		ops.piped = true;
		Date start = new Date();
		try {
			while(ops.bancoRegistradores[32]/4 < ops.getAssemblyList().size()) {
				pipelineSequencial();
				if(getTime(start) > 4 && (ops.bancoRegistradores[32]/4 < ops.getAssemblyList().size())) 
					throw new TimeLimitExceededException("Execução direta entrou em loop infinito e será terminada.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage(),
                    "Erro!",0);
			this.setVisible(false);
		} 
	}
	
    private void resetarPrograma() {
		ops.inicializarRegistradoresEMemoria();
		MipsOperational.log = "Log de execução: \n";
		atualizarUI();
		jButton1.setEnabled(true);
		jButton2.setEnabled(true);
		ops.setClock(0);
		ops.pipeline.clear();
		btnPipelineDireto.setEnabled(true);
		btnPipelineSequencial.setEnabled(true);
		ops.piped =  false;
	}

	private void finalizarPrograma() {
		JOptionPane.showMessageDialog(rootPane, "A compilação do conjunto de instruções foi finalizado. "
				+ "Aperte reset caso queira rodar novamente. Arquivos criados na pasta output.",
		        "Fim do programa",0);
		jButton1.setEnabled(false);
		jButton2.setEnabled(false);
		String memo = FileManager.criarStringArrayMemoria(ops.memoria);
		try {
			FileManager.escreverEmArquivo(memo, "memoria.txt");
			FileManager.escreverEmArquivo(ops.log, "log.txt");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Falha ao criar algum dos arquivos.",
			        "Erro!",0);
		}
	}

    private long getTime(Date start) {
    	Date now = new Date();
		long millis = now.getTime() - start.getTime();
		return millis/1000;
	}


	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        registradores = new javax.swing.JTable();
        registradores.setEnabled(false);
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton3.setFont(new Font("Arial Black", Font.BOLD, 11));
        jButton3.setBackground(Color.RED);
        jScrollPane4 = new javax.swing.JScrollPane();
        instrucoes = new javax.swing.JTable();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulador MIPS");
        if (instrucoes.getColumnModel().getColumnCount() > 0) {
            instrucoes.getColumnModel().getColumn(0).setResizable(false);
            instrucoes.getColumnModel().getColumn(1).setResizable(false);
        }

        registradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"$zero", "0"},
                {"$at", "###"},
                {"$v0", "###"},
                {"$v1", "###"},
                {"$a0", "###"},
                {"$a1", "###"},
                {"$a2", "###"},
                {"$a3", "###"},
                {"$t0", "###"},
                {"$t1", "###"},
                {"$t2", "###"},
                {"$t3", "###"},
                {"$t4", "###"},
                {"$t5", "###"},
                {"$t6", "###"},
                {"$t7", "###"},
                {"$s0", "###"},
                {"$s1", "###"},
                {"$s2", "###"},
                {"$s3", "###"},
                {"$s4", "###"},
                {"$s5", "###"},
                {"$s6", "###"},
                {"$s7", "###"},
                {"$t8", "###"},
                {"$t9", "###"},
                {"$k0", "###"},
                {"$k1", "###"},
                {"$gp", "###"},
                {"$sp", "###"},
                {"$fp", "###"},
                {"$ra", "###"},
                {"PC", "0"}
            },
            new String [] {
                "Registrador", "Valor"
            }
        ));
        registradores.setFocusable(false);
        registradores.setRequestFocusEnabled(false);
        registradores.setRowSelectionAllowed(false);
        registradores.getTableHeader().setReorderingAllowed(false);
        registradores.setUpdateSelectionOnSort(false);
        registradores.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(registradores);
        if (registradores.getColumnModel().getColumnCount() > 0) {
            registradores.getColumnModel().getColumn(0).setResizable(false);
            registradores.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton1.setText("Execução direta >>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Execução Sequencial >");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        instrucoes.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Endere\u00E7o", "Instru\u00E7\u00E3o"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        instrucoes.getColumnModel().getColumn(0).setResizable(false);
        instrucoes.getColumnModel().getColumn(1).setResizable(false);
        instrucoes.setEnabled(false);
        instrucoes.setFocusable(false);
        instrucoes.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(instrucoes);
        if (instrucoes.getColumnModel().getColumnCount() > 0) {
            instrucoes.getColumnModel().getColumn(0).setResizable(false);
            instrucoes.getColumnModel().getColumn(1).setResizable(false);
        }
        
        scrollPane = new JScrollPane();
        
        JScrollPane scrollPane_1 = new JScrollPane();
        
        btnPipelineDireto = new JButton();
        btnPipelineDireto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		executarTodoPipeline();
        	}

			
        });
        btnPipelineDireto.setText("Pipeline direto >>");
        
        btnPipelineSequencial = new JButton();
        btnPipelineSequencial.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					pipelineSequencial();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}

			
        });
        btnPipelineSequencial.setText("Pipeline Sequencial >");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btnPipelineDireto, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnPipelineSequencial, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jButton1)
        					.addGap(18)
        					.addComponent(jButton2)
        					.addGap(18)
        					.addComponent(jButton3))
        				.addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE))
        			.addGap(72)
        			.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
        			.addGap(121)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
        			.addGap(150))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 493, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jButton3)
        						.addComponent(jButton2)
        						.addComponent(jButton1)))
        				.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(27)
        					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)))
        			.addGap(32)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnPipelineDireto)
        				.addComponent(btnPipelineSequencial))
        			.addGap(184))
        );
        
        memoria = new JTable();
        memoria.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Endere\u00E7o de mem\u00F3ria", "Valor"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		Object.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        	boolean[] columnEditables = new boolean[] {
        		false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        memoria.getColumnModel().getColumn(0).setResizable(false);
        memoria.getColumnModel().getColumn(0).setPreferredWidth(50);
        memoria.getColumnModel().getColumn(1).setResizable(false);
        memoria.setRowSelectionAllowed(false);
        memoria.setFillsViewportHeight(true);
        scrollPane_1.setViewportView(memoria);
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    	btnPipelineDireto.setEnabled(false);
		btnPipelineSequencial.setEnabled(false);
    	executarTodasAsInstrucoes();
    }//GEN-LAST:event_jButton1ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        iniciarNovaInstrução();
        btnPipelineDireto.setEnabled(false);
		btnPipelineSequencial.setEnabled(false);
        if(ops.bancoRegistradores[32]/4 >= ops.getAssemblyList().size()) {
        	finalizarPrograma();
        }
    }

    private void atualizarUI() {
		for(int i = 1; i < MipsOperational.bancoRegistradores.length; i++) {
			if(MipsOperational.bancoRegistradores[i] != -999999) 
				registradores.setValueAt(MipsOperational.bancoRegistradores[i], i, 1);
			else
				registradores.setValueAt("###", i, 1);
		}
		for(int i = 0; i < MipsOperational.memoria.length; i++) {
			if(MipsOperational.memoria[i] != -999999) 
				memoria.setValueAt(MipsOperational.memoria[i], i, 1);
			else
				memoria.setValueAt("###", i, 1);
		}
		log.setText(MipsOperational.log);		
	}


	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       resetarPrograma();
    }//GEN-LAST:event_jButton3ActionPerformed

	/**
 * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MipsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MipsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MipsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MipsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable instrucoes;
    private javax.swing.JTable registradores;
    private JScrollPane scrollPane;
    private JTable memoria;
    private JButton btnPipelineDireto;
    private JButton btnPipelineSequencial;
}
