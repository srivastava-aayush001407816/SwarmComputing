package com.neu.swing.views.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.text.NumberFormat;

import javax.swing.SwingWorker;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import com.neu.algorithm.AlgorithmManager;
import com.neu.controller.SimulationDataAggregator;
import com.neu.swing.views.tabs.AlgorithmForm;
import com.neu.swing.views.tabs.buttons.AbstractButton;

public class JPaintablePanel extends ChartPanel {
	private AlgorithmManager algoManager;
	private AlgorithmForm view;
	private SimulationDataAggregator data;
	private String managerType;
	private com.neu.swing.views.grid.JPaintablePanel gridPaintablePanel;

	private int iterations, periods;
	private int percent = 10;
	double[][] series;
	private String seriesName;
	private TwoWorker task = null;

	public JPaintablePanel(String s) {
		super(JPaintablePanel.chart, false);
		seriesName = s;
	}

	public void setView(AlgorithmForm view) {
		this.view = view;
	}

	public void setAlgorithmManager(String managerType, AlgorithmManager man,
			SimulationDataAggregator d,
			com.neu.swing.views.grid.JPaintablePanel gridPaintablePanel) {
		this.managerType = managerType;
		this.algoManager = man;
		this.gridPaintablePanel = gridPaintablePanel;
		data = d;
		iterations = (int) data.getNumberOfIterations(managerType);
		if (iterations > 100) {
			periods = iterations / percent;
		} else {
			percent = 1;
			periods = iterations;
		}
		series = new double[2][periods];
		for (int i = 0; i < periods; i++) {
			series[0][i] = (double) i;
			series[1][i] = 0;
		}
		dataset.addSeries(seriesName, series);
	}

	public void start(AbstractButton start, AbstractButton stop) {
		if (task != null) {
			task.cancel(true);
		}
		task = new TwoWorker(start, stop);
		task.execute();
	}

	public void stop() {
		if (task != null) {
			task.cancel(true);
		}
		task = null;
	}

	private class TwoWorker extends SwingWorker<Double, Double> {
		int it = 0;
		AbstractButton start;
		AbstractButton stop;

		public TwoWorker(AbstractButton start, AbstractButton stop) {
			this.start = start;
			this.stop = stop;
		}

		@Override
		protected Double doInBackground() throws Exception {
			double x = 0.0;
			for (int i = 0; i < periods; i++) {
				if (!isCancelled()) {
					algoManager.step(percent); // algoManager.step(periods*(i+1));
					x = algoManager.getStepDistance();
					publish(Double.valueOf(x));
				}
			}
			// System.out.println("Zwracam:" + Double.valueOf(x));
			return Double.valueOf(x);
		}

		@Override
		protected void process(java.util.List<Double> chunks) {
			for (double d : chunks) {
				series[1][it++] = d;
				dataset.removeSeries(seriesName);
				dataset.addSeries(seriesName, series);

				data.setBestCitiesOrder(managerType,
						algoManager.getBestCitiesOrder());
				data.setBestDistance(managerType, algoManager.getBestDistance());
				// gridPaintablePanel.setSimulationDataAggregator(data);
				gridPaintablePanel.plot(managerType);
			}
		}

		@Override
		protected void done() {
			start.setEnabled(true);
			stop.setEnabled(false);
			start.changeMenuState(true);
			view.setResult(algoManager.getBestDistance());
			view.setIterationNumber(algoManager.getBestSolutionIteration());
			data.setBestCitiesOrder(managerType,
					algoManager.getBestCitiesOrder());
			data.setBestDistance(managerType, algoManager.getBestDistance());
			data.setBestDistanceIteration(managerType,
					algoManager.getBestSolutionIteration());
			gridPaintablePanel.plot(managerType);
			// printData();
		}

		// /---------------------__DEBUG_____________________________
		@SuppressWarnings("unused")
		private void printData() {
			System.out.println(new String(new char[10]).replace("\0", "-"));
			System.out.println("psoParams:" + data.getParams("pso").toString());
			System.out.println("psoBestDistance:\t"
					+ data.getBestDistance("pso"));
			System.out.println("psoBestSolutionIteration:\t"
					+ data.getBestDistanceIteration("pso"));
			System.out.println("psoBestCitiesOrder"
					+ data.getBestCitiesOrder("pso"));
			System.out.println(new String(new char[10]).replace("\0", "-"));
			System.out.println("fireflyParams:"
					+ data.getParams("firefly").toString());
			System.out.println("fireflyBestDistance:\t"
					+ data.getBestDistance("firefly"));
			System.out.println("fireflyBestSolutionIteration:\t"
					+ data.getBestDistanceIteration("firefly"));
			System.out.println("fireflyBestCitiesOrder"
					+ data.getBestCitiesOrder("firefly"));

		}
	}

	// ------------------------JFreeChart part---------------------------------
	private static DefaultXYDataset dt = new DefaultXYDataset();
	private static JFreeChart chart = createChart(JPaintablePanel.dt);
	// public DefaultXYDataset dataset = new DefaultXYDataset();
	private DefaultXYDataset dataset = JPaintablePanel.dt;

	private static JFreeChart createChart(XYDataset dataset) {

		// create the chart...
		JFreeChart chart = ChartFactory.createXYLineChart(null, // chart title
				"Iterations", // domain axis label
				"Shortest path", // range axis label
				dataset, // initial series
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, //
				false //
				);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(0xffffe0)); // pink
		plot.setDomainGridlinesVisible(true); // draw a grid
		plot.setDomainGridlinePaint(Color.lightGray); // vertical grid
		plot.setRangeGridlinePaint(Color.lightGray); // horizontal grid

		// axes displaying numbers
		TickUnitSource ticks = NumberAxis.createIntegerTickUnits();
		NumberAxis domain = (NumberAxis) plot.getDomainAxis();
		domain.setStandardTickUnits(ticks);
		NumberAxis range = (NumberAxis) plot.getRangeAxis();
		range.setStandardTickUnits(ticks);

		// shape rendering
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
		plot.setRenderer(renderer);
		renderer.setBaseItemLabelPaint(Color.blue);
		renderer.setBaseShapesVisible(true); // visible points
		renderer.setBaseShapesFilled(true); // filled points

		// set the renderer's stroke
		Stroke stroke = new BasicStroke(1.5f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL); // line thickness, round ending,
											// not a sharp connection
		renderer.setBaseOutlineStroke(stroke);
		// renderer.setBasePaint(Color.RED);

		// point markers
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(2);
		XYItemLabelGenerator generator = new StandardXYItemLabelGenerator(
				StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT, format,
				format);
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(false);

		// XYItemRenderer rende = chart.getXYPlot().getRenderer();
		// rende.setSeriesPaint(0, Color.blue);
		// rende.setSeriesPaint(1, new Color(0xffffe0));
		return chart;
	}

	private static final long serialVersionUID = -673866049155968395L;
}
