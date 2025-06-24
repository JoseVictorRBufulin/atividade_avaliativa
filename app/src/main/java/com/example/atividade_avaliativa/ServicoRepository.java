package com.example.atividade_avaliativa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepository {
    private static volatile ServicoRepository instance;
    private final MutableLiveData<List<Servico>> listaDeServicosLiveData = new MutableLiveData<>();
    private final ArrayList<Servico> servicosEmMemoria = new ArrayList<>();

    private ServicoRepository() {
        // Construtor privado para prevenir instanciação direta
        listaDeServicosLiveData.setValue(new ArrayList<>(servicosEmMemoria)); // Publica uma cópia inicial
    }

    public static ServicoRepository getInstance() {
        if (instance == null) {
            synchronized (ServicoRepository.class) {
                if (instance == null) {
                    instance = new ServicoRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<List<Servico>> getListaDeServicos() {
        return listaDeServicosLiveData;
    }

    public void adicionarServico(Servico servico) {
        servicosEmMemoria.add(servico);
        // Notifica os observadores com uma nova lista (imutabilidade para LiveData)
        listaDeServicosLiveData.postValue(new ArrayList<>(servicosEmMemoria));
    }

    public void limparTodosOsServicos() {
        servicosEmMemoria.clear();
        listaDeServicosLiveData.postValue(new ArrayList<>(servicosEmMemoria));
    }

    // Método para obter a lista atual diretamente se necessário (cuidado com modificações externas)
    public List<Servico> getServicosSnapshot() {
        return new ArrayList<>(servicosEmMemoria);
    }
}