public class Agendamento {

    private String data;
    private String hora;
    private String tipoServico;
    private String status; // confirmado / cancelado / pendente

    public Agendamento(String data, String hora, String tipoServico, String status) {
        this.data = data;
        this.hora = hora;
        this.tipoServico = tipoServico;
        this.status = status;
    }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getTipoServico() { return tipoServico; }
    public void setTipoServico(String tipoServico) { this.tipoServico = tipoServico; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
