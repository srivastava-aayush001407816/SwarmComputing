package com.neu.swing.views.grid;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JGridCard extends JPanel {
	private JPaintablePanel psoGridPanel = new JPaintablePanel();
	private JPaintablePanel fireFlyGridPanel = new JPaintablePanel();

	public JGridCard() {
		super(new CardLayout(0, 0));
		setBorder(BorderFactory.createLineBorder(Color.black));

		add(psoGridPanel, "psoGridPanel");
		add(fireFlyGridPanel, "fireFlyGridPanel");
	}

	public JPaintablePanel getPSO() {
		return psoGridPanel;
	}

	public JPaintablePanel getFireFly() {
		return fireFlyGridPanel;
	}

	public GridBagConstraints getGridBagConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 3.0;
		gbc.weighty = 5.0;// 3.0;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		return gbc;
	}

	private static final long serialVersionUID = -1979373680334627226L;
}
