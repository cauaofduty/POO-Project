package negocio.modelos;

public interface FormaDePagamento {
    void pagar(double valor) throws Exception;
}

// Faz sentido ter uma classe de Historico de pagamentos, para ter um repositorio de pagamentos feitos por clientes e pagamentos recebidos por motoristas
