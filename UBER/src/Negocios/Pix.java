package Negocios;

public class Pix implements FormaDePagamento {
    String chave;
    double saldoPix;

    @Override
    public void pagar(double valor) throws Exception{
        if(valor > saldoPix){
            throw new Exception("saldo insuficiente! tente outra forma de pagamento");
        }
        this.saldoPix -= valor;//o recebimento sera feito ao motorista
}
}
