package negocio.pessoas;
import java.util.ArrayList;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;
import negocio.veiculos.Veiculo;

public class Motorista extends Pessoa { // como fazer para ele receber dinheiro?
    private static final long serialVersionUID = 100002L;
    private double saldoMotorista; //TERIA UMA CLASSE PARA O MOTORISTA PASSAR TROCO EM CASO DE PIX X DINHEIRO, MAS O CLIENTE NAO ESTA PAGANDO TANTO ASSIM PELO PROJETO
    Veiculo veiculo;//ver se funciona pois tarta-se de classe abstrata aqui
    ArrayList<Veiculo> historicoVeiculos;//historico de veiculos do motorista, caso ele tenha mais de um veiculo cadastrado, ou seja, o motorista pode ter mais de um veiculo, mas apenas um veiculo por vez em uso;
    //construtor default
    public Motorista(Veiculo veiculo, String IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso, ArrayList<Veiculo> historicoVeiculos) {
        super(IDPessoa, idade, localAtual, nome, senhaAcesso);
        this.saldoMotorista = 0;//começa com 0
        this.veiculo = veiculo;
        this.historicoVeiculos = historicoVeiculos;
    }
    
    //construtor para simulacao
    public Motorista(double saldoMotorista, Veiculo veiculo, String IDPessoa, int idade, Local localAtual, String nome) {
        super(IDPessoa, idade, localAtual, nome);
        this.saldoMotorista = saldoMotorista;
        this.veiculo = veiculo;
    }

    public double getSaldoMotorista(){
        return this.saldoMotorista;
    }
    public void setSaldoMotorista(double valor){
        this.saldoMotorista += valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ArrayList<Veiculo> getHistoricoVeiculos() {
        return historicoVeiculos;
    }
}
