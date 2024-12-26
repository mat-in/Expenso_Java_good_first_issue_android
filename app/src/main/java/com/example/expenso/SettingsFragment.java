package com.example.expenso;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragmentCompat {

    private static final String PREF_DARK_MODE = "pref_dark_mode";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load preferences from XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Ensure the dark mode setting is applied when fragment is created
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        if (!sharedPreferences.contains(PREF_DARK_MODE)) {
            sharedPreferences.edit().putBoolean(PREF_DARK_MODE, true).apply();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Register listener for preference changes
        getPreferenceManager().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Unregister listener to avoid memory leaks
        getPreferenceManager().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    // Listener for preference changes
    private final SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener = (sharedPreferences, key) -> {
        if (PREF_DARK_MODE.equals(key)) {
            boolean isDarkMode = sharedPreferences.getBoolean(key, true); // Default to true
            AppCompatDelegate.setDefaultNightMode(
                    isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
        }
    };

    @Override
    public boolean onPreferenceTreeClick(@NonNull Preference preference) {
        String key = preference.getKey();

        if ("pref_export_data".equals(key)) {
            exportDataToCSV();
            return true;
        } else if ("pref_import_data".equals(key)) {
            importDataFromCSV();
            return true;
        } else if ("pref_logout".equals(key)) {
            logoutUser();
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    // Placeholder method for exporting data
    private void exportDataToCSV() {
        showMessage("Exporting data...");
    }

    // Placeholder method for importing data
    private void importDataFromCSV() {
        showMessage("Importing data...");
    }

    // Placeholder method for logging out the user
    private void logoutUser() {
        showMessage("Logging out...");
    }

    // Utility method to show a message (e.g., Toast)
    private void showMessage(String message) {
        if (getContext() != null) {
            android.widget.Toast.makeText(getContext(), message, android.widget.Toast.LENGTH_SHORT).show();
        }
    }
}
