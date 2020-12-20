package br.com.bootcamp.zup.proposta.proposta.nova.request;

import br.com.bootcamp.zup.proposta.compartilhado.annotation.CpfOrCnpj;
import br.com.bootcamp.zup.proposta.proposta.Proposta;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NovaPropostaRequest {

    @NotBlank
    private final String nome;

    @NotBlank
    private final String endereco;

    @NotNull
    @PositiveOrZero
    private final BigDecimal salario;

    @CpfOrCnpj
    private final String documento;

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
