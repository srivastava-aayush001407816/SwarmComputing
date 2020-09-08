package com.neu.swing.views.tabs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.neu.swing.views.graph.JGraphCard;
import com.neu.swing.views.grid.JGridCard;
import com.neu.swing.views.menu.JMenuBarCustom;
import com.neu.swing.views.tabs.buttons.JStartButton;
import com.neu.swing.views.tabs.buttons.JStopButton;

public class JMenuPanelCustom extends JPanel {
	JStartButton start;
	JStopButton stop;

	public JMenuPanelCustom(JGridCard gridCard, JGraphCard graphCard,
			JMenuBarCustom menuBar) {
		super();
		setGridLayout();

		stop = new JStopButton("Stop", menuBar);
		start = new JStartButton("Start", menuBar);
		start.setSecondButton(stop);
		stop.setSecondButton(start);
		stop.setEnabled(false);

		JCompositeTabs tabbedPane = new JCompositeTabs(JTabbedPane.TOP,
				gridCard, graphCard, start, stop, menuBar.getParametersData());
		add(tabbedPane, tabbedPane.getGridBagConstraints());

		add(start, start.getGridBagConstraints(0));
		add(stop, stop.getGridBagConstraints(1));
	}

	// --------------------Setup GUI---------------------------
	public GridBagConstraints getGridBagConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 0;
		return gbc;
	}

	private void setGridLayout() {
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 0, 0, 0 };
		gbl.rowHeights = new int[] { 0, 0, 0 };
		gbl.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl);
	}

	private static final long serialVersionUID = -7107890149686710586L;
}
