package com.neu.algorithm.firefly;

import java.util.ArrayList;

import com.neu.algorithm.AlgorithmManager;

public class FireflyAlgorithm implements AlgorithmManager {

	private double beta;
	private double gamma;
	private double[][] cities;

	private Firefly[] fireflies;

	private Firefly bestSolution;
	private int bestSolutionIteration;
	private int iterationsMade;

	FireflyAlgorithm(int numberOfFireflies, double beta, double gamma,
			double[][] cities) {

		this.cities = cities;
		this.beta = beta;
		this.gamma = gamma;
		this.iterationsMade = 0;

		this.fireflies = new Firefly[numberOfFireflies];
		for (int i = 0; i < numberOfFireflies; ++i)
			fireflies[i] = new Firefly(cities.length);

	}

	private double computeAttrractiveness(double distance) {
		return beta / (1.0 + gamma * distance * distance);
	}

	@Override
	public void step(int numberOfIterations) {
		if (numberOfIterations < 1)
			throw new IllegalArgumentException();

		int currentIteration = 1;

		while (currentIteration++ <= numberOfIterations) {
			iterationsMade++;

			for (int i = 0; i < fireflies.length; ++i) {
				int mostAttractive = i;
				double biggestAttractiveness = computeAttrractiveness(fireflies[i]
						.computeRealDistance(cities));

				for (int j = 0; j < fireflies.length; ++j) {
					if (i != j) {

						double presentAttractiveness = computeAttrractiveness(fireflies[j]
								.computeRealDistance(cities));

						if (presentAttractiveness > biggestAttractiveness) {
							biggestAttractiveness = presentAttractiveness;
							mostAttractive = j;
						}
					}
				}

				if (mostAttractive != i)
					fireflies[i] = fireflies[i].performDirectFlight(
							fireflies[mostAttractive], cities);
				else
					fireflies[i] = fireflies[i].performRandomFlight(cities);
			}
			updateBestSolution();
		}
	}

	private void updateBestSolution() {
		if (iterationsMade == 1) {
			bestSolution = findBestFirefly();
			bestSolutionIteration = 1;
		} else {
			Firefly iterationBestSolution = findBestFirefly();

			if (iterationBestSolution.computeRealDistance(cities) < bestSolution
					.computeRealDistance(cities)) {
				bestSolutionIteration = iterationsMade;
				bestSolution = iterationBestSolution;
			}
		}
	}

	private Firefly findBestFirefly() {
		double lowestDistance = fireflies[0].computeRealDistance(cities);
		Firefly solution = fireflies[0];

		for (int i = 1; i < fireflies.length; ++i)
			if (fireflies[i].computeRealDistance(cities) < lowestDistance) {
				lowestDistance = fireflies[i].computeRealDistance(cities);
				solution = fireflies[i];
			}
		return solution;
	}

	@Override
	public double getStepDistance() {
		return findBestFirefly().computeRealDistance(cities);
	}

	@Override
	public double getBestDistance() {
		return bestSolution.computeRealDistance(cities);
	}

	@Override
	public ArrayList<Integer> getBestCitiesOrder() {
		return bestSolution.getCitiesOrder();
	}

	@Override
	public int getBestSolutionIteration() {
		return bestSolutionIteration;
	}
}
