package negocio;
import java.util.ArrayList;

import negocio.locais.Viagem;
import negocio.modelos.FormaDePagamento;
import negocio.modelos.Pessoa;

public class Cliente extends Pessoa{
    private final ArrayList<Viagem> viagensHistorico; 
    private final ArrayList<FormaDePagamento> formasPagamento;//criar toString para cada forma de pagamento
    
    public Cliente(String nome, String telefone, int IDPessoa, String dataNascimento, ArrayList<FormaDePagamento> formasPagamento, String senhaAcesso){
        super(nome, telefone, IDPessoa, dataNascimento, senhaAcesso);
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


