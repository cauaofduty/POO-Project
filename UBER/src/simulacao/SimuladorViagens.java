package simulacao;

import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.servicos.GerenciadorViagens;

public class SimuladorViagens {
    private GerenciadorViagens viagemManager;
    private PessoasRandom geradorPessoas;

    public SimuladorViagens() {
        this.geradorPessoas = new PessoasRandom();
        this.viagemManager = new GerenciadorViagens();
    }

    public void simularUmaViagem() {
        Cliente cliente = gerarCliente();
        Local origem = cliente.getLocalizacaoAtual();
        Local destino = gerenciador.getLocalAleatorioDiferenteDe(origem);

        try {
            gerenciador.solicitarViagemCliente(origem, destino, cliente);
        } catch (EntidadeNaoEncontradaException e) {
            gerarMotoristas();
            // tenta novamente se quiser
        }
    }

    private Cliente gerarCliente() {
        // Gere um cliente e cadastre no gerenciador
    }

    private void gerarMotoristas() {
        // Gere motoristas e cadastre no gerenciador
    }
}
