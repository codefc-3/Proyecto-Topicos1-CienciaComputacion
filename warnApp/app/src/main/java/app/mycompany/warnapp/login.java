package app.mycompany.warnapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void register(View view){
        Intent i = new Intent(this,Registro.class);
        startActivity(i);
    }
    public void panel(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
