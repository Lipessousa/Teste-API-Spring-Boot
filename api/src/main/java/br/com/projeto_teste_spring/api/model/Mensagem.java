package br.com.projeto_teste_spring.api.model;

import org.springframework.stereotype.Component;

@Component
public class Mensagem {
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
