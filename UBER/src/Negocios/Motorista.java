package Negocios;

public class Motorista extends Pessoa {
    double saldoMotorista; //TERIA UMA CLASSE PARA O MOTORISTA PASSAR TROCO EM CASO DE PIX X DINHEIRO, MAS O CLIENTE NAO ESTA PAGANDO TANTO ASSIM PELO PROJETO
    public Motorista(String nome,String telefone,int IDPessoa){
        super(nome,telefone,IDPessoa);
        this.saldoMotorista = 0;
    }
    public double getSaldoMotorista(){
        return this.saldoMotorista;
    }
    public void setSaldoMotorista(double valor){
        this.saldoMotorista += valor;
    }
}
