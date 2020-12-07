package br.com.bootcamp.zup.proposta.compartilhado.client;

import br.com.bootcamp.zup.proposta.cartao.associaCartao.request.CriaCartaoClientRequest;
import br.com.bootcamp.zup.proposta.cartao.associaCartao.response.CartaoClientResponse;
import br.com.bootcamp.zup.proposta.cartao.carteiraDigital.AssociaCarteiraDigitalRequestClient;
import br.com.bootcamp.zup.proposta.cartao.carteiraDigital.AssociacaoCarteiraDigitalResponseCliente;
import br.com.bootcamp.zup.proposta.cartao.viagem.request.ViagemRequestClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Validated
@FeignClient(url = "http://localhost:8888/api/cartoes",name = "cartoes")
public interface CartaoClient {


    @PostMapping
    @Valid
    CartaoClientResponse solicitaCriacaoDoCartao(@RequestBody CriaCartaoClientRequest criaCartaoClientRequest);

    @GetMapping
    @Valid
    CartaoClientResponse verificaSeCartaoFoiCriado(@RequestParam String idProposta);


    @PostMapping("/{idCartao}/bloqueios")
    void bloquearCartao(@PathVariable String idCartao, @RequestBody Map sistemaResponsavel);


    @PostMapping("/{idCartao}/avisos")
    void avisaViagem(@PathVariable String idCartao, @RequestBody ViagemRequestClient viagemRequestClient);

    @PostMapping("/{idCartao}/carteiras")
    AssociacaoCarteiraDigitalResponseCliente associaCarteiraDigital(@PathVariable String idCartao, @RequestBody AssociaCarteiraDigitalRequestClient associacaoCarteiraDigitalRequest);


}
