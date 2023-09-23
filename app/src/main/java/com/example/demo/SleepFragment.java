package com.example.demo;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
    private String selectedOption1;
    private String selectedOption2;
    private String selectedOption3;
    private String selectedOption4;

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
    public static SleepFragment newInstance(String param1, String param2,String selectedOption1, String selectedOption2, String selectedOption3, String selectedOption4) {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);
        View rootView = inflater.inflate(R.layout.fragment_sleep, container, false);
        TimerViewModel timerViewModel = new ViewModelProvider(requireActivity()).get(TimerViewModel.class);
        long startTimeInMillis = timerViewModel.getStartTimeInMillis();
// Tìm TextView trong layout
        TextView startTimeTextView = view.findViewById(R.id.bedtime);
        TextView selectedOptionTextView1 = view.findViewById(R.id.selectedOptionTextView1);
        Bundle args = getArguments();
        if (args != null) {
            String selectedOption1 = args.getString("selectedOption1");
            // Cập nhật TextView với giá trị đã chọn
            selectedOptionTextView1.setText(selectedOption1);
        }

// Chuyển đổi giá trị startTimeInMillis thành chuỗi ngày/giờ/phút/... tùy theo định dạng bạn muốn và hiển thị nó trên TextView
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedStartTime = sdf.format(new Date(startTimeInMillis));

        startTimeTextView.setText("Start Time: " + formattedStartTime);

        PieChart pieChart = view.findViewById(R.id.pieChart);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(40f, "Item 1"));
        entries.add(new PieEntry(25f, "Item 2"));
        entries.add(new PieEntry(35f, "Item 3"));

        PieDataSet dataSet = new PieDataSet(entries, "My Chart");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(12f);

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleRadius(40f);

        pieChart.invalidate(); // Refresh the chart
        return view;
    }
}