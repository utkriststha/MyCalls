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

public class PieChartNoOfCalls extends AppCompatActivity {
    //Initializing pie chart, database handler and textview
    private PieChart pieChart;
    DBHandler dbHandler;

    TextView tvResultICNoOfCalls,tvResultOCNoOfCalls,
            tvResultMCNoOfCalls,tvResultTCNoOfCalls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Assigning layout
        setContentView(R.layout.activity_pc_no_of_calls);

        //Assigning pie chart to the id
        pieChart = findViewById(R.id.chartPieNoOfCalls);
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
        pieChart.setCenterText("Number of Calls");
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
        dbHandler = new DBHandler(PieChartNoOfCalls.this);
        //Loading the pie chart with data entry
        List<CallEntry> allCallEntries = dbHandler.getData();
        //Getting the incoming, missed and outgoing in percentage through dbHandler methods.
        ArrayList<PieEntry> entries = new ArrayList<>();
        //Getting the incoming and outgoing in percentage through dbHandler methods.
        float incomingCalls = (float)(((double)dbHandler.getNumOfIncomingCalls())/((double)allCallEntries.size()));
        float outgoingCalls = (float)(((double)dbHandler.getNumOfOutgoingCalls())/((double)allCallEntries.size()));
        float missedCalls = (float)(((double)dbHandler.getNumOfMissedCalls())/((double)allCallEntries.size()));

        //Adding the entries
        entries.add(new PieEntry(incomingCalls, "Incoming Calls"));
        entries.add(new PieEntry(outgoingCalls, "Outgoing Calls"));
        entries.add(new PieEntry(missedCalls, "Missed Calls"));
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
    private void setTable(){
        //Assigning the database handler
        dbHandler = new DBHandler(PieChartNoOfCalls.this);
        List<CallEntry> allCallEntries = dbHandler.getData();

        //Assigning the items with their respected id
        tvResultICNoOfCalls = findViewById(R.id.tvResultICNoOfCalls);
        tvResultOCNoOfCalls = findViewById(R.id.tvResultOCNoOfCalls);
        tvResultMCNoOfCalls = findViewById(R.id.tvResultMCNoOfCalls);
        tvResultTCNoOfCalls = findViewById(R.id.tvResultTCNoOfCalls);

        //Setting the data for the items
        tvResultICNoOfCalls.setText(String.valueOf(dbHandler.getNumOfIncomingCalls()));
        tvResultOCNoOfCalls.setText(String.valueOf(dbHandler.getNumOfOutgoingCalls()));
        tvResultMCNoOfCalls.setText(String.valueOf(dbHandler.getNumOfMissedCalls()));
        tvResultTCNoOfCalls.setText(String.valueOf(allCallEntries.size()));
    }
}