package com.neu.algorithm.firefly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Firefly {
	private double distance = 0;
	private boolean statusChanged = true;
	private ArrayList<Integer> citiesOrder;

	private Random rand = new Random();

	public Firefly(int size) {
		citiesOrder = new ArrayList<Integer>(size);

		for (int i = 0; i < size; ++i)
			citiesOrder.add(i);

		Collections.shuffle(citiesOrder);
	}

	private Firefly(Firefly f) {
		citiesOrder = new ArrayList<Integer>(f.citiesOrder.size());

		for (int i = 0; i < f.citiesOrder.size(); ++i)
			citiesOrder.add(new Integer(f.citiesOrder.get(i)));
	}

	public ArrayList<Integer> getCitiesOrder() {
		return citiesOrder;
	}

	public double computeRealDistance(double[][] cities) {
		if (statusChanged) {
			distance = 0;

			for (int i = 1; i < citiesOrder.size(); ++i)
				distance += cities[citiesOrder.get(i)][citiesOrder.get(i - 1)];

			statusChanged = false;
		}
		return distance;
	}

	public Firefly performRandomFlight(double[][] cities) {
		Firefly bestSolution = null;

		for (int i = 0; i < citiesOrder.size() - 1; ++i) {
			Firefly temporary = new Firefly(this);

			int index1 = rand.nextInt(citiesOrder.size());
			int index2;

			do {
				index2 = rand.nextInt(citiesOrder.size());
			} while (index1 == index2);

			int tmp = temporary.citiesOrder.get(index1);
			temporary.citiesOrder
					.set(index1, temporary.citiesOrder.get(index2));
			temporary.citiesOrder.set(index2, tmp);

			if (bestSolution == null
					|| temporary.computeRealDistance(cities) < bestSolution
							.computeRealDistance(cities)) {
				bestSolution = temporary;
			} else if (temporary.computeRealDistance(cities) < computeRealDistance(cities)) {
				return temporary;
			}
		}
		return bestSolution;
	}

	public Firefly performDirectFlight(Firefly f, double[][] cities) {
		Firefly bestSolution = null;

		for (int i = 0; i < citiesOrder.size() - 1; ++i) {

			Firefly temporary = new Firefly(this);

			int index1 = rand.nextInt(citiesOrder.size());
			int index2;

			do {
				index2 = rand.nextInt(citiesOrder.size());
			} while (index1 == index2);

			int tmpIndex1 = temporary.citiesOrder.indexOf(f.citiesOrder
					.get(index1));
			int tmpIndex2 = temporary.citiesOrder.indexOf(f.citiesOrder
					.get(index2));
			
			if (index1 != tmpIndex1) {
				int tmp = temporary.citiesOrder.get(index1);
				temporary.citiesOrder.set(index1, temporary.citiesOrder.get(tmpIndex1));
				temporary.citiesOrder.set(tmpIndex1, tmp);
			}

			if (index2 != tmpIndex2) {
				int tmp = temporary.citiesOrder.get(index2);
				temporary.citiesOrder.set(index2, temporary.citiesOrder.get(tmpIndex2));
				temporary.citiesOrder.set(tmpIndex2, tmp);

			}
			if (bestSolution == null
					|| temporary.computeRealDistance(cities) < bestSolution
							.computeRealDistance(cities)) {
				bestSolution = temporary;
			} else if (temporary.computeRealDistance(cities) < computeRealDistance(cities)) {
				return temporary;
			}
		}
		return bestSolution;
	}
}
