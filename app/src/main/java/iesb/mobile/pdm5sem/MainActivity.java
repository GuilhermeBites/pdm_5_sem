package iesb.mobile.pdm5sem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth.createUserWithEmailAndPassword("marcelo.dfx@gmail.com", "123456")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Usuário criado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuário já criado!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    @Override
    protected void onStart() {
        super.onStart();
        singIn();
    }

    public void singIn() {
        final EditText edEmail = findViewById(R.id.emailIN);
        final EditText edSenha = findViewById(R.id.senhaIN);
        final Button singIN = findViewById(R.id.singIN);

        singIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((edEmail.getText().toString()).equals("") || (edSenha.getText().toString()).equals("")) {
                    Toast.makeText(getApplicationContext(),"Obs: Os campos não podem ser nulos", Toast.LENGTH_LONG).show();
                }
                else {
                    singIN.setVisibility(View.INVISIBLE);
                    logar(edEmail.getText().toString(), edSenha.getText().toString());
                }
            }
        });
    }

    private void logar(String email, String senha) {
        final Button singIN = findViewById(R.id.singIN);
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent go = new Intent("homeAcao");
                            startActivity(go);
                        } else {
                            singIN.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
