package br.com.bootcamp.zup.proposta.proposta;

import br.com.bootcamp.zup.proposta.compartilhado.annotation.CpfOrCnpj;
import br.com.bootcamp.zup.proposta.proposta.nova.enumerate.StatusEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        this.documento = encriptaDocumento(documento);
    }

    public String encriptaDocumento(String documento){
        return new BCryptPasswordEncoder().encode(documento);
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
