package com.example.rojas_pa2_memoriaexterna;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText nombreape,dni,it1,it2,it3,pre1,pre2,pre3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombreape = (EditText) findViewById(R.id.nombape);
        dni = (EditText) findViewById(R.id.dni);
        it1 = (EditText) findViewById(R.id.item1);
        it2 = (EditText) findViewById(R.id.item2);
        it3 = (EditText) findViewById(R.id.item3);
        pre1 = (EditText) findViewById(R.id.precio1);
        pre2 = (EditText) findViewById(R.id.precio2);
        pre3 = (EditText) findViewById(R.id.precio3);

        String[] archivos = fileList();
        if (valida(archivos, "comprobante.txt")) {
            String texto = "";
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("comprobante.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                while (linea != null) {
                    texto = texto + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                nombreape.setText(texto);
                dni.setText(texto);
                it1.setText(texto);
                it2.setText(texto);
                it3.setText(texto);
                pre1.setText(texto);
                pre2.setText(texto);
                pre3.setText(texto);
            } catch (IOException e) {
                Toast.makeText(this, "Error de lectura en el archivo externo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean valida(String[] archivos, String buscarArchivo) {
        for (int f = 0; f < archivos.length; f++) {
            if (buscarArchivo.equals(archivos[f])) {
                return true;
            }
        }
        return false;
    }


    public void grabar (View view)
    {
        try{
            /*LocalDateTime hoy = LocalDateTime.now();
            Date now = Date.from(Instant.now());*/
            DateFormat today = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss z");
            String fechahoy = today.format(new Date());
            double a = Double.parseDouble(pre1.getText().toString());
            double b = Double.parseDouble(pre2.getText().toString());
            double c = Double.parseDouble(pre3.getText().toString());
            double total = a+b+c;
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("comprobante.txt", Activity.MODE_PRIVATE));
            archivo.write("**************************************" + "\n");
            archivo.write("            RESTAURANTE SAC             " + "\n");
            archivo.write("            RUC 23456789012             " + "\n");
            archivo.write("            RUC 23456789012             " + "\n");
            archivo.write( "       " + fechahoy + "\n");

            archivo.write("  Cliente:     " + nombreape.getText().toString() + "\n");
            archivo.write("  DNI:         " + dni.getText().toString() + "\n");
            archivo.write("  " + it1.getText().toString() + "       " + "S/. " + pre1.getText().toString() + "\n");
            archivo.write("  " + it2.getText().toString() + "       " + "S/. " + pre2.getText().toString() + "\n");
            archivo.write("  " + it3.getText().toString() + "       " + "S/. " + pre3.getText().toString() + "\n");
            archivo.write("  TOTAL:                 " + "S/. " + String.format("%.2f",total) + "\n");
            archivo.write("**************************************" + "\n");

            archivo.flush();
            archivo.close();
            Toast.makeText(this,"Comprobante Enviado",Toast.LENGTH_LONG).show();
        }
        catch(IOException e)
        {
            Toast.makeText(this,"El texto no se pudo guardar",Toast.LENGTH_LONG).show();

        }
        //finish();
    }

    }
