package negocio.pessoas;

public class PlacaNaoEncontradaException extends Exception {
    public PlacaNaoEncontradaException(String placa) {
        super("Veículo com placa " + placa + " não encontrado.");
    }
}
