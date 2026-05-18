
public class Vacina {

    private String nome;
    private String dataAplicacao;
    private String dataReforco;
    private String lote;

    public Vacina(String nome, String dataAplicacao, String dataReforco, String lote) {
        this.nome = nome;
        this.dataAplicacao = dataAplicacao;
        this.dataReforco = dataReforco;
        this.lote = lote;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getDataReforco() {
        return dataReforco;
    }

    public void setDataReforco(String dataReforco) {
        this.dataReforco = dataReforco;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
