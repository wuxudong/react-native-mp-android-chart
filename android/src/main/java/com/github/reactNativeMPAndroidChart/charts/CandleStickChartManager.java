package com.github.reactNativeMPAndroidChart.charts;

import android.graphics.Color;
import android.graphics.Paint;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;

import java.util.ArrayList;

public class CandleStickChartManager extends BarLineChartBaseManager<CandleStickChart, CandleEntry> {

    @Override
    public String getName() {
        return "MPAndroidCandleStickChart";
    }

    @Override
    protected CandleStickChart createViewInstance(ThemedReactContext reactContext) {
        return new CandleStickChart(reactContext);
    }

    @Override
    ChartData createData() {
        return new CandleData();
    }

    @Override
    IDataSet createDataSet(ArrayList<CandleEntry> entries, String label) {
        return new CandleDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<CandleEntry> dataSet, ReadableMap config) {
        CandleDataSet candleDataSet = (CandleDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(candleDataSet, config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(candleDataSet, config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(candleDataSet, config);

        // CandleDataSet only config
        if (BridgeUtils.validate(config, ReadableType.Number, "barSpace")) {
            candleDataSet.setBarSpace((float) config.getDouble("barSpace"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "shadowWidth")) {
            candleDataSet.setShadowWidth((float) config.getDouble("shadowWidth"));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "shadowColor")) {
            candleDataSet.setShadowColor(Color.parseColor(config.getString("shadowColor")));
        }
        if (BridgeUtils.validate(config, ReadableType.Boolean, "shadowColorSameAsCandle")) {
            candleDataSet.setShadowColorSameAsCandle(config.getBoolean("shadowColorSameAsCandle"));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "neutralColor")) {
            candleDataSet.setNeutralColor(Color.parseColor(config.getString("neutralColor")));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "decreasingColor")) {
            candleDataSet.setDecreasingColor(Color.parseColor(config.getString("decreasingColor")));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "decreasingPaintStyle")) {
            candleDataSet.setDecreasingPaintStyle(Paint.Style.valueOf(config.getString("decreasingPaintStyle").toUpperCase()));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "increasingColor")) {
            candleDataSet.setIncreasingColor(Color.parseColor(config.getString("increasingColor")));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "increasingPaintStyle")) {
            candleDataSet.setIncreasingPaintStyle(Paint.Style.valueOf(config.getString("increasingPaintStyle").toUpperCase()));
        }
    }

    @Override
    CandleEntry createEntry(ReadableArray values, int index) {
        if (!ReadableType.Map.equals(values.getType(index))) {
            throw new IllegalArgumentException();
        }

        ReadableMap map = values.getMap(index);

        float x;
        if (map.hasKey("x")) {
            x = (float) map.getDouble("x");
        } else {
            x = index;
        }

        if (
                !BridgeUtils.validate(map, ReadableType.Number, "shadowH") ||
                !BridgeUtils.validate(map, ReadableType.Number, "shadowL") ||
                !BridgeUtils.validate(map, ReadableType.Number, "open") ||
                !BridgeUtils.validate(map, ReadableType.Number, "close")) {
            throw new IllegalArgumentException("CandleStick data must contain: shadowH, shadowL, open and close values");
        }

        float shadowH = (float) map.getDouble("shadowH");
        float shadowL = (float) map.getDouble("shadowL");
        float open = (float) map.getDouble("open");
        float close = (float) map.getDouble("close");

        CandleEntry candleEntry = new CandleEntry(x, shadowH, shadowL, open, close, map);

        return candleEntry;
    }
}
