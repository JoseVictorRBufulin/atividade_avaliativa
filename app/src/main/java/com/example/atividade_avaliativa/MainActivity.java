package com.example.atividade_avaliativa; // IMPORTANTE: Substitua pelo seu nome de pacote real

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Para feedback simples

// Se você renomeou sua activity principal para algo como TelaprincipalActivity,
// o nome da classe aqui deve corresponder.
public class MainActivity extends AppCompatActivity {

    EditText editTextUsuario;
    EditText editTextSenha;
    Button buttonEntrar;
    TextView textViewCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // O nome do arquivo XML deve corresponder ao que está no seu projeto.
        // Se você compartilhou activity_main.xml, então R.layout.activity_main está correto.
        setContentView(R.layout.activity_main);

        // Inicializar as Views
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonEntrar = findViewById(R.id.buttonEntrar);
        textViewCadastrar = findViewById(R.id.textViewCadastrar);

        // Configurar OnClickListener para o botão Entrar
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsuario.getText().toString().trim();
                String senha = editTextSenha.getText().toString().trim();

                // --- LÓGICA DE LOGIN (SIMPLIFICADA) ---
                // No futuro, você substituirá isso por uma verificação real
                // (por exemplo, contra um banco de dados ou um backend)
                if (usuario.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha usuário e senha.", Toast.LENGTH_SHORT).show();
                } else {
                    // Simulação de login bem-sucedido
                    Toast.makeText(MainActivity.this, "Login bem-sucedido (simulado)!", Toast.LENGTH_SHORT).show();

                    // Navegar para a tela principal do app
                    // Certifique-se de que TelaPrincipalAppActivity exista ou substitua pelo nome correto
                    Intent intent = new Intent(MainActivity.this, TelaprincipalActivity.class);
                    startActivity(intent);
                    // Opcionalmente, feche a MainActivity para que o usuário não possa voltar para ela com o botão "voltar" do Android
                    // finish();
                }
                // --- FIM DA LÓGICA DE LOGIN (SIMPLIFICADA) ---
            }
        });

        // Configurar OnClickListener para o TextView Cadastrar
        textViewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de cadastro de usuário
                // Certifique-se de que CadastroUsuarioActivity exista ou substitua pelo nome correto
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}