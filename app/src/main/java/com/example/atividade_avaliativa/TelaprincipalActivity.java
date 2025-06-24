package com.example.atividade_avaliativa; // IMPORTANTE: Substitua pelo seu nome de pacote real

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Se o nome da sua classe for diferente de TelaPrincipalAppActivity (como usamos na MainActivity),
// ajuste aqui e em qualquer lugar que a chame.
// Supondo que esta seja a tela principal APÓS o login.
public class TelaprincipalActivity extends AppCompatActivity { // Renomeei para ser consistente com o exemplo da MainActivity

    Button buttonSairPrincipal;
    Button buttonBuscarServicoPrincipal;
    Button buttonCadastrarServicoPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Certifique-se que o nome do arquivo XML está correto (ex: R.layout.telaprincipal)
        setContentView(R.layout.telaprincipal);

        // Inicializar as Views
        // Use os IDs definidos no seu telaprincipal.xml
        buttonSairPrincipal = findViewById(R.id.buttonSair);
        buttonBuscarServicoPrincipal = findViewById(R.id.buttonBuscarServico);
        buttonCadastrarServicoPrincipal = findViewById(R.id.buttonCadastrarServico);

        // Configurar OnClickListener para o botão Sair
        buttonSairPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar de volta para a MainActivity (tela de login) e limpar a pilha
                Intent intent = new Intent(TelaprincipalActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish(); // Fecha a TelaPrincipalAppActivity
            }
        });

        // Configurar OnClickListener para o botão Buscar Serviço
        buttonBuscarServicoPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de buscar serviço
                // Certifique-se de que BuscarservicoActivity exista ou substitua pelo nome correto
                Intent intent = new Intent(TelaprincipalActivity.this, BuscarservicoActivity.class);
                startActivity(intent);
            }
        });

        // Configurar OnClickListener para o botão Cadastrar Serviço
        buttonCadastrarServicoPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de cadastrar/listar serviços
                // Certifique-se de que CadastrarservicoActivity exista ou substitua pelo nome correto
                Intent intent = new Intent(TelaprincipalActivity.this, CadastrarservicoActivity.class);
                startActivity(intent);
            }
        });
    }

    // Opcional: Impedir que o usuário volte para a tela de login usando o botão "voltar" do Android
    // se esta é a tela principal após o login e a tela de login foi finalizada.
    // Se a tela de login NÃO foi finalizada (finish()), então este método não é estritamente necessário
    // para essa funcionalidade específica, mas pode ser útil para outros cenários de "sair".
    @Override
    public void onBackPressed() {
        // Para simular um "logout" ao pressionar voltar nesta tela,
        // podemos fazer o mesmo que o botão Sair.
        // Ou, se preferir o comportamento padrão de voltar (se houver algo antes na pilha),
        // chame super.onBackPressed();
        // Ou, para não fazer nada e "travar" o usuário aqui até ele usar o botão Sair:
        // // (não chame super.onBackPressed())

        // Exemplo: Sair para a tela de login ao pressionar voltar
        Intent intent = new Intent(TelaprincipalActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

        // Se você quiser o comportamento padrão de voltar:
        // super.onBackPressed();
    }
}