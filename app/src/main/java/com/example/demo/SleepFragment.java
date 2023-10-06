package com.example.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SleepFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView startTimeTextView;
    private TextView stopTimeTextView;
    private TextView elapsedTimeTextView;
    private PieChart pieChart;

    public SleepFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SleepFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SleepFragment newInstance(String param1, String param2) {
        SleepFragment fragment = new SleepFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private String formatTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date(timeInMillis);
        return sdf.format(date);
    }

    private String formatElapsedTime(long elapsedTimeInSeconds) {
        long hours = elapsedTimeInSeconds / 3600;
        long remainingSeconds = elapsedTimeInSeconds % 3600;
        long minutes = remainingSeconds / 60;
        return String.format(Locale.getDefault(), "%02d tiếng", hours);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);

        // Initialize your UI elements here
        startTimeTextView = view.findViewById(R.id.starttime);
        stopTimeTextView = view.findViewById(R.id.stoptime);
        elapsedTimeTextView = view.findViewById(R.id.thoigianngu);
        pieChart = view.findViewById(R.id.pieChart);

        TextView currentDateTextView = view.findViewById(R.id.Ngay);
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);
        currentDateTextView.setText(formattedDate);

        String filename = "data.json";
        String filePath = getActivity().getFilesDir().getPath() + "/" + filename;
        TimerData loadedTimerData = TimerData.loadFromJson(filePath);

        if (loadedTimerData != null) {
            long startTime = loadedTimerData.getStart();
            long stopTime = loadedTimerData.getStop();

            String formattedStartTime = formatTime(startTime);
            String formattedStopTime = formatTime(stopTime);

            long time1 = loadedTimerData.getTime1();
            long time2 = loadedTimerData.getTime2();
            long time1MinusStartTime1 = time1 - startTime;
            long time1MinusStartTime2 = time2 - stopTime;

            String timeDifferenceLabel1;
            String timeDifferenceLabel2;

            if (time1MinusStartTime1 > 0) {

                timeDifferenceLabel1 = "sớm " + time1MinusStartTime1;
            } else if (time1MinusStartTime1 < 0) {

                timeDifferenceLabel1 = "trễ "+ time1MinusStartTime1;
            } else {

                timeDifferenceLabel1 = "đúng " + time1MinusStartTime1 + " giờ";
            }
            Log.d("TimeDifferenceLabel", "Time Difference Label: " + timeDifferenceLabel1);

            if (time1MinusStartTime2 > 0) {

                timeDifferenceLabel2 = "sớm " + time1MinusStartTime2;
            } else if (time1MinusStartTime2 < 0) {

                timeDifferenceLabel2 = "trễ " + time1MinusStartTime2;
            } else {

                timeDifferenceLabel2 = "đúng " + time1MinusStartTime2 + " giờ";
            }

            Log.d("TimeDifferenceLabel", "Time Difference Label: " + timeDifferenceLabel2);


            startTimeTextView.setText(formattedStartTime);
            stopTimeTextView.setText(formattedStopTime);

            long elapsedTimeInSeconds = (stopTime - startTime) / 1000;
            String formattedElapsedTime = formatElapsedTime(elapsedTimeInSeconds);
            elapsedTimeTextView.setText(formattedElapsedTime);

            String selectedOption1 = loadedTimerData.getSelectedOption1();
            String selectedOption2 = loadedTimerData.getSelectedOption2();
            String selectedOption3 = loadedTimerData.getSelectedOption3();
            String selectedOption4 = loadedTimerData.getSelectedOption4();

            long selectedOptionMinutes1 = convertOptionToMinutes1(selectedOption1);
            long selectedOptionMinutes2 = convertOptionToMinutes2(selectedOption2);
            long selectedOptionMinutes4 = convertOptionToMinutes4(selectedOption4);

            long start = convertOptionToMinutesStart(formattedStartTime);
            long stop = convertOptionToMinutesStop(formattedStopTime);

            long SleepTimeMinutes = start - selectedOptionMinutes1 + selectedOptionMinutes2 + selectedOptionMinutes4;
            long TotalSleepTimeMinutes = stop + (start - selectedOptionMinutes1);
            double sleepEfficiencyPercentage = (double) SleepTimeMinutes / TotalSleepTimeMinutes * 100;

                displayPieChart(sleepEfficiencyPercentage);
        }
        String jsonData = "";

        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            jsonData = stringBuilder.toString();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    public void saveJsonToFile(String jsonData, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long convertOptionToMinutes1(String selectedOption1) {

        if (selectedOption1 != null) {
            try {
                String[] parts = selectedOption1.split(":");
                long hours = Long.parseLong(parts[0]);
                long minutes = Long.parseLong(parts[1]);

                return hours * 60 + minutes;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private long convertOptionToMinutes2(String selectedOption2) {

        if (selectedOption2 != null) {
            if (selectedOption2.contains("tiếng")) {
                String hoursString = selectedOption2.split(" ")[0];
                try {
                    long hours = Long.parseLong(hoursString);
                    return hours * 60;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else if (selectedOption2.contains("phút")) {
                String minutesString = selectedOption2.split(" ")[0];
                try {
                    long minutes = Long.parseLong(minutesString);
                    return minutes;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    private long convertOptionToMinutes4(String selectedOption4) {

        if (selectedOption4 != null) {
            if (selectedOption4.contains("tiếng")) {
                String hoursString = selectedOption4.split(" ")[0];
                try {
                    long hours = Long.parseLong(hoursString);
                    return hours * 60;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else if (selectedOption4.contains("phút")) {
                String minutesString = selectedOption4.split(" ")[0];
                try {
                    long minutes = Long.parseLong(minutesString);
                    return minutes;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
    private long convertOptionToMinutesStart(String formattedStartTime) {

        if (formattedStartTime != null) {
            try {
                String[] parts = formattedStartTime.split(":");
                long hours = Long.parseLong(parts[0]);
                long minutes = Long.parseLong(parts[1]);

                return hours * 60 + minutes;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    private long convertOptionToMinutesStop(String formattedStopTime) {

        if (formattedStopTime != null) {
            try {
                String[] parts = formattedStopTime.split(":");
                long hours = Long.parseLong(parts[0]);
                long minutes = Long.parseLong(parts[1]);

                return hours * 60 + minutes;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private void displayPieChart(double sleepEfficiencyPercentage) {
        // Define custom shades of blue for the pie chart
        int[] colors = new int[]{Color.BLUE, Color.rgb(0, 0, 128)}; // You can add more shades of blue as needed

        // Create pie chart entries for efficiency and inefficiency
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) sleepEfficiencyPercentage, (int) sleepEfficiencyPercentage + "%"));
        entries.add(new PieEntry((float) (100.0 - sleepEfficiencyPercentage), (int) (100.0 - sleepEfficiencyPercentage) + "%"));

        // Configure the border
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors); // Use custom colors
        dataSet.setValueTextSize(0f); // Hide default percentage values
        dataSet.setSliceSpace(2f); // Space between pie slices
        dataSet.setSelectionShift(5f); // Shifts the selected pie slice outward

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        pieChart.setUsePercentValues(true); // Do not display as percentages
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setDrawEntryLabels(true); // Display entry labels
        pieChart.setEntryLabelTextSize(22f); // Set the text size of entry labels
        pieChart.setEntryLabelColor(Color.WHITE);// Hide default entry labels

        // Create a legend (ghi chú)
        LegendEntry nguLegendEntry = new LegendEntry();
        nguLegendEntry.label = "Ngủ";
        nguLegendEntry.formColor = Color.BLUE; // Set color for "Ngủ" slice

        LegendEntry thucLegendEntry = new LegendEntry();
        thucLegendEntry.label = "Thức";
        thucLegendEntry.formColor = Color.rgb(0, 0, 128); // Set color for "Thức" slice

        Legend legend = pieChart.getLegend();
        legend.setTextSize(24f);
        legend.setCustom(new LegendEntry[]{nguLegendEntry, thucLegendEntry}); // Set custom legend entries
        legend.setTextColor(Color.WHITE); // Set legend text color
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false); // Position legend at the bottom

        pieChart.invalidate(); // Refresh the chart
    }

}
