package apps;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Gravador;

import java.awt.FlowLayout;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JFileChooser;


public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Gravador gr = new Gravador();
	
	JButton btnGravar = new JButton("Gravar");
	JButton btnParar = new JButton("Parar");
	private final JButton bntSel = new JButton("Selecionar Pasta");
	
	
	
	public TelaInicial() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{140, 140, 0};
		gbl_contentPane.rowHeights = new int[] {130, 30, 30, 30};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lbltela = new JLabel("");
		lbltela.setFont(new Font("Arial", Font.BOLD, 40));
		lbltela.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lbltela = new GridBagConstraints();
		gbc_lbltela.gridwidth = 2;
		gbc_lbltela.insets = new Insets(0, 0, 5, 0);
		gbc_lbltela.fill = GridBagConstraints.BOTH;
		gbc_lbltela.gridx = 0;
		gbc_lbltela.gridy = 0;
		contentPane.add(lbltela, gbc_lbltela);
		btnGravar.setFocusable(false);
		
		
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnParar.setEnabled(true);
			btnGravar.setEnabled(false);
			iniciar();
			lbltela.setText("Gravando para pasta " + gr.getWavFile());
			}
		});
		
		GridBagConstraints gbc_bntSel = new GridBagConstraints();
		gbc_bntSel.fill = GridBagConstraints.BOTH;
		gbc_bntSel.gridwidth = 2;
		gbc_bntSel.insets = new Insets(0, 0, 5, 0);
		gbc_bntSel.gridx = 0;
		gbc_bntSel.gridy = 2;
		bntSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaArquivo tr;
				try {
					tr = new TelaArquivo();
					tr.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		GridBagConstraints gbc_fileChooser = new GridBagConstraints();
		gbc_fileChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fileChooser.gridx = 0;
		gbc_fileChooser.gridy = 1;
		bntSel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(bntSel, gbc_bntSel);
		btnGravar.setFont(new Font("Arial", Font.BOLD, 16));
		GridBagConstraints gbc_btnGravar = new GridBagConstraints();
		gbc_btnGravar.gridheight = 2;
		gbc_btnGravar.fill = GridBagConstraints.BOTH;
		gbc_btnGravar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGravar.gridx = 0;
		gbc_btnGravar.gridy = 3;
		contentPane.add(btnGravar, gbc_btnGravar);
		btnParar.setFocusable(false);
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			parar();
			}
			});
		btnParar.setFont(new Font("Arial", Font.BOLD, 16));
		GridBagConstraints gbc_btnParar = new GridBagConstraints();
		gbc_btnParar.gridheight = 2;
		gbc_btnParar.fill = GridBagConstraints.BOTH;
		gbc_btnParar.gridx = 1;
		gbc_btnParar.gridy = 3;
		contentPane.add(btnParar, gbc_btnParar);
	}
	
	private void iniciar() {
		Thread recordThread = new Thread(new Runnable() {
			@Override
			public void run() {
				btnGravar.setEnabled(false);
				btnParar.setEnabled(true);
				gr.start();
			}
		});
		recordThread.start();
	}
	
	private void parar() {
				btnParar.setEnabled(false);
				btnGravar.setEnabled(true);
				gr.finish();
	}
	
}
