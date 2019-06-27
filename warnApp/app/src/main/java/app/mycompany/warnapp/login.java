package app.mycompany.warnapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EmailPassword";

    //private TextView mStatusTextView;
    //private TextView mDetailTextView;
    private EditText TextEmail;
    private EditText TextPassword;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //mStatusTextView = findViewById(R.id.status);
        //mDetailTextView = findViewById(R.id.detail);
        TextEmail = (EditText) findViewById(R.id.EditText_username);
        TextPassword = (EditText) findViewById(R.id.EditText_password);

        progressDialog = new ProgressDialog(this);

        //findViewById(R.id.button_login).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mUser!=null){
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }

    }

    public void register(View view){
        Intent i = new Intent(this,Registro.class);
        startActivity(i);
    }

    public void panel(View view){
        String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Falta agregar email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta agregar contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        //progressDialog.setMessage("Iniciando sesión...");
        //progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Bienvenido",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user!=null){
                    //progressDialog.dismiss();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(login.this, "No está logueado", Toast.LENGTH_LONG).show();
                }
            }
        }, 3000);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        progressDialog.setMessage("Realizando registro de usuario...");
        progressDialog.show();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        /*if (!task.isSuccessful()) {
                            mStatusTextView.setText(R.string.auth_failed);
                        }*/
                        progressDialog.dismiss();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = TextEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            TextEmail.setError("Required.");
            valid = false;
        } else {
            TextEmail.setError(null);
        }

        String password = TextPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            TextPassword.setError("Required.");
            valid = false;
        } else {
            TextPassword.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        progressDialog.dismiss();
        if (user != null) {
            /*findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
            findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);

            findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());*/
        } else {
            /*mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);*/

            /*findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
            findViewById(R.id.signedInButtons).setVisibility(View.GONE);*/
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        /*if (i == R.id.button_register) {
            createAccount(TextEmail.getText().toString(), TextPassword.getText().toString());
        } else*/ if (i == R.id.button_login) {
            signIn(TextEmail.getText().toString(), TextPassword.getText().toString());
        }
        /*} else if (i == R.id.signOutButton) {
            signOut();
        } else if (i == R.id.verifyEmailButton) {
            sendEmailVerification();
        }*/
    }
}
