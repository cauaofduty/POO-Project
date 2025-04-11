package negocio.localizacao;
import java.io.Serializable;

import negocio.veiculos.Veiculo;

public class CalculadorPreco implements Serializable {
    private static final long serialVersionUID = 400003L;
    
    public static double calcularPrecoViagem(Local origem, Local destino, Veiculo veiculo) {
        if (origem == null || destino == null || veiculo == null) {
            throw new IllegalArgumentException("Origem, destino ou veículo não podem ser nulos.");
        }
    
        Zona zonaOrigem = origem.getZona();
        Zona zonaDestino = destino.getZona();
    
        if (zonaOrigem == null || zonaDestino == null) {
            throw new IllegalArgumentException("Zonas de origem e destino não podem ser nulas.");
        }
    
        double taxa = veiculo.getTaxaViagem();
        double distancia = Math.sqrt(
            Math.pow(zonaDestino.getX() - zonaOrigem.getX(), 2) + Math.pow(zonaDestino.getY() - zonaOrigem.getY(), 2)
        );
    
        double precoBase = 5.0; // Preço base da viagem
        double preco = precoBase + (distancia * 5.0); // Custo por unidade de distância
        preco = preco + (preco * taxa);
    
        return Math.round(preco * 100.0) / 100.0; // arredondado para 2 casas decimais
    }

    public static double calcularPrecoEntrega(Local origem, Local destino, Veiculo veiculo, double pesoKg) {
        if (origem == null || destino == null || veiculo == null) {
            throw new IllegalArgumentException("Origem, destino ou veículo não podem ser nulos.");
        }
    
        if (pesoKg < 0) {
            throw new IllegalArgumentException("Peso da encomenda não pode ser negativo.");
        }
    
        Zona zonaOrigem = origem.getZona();
        Zona zonaDestino = destino.getZona();
    
        if (zonaOrigem == null || zonaDestino == null) {
            throw new IllegalArgumentException("Zonas de origem e destino não podem ser nulas.");
        }
    
        double taxa = veiculo.getTaxaViagem();
        double distancia = Math.sqrt(
            Math.pow(zonaDestino.getX() - zonaOrigem.getX(), 2) + Math.pow(zonaDestino.getY() - zonaOrigem.getY(), 2)
        );
    
        double precoBase = 5.0; // Preço base para entrega
        double precoPorPeso = pesoKg * 0.5; // 0.5 por kg
        double precoDistancia = distancia * 5.0;
    
        double preco = precoBase + precoDistancia + precoPorPeso;
        preco = preco + (preco * taxa); // Aplicando modificação com a taxa do veículo
    
        return Math.round(preco * 100.0) / 100.0;
    }
}
