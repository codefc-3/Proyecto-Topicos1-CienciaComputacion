package app.mycompany.warnapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createAlert extends AppCompatActivity {

EditText txtUbicacion,txtAlerta;
Button btnCrearAlerta;

private DatabaseReference AlertaDB;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);

        AlertaDB = FirebaseDatabase.getInstance().getReference("Alerta");
        txtUbicacion = (EditText) findViewById(R.id.txt_ubicacion);
        txtAlerta = (EditText) findViewById(R.id.txt_alerta);
        btnCrearAlerta = (Button) findViewById(R.id.btn_create_alert);
    }

    public void registrarAlerta(){
        String ubicacion = txtUbicacion.getText().toString();
        String alerta = txtAlerta.getText().toString();

        if(!TextUtils.isEmpty(alerta)){
            String id = AlertaDB.push().getKey();
            //faltaria agregar el id del usuario
            Alerta alertaP = new Alerta(id,ubicacion,alerta);

            AlertaDB.child(id).setValue(alertaP);

            Toast.makeText(this,"Alerta creada", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Error - Alerta no creada", Toast.LENGTH_LONG).show();
        }
    }

    public void Onclick(View view){
        registrarAlerta();
    }

}
