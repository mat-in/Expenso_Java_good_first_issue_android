package com.example.expenso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsFragment extends Fragment {

    private RecyclerView rvTransactions;
    private TransactionAdapter transactionAdapter;
    private FloatingActionButton fabAddTransaction;
    private TextView tvBalance, tvIncome, tvExpenses;

    public AnalyticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI components
        rvTransactions = view.findViewById(R.id.rv_transactions);
        fabAddTransaction = view.findViewById(R.id.fab_add_transaction);
        tvBalance = view.findViewById(R.id.tv_balance);
        tvIncome = view.findViewById(R.id.tv_income);
        tvExpenses = view.findViewById(R.id.tv_expenses);

        // Set up RecyclerView
        setupRecyclerView();

        // Set up FAB
        setupFab();

        // Display balance summary
        updateBalanceSummary();
    }

    private void setupRecyclerView() {
        // Initialize dummy transaction list
        List<Transaction> transactionList = getSampleTransactions();

        // Set up adapter and layout manager
        transactionAdapter = new TransactionAdapter(transactionList);
        rvTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTransactions.setAdapter(transactionAdapter);
    }

    private void setupFab() {
        fabAddTransaction.setOnClickListener(v -> {
            // TODO: Open dialog to add a new transaction
            showAddTransactionDialog();
        });
    }

    private void updateBalanceSummary() {
        // Dummy data for balance, income, and expenses
        double totalIncome = 5000.00;
        double totalExpenses = 3000.00;
        double balance = totalIncome - totalExpenses;

        // Update TextViews
        tvBalance.setText(String.format("₹%.2f", balance));
        tvIncome.setText(String.format("Income: ₹%.2f", totalIncome));
        tvExpenses.setText(String.format("Expenses: ₹%.2f", totalExpenses));
    }

    private void showAddTransactionDialog() {
        // TODO: Implement dialog logic to add a new transaction
        // For now, show a placeholder message
        if (getContext() != null) {
            android.widget.Toast.makeText(getContext(), "Add Transaction Dialog Placeholder", android.widget.Toast.LENGTH_SHORT).show();
        }
    }

    private List<Transaction> getSampleTransactions() {
        // Create dummy transactions for display
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Groceries", 1200.00, "Expense"));
        transactions.add(new Transaction("Salary", 5000.00, "Income"));
        transactions.add(new Transaction("Electricity Bill", 800.00, "Expense"));
        return transactions;
    }
}
