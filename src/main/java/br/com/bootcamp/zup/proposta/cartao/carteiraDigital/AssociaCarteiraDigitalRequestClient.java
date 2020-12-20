package br.com.bootcamp.zup.proposta.cartao.carteiraDigital;

public class AssociaCarteiraDigitalRequestClient {
    private final String email;
    private final String carteira;

    public AssociaCarteiraDigitalRequestClient(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
