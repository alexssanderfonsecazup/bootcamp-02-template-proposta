package br.com.bootcamp.zup.proposta.proposta;

import br.com.bootcamp.zup.proposta.cartao.Cartao;
import br.com.bootcamp.zup.proposta.proposta.nova.enumerate.StatusEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "documento_unico", columnNames = {"documento"})})
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

    private String documento;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToOne
    private Cartao cartao;

    /**
     * Construtor default, deve ser usado
     * apenas pelo hibernate
     */
    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotBlank String nome, @NotBlank String endereco,
                    @NotNull BigDecimal salario, String documento) {
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.documento = encriptaDocumento(documento);
    }

    public void defineStatus(String status) {
        if (("SEM_RESTRICAO").equals(status)) {
            this.status = StatusEnum.ELEGIVEL_SEM_CARTAO;
            return;
        } else if (("COM_RESTRICAO").equals(status)) {
            this.status = StatusEnum.NAO_ELEGIVEL;
            return;
        }
        throw new IllegalStateException("Status recebido inesperado");
    }

    public String encriptaDocumento(String documento) {
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

    public BigDecimal getSalario() {
        return salario;
    }


    public Cartao getCartao() {
        return cartao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
