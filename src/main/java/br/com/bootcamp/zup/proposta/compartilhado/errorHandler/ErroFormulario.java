package br.com.bootcamp.zup.proposta.compartilhado.errorHandler;

import java.util.List;

public class ErroFormulario {

    private final List<String> mensagens;

    public ErroFormulario(List<String> mensagens) {
        this.mensagens = mensagens;
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}
