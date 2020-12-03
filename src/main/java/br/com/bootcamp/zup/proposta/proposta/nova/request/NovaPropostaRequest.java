package br.com.bootcamp.zup.proposta.proposta.nova.request;

import br.com.bootcamp.zup.proposta.compartilhado.annotation.CpfOrCnpj;
import br.com.bootcamp.zup.proposta.proposta.Proposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @PositiveOrZero
    private BigDecimal salario;

    @CpfOrCnpj
    private String documento;

    public NovaPropostaRequest(@NotBlank String nome, @NotBlank String endereco,
                               @NotNull BigDecimal salario, String documento) {
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.documento = documento;
    }

    public Proposta toModel(){
        return new Proposta(nome,endereco,salario,documento);

    }
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getDocumento() {
        return documento;
    }
}
