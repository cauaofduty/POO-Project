package negocio.localizacao;

public enum Zona { //como plano cartesiano, calcula essas distâncias
    NORTE(0, 1), //parte superior do plano
    CENTRO(0, 0), //origem
    LESTE(1, 0), //direita
    OESTE(-1, 0), //esquerda
    SUL(0, -1); //parte inferior

    private final int x;
    private final int y;

    Zona(int x, int y) { //coordenadas como indices
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
