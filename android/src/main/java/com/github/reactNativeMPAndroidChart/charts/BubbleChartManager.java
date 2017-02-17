package com.github.reactNativeMPAndroidChart.charts;


import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;

import java.util.ArrayList;

public class BubbleChartManager extends ChartBaseManager<BubbleChart, BubbleEntry> {

    @Override
    public String getName() {
        return "MPAndroidBubbleChart";
    }

    @Override
    protected BubbleChart createViewInstance(ThemedReactContext reactContext) {
        return new BubbleChart(reactContext);
    }

    @Override
    ChartData createData() {
        return new BubbleData();
    }


    @Override
    IDataSet createDataSet(ArrayList<BubbleEntry> entries, String label) {
        return new BubbleDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<BubbleEntry> dataSet, ReadableMap config) {
        BubbleDataSet bubbleDataSet = (BubbleDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(bubbleDataSet, config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(bubbleDataSet, config);

        // BubbleDataSet only config
        if (BridgeUtils.validate(config, ReadableType.Number, "highlightCircleWidth")) {
            bubbleDataSet.setHighlightCircleWidth((float) config.getDouble("highlightCircleWidth"));
        }
    }

    @Override
    BubbleEntry createEntry(ReadableArray values, int index) {
        if (!ReadableType.Map.equals(values.getType(index))) {
            throw new IllegalArgumentException("Invalid BubbleEntry data");
        }

        ReadableMap entry = values.getMap(index);
        if(BridgeUtils.validate(entry, ReadableType.Number, "x") ||
                !BridgeUtils.validate(entry, ReadableType.Number, "y") ||
                !BridgeUtils.validate(entry, ReadableType.Number, "size")) {
            throw new IllegalArgumentException("Invalid BubbleEntry data");
        }


        float x = (float) entry.getDouble("x");
        float y = (float) entry.getDouble("y");
        float size = (float) entry.getDouble("size");

        BubbleEntry bubbleEntry = new BubbleEntry(x, y, size);
        if (entry.hasKey("payload")) {
            bubbleEntry.setData(entry.getMap("payload"));
        }

        return bubbleEntry;
    }
}
