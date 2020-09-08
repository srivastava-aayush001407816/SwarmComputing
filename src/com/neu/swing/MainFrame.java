package com.neu.swing;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.neu.swing.views.graph.JGraphCard;
import com.neu.swing.views.grid.JGridCard;
import com.neu.swing.views.menu.JMenuBarCustom;
import com.neu.swing.views.tabs.JMenuPanelCustom;

public class MainFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void invokeMainFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception excep) {
			System.out.println("Set Look and Feel");
			excep.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(getGridBagLayout());

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("etc/icon.png");
		frame.setIconImage(img);
		frame.setTitle("TSP visualizer");

		//The main menu File, etc. so far, with the vague eavesdropper just opens
		//filechooser and does not do anything with it
		
		
		JMenuBarCustom menuBar = new JMenuBarCustom(frame);
		frame.setJMenuBar(menuBar);
		
		/*
		 * Three main GUI lower panels - gridPanel (graph) right - menuPanel
			left - graphPanel (idea idea?)
		 */
		// LEFT
		
		
		JGridCard gridCard = new JGridCard();
		frame.getContentPane().add(gridCard, gridCard.getGridBagConstraints());

		// BOTTOM
		JGraphCard graphCard = new JGraphCard();
		frame.getContentPane()
				.add(graphCard, graphCard.getGridBagConstraints());

		// RIGHT
		JMenuPanelCustom menuPanel = new JMenuPanelCustom(gridCard, graphCard,
				menuBar);
		frame.getContentPane()
				.add(menuPanel, menuPanel.getGridBagConstraints());
	}

	private GridBagLayout getGridBagLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 }; // { 344, -91, 0 }
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		return gridBagLayout;
	}
}
