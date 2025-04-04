package negocio.pessoas;
import java.util.ArrayList;
import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Viagem;

public class Cliente extends Pessoa{
     
    private final ArrayList<FormaDePagamento> formasPagamento;//criar toString para cada forma de pagamento
    
    public Cliente(String nome, String telefone, int IDPessoa, int idade, ArrayList<FormaDePagamento> formasPagamento, String senhaAcesso){
        super(nome, telefone, IDPessoa, idade, senhaAcesso);
        viagensHistorico = new ArrayList<>();
        this.formasPagamento = formasPagamento;
        //adicionar aqui uma primeira forma de pagamento
    }

    public ArrayList<Viagem> getViagensHistorico(){
        return viagensHistorico;
    }

    public ArrayList<FormaDePagamento> getFormasPagamento() {
        return formasPagamento;
    }

}


