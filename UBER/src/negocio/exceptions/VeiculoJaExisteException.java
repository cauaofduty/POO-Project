package negocio.exceptions;

public class VeiculoJaExisteException extends Exception {
    public VeiculoJaExisteException(String message,String placa) {
        super(message + placa);
    }
    
}
