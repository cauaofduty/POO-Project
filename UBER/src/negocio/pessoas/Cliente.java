package negocio.pessoas;

import java.util.ArrayList;
import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Local;

public class Cliente extends Pessoa{
    private static final long serialVersionUID = 100001L;
    private final ArrayList<FormaDePagamento> formasPagamento;
    

    //construtor simulacao
    public Cliente(ArrayList<FormaDePagamento> formasPagamento, String IDPessoa, int idade, Local localAtual, String nome) {
        super(IDPessoa, idade, localAtual, nome);
        this.formasPagamento = formasPagamento;
    }


    public Cliente(ArrayList<FormaDePagamento> formasPagamento, String IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso) {
        super(IDPessoa, idade, localAtual, nome, senhaAcesso);
        this.formasPagamento = formasPagamento;
    }
    
    
    public ArrayList<FormaDePagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void adicionarFormaPagamento(FormaDePagamento formaPagamento) {
        this.formasPagamento.add(formaPagamento);
    }

    @Override
    public String toString() {
    return super.toString() +
        " | Formas de pagamento: " + listarFormasPagamento();
    }

    private String listarFormasPagamento() {
    if (formasPagamento.isEmpty()) {
        return "Nenhuma";
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < formasPagamento.size(); i++) {
        sb.append(formasPagamento.get(i).toString());
        if (i < formasPagamento.size() - 1) {
            sb.append(", ");
        }
    }
    return sb.toString();
    }

}


