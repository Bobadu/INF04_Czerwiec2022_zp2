package com.example.kursy;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ZapisyNaKursy";

    private final String[] kursy = {"Programowanie w C#", "Angular dla początkujących", "Kurs Django"};

    private EditText poleImieNazwisko;
    private EditText poleNumerKursu;
    private TextView wynik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this, SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT));
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        poleImieNazwisko = findViewById(R.id.imieNazwisko);
        poleNumerKursu = findViewById(R.id.numerKursu);
        wynik = findViewById(R.id.wynik);
        Button przyciskZapisz = findViewById(R.id.przyciskZapisz);

        TextView naglowek = findViewById(R.id.naglowek);
        naglowek.setText(getString(R.string.liczba_kursow, kursy.length));
        wypelnijListeKursow();

        przyciskZapisz.setOnClickListener(v -> zapiszDoKursu());
    }

    private void wypelnijListeKursow() {
        LinearLayout listaKursow = findViewById(R.id.listaKursow);
        int odstep = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());

        for (int i = 0; i < kursy.length; i++) {
            TextView pozycja = new TextView(this);
            pozycja.setText(getString(R.string.pozycja_listy, i + 1, kursy[i]));
            pozycja.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            pozycja.setTextColor(Color.parseColor("#212529"));
            pozycja.setPadding(odstep * 4, odstep, 0, odstep);
            listaKursow.addView(pozycja);
        }
    }

    private void zapiszDoKursu() {
        String imieNazwisko = poleImieNazwisko.getText().toString().trim();
        String kurs = znajdzKurs(poleNumerKursu.getText().toString().trim());

        Log.i(TAG, imieNazwisko);
        if (kurs != null) {
            Log.i(TAG, kurs);
        } else {
            Log.i(TAG, getString(R.string.nieprawidlowy_numer));
        }

        String opisKursu = kurs != null ? kurs : getString(R.string.nieprawidlowy_numer);
        wynik.setText(getString(R.string.wynik_zapisu, imieNazwisko, opisKursu));
        wynik.setBackgroundResource(kurs != null ? R.drawable.alert_sukces : R.drawable.alert_blad);
        wynik.setTextColor(Color.parseColor(kurs != null ? "#0F5132" : "#842029"));
        wynik.setVisibility(View.VISIBLE);
    }

    private String znajdzKurs(String numerTekst) {
        try {
            int numer = Integer.parseInt(numerTekst);
            if (numer >= 1 && numer <= kursy.length) {
                return kursy[numer - 1];
            }
        } catch (NumberFormatException e) {
            Log.w(TAG, "Niepoprawny numer: " + numerTekst);
        }
        return null;
    }
}
