public class extend Exame {

    private String tipo;
    private String resultado;
    private String data;
    private String arquivoResultado;

    public Exame(String tipo, String resultado, String data, String arquivoResultado) {
        this.tipo = tipo;
        this.resultado = resultado;
        this.data = data;
        this.arquivoResultado = arquivoResultado;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getArquivoResultado() { return arquivoResultado; }
    public void setArquivoResultado(String arquivoResultado) { this.arquivoResultado = arquivoResultado; }
}

