package com.neu.algorithm;

import java.util.ArrayList;

public interface AlgorithmManager {

	/*
	 * performs n-iteration
	 */
	void step(int n);

	/*
	 * current result after the nth iteration
	 */
	double getStepDistance();

	/*
	 * the best result for all iterations updated after each call
	 */
	
	double getBestDistance();

	/*
	 * 
		best order of cities, updated after each step call
	 */
	ArrayList<Integer> getBestCitiesOrder();

	/*
	 * in which iteration would be the best result
	 */
	public int getBestSolutionIteration();
}

/*
 * Every algo has to implement a static method return null: means
 * error in the input data, you drop everything from the list and leave one
 * element being an error message ArrayList <String>, single
 * element: "parameter_name: value" two-dimensional array argument distance
 * between cities
 */
// public static AlgorithmManager init(ArrayList<String> parametry, double[][]
// cities);

