package negocio.pessoas;
import java.util.ArrayList;
import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;

public class Cliente extends Pessoa {
    private static final long serialVersionUID = 100001L;
    private final ArrayList<FormaDePagamento> formasPagamento;
    
    //construtor default| CRIAR UM QUE RECEBA SENHA
    
    
    //construtor simulacao
    public Cliente(ArrayList<FormaDePagamento> formasPagamento, String IDPessoa, int idade, Local localAtual, String nome) {
        super(IDPessoa, idade, localAtual, nome);
        this.formasPagamento = formasPagamento;
    }

    public Cliente(ArrayList<FormaDePagamento> formasPagamento, String IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso) {
        super(IDPessoa, idade, localAtual, nome, senhaAcesso);
        this.formasPagamento = formasPagamento;
    }
    
    
   

    @Override
    public ArrayList<Viagem> getViagensHistorico(){
        return viagensHistorico;
    }

    public ArrayList<FormaDePagamento> getFormasPagamento() {
        return formasPagamento;
    }

}


