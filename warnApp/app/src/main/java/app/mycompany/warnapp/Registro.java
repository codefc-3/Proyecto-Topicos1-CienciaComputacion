package app.mycompany.warnapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.Logger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private EditText TextUsername;
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        TextUsername = (EditText) findViewById(R.id.EditText_signUp_username);
        TextEmail = (EditText) findViewById(R.id.EditText_signUp_email);
        TextPassword = (EditText) findViewById(R.id.EditText_signUp_password);

        btnRegistrar = (Button) findViewById(R.id.button_signUp);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);
        //btnRegistrar.setOnClickListener(this);
    }

    private void registrarUsuario(){
        final String username = TextUsername.getText().toString().trim();
        String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Agregar nombre de usuario",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Agregar email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Agregar contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro de usuario...");
        progressDialog.show();

        Map<String,Object> datosuser = new HashMap<>();
        datosuser.put("username",username);
        datosuser.put("email",email);

        //se crea el usuario con correo y contraseña
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registro.this,"Se ha registrado correctamente",Toast.LENGTH_LONG).show();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(username)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Registro.this,"Se ha cargado sus datos correctamente",Toast.LENGTH_LONG).show();
                                                }
                                                else{
                                                    Toast.makeText(Registro.this,"No ERRRORRR",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                //progressDialog.dismiss();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        Intent i = new Intent(Registro.this,MainActivity.class);
                                        startActivity(i);
                                    }
                                }, 3000);
                            } else {
                                Toast.makeText(Registro.this, "No está logueado", Toast.LENGTH_LONG).show();
                            }

                        }
                        else{
                            Toast.makeText(Registro.this,"No se pudo registrar",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
            }
        }, 3000);
    }

    @Override
    public void onClick(View view) {
        registrarUsuario();
    }
}
