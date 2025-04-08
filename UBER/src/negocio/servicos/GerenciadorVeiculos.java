package negocio.servicos;

import dados.RepositorioVeiculoArquivo;
import negocio.exceptions.VeiculoJaExisteException;
import negocio.veiculos.Economico;
import negocio.veiculos.Luxo;
import negocio.veiculos.Motocicleta;
import negocio.veiculos.SUV;
import negocio.veiculos.Veiculo;

public class GerenciadorVeiculos {
    private final RepositorioVeiculoArquivo repoVeiculo;
    

    public GerenciadorVeiculos() {
        this.repoVeiculo = new RepositorioVeiculoArquivo();
    }

    public Veiculo cadastrarVeiculo(String placa, String cor, int ano, String nome, String tipo, int tipoVeiculo)throws  VeiculoJaExisteException{//adiciona qualquer tipo de veículo ao repositorio, e deve ser adicionado ao histórico de veículos do motorista
            if (repoVeiculo.buscarPorPlaca(placa) != null) {//na interface o erro e tratado, aqui apenas é verificado se o veículo já existe
                throw new VeiculoJaExisteException("Não foi possível cadastrar veículo. Já existe veículo com mesma placa na base de dados: ", placa);
            }
            //caso não tenha dado erro, o veículo é adicionado ao repositório
            switch (tipoVeiculo) {
                case 1->{//Economico
                    //faz adição diretamente
                    Veiculo veiculo = new Economico(placa, cor, nome, ano);
                    repoVeiculo.adicionar(veiculo);
                    return veiculo;
                }
                case 2->{//Luxo
                    //faz adiçao diretamente
                    Veiculo veiculo = new Luxo(placa, cor, nome, ano);
                    repoVeiculo.adicionar(veiculo);
                    return veiculo;
                } 
                case 3->{//SUV
                    //faz adição diretamente
                    Veiculo veiculo = new SUV(placa, cor, nome, ano);
                    repoVeiculo.adicionar(veiculo);
                    return veiculo;
                }
                case 4->{//motocicleta
                    //faz adição diretamente
                    Veiculo veiculo = new Motocicleta(placa, cor, nome, ano);
                    repoVeiculo.adicionar(veiculo);
                    return veiculo;
                }
                default->{
                    System.out.println("Tipo de veículo inválido.");//apenas a pedido do compilador
                    throw new AssertionError();
                }
            
            }
        }
        public Veiculo buscarVeiculo(String placa) {//veiculo ou null
            return repoVeiculo.buscarPorPlaca(placa);
        }
    }

