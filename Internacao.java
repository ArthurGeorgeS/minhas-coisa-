
public class Internacao {

    private String dataEntrada;
    private String dataSaida;
    private String motivo;
    private String observacoes;

    public Internacao(String dataEntrada, String dataSaida, String motivo, String observacoes) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.motivo = motivo;
        this.observacoes = observacoes;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    @Override
    public String toString() {
        return motivo + " | Entrada: " + dataEntrada;
    }
}
