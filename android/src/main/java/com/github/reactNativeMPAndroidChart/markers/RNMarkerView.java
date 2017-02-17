package com.github.reactNativeMPAndroidChart.markers;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;

public abstract class RNMarkerView extends MarkerView {

    private RelativeLayout markerContent;
    private TextView tvContent;

    public RNMarkerView(Context context, int layoutResource, int markerContentId, int tvContentId) {
        super(context, layoutResource);

        tvContent = (TextView) findViewById(tvContentId);
        markerContent = (RelativeLayout) findViewById(markerContentId);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            tvContent.setText(Utils.formatNumber(ce.getClose(), 2, true));
        } else {
            tvContent.setText(Utils.formatNumber(e.getY(), 0, true));
        }
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public RelativeLayout getMarkerContent() {
        return markerContent;
    }
}
