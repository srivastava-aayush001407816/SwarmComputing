package com.neu.swing.views.tabs;




import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTabbedPane;

import com.neu.controller.SimulationDataAggregator;
import com.neu.swing.views.graph.GraphPainterManager;
import com.neu.swing.views.graph.JGraphCard;
import com.neu.swing.views.grid.JGridCard;
import com.neu.swing.views.tabs.buttons.JStartButton;
import com.neu.swing.views.tabs.buttons.JStopButton;

public class JCompositeTabs extends JTabbedPane {
AlgorithmForm pso;
AlgorithmForm firefly;
JGridCard gridCard;
JGraphCard graphCard;
JStartButton start;
JStopButton stop;
SimulationDataAggregator data;
GraphPainterManager painter;

public JCompositeTabs(int position, JGridCard grid, JGraphCard graph,
		JStartButton start, JStopButton stop, SimulationDataAggregator data) {
	super(position);
	gridCard = grid;
	graphCard = graph;
	this.start = start;
	this.stop = stop;
	this.data = data;

	painter = new GraphPainterManager(data);
	pso = new AlgorithmForm(data, "pso");
	firefly = new AlgorithmForm(data, "firefly");

	graphCard.getPSO().setView(pso);
	graphCard.getFireFly().setView(firefly);

	initForms();
	addTab("pso", null, pso, null);
	addTab("firefly", null, firefly, null);
}

@Override
public void setSelectedIndex(int index) {
	if (index == 0) { // pso
		((CardLayout) (gridCard.getLayout()))
				.show(gridCard, "psoGridPanel");
		((CardLayout) (graphCard.getLayout())).show(graphCard,
				"psoGraphPanel");
		painter.setManagerName(GraphPainterManager.PSO);
		painter.setPanels(graphCard.getPSO(), gridCard.getPSO());
		start.setAlgorithmForm(pso);
		// stop.setAlgorithmForm(pso);
	} else if (index == 1) { // firefly
		((CardLayout) (gridCard.getLayout())).show(gridCard,
				"fireFlyGridPanel");
		((CardLayout) (graphCard.getLayout())).show(graphCard,
				"fireFlyGraphPanel");
		painter.setManagerName(GraphPainterManager.FIREFLY);
		painter.setPanels(graphCard.getFireFly(), gridCard.getFireFly());
		start.setAlgorithmForm(firefly);
		// stop.setAlgorithmForm(firefly);
	}
	start.setGraphPainterManager(painter);
	stop.setGraphPainterManager(painter);
	super.setSelectedIndex(index);
}

public GridBagConstraints getGridBagConstraints() {
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridwidth = 2;
	gbc.insets = new Insets(0, 0, 5, 5);
	gbc.fill = GridBagConstraints.BOTH;
	gbc.gridx = 0;
	gbc.gridy = 0;
	return gbc;
}

private void initForms() {
	int anchor = GridBagConstraints.EAST;
	int fill = GridBagConstraints.HORIZONTAL;
	int columns = 5;

	initPSOForm(anchor, fill, columns);
	initFireFlyForm(anchor, fill, columns);
}

private void initPSOForm(int anchor, int fill, int columns) {
	pso.addJLabel("confidenceCoefficent1", anchor, 5, 5, 0, 0);
	pso.addJTextField(5, 0, fill, 1, 0, columns, Double.toString(0.9));

	pso.addJLabel("confidenceCoefficent2", anchor, 5, 0, 0, 1);
	pso.addJTextField(5, 0, fill, 1, 1, columns, Double.toString(1.0));

	pso.addJLabel("confidenceCoefficent3", anchor, 5, 5, 0, 2);
	pso.addJTextField(5, 0, fill, 1, 2, columns, Double.toString(1.0));

	pso.addJLabel("numberOfSwarms", anchor, 5, 5, 0, 3);
	pso.addJTextField(5, 0, fill, 1, 3, columns, "100");

	pso.addJLabel("numberOfIterations", anchor, 5, 5, 0, 4);
	pso.addJTextField(5, 0, fill, 1, 4, columns, "100");

	pso.addJLabel("iteration", anchor, 5, 5, 0, 5);
	pso.setIterationNumberTextField(pso.addJTextField(5, 0, fill, 1, 5,
			columns, "0"));

	pso.addJLabel("result", anchor, 0, 5, 0, 6);
	pso.setResultTextField(pso
			.addJTextField(5, 0, fill, 1, 6, columns, "0"));
}

private void initFireFlyForm(int anchor, int fill, int columns) {
	firefly.addJLabel("beta", anchor, 5, 5, 0, 0);
	firefly.addJTextField(5, 0, fill, 1, 0, columns, Double.toString(1.0));

	firefly.addJLabel("gamma", anchor, 5, 5, 0, 1);
	firefly.addJTextField(5, 0, fill, 1, 1, columns, Double.toString(1.0));

	firefly.addJLabel("numberOfFireflies", anchor, 5, 5, 0, 2);
	firefly.addJTextField(5, 0, fill, 1, 2, columns, "100");

	firefly.addJLabel("numberOfIterations", anchor, 5, 5, 0, 3);
	firefly.addJTextField(5, 0, fill, 1, 3, columns, "100");

	firefly.addJLabel("iteration", anchor, 5, 5, 0, 4);
	firefly.setIterationNumberTextField(firefly.addJTextField(5, 0, fill,
			1, 4, columns, "0"));

	firefly.addJLabel("result", anchor, 0, 5, 0, 5);
	firefly.setResultTextField(firefly.addJTextField(5, 0, fill, 1, 5,
			columns, "0"));
}

private static final long serialVersionUID = 6913137123240449900L;
}
