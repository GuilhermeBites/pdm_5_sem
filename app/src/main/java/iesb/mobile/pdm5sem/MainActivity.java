package iesb.mobile.pdm5sem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        String email = findViewById(R.id.usuarioTxt).toString();
        String senha = findViewById(R.id.senhaTxt).toString();
        logar(email,senha);

        mAuth.createUserWithEmailAndPassword("marcelo.dfx@gmail.com", "marcelo")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Usuário criado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Usuário já criado!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void logar(final String email, final String senha) {
        Button btac = findViewById(R.id.btacessar);
        btac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Usuário Logado", Toast.LENGTH_SHORT).show();
                                    Intent go = new Intent("homeAcao");
                                    startActivity(go);

                                } else {
                                    Toast.makeText(MainActivity.this, "Falha de acesso", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
