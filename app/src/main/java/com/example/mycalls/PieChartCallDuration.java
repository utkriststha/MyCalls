package com.example.mycalls;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class PieChartCallDuration extends AppCompatActivity {
    //Initializing pie chart, database handler and textview
    private PieChart pieChart;

    DBHandler dbHandler;
    TextView tvResultICCallDuration,tvResultOCCallDuration
            ,tvResultMCCallDuration,tvResultTCCallDuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Assigning layout
        setContentView(R.layout.activity_pc_call_duration);

        //Assigning pie chart to the id
        pieChart = findViewById(R.id.chartPieCallDuration);
        //Setting and loading pie chart
        setupPieChart();
        loadPieChartData();
        //Setting the table of the data
        setTable();
    }

    //Method to setup pie chart
    private void setupPieChart() {
        //Setting the pie chart properties
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(18);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Call Duration");
        pieChart.setCenterTextSize(30);
        pieChart.getDescription().setEnabled(false);

        //Setting the legend format and properties
        Legend legend = pieChart.getLegend();
        legend.setTextColor(Color.BLACK);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextSize(20);
        legend.setDrawInside(true);
        legend.setEnabled(true);
    }

    private void loadPieChartData() {
        //Assigning the database handler
        dbHandler = new DBHandler(PieChartCallDuration.this);

        //Loading the pie chart with data entry
        ArrayList<PieEntry> entries = new ArrayList<>();
        //Getting the incoming and outgoing in percentage through dbHandler methods.
        float incomingCalls = dbHandler.getIncomingCallDurationPercentage();
        float outgoingCalls = dbHandler.getOutgoingCallDurationPercentage();
        //Adding the entries
        entries.add(new PieEntry(incomingCalls, "Incoming Calls"));
        entries.add(new PieEntry(outgoingCalls, "Outgoing Calls"));
        //Setting the color of pie chart
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }
        PieDataSet dataSet = new PieDataSet(entries,"");
        dataSet.setColors(colors);

        //Setting the properties of the data on pie chart
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(22f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        //Setting the animation when the pie chart gets created
        pieChart.animateY(1400, Easing.EaseInCubic);
    }

    //Setting table for the pie chart
    private void setTable() {
        //Assigning the database handler
        dbHandler = new DBHandler(PieChartCallDuration.this);
        List<CallEntry> allCallEntries = dbHandler.getData();

        //Assigning the items with their respected id
        tvResultICCallDuration = findViewById(R.id.tvResultICCallDuration);
        tvResultOCCallDuration = findViewById(R.id.tvResultOCCallDuration);
        tvResultMCCallDuration = findViewById(R.id.tvResultMCCallDuration);
        tvResultTCCallDuration = findViewById(R.id.tvResultTCCallDuration);

        //Setting the data for the items
        tvResultICCallDuration.setText(dbHandler.getIncomingCallDuration());
        tvResultOCCallDuration.setText(dbHandler.getOutgoingCallDuration());
        tvResultTCCallDuration.setText(dbHandler.getTotalCallDuration());
        tvResultMCCallDuration.setText("NULL");
    }


}