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

    public String getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(String dataEntrada) { this.dataEntrada = dataEntrada; }

    public String getDataSaida() { return dataSaida; }
    public void setDataSaida(String dataSaida) { this.dataSaida = dataSaida; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
