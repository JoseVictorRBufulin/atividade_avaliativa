package com.example.atividade_avaliativa;

import android.app.Application; // Necessário se for AndroidViewModel
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel; // Ou apenas ViewModel se não precisar do contexto da App
import androidx.lifecycle.LiveData;
// import androidx.lifecycle.MutableLiveData; // Não mais necessário aqui
import java.util.List; // Mude de ArrayList para List para maior flexibilidade se o repo retornar List

public class ServicosViewModel extends AndroidViewModel { // Ou ViewModel

    private ServicoRepository repository;
    private LiveData<List<Servico>> listaDeServicosLiveData;

    public ServicosViewModel(@NonNull Application application) { // Ou construtor vazio se for ViewModel simples
        super(application);
        repository = ServicoRepository.getInstance();
        listaDeServicosLiveData = repository.getListaDeServicos();
    }

    public LiveData<List<Servico>> getListaDeServicos() {
        return listaDeServicosLiveData;
    }

    // O ViewModel agora apenas encaminha a ação para o repositório
    public void adicionarServico(Servico servico) {
        repository.adicionarServico(servico);
    }

    public void limparTodosOsServicos() {
        repository.limparTodosOsServicos();
    }
}