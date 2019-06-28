package app.mycompany.warnapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class comments extends AppCompatActivity {

    private TextView mTextViewData;
    private FirebaseFirestore db;
    private CollectionReference comentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        mTextViewData = (TextView) findViewById((R.id.txt_viewdata));
        db = FirebaseFirestore.getInstance();

        comentarios = db.collection("alertas");

        db.collection("alertas").orderBy("titulo")//.orderBy("fecha")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    private static final String TAG = "AA" ;

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String data = "";
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String username = document.get("username").toString();
                                String titulo = document.get("titulo").toString();
                                String alerta = document.get("alerta").toString();

                                data += "Usuario: "+username+"\nTitulo: "+titulo+"\n"+alerta+"\n\n";

                                mTextViewData.setText(data);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    public void agregarComentario(){
        //String
    }
}
