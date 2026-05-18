import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static class Vacina {
        private String nome, dataAplicacao, dataReforco, lote;

        public Vacina(String nome, String dataAplicacao, String dataReforco, String lote) {
            this.nome = nome;
            this.dataAplicacao = dataAplicacao;
            this.dataReforco = dataReforco;
            this.lote = lote;
        }

        public String getNome() { return nome; }
        public String getDataAplicacao() { return dataAplicacao; }
        public String getDataReforco() { return dataReforco; }
        public String getLote() { return lote; }
    }

    public static class Internacao {
        private String dataEntrada, dataSaida, motivo, observacoes;

        public Internacao(String dataEntrada, String dataSaida, String motivo, String observacoes) {
            this.dataEntrada = dataEntrada;
            this.dataSaida = dataSaida;
            this.motivo = motivo;
            this.observacoes = observacoes;
        }

        public String getDataEntrada() { return dataEntrada; }
        public String getDataSaida() { return dataSaida; }
        public String getMotivo() { return motivo; }
        public String getObservacoes() { return observacoes; }
    }

    public static class Exame {
        private String tipo, resultado, data, arquivoResultado;

        public Exame(String tipo, String resultado, String data, String arquivoResultado) {
            this.tipo = tipo;
            this.resultado = resultado;
            this.data = data;
            this.arquivoResultado = arquivoResultado;
        }

        public String getTipo() { return tipo; }
        public String getResultado() { return resultado; }
        public String getData() { return data; }
        public String getArquivoResultado() { return arquivoResultado; }
    }

    public static class Agendamento {
        private String data, hora, tipoServico, status;

        public Agendamento(String data, String hora, String tipoServico, String status) {
            this.data = data;
            this.hora = hora;
            this.tipoServico = tipoServico;
            this.status = status;
        }

        public String getData() { return data; }
        public String getHora() { return hora; }
        public String getTipoServico() { return tipoServico; }
        public String getStatus() { return status; }
    }

    // Banco de dados simples
    static List<Agendamento> agendamentos = new ArrayList<>();
    static List<Exame> exames = new ArrayList<>();
    static List<Vacina> vacinas = new ArrayList<>();
    static List<Internacao> internacoes = new ArrayList<>();

    // Menus
    public static void menuPrincipal() {
        System.out.println("\n===== SISTEMA VETERINÁRIO =====");
        System.out.println("1 - Serviços");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    public static void menuServicos() {
        System.out.println("\n--- SERVIÇOS ---");
        System.out.println("1 - Agendamento");
        System.out.println("2 - Exame");
        System.out.println("3 - Vacina");
        System.out.println("4 - Internação");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
    }

    // CRUDs
    public static void crudAgendamento(Scanner sc) {
        System.out.println("\n--- AGENDAMENTO ---");
        System.out.print("Data: ");
        String data = sc.nextLine();
        System.out.print("Hora: ");
        String hora = sc.nextLine();
        System.out.print("Tipo de serviço: ");
        String tipo = sc.nextLine();
        System.out.print("Status: ");
        String status = sc.nextLine();

        agendamentos.add(new Agendamento(data, hora, tipo, status));
        System.out.println("Agendamento cadastrado!");
    }

    public static void listarAgendamentos() {
        System.out.println("\n--- LISTA DE AGENDAMENTOS ---");
        int i = 0;
        for (Agendamento a : agendamentos) {
            System.out.println(i + " - " + a.getTipoServico() + " | " + a.getData() + " " + a.getHora());
            i++;
        }
    }

    public static void crudExame(Scanner sc) {
        System.out.println("\n--- EXAME ---");
        System.out.print("Tipo: ");
        String tipo = sc.nextLine();
        System.out.print("Resultado: ");
        String resultado = sc.nextLine();
        System.out.print("Data: ");
        String data = sc.nextLine();
        System.out.print("Arquivo do resultado: ");
        String arquivo = sc.nextLine();

        exames.add(new Exame(tipo, resultado, data, arquivo));
        System.out.println("Exame cadastrado!");
    }

    public static void listarExames() {
        System.out.println("\n--- LISTA DE EXAMES ---");
        int i = 0;
        for (Exame e : exames) {
            System.out.println(i + " - " + e.getTipo() + " | " + e.getData());
            i++;
        }
    }

    public static void crudVacina(Scanner sc) {
        System.out.println("\n--- VACINA ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Data aplicação: ");
        String aplic = sc.nextLine();
        System.out.print("Data reforço: ");
        String reforco = sc.nextLine();
        System.out.print("Lote: ");
        String lote = sc.nextLine();

        vacinas.add(new Vacina(nome, aplic, reforco, lote));
        System.out.println("Vacina cadastrada!");
    }

    public static void listarVacinas() {
        System.out.println("\n--- LISTA DE VACINAS ---");
        int i = 0;
        for (Vacina v : vacinas) {
            System.out.println(i + " - " + v.getNome() + " | Aplicação: " + v.getDataAplicacao());
            i++;
        }
    }

    public static void crudInternacao(Scanner sc) {
        System.out.println("\n--- INTERNAÇÃO ---");
        System.out.print("Data entrada: ");
        String entrada = sc.nextLine();
        System.out.print("Data saída: ");
        String saida = sc.nextLine();
        System.out.print("Motivo: ");
        String motivo = sc.nextLine();
        System.out.print("Observações: ");
        String obs = sc.nextLine();

        internacoes.add(new Internacao(entrada, saida, motivo, obs));
        System.out.println("Internação cadastrada!");
    }

    public static void listarInternacoes() {
        System.out.println("\n--- LISTA DE INTERNAÇÕES ---");
        int i = 0;
        for (Internacao in : internacoes) {
            System.out.println(i + " - " + in.getMotivo() + " | Entrada: " + in.getDataEntrada());
            i++;
        }
    }

    // MAIN
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            menuPrincipal();
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {

                case 1:
                    int opServ;
                    do {
                        menuServicos();
                        opServ = sc.nextInt();
                        sc.nextLine();

                        switch (opServ) {
                            case 1:
                                crudAgendamento(sc);
                                listarAgendamentos();
                                break;

                            case 2:
                                crudExame(sc);
                                listarExames();
                                break;

                            case 3:
                                crudVacina(sc);
                                listarVacinas();
                                break;

                            case 4:
                                crudInternacao(sc);
                                listarInternacoes();
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opção inválida");
                        }

                    } while (opServ != 0);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }

        } while (opcao != 0);

        sc.close();
    }
}
