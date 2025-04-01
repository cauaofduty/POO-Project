package negocio.demaisEntidades;

import java.util.ArrayList;

public class Pix implements FormaDePagamento {
    private ArrayList<String> chaves;//PESSOA DEVE TER MAIS DE UMA CHAVE PIX! mnas como fazer do jeito certo?
    private double saldoPix;//saldo deve ser uma classe??

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
    public void pagar(double valor) throws Exception{
        if(valor > saldoPix){
            throw new Exception("saldo insuficiente! tente outra forma de pagamento");
        }
        this.saldoPix -= valor;//o recebimento sera feito ao motorista e issoo
        //tipo isso aqui
}
}
