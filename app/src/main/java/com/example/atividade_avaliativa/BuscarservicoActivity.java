package com.example.atividade_avaliativa; // IMPORTANTE: Substitua pelo seu nome de pacote real

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BuscarservicoActivity extends AppCompatActivity {

    Button buttonVoltarBuscar;
    Button buttonSairBuscar;
    LinearLayout linearLayoutServicosContainer;
    TextView textViewNenhumServicoBuscar; // Para mensagens como "Nenhum serviço"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Certifique-se que o nome do arquivo XML está correto (ex: R.layout.buscarservico)
        setContentView(R.layout.buscarservico);

        // Inicializar as Views
        buttonVoltarBuscar = findViewById(R.id.buttonVoltarBuscar);
        buttonSairBuscar = findViewById(R.id.buttonSairBuscar);
        linearLayoutServicosContainer = findViewById(R.id.linearLayoutServicosContainer);
        // Supondo que você tenha um TextView no seu buscarservico.xml para mensagens
        // com o id textViewNenhumServico (se não, você pode adicionar ou remover esta linha)
        textViewNenhumServicoBuscar = findViewById(R.id.textViewNenhumServico);


        // Configurar OnClickListener para o botão Voltar
        buttonVoltarBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplesmente fecha a activity atual, voltando para a anterior (TelaPrincipalAppActivity)
                finish();
            }
        });

        // Configurar OnClickListener para o botão Sair
        buttonSairBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar de volta para a MainActivity (tela de login) e limpar a pilha
                Intent intent = new Intent(BuscarservicoActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finishAffinity(); // Fecha esta e todas as activities da tarefa atual
            }
        });

        // Carregar e exibir os serviços (Placeholder por enquanto)
        carregarServicosDisponiveis();
    }

    private void carregarServicosDisponiveis() {
        // --- LÓGICA PARA CARREGAR SERVIÇOS DO BANCO DE DADOS (FUTURO) ---
        // Por enquanto, vamos simular alguns dados e adicionar botões de exemplo
        // ou mostrar uma mensagem se estiver vazio.

        // Limpar quaisquer views de exemplo do XML ou de carregamentos anteriores
        linearLayoutServicosContainer.removeAllViews();

        // Exemplo de dados (no futuro, viriam do banco de dados)
        String[] servicosExemplo = {"Serviço de Encanador", "Aula de Violão", "Consultoria de TI", "Pintura Residencial"};
        // String[] servicosExemplo = {}; // Teste com lista vazia

        if (servicosExemplo.length == 0) {
            if (textViewNenhumServicoBuscar != null) {
                textViewNenhumServicoBuscar.setText("Nenhum serviço disponível no momento.");
                textViewNenhumServicoBuscar.setVisibility(View.VISIBLE);
            } else {
                // Se não houver um TextView dedicado, podemos adicionar um programaticamente
                TextView msg = new TextView(this);
                msg.setText("Nenhum serviço disponível no momento.");
                msg.setPadding(20, 20, 20, 20);
                msg.setGravity(Gravity.CENTER);
                linearLayoutServicosContainer.addView(msg);
            }
        } else {
            if (textViewNenhumServicoBuscar != null) {
                textViewNenhumServicoBuscar.setVisibility(View.GONE);
            }
            for (String nomeServico : servicosExemplo) {
                Button botaoServico = new Button(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0,0,0, 16); // Adiciona uma margem inferior
                botaoServico.setLayoutParams(params);
                botaoServico.setText(nomeServico);
                botaoServico.setPadding(32,32,32,32); // Adiciona padding interno
                // botaoServico.setBackgroundColor(getResources().getColor(android.R.color.darker_gray)); // Exemplo de cor

                final String servicoSelecionado = nomeServico; // Precisa ser final para usar no listener
                botaoServico.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Ação ao clicar em um serviço específico
                        // Ex: Abrir uma tela de detalhes do serviço
                        Toast.makeText(BuscarservicoActivity.this, "Selecionado: " + servicoSelecionado, Toast.LENGTH_SHORT).show();
                        // Intent intent = new Intent(BuscarservicoActivity.this, DetalhesServicoActivity.class);
                        // intent.putExtra("NOME_SERVICO", servicoSelecionado);
                        // startActivity(intent);
                    }
                });
                linearLayoutServicosContainer.addView(botaoServico);
            }
        }
        // --- FIM DA LÓGICA DE CARREGAMENTO DE SERVIÇOS ---
    }
}