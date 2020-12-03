package br.com.bootcamp.zup.proposta.proposta;

import br.com.bootcamp.zup.proposta.compartilhado.annotation.CpfOrCnpj;
import br.com.bootcamp.zup.proposta.proposta.nova.enumerate.StatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "documento_unico", columnNames = {"documento"})})
public class Proposta {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    private BigDecimal salario;

    @CpfOrCnpj
    private String documento;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private String numeroCartao;

    /**
     * Construtor default, deve ser usado
     * apenas pelo hibernate
     */
    @Deprecated
    public Proposta(){}

    public Proposta(@NotBlank String nome, @NotBlank String endereco,
                    @NotNull BigDecimal salario, String documento) {
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.documento = documento;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }


}
