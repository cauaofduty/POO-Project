package negocio.pessoas;

import negocio.localizacao.Local;
import negocio.veiculos.Veiculo;

public class Motorista extends Pessoa { 
    private static final long serialVersionUID = 100002L;
    private double saldoMotorista; // TERIA UMA CLASSE PARA O MOTORISTA PASSAR TROCO EM CASO DE DINHEIRO, MAS O CLIENTE NAO ESTA PAGANDO TANTO ASSIM PELO PROJETO
    private Veiculo veiculo;
    private boolean disponivel = true;
    
    
    //construtor default
    public Motorista(Veiculo veiculo, String IDPessoa, boolean disponivel, int idade, Local localAtual, String nome, String senhaAcesso) {
        super(IDPessoa, idade, localAtual, nome, senhaAcesso);
        this.disponivel = disponivel;
        this.saldoMotorista = 0;
        this.veiculo = veiculo;
    }
    

    //construtor para simulacao
    public Motorista(double saldoMotorista, Veiculo veiculo, String IDPessoa, boolean disponivel, int idade, Local localAtual, String nome) {
        super(IDPessoa, idade, localAtual, nome);
        this.saldoMotorista = saldoMotorista;
        this.veiculo = veiculo;
        this.disponivel = disponivel;
    }


    public double getSaldoMotorista() {
        return this.saldoMotorista;
    }

    public void setSaldoMotorista(double valor) {
        this.saldoMotorista += valor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

}
