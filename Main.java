
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Cliente cliente = new Cliente("Arthur", "Rua das Flores, 123");

        Lanche xBacon = new Lanche(
                "X-Bacon",
                25.0,
                Arrays.asList("Pão", "Bacon", "Queijo", "Maionese")
        );

        Lanche xSalada = new Lanche(
                "X-Salada",
                20.0,
                Arrays.asList("Pão", "Alface", "Tomate", "Queijo")
        );

        Bebida coca = new Bebida("Coca-Cola", 8.0, 350);

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(xBacon);
        pedido.adicionarItem(xSalada);
        pedido.adicionarItem(coca);

        pedido.exibirResumo();
    }
}
