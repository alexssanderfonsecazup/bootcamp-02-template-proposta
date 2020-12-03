package br.com.bootcamp.zup.proposta.compartilhado.client;

import br.com.bootcamp.zup.proposta.cartao.associaCartao.request.CriaCartaoRequest;
import br.com.bootcamp.zup.proposta.cartao.associaCartao.response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Validated
@FeignClient(url = "http://localhost:8888/api",name = "cartoes")
public interface CartaoClient {


    @PostMapping("/cartoes")
    @Valid
    CartaoResponse solicitaCriacaoDoCartao(@RequestBody CriaCartaoRequest criaCartaoRequest);

    @GetMapping("/cartoes")
    @Valid
    CartaoResponse verificaSeCartaoFoiCriado(@RequestParam String idProposta);





}
