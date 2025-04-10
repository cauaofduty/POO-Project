package negocio;

import java.util.ArrayList;

import negocio.basicas.Cliente;
import simulacao.GeradorPessoas;

public class Resto {
    //criar um repositorio de pagamentos TALVEZ -> extrai de Viagem
    public Pessoa cadastrarPessoa(){//flag para indicar qual tipo
        //recebe atributos necessarios apenas para criar conta
        System.out.println("Qual seu nome?");
        String nome = Util.entrada.nextLine();//apenas nome de usuario
        
        String IDPessoa = GeradorPessoas.gerarID(repoPessoa.getPessoas());//necessita checar IDs ja criados
        
        System.out.println("qual sua idade?");
        int idade = Util.entrada.nextInt();
        while (idade < 18) {//garante validade da idade
            System.out.println("Digite uma idade válida.");
            idade = Util.entrada.nextInt();
        }
        
        System.out.println("Agora, digite sua senha (mínimo de 8 caracteres)");
        String senhaAcesso = criarSenha();//função dedicada
        
        System.out.println("Agora, digite o nome da sua cidade:");
        String cidade = Util.entrada.nextLine();
        //a partir daqui cadastra os atributos especificos
        //e o usuario escolhe se quer ser motorista ou cliente
        System.out.println("Digite 1 para motorista ou 2 para cliente:");
        int flag = Util.entrada.nextInt();
        while(flag != 1 && flag != 2){
            System.out.println("Digite uma opção válida.");
            flag = Util.entrada.nextInt();
        }
        switch(flag) {
            case 1 ->{
                //criar função para cadastrar veiculo no repositorio de veiculos;
            } //cadastra motorista
            case 2 -> {//cadastra cliente, adicionando seus atributos especificos e instanciando a classe cliente
                ArrayList<FormaDePagamento> formasDePagamento = new ArrayList<>();
                adicionarFormaPagamento(formasDePagamento);
                Cidade cidadeLocal = new Cidade(cidade);
                Local local = new Local(cidadeLocal);//pode estar complicado: cidade é um tipo, porém basta a cidade para mostrar o mapa então será instanciado cidade sem bairro ou zona e Local apenas com cidade por agora  
                Cliente cliente = new Cliente(formasDePagamento, IDPessoa, idade, local, nome, senhaAcesso);
                System.out.println("Cliente " + nome+ " cadastrado com sucesso.");
                //adiciona ao repositorio e retorna o cliente
                this.repoPessoa.adicionar(cliente);//adiciona ao repositorio
                return cliente;
            }
            default -> System.out.println("Opção inválida.");
        }
        return null;//apenas para fins de compilação, não deve ser chamado
    }
}
