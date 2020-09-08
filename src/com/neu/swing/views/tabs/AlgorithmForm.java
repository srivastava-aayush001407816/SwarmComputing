package com.neu.swing.views.tabs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.neu.controller.SimulationDataAggregator;

//two pieces for pso and firefly

public class AlgorithmForm extends JPanel {
	String name;
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	JTextField result;
	JTextField iterationNumber;

	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	SimulationDataAggregator data;

	public AlgorithmForm(SimulationDataAggregator data, String name) {
		super();
		this.data = data;
		this.name = name;
		setGridLayout();
		updateDataFromForm();
	}

	public void setResult(double d) {
		result.setText(Double.toString(round(d, 2)));
	}

	public void setResultTextField(JTextField f) {
		result = f;
	}

	public void setIterationNumber(int x) {
		iterationNumber.setText(String.format("%d", x));
	}

	public void setIterationNumberTextField(JTextField iterationNumber) {
		this.iterationNumber = iterationNumber;
	}

	public String updateDataFromForm() {
		ArrayList<String> params = data.getParams(name);
		params.clear();
		for (int i = 0; i < labels.size(); i++) {
			try {
				String s = fields.get(i).getText();
				if (s.length() == 0) {
					throw new NullPointerException();
				}
				params.add(labels.get(i).getText() + ":" + s);
			} catch (NullPointerException e) {
				return "Field:" + labels.get(i).getText()
						+ " - no such file";
			}
		}
		return null;
	}

	public static double round(double value, int places) {
		long factor = (long) Math.pow(10, places);
		return (double) Math.round(value * factor) / factor;
	}

	// ----------------------Setting GUI------------------------------------
	
	
	public void addJLabel(String name, int anchor, int bottomInset,
			int rightInset, int gridx, int gridy) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = anchor;
		gbc.insets = new Insets(0, 0, bottomInset, rightInset);
		gbc.gridx = gridx;
		gbc.gridy = gridy;

		JLabel l = new JLabel(name);
		labels.add(l);
		add(l, gbc);
	}

	public JTextField addJTextField(int bottomInset, int rightInset, int fill,
			int gridx, int gridy, int columns, String defaultValue) {
		JTextField field = new JTextField();
		field.setText(defaultValue);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, bottomInset, rightInset);
		gbc.fill = fill;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		add(field, gbc);
		field.setColumns(columns);
		fields.add(field);
		return field;
	}

	private void setGridLayout() {
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 0, 0, 0 };
		gbl.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl);
	}

	private static final long serialVersionUID = -3491219077948173588L;
}
