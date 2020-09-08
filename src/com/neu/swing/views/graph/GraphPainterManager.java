package com.neu.swing.views.graph;

import java.util.ArrayList;
//import java.util.Arrays;

import javax.swing.JOptionPane;

import com.neu.algorithm.AlgorithmManager;
import com.neu.controller.AlgorithmFactory;
import com.neu.controller.SimulationDataAggregator;
import com.neu.swing.views.tabs.buttons.AbstractButton;

public class GraphPainterManager {
	public static String PSO = "pso";
	public static String FIREFLY = "firefly";
	private String managerType;
	private AlgorithmManager algoManager = null;
	private com.neu.swing.views.graph.JPaintablePanel grapPaintablePanel;
	private com.neu.swing.views.grid.JPaintablePanel gridPaintablePanel;
	private SimulationDataAggregator data;

	public GraphPainterManager(SimulationDataAggregator data) {
		this.data = data;
	}

	public GraphPainterManager(AlgorithmManager manager, JPaintablePanel panel) {
		this.algoManager = manager;
		this.grapPaintablePanel = panel;
	}

	public double getBestResult() {
		return algoManager.getBestDistance();
	}

	public void setManager(AlgorithmManager manager) {
		this.algoManager = manager;
	}

	public void setManagerName(String managerType) {
		this.managerType = managerType;
	}

	public void setPanels(com.neu.swing.views.graph.JPaintablePanel graph,
			com.neu.swing.views.grid.JPaintablePanel grid) {
		grapPaintablePanel = graph;
		gridPaintablePanel = grid;
	}

	public boolean stop() {
		if (algoManager != null) {
			grapPaintablePanel.stop();
			data.setBestCitiesOrder(managerType,
					algoManager.getBestCitiesOrder());
			data.setBestDistance(managerType, algoManager.getBestDistance());
			data.setBestDistanceIteration(managerType,
					algoManager.getBestSolutionIteration());
			gridPaintablePanel.setSimulationDataAggregator(data);
			gridPaintablePanel.plot(managerType);
			return true;
		} else {
			JOptionPane.showMessageDialog(grapPaintablePanel,
					"First, press start", "Error",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		//  best getBestresult()
	}

	public boolean start(AbstractButton start, AbstractButton stop) {
		ArrayList<String> params = new ArrayList<String>(
				data.getParams(managerType));
		algoManager = AlgorithmFactory.getAlgorithm(managerType, params,
				data.getCities());
		if (checkManagerParsing(params, data.getParams(managerType),
				algoManager)) {
			gridPaintablePanel.setSimulationDataAggregator(data);
			grapPaintablePanel.setAlgorithmManager(managerType, algoManager,
					data, gridPaintablePanel);
			grapPaintablePanel.start(start, stop);
			return true;
		} else {
			return false;
		}
	}

	private boolean checkManagerParsing(ArrayList<String> result,
			ArrayList<String> params, AlgorithmManager algoManager) {
		if ((algoManager == null) && (result.size() == 1)) {
			JOptionPane.showMessageDialog(grapPaintablePanel, result.get(0),
					"Error", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			for (String param : params) {
				try {
					if (param.startsWith("numberOfIterations:")) {
						Integer.parseInt(param.substring(param.indexOf(':') + 1));
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(grapPaintablePanel,
							"The number of iterations must be The total", "Error",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}
		}
		return true;
	}
}
