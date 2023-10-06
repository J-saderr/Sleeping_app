package com.example.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        LineChart lineChart1 = view.findViewById(R.id.lineChart1);

        SleepSchedule sleepSchedule = new SleepSchedule();
        sleepSchedule.setMonday("{\"bedtime\": \"22:08\", \"wake_up_time\": \"06:32\"}");
        sleepSchedule.setTuesday("{\"bedtime\": \"22:34\", \"wake_up_time\": \"06:10\"}");
        sleepSchedule.setWednesday("{\"bedtime\": \"23:25\", \"wake_up_time\": \"07:15\"}");
        sleepSchedule.setThursday("{\"bedtime\": \"23:32\", \"wake_up_time\": \"07:20\"}");
        sleepSchedule.setFriday("{\"bedtime\": \"23:45\", \"wake_up_time\": \"08:10\"}");

        List<Entry> entriesBedtime = loadSleepBedtimeDataFromSchedule(sleepSchedule);
        List<Entry> entriesWakeupTime = loadSleepWakeupTimeDataFromSchedule(sleepSchedule);

        if (entriesBedtime != null && !entriesBedtime.isEmpty()) {
            configureLineChart(lineChart, entriesBedtime);
        }

        if (entriesWakeupTime != null && !entriesWakeupTime.isEmpty()) {
            configureLineChart1(lineChart1, entriesWakeupTime);
        }

        return view;
    }

    private List<Entry> loadSleepBedtimeDataFromSchedule(SleepSchedule sleepSchedule) {
        List<Entry> entries = new ArrayList<>();

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            String day = daysOfWeek[i];
            String dayDataString = null;

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
                // Thêm các ngày cuối tuần (Friday, Saturday, Sunday)
                case 4:
                    dayDataString = sleepSchedule.getFriday();
                    break;
                case 5:
                case 6:
                    continue;
            }

            if (dayDataString != null) {
                try {
                    JSONObject dayData = new JSONObject(dayDataString);
                    String bedtime = dayData.getString("bedtime");

                    float x = i;
                    float bedtimeMinutes = convertTimeToHours(bedtime);
                    entries.add(new Entry(x, bedtimeMinutes));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return entries;
    }

    private List<Entry> loadSleepWakeupTimeDataFromSchedule(SleepSchedule sleepSchedule) {
        List<Entry> entries = new ArrayList<>();

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            String day = daysOfWeek[i];
            String dayDataString = null;

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
                case 4:
                    dayDataString = sleepSchedule.getFriday();
                    break;
                case 5:
                case 6:
                    continue;
            }

            if (dayDataString != null) {
                try {
                    JSONObject dayData = new JSONObject(dayDataString);
                    String wakeupTime = dayData.getString("wake_up_time");

                    float x = i;
                    float wakeupTimeMinutes = convertTimeToHours(wakeupTime);
                    entries.add(new Entry(x, wakeupTimeMinutes));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return entries;
    }

    private float convertTimeToHours(String time) {
        String[] parts = time.split(":");
        if (parts.length == 2) {
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            return hours + (minutes / 60.0f);
        }
        return 0;
    }

    private void configureLineChart(LineChart lineChart, List<Entry> entries) {
        if (entries != null && !entries.isEmpty()) {
            LineDataSet dataSet = new LineDataSet(entries, "Bedtime Data");
            dataSet.setColor(Color.BLUE);
            dataSet.setValueTextColor(Color.WHITE);

            LineData lineData = new LineData(dataSet);
            lineChart.setData(lineData);

            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelCount(7);

            xAxis.setLabelCount(entries.size());
            xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
            xAxis.setGranularity(1f);
            xAxis.setTextColor(Color.WHITE);

            YAxis yAxis = lineChart.getAxisLeft();
            yAxis.setAxisMinimum(22f);
            yAxis.setAxisMaximum(31f);
            yAxis.setValueFormatter(new HourAxisValueFormatter());
            yAxis.setTextColor(Color.WHITE);

            lineChart.invalidate();
        }
    }
    public class HourAxisValueFormatter extends ValueFormatter {
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            int hours = (int) value % 12;
            if (hours == 0) {
                hours = 12;
            }
            int minutes = (int) ((value - (int) value) * 60);
            String amPm = (int) value < 12 ? "AM" : "PM";
            return String.format(Locale.getDefault(), "%02d:%02d %s", hours, minutes, amPm);
        }
    }
    private void configureLineChart1(LineChart lineChart1, List<Entry> entries) {
        if (entries != null && !entries.isEmpty()) {
            LineDataSet dataSet = new LineDataSet(entries, "Bedtime Data");
            dataSet.setColor(Color.GREEN);
            dataSet.setValueTextColor(Color.WHITE);

            LineData lineData = new LineData(dataSet);
            lineChart1.setData(lineData);

            XAxis xAxis = lineChart1.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setLabelCount(7);

            xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
            xAxis.setGranularity(1f);
            xAxis.setTextColor(Color.WHITE);

            YAxis yAxis = lineChart1.getAxisLeft();
            yAxis.setAxisMinimum(0f);
            yAxis.setAxisMaximum(12f);
            yAxis.setValueFormatter(new HourAxisValueFormatter());
            yAxis.setTextColor(Color.WHITE);

            lineChart1.invalidate();
        }
    }
}