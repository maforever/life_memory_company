/**
 * Copyright (C) 2009, 2010 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lifememory.activity;

import java.util.ArrayList;
import java.util.List;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.example.lifememory.R;
import com.example.lifememory.activity.model.Bill;
import com.example.lifememory.activity.model.BillCatagoryItem;
import com.example.lifememory.chart.AbstractDemoChart;
import com.example.lifememory.db.service.BillCatagoryService;
import com.example.lifememory.db.service.BillInfoService;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

/**
 * Average temperature demo chart.
 */
public class AverageTemperatureChart extends AbstractDemoChart {
	double[] inValues, outValues;
	BillInfoService infoService;
	Context context;
	public double totalValue = 0;
		
	
	/**
	 * Returns the chart name.
	 * 
	 * @return the chart name
	 */
	public String getName() {
		return "Average temperature";
	}

	/**
	 * Returns the chart description.
	 * 
	 * @return the chart description
	 */
	public String getDesc() {
		return "The average temperature in 4 Greek islands (line chart)";
	}

	public AverageTemperatureChart(Context context, LinearLayout layout,
			String year, int billType, BillInfoService infoService) {
		super();
		this.infoService = infoService;
		this.context = context;
		myExecute(context, layout, year, billType);
	}

	// 线图渲染器

	private XYMultipleSeriesRenderer getLineRenderer() {
		int[] colors = new int[] {
				context.getResources().getColor(R.color.incomeColor),
				context.getResources().getColor(R.color.spendColor) };
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,
				PointStyle.DIAMOND, };
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setChartTitleTextSize(30);
		renderer.setYTitle("金额");
		renderer.setXLabelsColor(Color.BLACK);
		renderer.setAxisTitleTextSize(30);
		renderer.setAxesColor(Color.BLACK);
		renderer.setYLabelsColor(0, Color.BLACK);
		renderer.setLegendHeight(10);
		renderer.setLabelsTextSize(30);
		renderer.setLegendTextSize(30);
		renderer.setDisplayChartValues(true);
		renderer.setShowGrid(true);
		renderer.setAxisTitleTextSize(25);

		renderer.setLabelsTextSize(25);
		renderer.setLegendTextSize(25);
		renderer.setPointSize(10f);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(200);
		renderer.setMargins(new int[] { 0, 60, 30, 30 });
		renderer.setApplyBackgroundColor(true);// 使背景色生效
		renderer.setBackgroundColor(context.getResources().getColor(
				android.R.color.transparent));
		renderer.setMarginsColor(Color.argb(0, 0xff, 0, 0)); // 设置4边留白透明
		renderer.setFitLegend(true);
		renderer.setPanEnabled(true, true);
		XYSeriesRenderer r = new XYSeriesRenderer();
		r = new XYSeriesRenderer();
		r.setPointStyle(styles[0]);
		r.setColor(colors[0]);
		r.setFillPoints(true);
		r.setLineWidth(5f);
		renderer.addSeriesRenderer(r);
		
		r = new XYSeriesRenderer();
		r = new XYSeriesRenderer();
		r.setPointStyle(styles[1]);
		r.setColor(colors[1]);
		r.setFillPoints(true);
		r.setLineWidth(5f);
		renderer.addSeriesRenderer(r);

		renderer.setAxesColor(Color.DKGRAY);
		renderer.setLabelsColor(Color.LTGRAY);
		renderer.setFitLegend(true);
		renderer.setXLabels(0);
		renderer.setYLabels(4);
		renderer.addXTextLabel(1, "一月");
		renderer.addXTextLabel(2, "二月");
		renderer.addXTextLabel(3, "三月");
		renderer.addXTextLabel(4, "四月");
		renderer.addXTextLabel(5, "五月");
		renderer.addXTextLabel(6, "六月");
		renderer.addXTextLabel(7, "七月");
		renderer.addXTextLabel(8, "八月");
		renderer.addXTextLabel(9, "九月");
		renderer.addXTextLabel(10, "十月");
		renderer.addXTextLabel(11, "十一月");
		renderer.addXTextLabel(12, "十二月");

		return renderer;
	}

	// 线图数据
	private XYMultipleSeriesDataset getDemoDataset() {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		CategorySeries series = new CategorySeries("收入");
		for(double d : inValues) {
			series.add(d);
			
		}
		dataset.addSeries(series.toXYSeries());
		series = new CategorySeries("支出");
		for(double d : outValues) {
			series.add(d);
		}
		dataset.addSeries(series.toXYSeries());

		return dataset;
	}

	private void myExecute(Context context, LinearLayout layout, String year,
			int billType) {
		
//		inValues = infoService.findIncomeOrSpendByYear(2, year);
//		outValues = infoService.findIncomeOrSpendByYear(1, year);
		
//		for(double d : inValues) {
//			Log.i("a", "invalues = " + d);
//		}
//		
//		for(double d : outValues) {
//			Log.i("a", "outvalues = " + d);
//		}
		
		
		
		String[] titles = new String[] { "收入", "支出" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4,
				26.1, 23.6, 20.3, 17.2, 13.9 });
		values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14,
				11 });
		
		GraphicalView view = ChartFactory.getLineChartView(context,
				getDemoDataset(), getLineRenderer());
		layout.addView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		
		// values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9,
		// 6 });
		// values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13,
		// 10 });
		// int[] colors = new int[] {
		// context.getResources().getColor(R.color.incomeColor),
		// context.getResources().getColor(R.color.spendColor) };
		// PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,
		// PointStyle.DIAMOND, };
		// XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		// int length = renderer.getSeriesRendererCount();
		// for (int i = 0; i < length; i++) {
		// ((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
		// .setFillPoints(true);
		// }
		// setChartSettings(renderer, "Average temperature", "Month",
		// "Temperature", 0.5, 12.5, 0, 32, Color.LTGRAY, Color.LTGRAY);
		// renderer.setXLabels(12);
		// renderer.setYLabels(10);
		// renderer.setShowGrid(true);
		// renderer.setXLabelsAlign(Align.RIGHT);
		// renderer.setYLabelsAlign(Align.RIGHT);
		// renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		// renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });

	}

	/**
	 * Executes the chart demo.
	 * 
	 * @param context
	 *            the context
	 * @return the built intent
	 */
	public AverageTemperatureChart(Context conetxt, LinearLayout layout) {
		
	}
	
	public void execute2(Context context, LinearLayout layout) {
		String[] titles = new String[] { "Crete", "Corfu", "Thassos",
				"Skiathos" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4,
				26.1, 23.6, 20.3, 17.2, 13.9 });
		values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14,
				11 });
		values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
		values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN,
				Color.YELLOW };
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,
				PointStyle.DIAMOND, PointStyle.TRIANGLE, PointStyle.SQUARE };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}
		setChartSettings(renderer, "Average temperature", "Month",
				"Temperature", 0.5, 12.5, 0, 32, Color.LTGRAY, Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
		GraphicalView view = ChartFactory.getLineChartView(context, buildDataset(titles, x, values), renderer);
		layout.addView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	}

	
	public Intent execute(Context context) {
		String[] titles = new String[] { "Crete", "Corfu", "Thassos",
				"Skiathos" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4,
				26.1, 23.6, 20.3, 17.2, 13.9 });
		values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14,
				11 });
		values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
		values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN,
				Color.YELLOW };
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,
				PointStyle.DIAMOND, PointStyle.TRIANGLE, PointStyle.SQUARE };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}
		setChartSettings(renderer, "Average temperature", "Month",
				"Temperature", 0.5, 12.5, 0, 32, Color.LTGRAY, Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
		Intent intent = ChartFactory.getLineChartIntent(context,
				buildDataset(titles, x, values), renderer,
				"Average temperature");
		return intent;
	}

}
