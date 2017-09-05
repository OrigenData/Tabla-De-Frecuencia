package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tabla.tablepress;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class main extends JFrame {

	private JPanel contentPane;
	private JTextField txtVarCapt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JButton BttnVarCapt = new JButton("OK");

		//New Windows
		//ablepress tp;
		BttnVarCapt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Captura

				try {

					int numCaptura=0;
					numCaptura = Integer.parseInt(txtVarCapt.getText());

					//Tiene que ser mayor a 0 y menor a 100
					if(numCaptura>0 && numCaptura<=100) {

						tablepress tp=new tablepress();

						tp.setNumCaptura(numCaptura);

						tp.setVisible(true);
						main.this.dispose();

					}else {

						JOptionPane.showMessageDialog(null, "El numero tiene que ser mayor a 0 y menor o igual 100");

					}





				}catch(Exception ee) {

					JOptionPane.showMessageDialog(null, "Introduzca solo numeros");
					System.err.println("Error: "+ee.getMessage());

				}


			}
		});

		JLabel lblNewLabel = new JLabel("Ingrese la cantidad de datos que va a capturar");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		txtVarCapt = new JTextField();
		GridBagConstraints gbc_txtVarCapt = new GridBagConstraints();
		gbc_txtVarCapt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVarCapt.anchor = GridBagConstraints.NORTH;
		gbc_txtVarCapt.insets = new Insets(0, 0, 5, 5);
		gbc_txtVarCapt.gridx = 1;
		gbc_txtVarCapt.gridy = 4;
		contentPane.add(txtVarCapt, gbc_txtVarCapt);
		txtVarCapt.setColumns(10);
		GridBagConstraints gbc_BttnVarCapt = new GridBagConstraints();
		gbc_BttnVarCapt.anchor = GridBagConstraints.SOUTH;
		gbc_BttnVarCapt.insets = new Insets(0, 0, 0, 5);
		gbc_BttnVarCapt.gridx = 1;
		gbc_BttnVarCapt.gridy = 5;
		contentPane.add(BttnVarCapt, gbc_BttnVarCapt);
	}

}
