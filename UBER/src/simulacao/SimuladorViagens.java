package simulacao;

import java.util.ArrayList;
import java.util.List;
import negocio.financeiro.Cartao;
import negocio.financeiro.FormaDePagamento;
import negocio.financeiro.Pix;
import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.servicos.GerenciadorViagens;

public class SimuladorViagens {
    private final GerenciadorViagens viagemManager;

    public SimuladorViagens() {
        this.viagemManager = new GerenciadorViagens();
    }
    public void simularPagamento(Cliente cliente, double preco) {
        List<FormaDePagamento> formas = cliente.getFormasPagamento();
    
        if (formas == null || formas.isEmpty()) {
            System.out.println(cliente.getNome() + " pagará em dinheiro (nenhuma forma de pagamento cadastrada).");
            return;
        }
    
        for (FormaDePagamento forma : formas) {
            switch (forma) {
                case Cartao cartao -> {
                    if (cartao.getLimiteCartao() >= preco) {
                        cartao.setLimiteCartao(cartao.getLimiteCartao() - preco);
                        System.out.println(cliente.getNome() + " pagou com cartão. Limite restante: R$ " + cartao.getLimiteCartao());
                        return;
                    }
                }
                case Pix pix -> {
                    if (pix.getSaldoPix() >= preco) {
                        pix.setSaldoPix(pix.getSaldoPix() - preco);
                        System.out.println(cliente.getNome() + " pagou com Pix. Saldo restante: R$ " + pix.getSaldoPix());
                        return;
                    }
                }
                default -> {
                }
            }
        }
    
        System.out.println(cliente.getNome() + " não tinha saldo suficiente. Pagamento simulado em dinheiro.");
    }
    //soiular local destino na UI maybe
    public void simularViagemMotorista(Cliente cliente, Motorista motorista, Local origem, Local destino) {
    
    double preco = viagemManager.calcularPrecoViagem(origem, destino, motorista.getVeiculo());

    // Simula pagamento do cliente
    simularPagamento(cliente, preco);

    // Atualiza saldo do motorista 
    motorista.setSaldoMotorista(motorista.getSaldoMotorista() + preco);
}

    public void simularViagemCliente(Cliente cliente, Motorista motorista, Local origem, Local destino) throws Exception {
        double preco = viagemManager.calcularPrecoViagem(origem, destino, motorista.getVeiculo());
        ArrayList<FormaDePagamento> formas = cliente.getFormasPagamento();
        boolean pagamentoRealizado = false;

        for (FormaDePagamento forma : formas) {
            switch (forma) {
                case Cartao cartao -> {
                    if (cartao.getLimiteCartao() >= preco) {
                        cartao.setLimiteCartao(cartao.getLimiteCartao() - preco);
                        pagamentoRealizado = true;
                        System.out.println("Pagamento realizado via cartão.");
                        break;
                    }
                }
                case Pix pix -> {
                    if (pix.getSaldoPix() >= preco) {
                        pix.setSaldoPix(pix.getSaldoPix() - preco);
                        pagamentoRealizado = true;
                        System.out.println("Pagamento realizado via Pix.");
                        break;
                    }
                }
                default -> {
                }
            }
        }
        //checa se as formas de pagamento foram válidas
        if (!pagamentoRealizado) {
            throw new Exception("Nenhuma forma de pagamento válida. Pagamento será feito em dinheiro.");
        }
        boolean simulacao = true;
        viagemManager.adicionarViagemCliente(origem, destino, cliente, motorista, preco, simulacao);
        motorista.setSaldoMotorista(motorista.getSaldoMotorista() + preco);
    }
    public void simularViagemEntrega(Cliente cliente, Motorista motorista, Local origem, Local destino, double peso) throws Exception {
        double preco = viagemManager.calcularPrecoEntrega(origem, destino, motorista.getVeiculo(), peso);
        ArrayList<FormaDePagamento> formas = cliente.getFormasPagamento();
        boolean pagamentoRealizado = false;
    
        for (FormaDePagamento forma : formas) {
            switch (forma) {
                case Cartao cartao -> {
                    if (cartao.getLimiteCartao() >= preco) {
                        cartao.setLimiteCartao(cartao.getLimiteCartao() - preco);
                        pagamentoRealizado = true;
                        System.out.println("Pagamento realizado via cartão.");
                        boolean simulacao = true;
                        viagemManager.adicionarViagemEntrega(origem, destino, cliente, motorista, preco, peso, simulacao);
                        motorista.setSaldoMotorista(motorista.getSaldoMotorista() + preco);
                        break;
                    }
                }
                case Pix pix -> {
                    if (pix.getSaldoPix() >= preco) {
                        pix.setSaldoPix(pix.getSaldoPix() - preco);
                        pagamentoRealizado = true;
                        System.out.println("Pagamento realizado via Pix.");
                        boolean simulacao = true;
                        viagemManager.adicionarViagemEntrega(origem, destino, cliente, motorista, preco, peso, simulacao);
                        motorista.setSaldoMotorista(motorista.getSaldoMotorista() + preco);
                        break;
                    }
                }
                default -> {
                }
            }
        }
    
        if (!pagamentoRealizado) {
            throw new Exception("Nenhuma forma de pagamento válida. Pagamento será feito em dinheiro.");
        }

            boolean simulacao = true;
            viagemManager.adicionarViagemEntrega(origem, destino, cliente, motorista, preco, peso, simulacao);
            motorista.setSaldoMotorista(motorista.getSaldoMotorista() + preco);
        
    }
}
