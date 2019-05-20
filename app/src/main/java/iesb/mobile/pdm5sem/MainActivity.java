package iesb.mobile.pdm5sem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singUP();
        reset();

    }

    @Override
    protected void onStart() {
        super.onStart();
        singIn();
    }

    public void singUP() {
        final EditText cadEmail = findViewById(R.id.cadEmail);
        final EditText cadSenha = findViewById(R.id.cadSenha);
        final Button btCad = findViewById(R.id.btCad);
        final TextView seta1 = findViewById(R.id.seta1);
        final TextView cadtx = findViewById(R.id.cadtx);
        final ConstraintLayout const1 = findViewById(R.id.const1);

        const1.setVisibility(View.GONE);

        seta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seta1.getRotation() == 0) {
                    seta1.setRotation(270);
                    const1.setVisibility(View.VISIBLE);

                } else {
                    seta1.setRotation(0);
                    const1.setVisibility(View.GONE);
                }
            }
        });

        cadtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seta1.getRotation() == 0) {
                    seta1.setRotation(270);
                    const1.setVisibility(View.VISIBLE);

                } else {
                    seta1.setRotation(0);
                    const1.setVisibility(View.GONE);
                }
            }
        });

        btCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((cadEmail.getText().toString()).equals("") || (cadSenha.getText().toString()).equals("")) {
                    Toast.makeText(getApplicationContext(),"Obs: Os campos não podem ser nulos", Toast.LENGTH_LONG).show();
                }
                else {
                    cadastrar(cadEmail.getText().toString(), cadSenha.getText().toString());
                }
            }
        });
    }

    public void cadastrar(String email, String senha) {
        final EditText cadEmail = findViewById(R.id.cadEmail);
        final EditText cadSenha = findViewById(R.id.cadSenha);
        final TextView seta1 = findViewById(R.id.seta1);
        final ConstraintLayout const1 = findViewById(R.id.const1);
        final EditText edEmail = findViewById(R.id.emailIN);
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Usuário criado", Toast.LENGTH_SHORT).show();
                            edEmail.setText(cadEmail.getText());
                            cadEmail.setText("");
                            cadSenha.setText("");
                            seta1.setRotation(0);
                            const1.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(getApplicationContext(), "Falha ao realizar o cadastro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
                    logar(edEmail.getText().toString(), edSenha.getText().toString());
                }
            }
        });
    }

    private void logar(String email, String senha) {
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent go = new Intent("homeAcao");
                            startActivity(go);
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void reset() {
        final Button sendEmail = findViewById(R.id.sendEmail);
        final EditText recEmail = findViewById(R.id.recEmail);
        final TextView seta2 = findViewById(R.id.seta2);
        final TextView esqTx = findViewById(R.id.esqTx);
        final ConstraintLayout const2 = findViewById(R.id.const2);

        const2.setVisibility(View.GONE);

        seta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seta2.getRotation() == 0) {
                    seta2.setRotation(270);
                    const2.setVisibility(View.VISIBLE);
                } else {
                    seta2.setRotation(0);
                    const2.setVisibility(View.GONE);
                }
            }
        });

        esqTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seta2.getRotation() == 0) {
                    seta2.setRotation(270);
                    const2.setVisibility(View.VISIBLE);
                } else {
                    seta2.setRotation(0);
                    const2.setVisibility(View.GONE);
                }
            }
        });

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((recEmail.getText()+"").equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira um e-mail", Toast.LENGTH_LONG).show();
                }
                else {
                    resetPassword(recEmail.getText()+"");
                }
            }
        });
    }

    public void resetPassword(String email) {
        final TextView seta2 = findViewById(R.id.seta2);
        final ConstraintLayout const2 = findViewById(R.id.const2);
        final EditText recEmail = findViewById(R.id.recEmail);
        final EditText edEmail = findViewById(R.id.emailIN);
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Foi encaminhado um e-mail para alterar a senha!", Toast.LENGTH_LONG).show();
                            const2.setVisibility(View.GONE);
                            edEmail.setText(recEmail.getText());
                            recEmail.setText("");
                            seta2.setRotation(0);
                        } else {
                            Toast.makeText(getApplicationContext(),"Falha ao encaminhar o e-mail para alterar a senha!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
