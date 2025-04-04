package negocio.financeiro;

import java.util.ArrayList;

public class Pix implements FormaDePagamento {
    private final ArrayList<String> chaves;//PESSOA DEVE TER MAIS DE UMA CHAVE PIX! mnas como fazer do jeito certo?
    private double saldoPix;//saldo deve ser uma classe??
    private final String tipo = "Pix";
    
    public Pix(String chave){//apenas cadastra a primeira chave pix
        this.chaves = new ArrayList<>();
        this.chaves.add(chave);
    }
    public void setSaldoPix(double saldoPix){
        this.saldoPix = saldoPix;
    }
    public double getSaldoPix(){
        return this.saldoPix;
    }
    public void addChave(String chave){
        this.chaves.add(chave);
    }
    
    public ArrayList<String> getChaves(){
        return this.chaves;
    }

    @Override
    public String getTipo(){
        return this.tipo;
    }
    @Override
    public void pagar(double valor) throws Exception{
        if(valor > saldoPix){
            throw new PagamentoNegadoException("pagamento recusado.", tipo);
        }
        this.saldoPix -= valor;//o recebimento sera feito ao motorista e isso
        //tipo isso aqui
}
}
