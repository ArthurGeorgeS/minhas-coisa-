
public class NotaFiscal {

    private int numero;
    private String data;
    private double valor;

    public NotaFiscal(int numero, String data, double valor) {
        this.numero = numero;
        this.data = data;
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "NF " + numero + " | " + data + " | R$ " + valor;
    }
}
