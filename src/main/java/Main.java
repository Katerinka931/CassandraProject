import com.datastax.driver.core.exceptions.InvalidQueryException;
import org.cassandra.entities.Team;
import org.cassandra.service.CassandraService;
import org.cassandra.utils.CassandraConnector;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static void printMainMenu() {
        System.out.println("Меню: ");
        System.out.println("1 - Создать команду");
        System.out.println("2 - Получить список всех команд");
        System.out.println("3 - Получить команду по идентификатору");
        System.out.println("4 - Удалить запись");
        System.out.println("5 - Получить все узлы по метке");
        System.out.println("6 - Получить список игроков команды");
        System.out.println("7 - Добавить игрока в команду");
        System.out.println("0 - Выход");
    }

    public static void main(String[] args) throws SQLException {
        CassandraConnector connector = CassandraConnector.getInstance();
        CassandraService service = new CassandraService(connector.getSession());

        Scanner input = new Scanner(System.in);

        System.out.println("Вас приветствует консольный клиент для работы с базой данных Cassandra!");
        printMainMenu();
        int choice;

        do {
            choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Введите название команды: ");
                    String name = input.next();

                    System.out.println("Введите количество участников: ");
                    try {
                        int count = Integer.parseInt(input.next());
                        service.createTeam(new Team(name, count));
                        System.out.println("Создана команда с названием " + name);
                    } catch (NumberFormatException e) {
                        System.out.println("Не удалось создать команду, количество участников должно быть числом");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                }
                case 2 -> service.getAllTeams();
                case 3 -> {
                    System.out.println("Введите идентификатор команды: ");
                    String id = input.next();

                    try {
                        service.getTeamById(id);
                    } catch (InvalidQueryException e) {
                        System.out.println("Неверно введен идентификатор");
                    }
                }
                case 4 -> {
                    System.out.println("Введите идентификатор команды: ");
                    String id = input.next();

                    try {
                        service.deleteTeam(id);
                    } catch (InvalidQueryException e) {
                        System.out.println("Неверно введен идентификатор");
                    }
                }
            }
            printMainMenu();
        } while (choice != 0);
        connector.closeConnection();
    }
}
//                case 3 -> {
//                    System.out.println("Введите идентификатор команды: ");
//                    String id = input.next();
//
//                    System.out.println("Введите название команды: ");
//                    String name = input.next();
//
//                    System.out.println("Введите количество участников: ");
//                    try {
//                        int count = Integer.parseInt(input.next());
//                        service.updateTeam(new Team(id, name, count));
//                        System.out.println("Команда отредактирована");
//                    } catch (NumberFormatException e) {
//                        System.out.println("Не удалось создать команду, количество участников должно быть числом");
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Ошибка при добавлении записи в коллекцию: " + e.getMessage());
//                    }
//
//                }
//                case 4 -> {
//                    System.out.println("Введите название метки: ");
//                    String label = input.next();
//
//                    System.out.println("Введите идентификатор: ");
//                    String id = input.next();
//
//                    try {
//                        service.deleteNode(label, id);
//                        System.out.println("Удаление успешно");
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Ошибка при удалении: " + e.getMessage());
//                    }
//                }
//                case 5 -> {
//                    System.out.println("Введите метку: ");
//                    String label = input.next();
//                    service.getNodesByMark(label);
//                }
//                case 6 -> {
//                    System.out.println("Введите название команды: ");
//                    String name = input.next();
//
//                    service.getTeamsPlayers(name);
//                }
//                case 7 -> {
//                    System.out.println("Введите идентификатор команды: ");
//                    String teamId = input.next();
//
//                    System.out.println("Введите идентификатор игрока: ");
//                    String playerId = input.next();
//
//                    try {
//                        service.addPlayerToTeam(teamId, playerId);
//                        System.out.println("Связь успешно создана");
//                    } catch (Exception e) {
//                        System.out.println("Ошибка: " + e.getMessage());
//                    }
//                }
//                default -> {
//                    continue;
//                }
//            }
//            printMainMenu();
//
//
//        connector.close();
//        System.exit(0);
//    }
//
//    private static void printMainMenu() {
//        System.out.println("Меню: ");
//        System.out.println("1 - Создать команду");
//        System.out.println("2 - Создать игрока");
//        System.out.println("3 - Редактировать команду");
//        System.out.println("4 - Удалить запись");
//        System.out.println("5 - Получить все узлы по метке");
//        System.out.println("6 - Получить список игроков команды");
//        System.out.println("7 - Добавить игрока в команду");
//        System.out.println("0 - Выход");
//    }