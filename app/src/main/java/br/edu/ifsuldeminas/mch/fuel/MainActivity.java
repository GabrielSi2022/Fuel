package br.edu.ifsuldeminas.mch.fuel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextEtanol;
    private TextInputEditText textInputEditTextGas;
    private TextView textViewMessage;

    private Button buttonCalcular;

    private ImageView imageViewFuel;
    private ImageView imageViewshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputEditTextEtanol = findViewById(R.id.textInputEditTextEtanol);
        textInputEditTextGas = findViewById(R.id.textInputEditTextGas);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        imageViewFuel = findViewById(R.id.imageViewFuel);
        textViewMessage = findViewById(R.id.textViewMessage);
        imageViewshare = findViewById(R.id.imageViewShare);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorEtanolString = textInputEditTextEtanol.getText().toString();
                String valorGasString = textInputEditTextGas.getText().toString();

                if (valorEtanolString.equals("")) {
                    Toast.makeText(getApplicationContext(), "Valor do etanol não pode ser vazio",
                            Toast.LENGTH_SHORT).show();

                    return;
                }
                if (valorGasString.equals("")) {
                    Toast.makeText(getApplicationContext(), "Valor da gasolina não pode ser vazio",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Double valorEtanol = Double.parseDouble(valorEtanolString);
                Double valorGas = Double.parseDouble(valorGasString);

                String mensagem = "";

                if ((valorEtanol / valorGas) >= 0.7) {

                    mensagem = "Melhor usar gasolia";
                    imageViewFuel.setImageResource(R.drawable.gas);
                } else {
                    mensagem = "Melhor usar etanol";
                    imageViewFuel.setImageResource(R.drawable.ethanol);
                }

                imageViewFuel.setVisibility(ImageView.VISIBLE);
                imageViewshare.setVisibility(ImageView.VISIBLE);

                textViewMessage.setText(mensagem);
                textViewMessage.setVisibility(TextView.VISIBLE);

            }
        });

        imageViewshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buider = new AlertDialog.Builder(MainActivity.this);
                buider.setTitle("Perços de qual Posto?");

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.alert_dialog_gas_station_view, null);
                buider.setView(layout);

                buider.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText nomePosto = layout.findViewById(R.id.editTextAlertDialogGasStationId);
                        String nomePostoStr = nomePosto.getText().toString();


                        String valorEtanolString = textInputEditTextEtanol.getText().toString();
                        String valorGasString = textInputEditTextGas.getText().toString();

                        Double valorEtanol = Double.parseDouble(valorEtanolString);
                        Double valorGas = Double.parseDouble(valorGasString);


                        Double relacao = (valorEtanol/valorGas)*100;

                        String opcao = relacao >= 70 ? "gasolina" : "etanol";

                        String mensagem = String.format("Preços no posto '%s'. Etanol R$%.2f - Gasoline R$%.2f. Melhor usar '%s', relação %.0f%%", nomePostoStr, valorEtanol, valorGas, opcao, relacao);

                       Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_LONG).show();
                    }
                });
                buider.setNegativeButton("Cancelar", null);

                AlertDialog dialog = buider.create();
                dialog.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        imageViewFuel.setVisibility(ImageView.INVISIBLE);
        textViewMessage.setVisibility(TextView.INVISIBLE);
        imageViewshare.setVisibility(ImageView.INVISIBLE);
    }
}