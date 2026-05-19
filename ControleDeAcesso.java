// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).

import java.util.ArrayList;
import java.util.List;

public class ControleDeAcesso {

    private List<Animal> animais = new ArrayList();
    private List<Tutor> tutores = new ArrayList();
    private List<Consulta> consultas = new ArrayList();
    private List<Agendamento> agendamentos = new ArrayList();
    private List<Internacao> internacoes = new ArrayList();
    private List<Pagamento> pagamentos = new ArrayList();
    private List<NotaFiscal> notasFiscais = new ArrayList();

    public ControleDeAcesso() {
    }

    public void cadastrarAnimal(Animal var1) {
        this.animais.add(var1);
        System.out.println("Animal cadastrado!");
    }

    public void cadastrarTutor(Tutor var1) {
        this.tutores.add(var1);
        System.out.println("Tutor cadastrado!");
    }

    public void cadastrarConsulta(Consulta var1) {
        this.consultas.add(var1);
        System.out.println("Consulta cadastrada!");
    }

    public void cadastrarAgendamento(Agendamento var1) {
        this.agendamentos.add(var1);
        System.out.println("Agendamento cadastrado!");
    }

    public void cadastrarInternacao(Internacao var1) {
        this.internacoes.add(var1);
        System.out.println("Internação cadastrada!");
    }

    public void registrarPagamento(Pagamento var1) {
        this.pagamentos.add(var1);
        System.out.println("Pagamento registrado!");
    }

    public void emitirNotaFiscal(NotaFiscal var1) {
        this.notasFiscais.add(var1);
        System.out.println("Nota fiscal emitida!");
    }

    public void listarAnimais() {
        System.out.println("\n--- ANIMAIS ---");

        for (Animal var2 : this.animais) {
            System.out.println(var2);
        }

    }

    public void listarTutores() {
        System.out.println("\n--- TUTORES ---");

        for (Tutor var2 : this.tutores) {
            System.out.println(var2);
        }

    }

    public void listarConsultas() {
        System.out.println("\n--- CONSULTAS ---");

        for (Consulta var2 : this.consultas) {
            System.out.println(var2);
        }

    }

    public void listarAgendamentos() {
        System.out.println("\n--- AGENDAMENTOS ---");

        for (Agendamento var2 : this.agendamentos) {
            System.out.println(var2);
        }

    }

    public void listarInternacoes() {
        System.out.println("\n--- INTERNAÇÕES ---");

        for (Internacao var2 : this.internacoes) {
            System.out.println(var2);
        }

    }

    public void listarPagamentos() {
        System.out.println("\n--- PAGAMENTOS ---");

        for (Pagamento var2 : this.pagamentos) {
            System.out.println(var2);
        }

    }

    public void listarNotasFiscais() {
        System.out.println("\n--- NOTAS FISCAIS ---");

        for (NotaFiscal var2 : this.notasFiscais) {
            System.out.println(var2);
        }

    }

    public static void main(String[] args) {
        ControleDeAcesso controle = new ControleDeAcesso();
        System.out.println("ControleDeAcesso iniciado.");
        // Exemplo mínimo: crie e liste sem dados adicionais.
        controle.listarAnimais();
    }
}
