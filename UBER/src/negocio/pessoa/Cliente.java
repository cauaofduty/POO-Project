package negocio.pessoa;

import java.util.ArrayList;

import negocio.demaisEntidades.Local;
import negocio.demaisEntidades.Viagem;

public class Cliente extends Pessoa{
    private final ArrayList<Viagem> viagensHistorico;  
    private Local localAtual;//simulação para o GPS
    
    public Cliente(String nome, String telefone, int IDPessoa, String dataNascimento){
        super(nome, telefone, IDPessoa, dataNascimento);
        viagensHistorico = new ArrayList<>();
    }

    public ArrayList<Viagem> getViagensHistorico() {
        return viagensHistorico;
    }
    public String getEndereco(){
        return this.endereco;
    }


}


