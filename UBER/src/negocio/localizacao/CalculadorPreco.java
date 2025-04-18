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
    
        double precoBase = 3.0; // Preço base da viagem (ex: para a mesma zona)
        double preco = precoBase + (distancia * 2.0); // Custo por unidade de distância
        preco = preco + (preco * taxa);
        
        //acrescimo para bairro diferente, como nao é sabido distância real e é caro        
        if(!origem.getBairro().equals(destino.getBairro())) {
            preco += 3.0; 
        }
        
        //acrescimo para cidade diferente, como nao é sabido distância real e é caro
        if(!origem.getCidade().equals(destino.getCidade())) {
            preco += 30.0; 
        }
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
    
        // Preço base para entrega, mais barato que viagem normal
        double precoBase = 2.0; 
        double precoPorPeso = pesoKg * 0.5; // 0.5 por kg
        double precoDistancia = distancia * 5.0;
        
        double preco = precoBase + precoDistancia + precoPorPeso;
        preco = preco + (preco * taxa); // Aplicando modificação com a taxa do veículo

        //acrescimo para bairro diferente, como nao é sabido distância real e é caro        
        if(!origem.getBairro().toUpperCase().equals(destino.getBairro().toUpperCase())) {
            preco += 3.0; 
        }
        
        //acrescimo para cidade diferente, como nao é sabido distância real e é caro
        if(!origem.getCidade().toUpperCase().equals(destino.getCidade().toUpperCase())) {
            preco += 30.0; 
        }
    
        return Math.round(preco * 100.0) / 100.0;
    }
}
