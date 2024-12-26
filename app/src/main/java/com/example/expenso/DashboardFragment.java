package com.example.expenso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private PieChart pieChart;
    private LineChart lineChart;
    private CircularProgressBar budgetFood, budgetRent, budgetEntertainment;
    private Spinner dateRangeSpinner, categorySpinner;
    private EditText searchView;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI components
        pieChart = view.findViewById(R.id.pieChart);
        lineChart = view.findViewById(R.id.lineChart);
        budgetFood = view.findViewById(R.id.budgetFood);
        budgetRent = view.findViewById(R.id.budgetRent);
        budgetEntertainment = view.findViewById(R.id.budgetEntertainment);
        dateRangeSpinner = view.findViewById(R.id.dateRangeSpinner);
        categorySpinner = view.findViewById(R.id.categorySpinner);
        searchView = view.findViewById(R.id.searchView);

        // Setup charts
        setupPieChart();
        setupLineChart();

        // Setup budgets
        setupBudgets();

        // Setup filter bar
        setupFilterBar();
    }

    private void setupPieChart() {
        // Sample data for Pie Chart
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(40, "Food"));
        pieEntries.add(new PieEntry(30, "Rent"));
        pieEntries.add(new PieEntry(20, "Entertainment"));
        pieEntries.add(new PieEntry(10, "Others"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "Expenses");
        dataSet.setColors(new int[]{R.color.budget_food, R.color.budget_rent, R.color.budget_entertainment, R.color.budget_background}, getContext());
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(14f);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false); // Disable description
        pieChart.setCenterText("Expenses");
        pieChart.setCenterTextSize(18f);
        pieChart.animateY(1000); // Animation
        pieChart.invalidate(); // Refresh chart
    }

    private void setupLineChart() {
        // Sample data for Line Chart
        List<Entry> lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(0, 500));
        lineEntries.add(new Entry(1, 700));
        lineEntries.add(new Entry(2, 400));
        lineEntries.add(new Entry(3, 600));
        lineEntries.add(new Entry(4, 800));

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Balance Over Time");
        lineDataSet.setColor(getResources().getColor(R.color.budget_rent));
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setCircleColor(getResources().getColor(R.color.budget_food));

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false); // Disable description
        lineChart.animateX(1000); // Animation
        lineChart.invalidate(); // Refresh chart
    }

    private void setupBudgets() {
        // Set progress for circular progress bars
        budgetFood.setProgress(70); // Food: 70%
        budgetRent.setProgress(50); // Rent: 50%
        budgetEntertainment.setProgress(90); // Entertainment: 90%
    }

    private void setupFilterBar() {
        // Populate spinners with sample data
        String[] dateRanges = {"Today", "This Week", "This Month"};
        String[] categories = {"All", "Food", "Rent", "Entertainment", "Others"};

        // You can use ArrayAdapter to set up spinners
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dateRanges);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateRangeSpinner.setAdapter(dateAdapter);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        // Set listeners (optional)
        dateRangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle date range selection
                String selectedRange = dateRanges[position];
                // TODO: Filter data based on selected range
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle category selection
                String selectedCategory = categories[position];
                // TODO: Filter data based on selected category
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Handle search input
        searchView.setOnEditorActionListener((v, actionId, event) -> {
            String query = searchView.getText().toString();
            // TODO: Filter data based on query
            return true;
        });
    }
}
