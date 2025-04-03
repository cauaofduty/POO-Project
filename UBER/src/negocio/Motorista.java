package negocio;
import negocio.modelos.Pessoa;
import negocio.modelos.Veiculo;

public class Motorista extends Pessoa { // como fazer para ele receber dinheiro?
    private double saldoMotorista; //TERIA UMA CLASSE PARA O MOTORISTA PASSAR TROCO EM CASO DE PIX X DINHEIRO, MAS O CLIENTE NAO ESTA PAGANDO TANTO ASSIM PELO PROJETO
    Veiculo veiculo;//ver se funciona pois tarta-se de classe abstrata aqui


    public Motorista(String nome,String telefone,int IDPessoa, String dataNascimento, String senhaAcesso, Veiculo veiculo){
        super(nome,telefone,IDPessoa,dataNascimento, senhaAcesso);
        this.saldoMotorista = 0;
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
