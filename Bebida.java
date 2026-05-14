
public class Bebida extends Produto {

    private int tamanhoML;

    public Bebida(String nome, double preco, int tamanhoML) {
        super(nome, preco);
        this.tamanhoML = tamanhoML;
    }

    public int getTamanhoML() {
        return tamanhoML;
    }

    public void setTamanhoML(int tamanhoML) {
        this.tamanhoML = tamanhoML;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + tamanhoML + "ml";
    }
}
