package app.mycompany.warnapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class createAlert extends AppCompatActivity {

    EditText txtTitulo,txtAlerta;
    Button btnCrearAlerta;
    CheckBox cbAnonimo;

    private DatabaseReference AlertaDB;
    private FirebaseFirestore db;
    FirebaseUser mUser;

    private ProgressDialog progressDialog;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);

        AlertaDB = FirebaseDatabase.getInstance().getReference();
        txtTitulo = (EditText) findViewById(R.id.txt_ubicacion);
        txtAlerta = (EditText) findViewById(R.id.txt_alerta);
        btnCrearAlerta = (Button) findViewById(R.id.btn_create_alert);
        cbAnonimo = (CheckBox) findViewById(R.id.mostrar_nombre);

        db = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        progressDialog = new ProgressDialog(this);
    }

    public void registrarAlerta(){
        String titulo = txtTitulo.getText().toString();
        String alerta = txtAlerta.getText().toString();
        Boolean noanonimo = cbAnonimo.isChecked();

        String username = mUser.getDisplayName();

        /*
        if(!TextUtils.isEmpty(alerta)){

            String id = AlertaDB.push().getKey();
            //faltaria agregar el id del usuario
            Alerta alertaP = new Alerta(id,titulo,alerta);

            AlertaDB.child(id).setValue(alertaP);

            Toast.makeText(this,"Alerta creada", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Error - Alerta no creada", Toast.LENGTH_LONG).show();
        }
        */
        //progressDialog.setMessage("Realizando alerta...");
        //progressDialog.show();

        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        //Date date = new Date();

        //String fecha = dateFormat.format(date);

        Map<String, Object> datosalerta = new HashMap<>();
        if(!noanonimo){
            datosalerta.put("username","anónimo");
        }else{
            datosalerta.put("username",username);  //aqui va el nombre
        }
        datosalerta.put("titulo", titulo);
        datosalerta.put("alerta",alerta);
        //datosalerta.put("fecha",fecha);

        db.collection("alertas")
                .add(datosalerta)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                            }
                        }, 3000);
                        Toast.makeText(createAlert.this,"Alerta creada",Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(createAlert.this,"Error: Alerta no creada",Toast.LENGTH_LONG).show();
                    }
                });
        //progressDialog.dismiss();
    }

    public void Onclick(View view){
        registrarAlerta();
    }

}
