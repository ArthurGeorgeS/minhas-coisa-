
public class Animal {

    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private Tutor tutor;

    public Animal(String nome, String especie, String raca, int idade, Tutor tutor) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.tutor = tutor;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public Tutor getTutor() {
        return tutor;
    }

    @Override
    public String toString() {
        return nome + " (" + especie + ", " + raca + ")";
    }
}
