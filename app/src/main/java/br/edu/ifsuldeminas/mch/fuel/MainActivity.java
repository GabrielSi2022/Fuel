package br.edu.ifsuldeminas.mch.fuel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textImputEditTextEtanol;
    private TextInputEditText textImputEditTextGas;
    private Button buttonCalcular;
    private ImageView imageViewMelhorOpcao;
    private TextView textViewMelhorOpcao;
    private ImageView imageViewShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textImputEditTextEtanol = findViewById(R.id.textInputEditTextEtanol);
        textImputEditTextGas = findViewById((R.id.textInputEditTextGas));
        buttonCalcular = findViewById(R.id.buttonCalcular);
        imageViewMelhorOpcao = findViewById(R.id.imageViewFuel);
        textViewMelhorOpcao = findViewById(R.id.textViewMessage);
        imageViewShare = findViewById(R.id.imageViewShare);


    }
}