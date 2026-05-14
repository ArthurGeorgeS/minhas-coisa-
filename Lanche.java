import java.util.List;

public class Lanche extends Produto {
    private List<String> ingredientes;

    public Lanche(String nome, double preco, List<String> ingredientes) {
        super(nome, preco);
        this.ingredientes = ingredientes;
    }

    public List<String> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<String> ingredientes) { this.ingredientes = ingredientes; }

    @Override
    public String toString() {
        return super.toString() + " | Ingredientes: " + ingredientes;
    }
}
