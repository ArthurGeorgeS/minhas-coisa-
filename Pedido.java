
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Cliente cliente;
    private List<Produto> itens = new ArrayList<>();
    private double taxaEntrega = 8.0;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(Produto p) {
        itens.add(p);
    }

    public double calcularTotal() {
        double subtotal = 0;

        for (Produto p : itens) {
            subtotal += p.getPreco();
        }

        if (subtotal > 50) {
            subtotal *= 0.90;
        }

        return subtotal + taxaEntrega;
    }

    public void exibirResumo() {
        IO.print("Cliente: " + cliente.getNome());
        IO.print("Endereço: " + cliente.getEndereco());
        IO.print("\nItens do Pedido:");

        for (Produto p : itens) {
            IO.print(" - " + p);
        }

        IO.print("\nTotal a pagar: R$ " + calcularTotal());
    }
}
