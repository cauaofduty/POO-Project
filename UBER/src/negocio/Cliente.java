package negocio;
import java.util.ArrayList;
import negocio.modelos.Local;
import negocio.modelos.Pessoa;
import negocio.modelos.Viagem;

public class Cliente extends Pessoa{
    private final ArrayList<Viagem> viagensHistorico;  
    private Local localAtual;//simulação para o GPS, nao esta no construtor
    //implementar senha com usuario sendo o login, talvez na abstrata mesmo
    
    public Cliente(String nome, String telefone, int IDPessoa, String dataNascimento){
        super(nome, telefone, IDPessoa, dataNascimento);
        viagensHistorico = new ArrayList<>();
    }

    public ArrayList<Viagem> getViagensHistorico() {
        return viagensHistorico;
    }

    public Local getLocalAtual() {
        return localAtual;
    }

    public void setLocalAtual(Local localAtual) {
        this.localAtual = localAtual;
    }

}


