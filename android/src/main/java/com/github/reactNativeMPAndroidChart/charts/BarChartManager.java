package com.github.reactNativeMPAndroidChart.charts;

import android.graphics.Color;
import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;

import java.util.ArrayList;

public class BarChartManager extends BarLineChartBaseManager<BarChart, BarEntry> {

    @Override
    public String getName() {
        return "MPAndroidBarChart";
    }

    @Override
    protected View createViewInstance(ThemedReactContext reactContext) {
        BarChart barChart = new BarChart(reactContext);

        return barChart;
    }

    @Override
    ChartData createData() {
        return new BarData();
    }

    @Override
    IDataSet createDataSet(ArrayList<BarEntry> entries, String label) {
        return new BarDataSet(entries, label);
    }

    @Override
    BarEntry createEntry(ReadableArray values, int index) {
        BarEntry entry;

        float x = index;
        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);

            if (map.hasKey("x")) {
                x = (float) map.getDouble("x");
            }

            if (ReadableType.Array.equals(map.getType("y"))) {
                entry = new BarEntry(x, BridgeUtils.convertToFloatArray(map.getArray("y")));
            } else if (ReadableType.Number.equals(map.getType("y"))) {
                entry = new BarEntry(x, (float) map.getDouble("y"));
            } else {
                throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
            }

            entry.setData(map);

        } else if (ReadableType.Array.equals(values.getType(index))) {
            entry = new BarEntry(x, BridgeUtils.convertToFloatArray(values.getArray(index)));
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new BarEntry(x, (float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }

        return entry;
    }

    @Override
    void dataSetConfig(IDataSet<BarEntry> dataSet, ReadableMap config) {
        BarDataSet barDataSet = (BarDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(barDataSet, config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(barDataSet, config);

        if (BridgeUtils.validate(config, ReadableType.String, "barShadowColor")) {
            barDataSet.setBarShadowColor(Color.parseColor(config.getString("barShadowColor")));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "highlightAlpha")) {
            barDataSet.setHighLightAlpha(config.getInt("highlightAlpha"));
        }
        if (BridgeUtils.validate(config, ReadableType.Array, "stackLabels")) {
            barDataSet.setStackLabels(BridgeUtils.convertToStringArray(config.getArray("stackLabels")));
        }

    }

    @ReactProp(name = "drawValueAboveBar")
    public void setDrawValueAboveBar(BarChart chart, boolean enabled) {
        chart.setDrawValueAboveBar(enabled);
    }

    @ReactProp(name = "drawBarShadow")
    public void setDrawBarShadow(BarChart chart, boolean enabled) {
        chart.setDrawBarShadow(enabled);
    }

}
