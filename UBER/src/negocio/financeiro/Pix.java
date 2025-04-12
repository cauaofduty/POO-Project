package negocio.financeiro;

import java.util.ArrayList;
import java.util.Objects;
import negocio.exceptions.PagamentoNegadoException;
import negocio.pessoas.Pessoa;

public class Pix extends FormaDePagamento {
    private static final long serialVersionUID = 400003L;
    private final ArrayList<String> chaves;
    private double saldoPix;
    private final String tipo = "Pix";
    

    public Pix(String chave, double saldoPix) { //cadastra apenas a primeira chave pix
        this.chaves = new ArrayList<>();
        this.chaves.add(chave);
        this.saldoPix = saldoPix;
    }


    @Override
    public void pagar(double valor) throws Exception {
        if(valor > saldoPix){
            throw new PagamentoNegadoException("Pagamento recusado.", tipo);
        }
        this.saldoPix -= valor;
    }

    //TERMINAR
    @Override
    public void registrarPagamento(Pessoa cliente, Pessoa motorista, double valor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pix other = (Pix) obj;

        for (String chave : this.chaves) {
            if (other.chaves.contains(chave)) {
                return true; // pelo menos uma chave em comum
            }
        }
        return false; // nenhuma chave em comum
    }

    @Override
    public int hashCode() {
        return Objects.hash(chaves);
    }

    @Override
    public String toString() {
        return "Pix: " + chaves;
    }

    public void addChave(String chave) {
        if (!this.chaves.contains(chave)) {
            this.chaves.add(chave);
        }
    }

    public double getSaldoPix() {
        return this.saldoPix;
    }

    public void setSaldoPix(double saldoPix) {
        this.saldoPix = saldoPix;
    }
    
    public ArrayList<String> getChaves() {
        return this.chaves;
    }
}
