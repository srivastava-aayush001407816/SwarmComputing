package com.neu.swing.views.tabs.buttons;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
//import javax.swing.JLabel;

import com.neu.swing.views.graph.GraphPainterManager;
import com.neu.swing.views.menu.JMenuBarCustom;

public abstract class AbstractButton extends JButton {
	protected GraphPainterManager manager;
	AbstractButton second;
	JMenuBarCustom menu;

	public AbstractButton(String name, JMenuBarCustom menu) {
		super(name);
		this.menu = menu;
	}

	public void setSecondButton(AbstractButton b) {
		second = b;
	}

	public void changeMenuState(boolean b) {
		menu.changeEnabledFileMenu(b);
	}

	public void setGraphPainterManager(GraphPainterManager man) {
		manager = man;
	}

	public GridBagConstraints getGridBagConstraints(int gridx) { // OLDDDDDD
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.gridx = gridx;// 0 start, 1 stop
		gbc.gridy = 1;
		return gbc;
	}

	public GridBagConstraints getGridBagConstraints(int anchor,
			int bottomInset, int rightInset, int gridx, int gridy) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		// gbc.anchor = anchor;
		gbc.insets = new Insets(0, 0, bottomInset, rightInset);
		gbc.gridx = gridx;// 0 start, 1 stop
		gbc.gridy = gridy;
		return gbc;
	}

	private static final long serialVersionUID = 1512979270676580434L;
}
