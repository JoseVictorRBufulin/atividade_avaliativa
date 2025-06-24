package com.example.atividade_avaliativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent; // Adicione esta importação
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat; // Para a data atual
import java.util.Date;            // Para a data atual
import java.util.Locale;          // Para a data atual

public class CadastrarnovoservicoActivity extends AppCompatActivity {

    public static final String EXTRA_SERVICO_CADASTRADO = "com.example.atividade_avaliativa.SERVICO_CADASTRADO";

    TextInputEditText editTextTipoServico;
    TextInputEditText editTextNomeEmpregador;
    TextInputEditText editTextLocalServico;
    TextInputEditText editTextContatoServico;
    TextInputEditText editTextDescricaoServico;
    Button buttonVoltarFormServico;
    Button buttonSalvarNovoServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrarnovoservico); // Seu XML do formulário

        editTextTipoServico = findViewById(R.id.editTextTipoServico);
        editTextNomeEmpregador = findViewById(R.id.editTextNomeEmpregador);
        editTextLocalServico = findViewById(R.id.editTextLocalServico);
        editTextContatoServico = findViewById(R.id.editTextContatoServico);
        editTextDescricaoServico = findViewById(R.id.editTextDescricaoServico);
        buttonVoltarFormServico = findViewById(R.id.buttonVoltarFormServico);
        buttonSalvarNovoServico = findViewById(R.id.buttonSalvarNovoServico);

        buttonVoltarFormServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED); // Indica que nada foi salvo
                finish();
            }
        });

        buttonSalvarNovoServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarESair();
            }
        });
    }

    private void salvarESair() {
        String tipoServico = editTextTipoServico.getText().toString().trim();
        String nomeEmpregador = editTextNomeEmpregador.getText().toString().trim();
        String localServico = editTextLocalServico.getText().toString().trim();
        String contatoServico = editTextContatoServico.getText().toString().trim();
        String descricaoServico = editTextDescricaoServico.getText().toString().trim();

        if (TextUtils.isEmpty(tipoServico)) {
            editTextTipoServico.setError("Tipo de serviço é obrigatório");
            editTextTipoServico.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(nomeEmpregador)) {
            editTextNomeEmpregador.setError("Nome do prestador é obrigatório");
            editTextNomeEmpregador.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(contatoServico)) {
            editTextContatoServico.setError("Contato é obrigatório");
            editTextContatoServico.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(descricaoServico)) {
            editTextDescricaoServico.setError("Descrição é obrigatória");
            editTextDescricaoServico.requestFocus();
            return;
        }

        // Simular data atual (você pode adicionar um campo de data se quiser)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String dataAtual = sdf.format(new Date());

        // Criar o objeto Servico
        Servico novoServico = new Servico(nomeEmpregador, tipoServico, localServico, contatoServico, descricaoServico, dataAtual);
        // Não precisamos de ID aqui, pois não estamos usando banco de dados

        // Preparar o Intent para retornar o resultado
        Intent resultadoIntent = new Intent();
        resultadoIntent.putExtra(EXTRA_SERVICO_CADASTRADO, novoServico); // Servico precisa ser Serializable
        setResult(RESULT_OK, resultadoIntent); // Indica que o resultado foi bem-sucedido

        Toast.makeText(this, "Serviço '" + tipoServico + "' pronto para ser listado!", Toast.LENGTH_LONG).show();
        finish(); // Fecha esta activity e retorna para a anterior
    }
}