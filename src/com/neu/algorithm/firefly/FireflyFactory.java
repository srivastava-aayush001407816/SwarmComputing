package com.neu.algorithm.firefly;

import java.util.ArrayList;

import com.neu.algorithm.AlgorithmManager;

public class FireflyFactory {
	public static AlgorithmManager init(ArrayList<String> parameter,
			double[][] cities) {

		int numberOfFireflies = 0;
		double beta = 0;
		double gamma = 0;
		boolean error = false;

		for (String param : parameter) {
			try {
				if (param.startsWith("numberOfFireflies:")) {
					numberOfFireflies = Integer.parseInt(param.substring(param
							.indexOf(':') + 1));
				} else if (param.startsWith("beta:")) {
					beta = Float
							.parseFloat(param.substring(param.indexOf(':') + 1));
				} else if (param.startsWith("gamma:")) {
					gamma = Float
							.parseFloat(param.substring(param.indexOf(':') + 1));
				}
			} catch (NumberFormatException e) {
				error = true;
				break;
			}
		}
		parameter.clear();
		if (error == true) {
			parameter.add("One of the parameters is not a number");
			return null;
		}
		if (numberOfFireflies <= 0) {
			parameter.add("bad number of skylights");
			error = true;
		}
		if (beta <= 0) {
			parameter.add("bad beta factor");
			error = true;
		}
		if (gamma <= 0) {
			parameter.add("bad gamma factor");
			error = true;
		}
		if (error == true)
			return null;

		return new FireflyAlgorithm(numberOfFireflies, beta, gamma, cities);
	}
}

