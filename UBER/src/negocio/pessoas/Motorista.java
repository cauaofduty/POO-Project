package negocio.pessoas;
import java.util.ArrayList;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;
import negocio.veiculos.Veiculo;

public class Motorista extends Pessoa { // como fazer para ele receber dinheiro?
    private static final long serialVersionUID = 100002L;
    private double saldoMotorista; //TERIA UMA CLASSE PARA O MOTORISTA PASSAR TROCO EM CASO DE PIX X DINHEIRO, MAS O CLIENTE NAO ESTA PAGANDO TANTO ASSIM PELO PROJETO
    Veiculo veiculo;//ver se funciona pois tarta-se de classe abstrata aqui

    //construtor default
    public Motorista(double saldoMotorista, Veiculo veiculo, int IDPessoa, int idade, Local localAtual, String nome) {
        super(IDPessoa, idade, localAtual, nome);
        this.saldoMotorista = saldoMotorista;
        this.veiculo = veiculo;
    }

    //construtor para simulacao
    public Motorista(double saldoMotorista, Veiculo veiculo, int IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso, ArrayList<Viagem> viagensHistorico) {
        super(IDPessoa, idade, localAtual, nome, senhaAcesso, viagensHistorico);
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
}
