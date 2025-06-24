package com.example.atividade_avaliativa; // IMPORTANTE: Substitua pelo seu nome de pacote real

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    Button buttonVoltarCadastro;
    EditText editTextNovoUsuarioCadastro;
    EditText editTextSenhaCadastro;
    EditText editTextConfirmarSenhaCadastro;
    Button buttonRealizarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Certifique-se que o nome do arquivo XML está correto (ex: R.layout.cadastro)
        // Vou usar R.layout.cadastro como exemplo, ajuste se necessário.
        setContentView(R.layout.cadastro);

        // Inicializar as Views
        buttonVoltarCadastro = findViewById(R.id.buttonVoltar); // Usando o ID do XML: buttonVoltar
        editTextNovoUsuarioCadastro = findViewById(R.id.editTextNovoUsuario); // Usando o ID do XML: editTextNovoUsuario
        editTextSenhaCadastro = findViewById(R.id.editTextSenhaCadastro);
        editTextConfirmarSenhaCadastro = findViewById(R.id.editTextConfirmarSenha);
        buttonRealizarCadastro = findViewById(R.id.buttonRealizarCadastro);

        // Configurar OnClickListener para o botão Voltar
        buttonVoltarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplesmente fecha a activity atual, voltando para a anterior (MainActivity)
                finish();
            }
        });

        // Configurar OnClickListener para o botão Realizar Cadastro
        buttonRealizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novoUsuario = editTextNovoUsuarioCadastro.getText().toString().trim();
                String senha = editTextSenhaCadastro.getText().toString().trim();
                String confirmarSenha = editTextConfirmarSenhaCadastro.getText().toString().trim();

                // --- LÓGICA DE VALIDAÇÃO E CADASTRO (SIMPLIFICADA) ---
                if (novoUsuario.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else if (!senha.equals(confirmarSenha)) {
                    Toast.makeText(CadastroActivity.this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
                    editTextConfirmarSenhaCadastro.setError("As senhas não coincidem"); // Destaca o campo
                    editTextConfirmarSenhaCadastro.requestFocus(); // Foca no campo
                } else {
                    // Simulação de cadastro bem-sucedido
                    // No futuro, aqui você salvaria os dados do usuário (ex: usando Room)
                    Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso (simulado)!", Toast.LENGTH_LONG).show();

                    // Após o cadastro, você pode, por exemplo, voltar para a tela de login (MainActivity)
                    // ou ir diretamente para a tela principal do app.
                    // Vamos voltar para a MainActivity e limpar a pilha de activities para que
                    // o usuário não volte para a tela de cadastro pressionando "voltar".

                    Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                    // Flags para limpar a pilha de activities e iniciar MainActivity como nova tarefa
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish(); // Fecha a CadastroUsuarioActivity
                }
                // --- FIM DA LÓGICA DE VALIDAÇÃO E CADASTRO (SIMPLIFICADA) ---
            }
        });
    }
}