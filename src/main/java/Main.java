import com.datastax.driver.core.exceptions.InvalidQueryException;
import org.cassandra.entities.Team;
import org.cassandra.service.CassandraService;
import org.cassandra.utils.CassandraConnector;

import java.util.Scanner;

public class Main {
    private static void printMainMenu() {
        System.out.println("Меню: ");
        System.out.println("1 - Создать команду");
        System.out.println("2 - Получить список всех команд");
        System.out.println("3 - Получить команду по идентификатору");
        System.out.println("4 - Удалить запись");
        System.out.println("5 - Редактировать запись");
        System.out.println("6 - Выбрать команды в диапазоне количества участников");
        System.out.println("0 - Выход");
    }

    public static void main(String[] args) {
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
                        System.out.println("Удаление успешно");
                    } catch (InvalidQueryException e) {
                        System.out.println("Неверно введен идентификатор");
                    }
                }
                case 5 -> {
                    System.out.println("Введите идентификатор команды: ");
                    String id = input.next();

                    System.out.println("Введите новое название команды: ");
                    String name = input.next();

                    System.out.println("Введите новое количество участников: ");
                    try {
                        int count = Integer.parseInt(input.next());
                        service.updateTeam(id, new Team(name, count));
                        System.out.println("Команда отредактирована");
                    } catch (NumberFormatException e) {
                        System.out.println("Не удалось создать команду, количество участников должно быть числом");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при добавлении записи в коллекцию: " + e.getMessage());
                    }
                }
                case 6 -> {
                    try {
                        System.out.println("Введите минимальное количество участников: ");
                        int min = Integer.parseInt(input.next());

                        System.out.println("Введите максимальное количество участников: ");
                        int max = Integer.parseInt(input.next());

                        service.selectTeamsByCount(min, max);
                        System.out.println("Команда отредактирована");
                    } catch (NumberFormatException e) {
                        System.out.println("Не удалось создать команду, количество участников должно быть числом");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при добавлении записи в коллекцию: " + e.getMessage());
                    }
                }
            }
            printMainMenu();
        } while (choice != 0);
        connector.closeConnection();
    }
}