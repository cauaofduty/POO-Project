package negocio;

import java.io.Serializable;
import java.util.ArrayList;

import negocio.modelos.FormaDePagamento;

public class Pix implements FormaDePagamento, Serializable {
    private static final long serialVersionUID = 302L;
    private final ArrayList<String> chaves;//PESSOA DEVE TER MAIS DE UMA CHAVE PIX! mnas como fazer do jeito certo?
    private double saldoPix;//saldo deve ser uma classe??  // Creio que não, a complexidade não é tanta (Hugo)
    
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
        if (!this.chaves.contains(chave)) {  // Adicionei a verificação para evitar chaves duplicadas (Hugo)
            this.chaves.add(chave);
        }
    }
    public ArrayList<String> getChaves(){
        return this.chaves;
    }
    
    @Override
    public void pagar(double valor) throws Exception{
        if(valor > saldoPix){
            throw new PagamentoNegadoException("Saldo insuficiente! Tente outra forma de pagamento");
        }
        this.saldoPix -= valor;//o recebimento sera feito ao motorista e issoo
        //tipo isso aqui
}
}
