package negocio.servicos;
import java.util.ArrayList;
import negocio.localizacao.Local;
public class GerenciadorLocaisMapa { //mexer aqui ainda
    private final ArrayList<Local> locaisPessoasDisponiveis;//localizacoes para motorista e cliente
 
    public GerenciadorLocaisMapa(ArrayList<Local> locaisPessoasDisponiveis) {//o construtor recebe a função gerarPessoaPara(Pessoa pessoa) e gera as localizacoes para motorista ou cliente
        this.locaisPessoasDisponiveis = locaisPessoasDisponiveis;
    }

    public ArrayList<Local> getLocais() {
        return locaisPessoasDisponiveis;
    }



}
