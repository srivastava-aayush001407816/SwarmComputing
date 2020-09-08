package com.neu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.neu.controller.AppManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.awt.event.ActionEvent;

public class TSP extends JFrame {

	private JPanel contentPane;
	private JTextField citiesTextBox;
	private JTextField rangeTextBox;
	static TSP frame = new TSP();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
	public TSP() {
		setTitle("PSA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCities = new JLabel("Cities:");
		lblCities.setBounds(96, 77, 107, 20);
		panel.add(lblCities);
		
		citiesTextBox = new JTextField();
		citiesTextBox.setBounds(225, 77, 86, 20);
		panel.add(citiesTextBox);
		citiesTextBox.setColumns(10);
		
		JLabel lblSwarmComputing = new JLabel("Swarm Computing");
		lblSwarmComputing.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwarmComputing.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblSwarmComputing.setBounds(96, 26, 215, 25);
		panel.add(lblSwarmComputing);
		
		JLabel lblRangeOfGrid = new JLabel("Range of Grid:");
		lblRangeOfGrid.setBounds(96, 125, 107, 20);
		panel.add(lblRangeOfGrid);
		
		rangeTextBox = new JTextField();
		rangeTextBox.setColumns(10);
		rangeTextBox.setBounds(225, 125, 86, 20);
		panel.add(rangeTextBox);
		
		JButton go = new JButton("Generate Data & Compute");
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int c=0;
				int r=0;
				
				try {
					c = Integer.parseInt(citiesTextBox.getText());
					r = Integer.parseInt(rangeTextBox.getText());
					if(c> 500 || r > 1000) {
						throw new Exception();
					}
				
				///Users/aayushsrivastava/Documents/PSO/etc
				
				File file = new File("/Users/aayushsrivastava/Documents/PSO/etc/default.cities");
				
				if(!file.exists()) {
					
					System.out.println("fine not exist");
						file.createNewFile();
					}
				
				
				PrintWriter pw = new PrintWriter(file);
				pw.println("citiesAmount "+c);
				pw.println("meshMaxRange "+r);
				
				Random randomGenerator = new Random();
				
				int i = 1;
				while(i<=c) {
				int randomInt1 = randomGenerator.nextInt(r) + 1;
				int randomInt2 = randomGenerator.nextInt(r) + 1;
				
				pw.println(randomInt1+" "+randomInt2);
				i++;
				}
				

				pw.close();
				
				
				new AppManager().start();
				frame.dispose();
				
				}
					
				
				catch (Exception n) {
						
						n.printStackTrace();
						JOptionPane.showMessageDialog(frame, "Invalid Input");
					
				}
				
				
			}
		});
		go.setBounds(96, 185, 215, 23);
		panel.add(go);
	}
}
