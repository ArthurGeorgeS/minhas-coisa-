
public class Tutor {

    private String nome;
    private String telefone;
    private String endereco;

    public Tutor(String nome, String telefone, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return nome + " | Tel: " + telefone;
    }
}
