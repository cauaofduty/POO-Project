package negocio.financeiro;

import java.util.ArrayList;
import negocio.pessoas.Pessoa;

public class Pix implements FormaDePagamento {
    private final ArrayList<String> chaves;
    private double saldoPix;//saldo deve ser uma classe??
    private final String tipo = "Pix";
    
    public Pix(String chave, double saldoPix){//apenas cadastra a primeira chave pix
        this.chaves = new ArrayList<>();
        this.chaves.add(chave);
        this.saldoPix = saldoPix;
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
    //TERMINAR
    @Override
    public void registrarPagamento(Pessoa cliente, Pessoa motorista, double valor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
