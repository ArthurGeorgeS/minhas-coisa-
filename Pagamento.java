
public class Pagamento {

    private double valor;
    private String data;
    private String metodo;

    public Pagamento(double valor, String data, String metodo) {
        this.valor = valor;
        this.data = data;
        this.metodo = metodo;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String getMetodo() {
        return metodo;
    }

    @Override
    public String toString() {
        return "R$ " + valor + " | " + data + " | " + metodo;
    }
}
