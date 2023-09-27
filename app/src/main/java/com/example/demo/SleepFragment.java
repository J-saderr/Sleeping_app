package com.example.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SleepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SleepFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timeInMillis));
    }
    private String formatElapsedTime(long elapsedTimeInSeconds) {
        long hours = elapsedTimeInSeconds / 3600;
        long remainingSeconds = elapsedTimeInSeconds % 3600;
        long minutes = remainingSeconds / 60;
        long seconds = remainingSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);
        View rootView = inflater.inflate(R.layout.fragment_sleep, container, false);
        Bundle bundle = getArguments();
        String filePath = getActivity().getFilesDir() + "/data.json";
        TimerData loadedTimerData = TimerData.loadFromJson(filePath);
        TextView elapsedTimeTextView = view.findViewById(R.id.thoigianngu);

        if (loadedTimerData != null) {
            long startTime = loadedTimerData.getStart();
            long stopTime = loadedTimerData.getStop();

            String formattedStartTime = formatTime(startTime);
            String formattedStopTime = formatTime(stopTime);

            TextView startTimeTextView = view.findViewById(R.id.starttime);
            TextView stopTimeTextView = view.findViewById(R.id.stoptime);

            startTimeTextView.setText(formattedStartTime);
            stopTimeTextView.setText(formattedStopTime);

            long elapsedTimeInSeconds = (stopTime - startTime) / 1000;
            String formattedElapsedTime = formatElapsedTime(elapsedTimeInSeconds);
            elapsedTimeTextView.setText(formattedElapsedTime);
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

        //textviewNgay
        TextView currentDateTextView = view.findViewById(R.id.Ngay);
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);
        currentDateTextView.setText(formattedDate);

        //Pie chart
            PieChart pieChart = view.findViewById(R.id.pieChart);

            ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new

            PieEntry(40f,"Item 1"));
                entries.add(new

            PieEntry(25f,"Item 2"));
                entries.add(new

            PieEntry(35f,"Item 3"));

            PieDataSet dataSet = new PieDataSet(entries, "My Chart");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                dataSet.setValueTextSize(12f);

            PieData pieData = new PieData(dataSet);
                pieChart.setData(pieData);

                pieChart.setUsePercentValues(true);
                pieChart.getDescription().

            setEnabled(false);
                pieChart.setDrawHoleEnabled(true);
                pieChart.setHoleRadius(30f);
                pieChart.setTransparentCircleRadius(40f);

                pieChart.invalidate(); // Refresh the chart
                return view;
        }
}