package ru.mirea.khadzhioglo.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ru.mirea.khadzhioglo.securesharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences secureSharedPreferences;
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        try {
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            secureSharedPreferences.edit().putString("secure", "Лермонтов");
            secureSharedPreferences.edit().putString("secure", "— Скажи-ка, дядя, ведь не даром\n" +
                    "Москва, спаленная пожаром,\n" +
                    "Французу отдана?\n" +
                    "Ведь были ж схватки боевые,\n" +
                    "Да, говорят, еще какие!\n" +
                    "Недаром помнит вся Россия\n" +
                    "Про день Бородина!");
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

        binding.textView2.setText(secureSharedPreferences.getString("secure", "Лермонтов"));
        binding.textView.setText(secureSharedPreferences.getString("secure", "— Скажи-ка, дядя, ведь не даром\n" +
                "Москва, спаленная пожаром,\n" +
                "Французу отдана?\n" +
                "Ведь были ж схватки боевые,\n" +
                "Да, говорят, еще какие!\n" +
                "Недаром помнит вся Россия\n" +
                "Про день Бородина!"));
    }
}