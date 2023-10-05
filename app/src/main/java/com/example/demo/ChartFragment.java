package com.example.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChartFragment newInstance(String param1, String param2) {
        ChartFragment fragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private SleepSchedule sleepSchedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        LineChart lineChart = view.findViewById(R.id.lineChart);

        SleepSchedule sleepSchedule = new SleepSchedule();
        sleepSchedule.setMonday("{\"bedtime\": \"22:00\", \"wake_up_time\": \"06:00\"}");
        sleepSchedule.setTuesday("{\"bedtime\": \"22:30\", \"wake_up_time\": \"06:30\"}");
        sleepSchedule.setWednesday("{\"bedtime\": \"23:00\", \"wake_up_time\": \"07:00\"}");
        sleepSchedule.setThursday("{\"bedtime\": \"23:30\", \"wake_up_time\": \"07:30\"}");

        List<Entry> entries = loadSleepDataFromSchedule(sleepSchedule);

        if (entries != null && !entries.isEmpty()) {
            configureLineChart(lineChart, entries);
        }

        return view;
    }

    private List<Entry> loadSleepDataFromSchedule(SleepSchedule sleepSchedule) {
        List<Entry> entries = new ArrayList<>();

        String[] daysOfWeek = {"monday", "tuesday", "wednesday", "thursday"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            String day = daysOfWeek[i];
            String dayDataString = null;

            // Determine which day to retrieve based on the loop index
            switch (i) {
                case 0:
                    dayDataString = sleepSchedule.getMonday();
                    break;
                case 1:
                    dayDataString = sleepSchedule.getTuesday();
                    break;
                case 2:
                    dayDataString = sleepSchedule.getWednesday();
                    break;
                case 3:
                    dayDataString = sleepSchedule.getThursday();
                    break;
            }

            if (dayDataString != null) {
                try {
                    JSONObject dayData = new JSONObject(dayDataString);
                    String bedtime = dayData.getString("bedtime");
                    String wakeUpTime = dayData.getString("wake_up_time");

                    // Log the data to check if it's read correctly
                    Log.d("DATA", "Day: " + day + ", Bedtime: " + bedtime + ", Wake Up Time: " + wakeUpTime);

                    float x = i; // Use the day index as x
                    float y = convertTimeToMinutes(bedtime); // Convert bedtime to minutes
                    entries.add(new Entry(x, y));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return entries;
    }


    private float convertTimeToMinutes(String time) {
        // Convert time in HH:mm format to minutes
        String[] parts = time.split(":");
        if (parts.length == 2) {
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            return hours * 60 + minutes;
        }
        return 0;
    }

    private void configureLineChart(LineChart lineChart, List<Entry> entries) {
        if (entries != null && !entries.isEmpty()) {
            // Create a DataSet and configure it
            LineDataSet dataSet = new LineDataSet(entries, "Bedtime Data");
            dataSet.setColor(Color.BLUE);
            dataSet.setValueTextColor(Color.RED);

            LineData lineData = new LineData(dataSet);
            lineChart.setData(lineData);

            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelCount(entries.size()); // Set the number of labels to match the number of entries
            xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday"})); // Set day labels

            YAxis yAxis = lineChart.getAxisLeft();
            yAxis.setAxisMinimum(0f);
            yAxis.setAxisMaximum(1440f); // Set the maximum value to represent a full day (24 hours)

            lineChart.invalidate();
        }
    }
}
