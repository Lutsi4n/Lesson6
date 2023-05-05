package ru.mirea.khadzhioglo.lesson6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import ru.mirea.khadzhioglo.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        binding.eTGroup.setText(sharedPref.getString("GROUP", "XXXX-XX-XX"));
        binding.eTNumber.setText(sharedPref.getString("NUMBER", "XX"));
        binding.eTFilm.setText(sharedPref.getString("FILM", "???"));

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("GROUP", binding.eTGroup.getText().toString());
                editor.putString("NUMBER", binding.eTNumber.getText().toString());
                editor.putString("FILM", binding.eTFilm.getText().toString());

                editor.apply();
            }
        });
    }
}