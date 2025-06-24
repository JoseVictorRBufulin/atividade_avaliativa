package com.example.atividade_avaliativa; // <<-- Certifique-se que o pacote está correto

import java.io.Serializable;

public class Servico implements Serializable { // <<-- Precisa implementar Serializable

    private String nomeEmpregador;
    private String tipoServico;
    private String localServico;
    private String contatoServico;
    private String descricaoServico;
    private String dataServico;
    // Removi o id, pois não estamos usando banco de dados no momento

    // Construtor
    public Servico(String nomeEmpregador, String tipoServico, String localServico,
                   String contatoServico, String descricaoServico, String dataServico) {
        this.nomeEmpregador = nomeEmpregador;
        this.tipoServico = tipoServico;
        this.localServico = localServico;
        this.contatoServico = contatoServico;
        this.descricaoServico = descricaoServico;
        this.dataServico = dataServico;
    }

    // Getters (e Setters se necessário, mas para exibir dados, Getters são essenciais)
    public String getNomeEmpregador() {
        return nomeEmpregador;
    }

    public void setNomeEmpregador(String nomeEmpregador) {
        this.nomeEmpregador = nomeEmpregador;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getLocalServico() {
        return localServico;
    }

    public void setLocalServico(String localServico) {
        this.localServico = localServico;
    }

    public String getContatoServico() {
        return contatoServico;
    }

    public void setContatoServico(String contatoServico) {
        this.contatoServico = contatoServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public String getDataServico() {
        return dataServico;
    }

    public void setDataServico(String dataServico) {
        this.dataServico = dataServico;
    }

    // (Opcional) toString para facilitar a depuração
    @Override
    public String toString() {
        return "Servico{" +
                "tipoServico='" + tipoServico + '\'' +
                ", nomeEmpregador='" + nomeEmpregador + '\'' +
                '}';
    }
}