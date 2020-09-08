package com.neu.controller;

import java.util.ArrayList;

import com.neu.algorithm.AlgorithmManager;
import com.neu.algorithm.firefly.FireflyFactory;
import com.neu.algorithm.pso.PSO;

public class AlgorithmFactory {
	public static AlgorithmManager getAlgorithm(String option,
			ArrayList<String> params, double[][] cities) {

		if (option.equals("pso")) {
			return checkCorrection(PSO.init(params, cities), params);
		} else if (option.equals("firefly")) {
			return checkCorrection(FireflyFactory.init(params, cities), params);
		} else {
			System.err.print("Files null, the factory does not know such an algorithm!");
			return null;
		}
	}

	// remove this function
	private static AlgorithmManager checkCorrection(AlgorithmManager manager,
			ArrayList<String> params) {
		if (manager == null) {
			// System.err.print("Null Manager (Algorithm Factory)"
			// + params.get(0));
		}
		return manager;
	}
}
