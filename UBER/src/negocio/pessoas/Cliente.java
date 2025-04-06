package negocio.pessoas;
import java.util.ArrayList;
import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;

public class Cliente extends Pessoa {
    private static final long serialVersionUID = 100001L;
     
    private final ArrayList<FormaDePagamento> formasPagamento;//criar toString para cada forma de pagamento
    
    //construtor default
    public Cliente(ArrayList<FormaDePagamento> formasPagamento, int IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso, ArrayList<Viagem> viagensHistorico) {
        super(IDPessoa, idade, localAtual, nome, senhaAcesso, viagensHistorico);
        this.formasPagamento = formasPagamento;
    }
    
    //construtor simulacao
    public Cliente(ArrayList<FormaDePagamento> formasPagamento, int IDPessoa, int idade, Local localAtual, String nome) {
        super(IDPessoa, idade, localAtual, nome);
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


