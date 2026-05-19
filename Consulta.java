
public class Consulta {

    private String data;
    private String hora;
    private Animal animal;
    private String veterinario;
    private String motivo;

    public Consulta(String data, String hora, Animal animal, String veterinario, String motivo) {
        this.data = data;
        this.hora = hora;
        this.animal = animal;
        this.veterinario = veterinario;
        this.motivo = motivo;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return data + " " + hora + " | " + animal.getNome() + " | Vet: " + veterinario;
    }
}
