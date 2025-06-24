package com.example.atividade_avaliativa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog; // <<---- ADICIONE ESTA IMPORTAÇÃO
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastrarservicoActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_NOVO_SERVICO = 1;

    Button buttonVoltarServico;
    Button buttonSairServico;
    Button buttonAbrirFormularioNovoServico;
    LinearLayout linearLayoutContainerServicos;
    TextView textViewNenhumServico;
    ScrollView scrollViewServicos;

    private ArrayList<Servico> listaDeServicos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrarservico);

        buttonVoltarServico = findViewById(R.id.buttonVoltarServico);
        buttonSairServico = findViewById(R.id.buttonSairServico);
        buttonAbrirFormularioNovoServico = findViewById(R.id.buttonCadastrarNovoServico);
        linearLayoutContainerServicos = findViewById(R.id.linearLayoutContainerServicos);
        scrollViewServicos = findViewById(R.id.scrollViewServicos);

        // Tente encontrar o TextView pelo ID que você deveria ter adicionado ao XML:


        // Fallback se o ID não foi adicionado (menos ideal)
        if (textViewNenhumServico == null && linearLayoutContainerServicos.getChildCount() > 0 &&
                linearLayoutContainerServicos.getChildAt(0) instanceof TextView) {
            // Esta condição é para o caso do seu XML ter o TextView como primeiro filho
            // e sem ID. Recomendo fortemente adicionar o ID "textViewPlaceholderServicos" ao XML.
            textViewNenhumServico = (TextView) linearLayoutContainerServicos.getChildAt(0);
        }


        if (buttonVoltarServico != null) {
            buttonVoltarServico.setOnClickListener(v -> finish());
        }

        if (buttonSairServico != null) {
            buttonSairServico.setOnClickListener(v -> {
                Intent intent = new Intent(CadastrarservicoActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finishAffinity();
            });
        }

        if (buttonAbrirFormularioNovoServico != null) {
            buttonAbrirFormularioNovoServico.setOnClickListener(v -> {
                Intent intent = new Intent(CadastrarservicoActivity.this, CadastrarnovoservicoActivity.class);
                startActivityForResult(intent, REQUEST_CODE_NOVO_SERVICO);
            });
        }
        atualizarListaDeServicosNaTela();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_NOVO_SERVICO) {
            if (resultCode == RESULT_OK && data != null) {
                Servico servicoRecebido = (Servico) data.getSerializableExtra(CadastrarnovoservicoActivity.EXTRA_SERVICO_CADASTRADO);
                if (servicoRecebido != null) {
                    listaDeServicos.add(servicoRecebido);
                    atualizarListaDeServicosNaTela();
                    Toast.makeText(this, "Novo serviço adicionado!", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cadastro de serviço cancelado.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void atualizarListaDeServicosNaTela() {
        linearLayoutContainerServicos.removeAllViews();

        if (listaDeServicos.isEmpty()) {
            if (textViewNenhumServico != null) {
                // Se o TextView foi encontrado pelo ID (ou como primeiro filho), apenas o torne visível
                textViewNenhumServico.setVisibility(View.VISIBLE);
                // Se ele não fazia parte do layout originalmente (porque não tinha ID e o if falhou),
                // você pode precisar adicioná-lo de volta aqui se a lógica do onCreate não o pegou.
                // Mas a melhor prática é ter o ID no XML e apenas controlar a visibilidade.
                if (textViewNenhumServico.getParent() == null) { // Adiciona apenas se não estiver já no layout
                    linearLayoutContainerServicos.addView(textViewNenhumServico);
                }
            } else {
                // Fallback: se o textViewNenhumServico é nulo, crie um temporário.
                TextView msg = new TextView(this);
                msg.setText("Nenhum serviço cadastrado ainda.");
                msg.setGravity(Gravity.CENTER);
                msg.setPadding(20, 40, 20, 40);
                linearLayoutContainerServicos.addView(msg);
            }
            return;
        }

        if (textViewNenhumServico != null) {
            textViewNenhumServico.setVisibility(View.GONE);
        }

        for (Servico servico : listaDeServicos) {
            Button botaoServico = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 8, 0, 8);
            botaoServico.setLayoutParams(params);
            botaoServico.setText(servico.getTipoServico() + " - " + servico.getNomeEmpregador());
            botaoServico.setPadding(24, 24, 24, 24);
            botaoServico.setTextSize(16);
            // Aplicar um estilo de botão padrão do Material para consistência visual
            // Você pode precisar ajustar o estilo se estiver usando um tema específico
            // ou pode criar seu próprio drawable de background para os botões.
            // botaoServico.setBackgroundResource(com.google.android.material.R.drawable.abc_btn_default_mtrl_shape); // Use um estilo apropriado

            // Ação ao clicar no botão do serviço é chamar mostrarDetalhesServico
            botaoServico.setOnClickListener(v -> mostrarDetalhesServico(servico));

            linearLayoutContainerServicos.addView(botaoServico);
        }
    }

    private void mostrarDetalhesServico(Servico servico) {
        // Formata a string de detalhes para o AlertDialog
        String detalhes = "Tipo de Serviço:\n" + servico.getTipoServico() + "\n\n" +
                "Prestador/Empresa:\n" + servico.getNomeEmpregador() + "\n\n" +
                "Local/Endereço:\n" + (servico.getLocalServico() != null && !servico.getLocalServico().isEmpty() ? servico.getLocalServico() : "Não informado") + "\n\n" +
                "Telefone/Contato:\n" + servico.getContatoServico() + "\n\n" +
                "Descrição Detalhada:\n" + servico.getDescricaoServico() + "\n\n" +
                "Data do Cadastro:\n" + (servico.getDataServico() != null ? servico.getDataServico() : "N/A");

        // Cria e exibe o AlertDialog
        new AlertDialog.Builder(this)
                .setTitle("Detalhes do Serviço") // Título do diálogo
                .setMessage(detalhes)            // Corpo da mensagem com os detalhes
                .setPositiveButton("OK", (dialog, which) -> {
                    // Ação ao clicar em OK (opcional, aqui apenas fecha o diálogo)
                    dialog.dismiss();
                })
                // Você poderia adicionar um botão "Excluir" ou "Editar" aqui se quisesse
                // .setNegativeButton("Excluir", (dialog, which) -> {
                //     // Lógica para excluir o serviço da listaDeServicos
                //     // listaDeServicos.remove(servico);
                //     // atualizarListaDeServicosNaTela();
                //     // Toast.makeText(CadastrarservicoActivity.this, "Serviço excluído", Toast.LENGTH_SHORT).show();
                // })
                .show(); // Mostra o diálogo
    }
}