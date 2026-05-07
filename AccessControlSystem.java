
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Sistema de Controle de Acesso - Autenticação por identificador e senha (hash)
 * - Níveis de acesso: VISITANTE, FUNCIONARIO, ADMIN - Áreas com permissões
 * diferentes - Registro de histórico de tentativas (data, hora, usuário, área,
 * resultado) - ADMIN pode gerenciar usuários e consultar histórico
 */
public class AccessControlSystem {

    // Enum de níveis de acesso
    enum Role {
        VISITANTE,
        FUNCIONARIO,
        ADMIN
    }

    // Enum de áreas do prédio
    enum Area {
        RECEPCAO,
        ESCRITORIO,
        LABORATORIO,
        SERVIDOR
    }

    // Classe de usuário
    static class User {

        private final String id;
        private String name;
        private Role role;
        private String passwordHash;

        public User(String id, String name, Role role, String passwordHash) {
            this.id = id;
            this.name = name;
            this.role = role;
            this.passwordHash = passwordHash;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Role getRole() {
            return role;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Nome: " + name + " | Perfil: " + role;
        }
    }

    // Classe de registro de acesso
    static class AccessLog {

        private final LocalDateTime timestamp;
        private final String userId;
        private final String userName;
        private final Area area;
        private final boolean authorized;

        public AccessLog(LocalDateTime timestamp, String userId, String userName, Area area, boolean authorized) {
            this.timestamp = timestamp;
            this.userId = userId;
            this.userName = userName;
            this.area = area;
            this.authorized = authorized;
        }

        @Override
        public String toString() {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return "[" + timestamp.format(fmt) + "] "
                    + "Usuário: " + userName + " (" + userId + ") | Área: " + area
                    + " | Resultado: " + (authorized ? "AUTORIZADO" : "NEGADO");
        }
    }

    // "Banco de dados" em memória
    private final Map<String, User> users = new HashMap<>();
    private final List<AccessLog> logs = new ArrayList<>();
    private final Map<Role, Set<Area>> permissions = new HashMap<>();

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private User loggedUser;

    public static void main(String[] args) {
        AccessControlSystem system = new AccessControlSystem();
        system.init();
        system.run();
    }

    /**
     * Inicializa permissões e cria usuário administrador padrão.
     */
    private void init() {
        // Modelagem de permissões por papel
        permissions.put(Role.VISITANTE, EnumSet.of(Area.RECEPCAO));
        permissions.put(Role.FUNCIONARIO, EnumSet.of(Area.RECEPCAO, Area.ESCRITORIO, Area.LABORATORIO));
        permissions.put(Role.ADMIN, EnumSet.allOf(Area.class));

        // Usuário admin padrão
        String adminId = "admin";
        String adminName = "Administrador";
        String adminPassword = "admin123"; // senha padrão (apenas para exemplo)
        String hash = hashPassword(adminPassword);
        User admin = new User(adminId, adminName, Role.ADMIN, hash);
        users.put(adminId, admin);
    }

    /**
     * Loop principal do sistema.
     */
    private void run() {
        System.out.println("=== Sistema de Controle de Acesso ===");
        while (true) {
            try {
                if (loggedUser == null) {
                    showLoginMenu();
                } else {
                    showMainMenu();
                }
            } catch (IOException e) {
                System.out.println("Erro de entrada/saída. Encerrando o sistema.");
                break;
            }
        }
    }

    /**
     * Menu de login.
     */
    private void showLoginMenu() throws IOException {
        System.out.println("\n1 - Login");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
        String option = reader.readLine();
        if (option == null) {
            return;
        }

        switch (option.trim()) {
            case "1":
                login();
                break;
            case "0":
                System.out.println("Encerrando o sistema. Até mais!");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    /**
     * Realiza login do usuário.
     */
    private void login() throws IOException {
        System.out.print("ID do usuário: ");
        String id = reader.readLine();
        System.out.print("Senha: ");
        String password = reader.readLine();

        if (id == null || password == null) {
            System.out.println("Entrada inválida.");
            return;
        }

        User user = users.get(id.trim());
        if (user == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        String hash = hashPassword(password);
        if (!hash.equals(user.getPasswordHash())) {
            System.out.println("Senha incorreta.");
            return;
        }

        loggedUser = user;
        System.out.println("Login realizado com sucesso. Bem-vindo, " + loggedUser.getName() + " (" + loggedUser.getRole() + ")");
    }

    /**
     * Menu principal após login.
     */
    private void showMainMenu() throws IOException {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("Usuário logado: " + loggedUser.getName() + " (" + loggedUser.getRole() + ")");
        System.out.println("1 - Tentar acessar área");
        if (loggedUser.getRole() == Role.ADMIN) {
            System.out.println("2 - Gerenciar usuários");
            System.out.println("3 - Consultar histórico de acessos");
        }
        System.out.println("9 - Logout");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
        String option = reader.readLine();
        if (option == null) {
            return;
        }

        switch (option.trim()) {
            case "1":
                attemptAccess();
                break;
            case "2":
                if (loggedUser.getRole() == Role.ADMIN) {
                    manageUsersMenu();
                } else {
                    System.out.println("Opção inválida.");
                }
                break;
            case "3":
                if (loggedUser.getRole() == Role.ADMIN) {
                    showLogs();
                } else {
                    System.out.println("Opção inválida.");
                }
                break;
            case "9":
                loggedUser = null;
                System.out.println("Logout realizado.");
                break;
            case "0":
                System.out.println("Encerrando o sistema. Até mais!");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    /**
     * Exibe as áreas e tenta realizar o acesso.
     */
    private void attemptAccess() throws IOException {
        System.out.println("\n=== Tentativa de Acesso ===");
        Area area = chooseArea();
        if (area == null) {
            System.out.println("Área inválida.");
            return;
        }

        boolean authorized = checkPermission(loggedUser, area);
        logs.add(new AccessLog(LocalDateTime.now(), loggedUser.getId(), loggedUser.getName(), area, authorized));

        if (authorized) {
            System.out.println("Acesso à área " + area + " AUTORIZADO.");
        } else {
            System.out.println("Acesso à área " + area + " NEGADO.");
        }
    }

    /**
     * Verifica se o usuário tem permissão para acessar a área.
     */
    private boolean checkPermission(User user, Area area) {
        Set<Area> allowed = permissions.get(user.getRole());
        return allowed != null && allowed.contains(area);
    }

    /**
     * Menu de gerenciamento de usuários (apenas ADMIN).
     */
    private void manageUsersMenu() throws IOException {
        while (true) {
            System.out.println("\n=== Gerenciamento de Usuários ===");
            System.out.println("1 - Listar usuários");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("3 - Editar usuário");
            System.out.println("4 - Remover usuário");
            System.out.println("9 - Voltar");
            System.out.print("Escolha: ");
            String option = reader.readLine();
            if (option == null) {
                return;
            }

            switch (option.trim()) {
                case "1":
                    listUsers();
                    break;
                case "2":
                    createUser();
                    break;
                case "3":
                    editUser();
                    break;
                case "4":
                    deleteUser();
                    break;
                case "9":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    /**
     * Lista todos os usuários cadastrados.
     */
    private void listUsers() {
        System.out.println("\n=== Usuários Cadastrados ===");
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (User u : users.values()) {
            System.out.println(u);
        }
    }

    /**
     * Cria um novo usuário.
     */
    private void createUser() throws IOException {
        System.out.println("\n=== Cadastro de Usuário ===");
        System.out.print("ID (login): ");
        String id = reader.readLine();
        if (id == null || id.trim().isEmpty()) {
            System.out.println("ID inválido.");
            return;
        }
        id = id.trim();
        if (users.containsKey(id)) {
            System.out.println("Já existe um usuário com esse ID.");
            return;
        }

        System.out.print("Nome: ");
        String name = reader.readLine();
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Nome inválido.");
            return;
        }

        Role role = chooseRole();
        if (role == null) {
            System.out.println("Perfil inválido.");
            return;
        }

        System.out.print("Senha: ");
        String password = reader.readLine();
        if (password == null || password.isEmpty()) {
            System.out.println("Senha inválida.");
            return;
        }

        String hash = hashPassword(password);
        User user = new User(id, name.trim(), role, hash);
        users.put(id, user);
        System.out.println("Usuário cadastrado com sucesso.");
    }

    /**
     * Edita um usuário existente.
     */
    private void editUser() throws IOException {
        System.out.println("\n=== Edição de Usuário ===");
        System.out.print("Informe o ID do usuário: ");
        String id = reader.readLine();
        if (id == null) {
            return;
        }
        id = id.trim();

        User user = users.get(id);
        if (user == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("Editando: " + user);
        System.out.print("Novo nome (enter para manter): ");
        String newName = reader.readLine();
        if (newName != null && !newName.trim().isEmpty()) {
            user.setName(newName.trim());
        }

        System.out.println("Alterar perfil? (S/N)");
        String changeRole = reader.readLine();
        if (changeRole != null && changeRole.trim().equalsIgnoreCase("S")) {
            Role newRole = chooseRole();
            if (newRole != null) {
                user.setRole(newRole);
            } else {
                System.out.println("Perfil inválido. Mantendo o atual.");
            }
        }

        System.out.println("Alterar senha? (S/N)");
        String changePass = reader.readLine();
        if (changePass != null && changePass.trim().equalsIgnoreCase("S")) {
            System.out.print("Nova senha: ");
            String newPass = reader.readLine();
            if (newPass != null && !newPass.isEmpty()) {
                user.setPasswordHash(hashPassword(newPass));
            } else {
                System.out.println("Senha inválida. Mantendo a atual.");
            }
        }

        System.out.println("Usuário atualizado: " + user);
    }

    /**
     * Remove um usuário.
     */
    private void deleteUser() throws IOException {
        System.out.println("\n=== Remoção de Usuário ===");
        System.out.print("Informe o ID do usuário: ");
        String id = reader.readLine();
        if (id == null) {
            return;
        }
        id = id.trim();

        if (!users.containsKey(id)) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (id.equals(loggedUser.getId())) {
            System.out.println("Você não pode remover o usuário atualmente logado.");
            return;
        }

        users.remove(id);
        System.out.println("Usuário removido com sucesso.");
    }

    /**
     * Exibe o histórico de acessos.
     */
    private void showLogs() {
        System.out.println("\n=== Histórico de Acessos ===");
        if (logs.isEmpty()) {
            System.out.println("Nenhum registro de acesso.");
            return;
        }
        for (AccessLog log : logs) {
            System.out.println(log);
        }
    }

    /**
     * Escolhe uma área a partir do menu.
     */
    private Area chooseArea() throws IOException {
        System.out.println("Selecione a área:");
        int i = 1;
        for (Area a : Area.values()) {
            System.out.println(i + " - " + a);
            i++;
        }
        System.out.print("Escolha: ");
        String opt = reader.readLine();
        if (opt == null) {
            return null;
        }

        int index;
        try {
            index = Integer.parseInt(opt.trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return null;
        }

        if (index < 1 || index > Area.values().length) {
            return null;
        }
        return Area.values()[index - 1];
    }

    /**
     * Escolhe um perfil (Role) a partir do menu.
     */
    private Role chooseRole() throws IOException {
        System.out.println("Selecione o perfil:");
        int i = 1;
        for (Role r : Role.values()) {
            System.out.println(i + " - " + r);
            i++;
        }
        System.out.print("Escolha: ");
        String opt = reader.readLine();
        if (opt == null) {
            return null;
        }

        int index;
        try {
            index = Integer.parseInt(opt.trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return null;
        }

        if (index < 1 || index > Role.values().length) {
            return null;
        }
        return Role.values()[index - 1];
    }

    /**
     * Gera hash SHA-256 da senha.
     */
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Em caso extremo, não deve acontecer em ambiente padrão
            throw new RuntimeException("Algoritmo de hash não disponível", e);
        }
    }
}
