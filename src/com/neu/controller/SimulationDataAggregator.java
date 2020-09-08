package com.neu.controller;

import java.util.ArrayList;

//import java.util.Arrays;

public class SimulationDataAggregator {
	private ArrayList<String> psoParams = new ArrayList<String>();
	private ArrayList<String> fireflyParams = new ArrayList<String>();
	private double[][] cities = { { 1.0 } };
	private int[][] positions = { { 1, 1 } };
	private int gridRange = 0;

	private ArrayList<Integer> psoCitiesOrder;
	private ArrayList<Integer> fireflyCitiesOrder;

	private Double psoBestDistance;
	private Double fireflyBestDistance;

	private Integer psoBestSolutionIteration;
	private Integer fireflyBestSolutionIteration;

	public int getNumberOfIterations(String algo) {
		ArrayList<String> params;
		if ("pso".equals(algo)) {
			params = psoParams;
		} else {
			params = fireflyParams;
		}
		for (String param : params) {
			if (param.split(":")[0].equals("numberOfIterations")) {
				return Integer.parseInt(param.split(":")[1]);
			}
		}
		return -1;
	}

	// ---------------------------SETTER/GETTER----------------------------------
	public int getGridRange() {
		return gridRange;
	}

	public void setGridRange(int gridRange) {
		this.gridRange = gridRange;
	}

	public int[][] getPositions() {
		return positions;
	}

	public void setPositions(int[][] pos) {
		positions = pos;
	}

	public double[][] getCities() {
		// System.out.println("Downloading:" + Arrays.deepToString(cities));
		return cities;
	}

	public void setCities(double[][] c) {
		// System.out.println("City set" + Arrays.deepToString(c));
		cities = c;
	}

	public ArrayList<String> getParams(String algo) {
		if ("pso".equals(algo)) {
			return psoParams;
		} else {
			return fireflyParams;
		}
		// System.out.println("Downloading:" + psoParams.toString());
	}

	public void setBestCitiesOrder(String algo, ArrayList<Integer> citiesOrder) {
		if ("pso".equals(algo)) {
			psoCitiesOrder = citiesOrder;
		} else {
			fireflyCitiesOrder = citiesOrder;
		}
	}

	public ArrayList<Integer> getBestCitiesOrder(String algo) {
		if ("pso".equals(algo)) {
			return psoCitiesOrder;
		} else {
			return fireflyCitiesOrder;
		}
	}

	public Double getBestDistance(String algo) {
		if ("pso".equals(algo)) {
			return psoBestDistance;
		} else {
			return fireflyBestDistance;
		}
	}

	public void setBestDistance(String algo, Double bestDistance) {
		if ("pso".equals(algo)) {
			psoBestDistance = bestDistance;
		} else {
			fireflyBestDistance = bestDistance;
		}
	}

	public Integer getBestDistanceIteration(String algo) {
		if ("pso".equals(algo)) {
			return psoBestSolutionIteration;
		} else {
			return fireflyBestSolutionIteration;
		}
	}

	public void setBestDistanceIteration(String algo,
			Integer bestDistanceIteration) {
		if ("pso".equals(algo)) {
			psoBestSolutionIteration = bestDistanceIteration;
		} else {
			fireflyBestSolutionIteration = bestDistanceIteration;
		}
	}
}
