package com.example.linegraph;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;
    private EditText numX, numY;
    private List<Entry> entries1;
    private LineDataSet dataSet1;
    private LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = findViewById(R.id.chart);
        numX = findViewById(R.id.numX);
        numY = findViewById(R.id.numY);
        Button btnAddPt = findViewById(R.id.btnAddPt);
        Button btnDelPt = findViewById(R.id.btnDelPt);

        Description description = new Description();
        description.setText("Students Record");
        description.setPosition(15f, 15f);
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(100f);
        xAxis.setAxisLineWidth(2f);
        xAxis.setLabelCount(10);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setGranularity(1f);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        entries1 = new ArrayList<>();

        dataSet1 = new LineDataSet(entries1, "Line1");
        dataSet1.setColor(Color.BLUE);

        lineData = new LineData(dataSet1);
        lineChart.setData(lineData);
        lineChart.invalidate();

        btnAddPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xValue = numX.getText().toString();
                String yValue = numY.getText().toString();

                if (TextUtils.isEmpty(xValue) || TextUtils.isEmpty(yValue)) {
                    Toast.makeText(MainActivity.this, "Please enter both X and Y values", Toast.LENGTH_SHORT).show();
                    return;
                }

                float x = Float.parseFloat(xValue);
                float y = Float.parseFloat(yValue);

                entries1.add(new Entry(x, y));
                lineData.notifyDataChanged();
                lineChart.notifyDataSetChanged();
                lineChart.invalidate();
            }
        });

        btnDelPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entries1.clear();
                lineData.notifyDataChanged();
                lineChart.notifyDataSetChanged();
                lineChart.invalidate();
            }
        });
    }
}
