package com.neu.controller;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import javax.swing.JOptionPane;

public class DataPersistenceWorker {

	@SuppressWarnings("resource")
	// for Formatter because I'm screaming that I'm not closing it
	public static void writeData(File selectedFile,
			SimulationDataAggregator data, Component component) {
		if (selectedFile == null) {
			selectedFile = new File(
					"etc"
							+ File.separator
							+ "results"
							+ File.separator
							+ new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
									.format(new Date())+".txt");
		}
		BufferedWriter out = null;
		try {
			int pauses = 35;
			ArrayList<String> it;
			ArrayList<Integer> iti;
			out = new BufferedWriter(new FileWriter(selectedFile));
			out.write(new String(new char[pauses]).replace("\0", "-") + "\n");
			out.write("\t\t\tAlgorithm PSO\n");
			out.write("\t\t\t"
					+ new String(new char["Algorithm PSO".length()]).replace(
							"\0", "-") + "\n");
			out.write("parameter: ");
			it = data.getParams("pso");
			if (it == null) {
				out.write("null\n");
			} else {
				out.write("\n");
				for (int i = 0; i < it.size() - 1; i++) {
					out.write("\t"
							+ new Formatter().format("%18s",
									it.get(i).split(":")[0]).toString() + ":"
							+ it.get(i).split(":")[1] + "\n");
				}
			}
			out.write(new Formatter().format("%-20s", "the shortest path: ")
					.toString() + data.getBestDistance("pso") + "\n");
			out.write(new Formatter().format("%-20s", "w iteration: ")
					.toString() + data.getBestDistanceIteration("pso") + "\n");
			out.write(new Formatter().format("%-20s", "Sequentially cities: ")
					.toString());
			iti = data.getBestCitiesOrder("pso");
			if (iti == null) {
				out.write("null\n");
			} else {
				for (int i = 0; i < iti.size(); i++) {
					if (i % 15 == 0) {
						out.write("\n");
					}
					out.write(iti.get(i) + " ");
				}
				if (!(iti.size() % 15 == 0)) {
					out.write("\n");
				}
			}
			out.write("\n" + new String(new char[pauses]).replace("\0", "-")
					+ "\n");
			out.write("\t\t\tAlgorithm Firefly\n");
			out.write("\t\t\t"
					+ new String(new char["Algorithm Firefly".length()])
							.replace("\0", "-") + "\n");
			out.write("parameter: ");
			it = data.getParams("firefly");
			if (it == null) {
				out.write("null\n");
			} else {
				out.write("\n");
				for (int i = 0; i < it.size() - 1; i++) {
					out.write("\t"
							+ new Formatter().format("%18s",
									it.get(i).split(":")[0]).toString() + ":"
							+ it.get(i).split(":")[1] + "\n");
				}
			}
			out.write(new Formatter().format("%-20s", "the shortest path: ")
					.toString() + data.getBestDistance("firefly") + "\n");
			out.write(new Formatter().format("%-20s", "w iteration: ")
					.toString()
					+ data.getBestDistanceIteration("firefly")
					+ "\n");
			out.write(new Formatter().format("%-20s", "Sequencially cities: ")
					.toString());
			iti = data.getBestCitiesOrder("firefly");

			if (iti == null) {
				out.write("null\n");
			} else {
				for (int i = 0; i < iti.size(); i++) {
					if (i % 15 == 0) {
						out.write("\n");
					}
					out.write(iti.get(i) + " ");
				}
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(component,
					"File deleted in between", "Error",
					JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(component,
					"Error while reading from a file", "Error",
					JOptionPane.WARNING_MESSAGE);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(component,
							"Error closing", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	// ------------------------------------------------------------------------------------------------
	public static void readDataAndConfiguration(File selectedFile,
			SimulationDataAggregator data, Component component) {
		if (selectedFile == null) {
			selectedFile = new File("etc" + File.separator + "default.cities");
		}
		if (selectedFile.canRead() && selectedFile.exists()) {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(
						new FileInputStream(selectedFile),
						Charset.forName("UTF-8")));
				int citiesAmount = Integer
						.parseInt(in.readLine().split(" ")[1]);
				data.setGridRange(Integer.parseInt(in.readLine().split(" ")[1]));

				int[][] positions = new int[citiesAmount][2];
				for (int i = 0; i < citiesAmount; i++) {
					String[] points = in.readLine().split(" ");
					positions[i][0] = Integer.parseInt(points[0]);
					positions[i][1] = Integer.parseInt(points[1]);
				}
				data.setPositions(positions);
				data.setCities(convertLengths(positions, citiesAmount));
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(component,
						"File deleted in between", "Error",
						JOptionPane.WARNING_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(component,
						"Error while reading from a file", "Error",
						JOptionPane.WARNING_MESSAGE);
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(component,
						"Error while reading from a file", "Error",
						JOptionPane.WARNING_MESSAGE);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(component,
								"Error while reading from a file", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(component, "Did not read from file!",
					"Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	public static char[] readHelpInformations() {
		BufferedReader in = null;
		char[] buff = new char[1024];
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					"etc" + File.separator + "Help.info"),
					Charset.forName("UTF-8")));
			in.read(buff, 0, 1024);
			return buff;
		} catch (FileNotFoundException ex) {
			System.err.print("Error while reading from file");
		} catch (IOException ex) {
			System.err.print("Error while reading from file");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					System.err.print("did not close the configuration help");
				}
			}
		}
		return null;
	}

	// ------------------------------------------------------------------------------------
	private static double[][] convertLengths(int[][] positions, int citiesAmount) {
		double[][] cities = new double[citiesAmount][citiesAmount];
		for (int i = 0; i < citiesAmount; i++) {
			int x1 = positions[i][0];
			int y1 = positions[i][1];
			for (int j = 0; j < citiesAmount; j++) {
				if (i == j) {
					cities[i][i] = 0.0;
				} else {
					int x2 = positions[j][0];
					int y2 = positions[j][1];
					double tmp = Math.sqrt(Math.pow(x1 - x2, 2)
							+ Math.pow(y1 - y2, 2));
					cities[i][j] = tmp;
					cities[j][i] = tmp;
				}
			}
		}
		// System.out.println(Arrays.deepToString(cities));
		// System.out.println("-------------------------");
		return cities;
	}
	// }
}