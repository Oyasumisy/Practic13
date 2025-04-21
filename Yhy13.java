import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Yhy13 {
    static class Record {
        LocalDateTime dateTime;
        String text;

        Record(LocalDateTime dateTime, String text) {
            this.dateTime = dateTime;
            this.text = text;
        }
    }

    static Record[] entries = new Record[50];
    static int count = 0;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати запис");
            System.out.println("2. Видалити запис за датою");
            System.out.println("3. Переглянути всі записи");
            System.out.println("4. Вийти");
            System.out.print("Ваш вибір: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addRecord(scanner);
                    break;
                case "2":
                    deleteRecord(scanner);
                    break;
                case "3":
                    viewRecords();
                    break;
                case "4":
                    System.out.println("До побачення!");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    public static void addRecord(Scanner scanner) {
        if (count >= entries.length) {
            System.out.println("Щоденник переповнений.");
            return;
        }

        System.out.print("Введіть дату і час (yyyy-MM-dd HH:mm): ");
        String dateInput = scanner.nextLine();
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateInput, formatter);
        } catch (Exception e) {
            System.out.println("Неправильний формат дати і часу.");
            return;
        }

        System.out.print("Введіть текст запису: ");
        String text = scanner.nextLine();

        entries[count++] = new Record(dateTime, text);
        System.out.println("Запис додано.");
    }

    public static void deleteRecord(Scanner scanner) {
        System.out.print("Введіть дату і час запису, який потрібно видалити (yyyy-MM-dd HH:mm): ");
        String input = scanner.nextLine();
        LocalDateTime dateTimeToDelete;
        try {
            dateTimeToDelete = LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            System.out.println("Неправильний формат дати і часу.");
            return;
        }

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (entries[i].dateTime.equals(dateTimeToDelete)) {
                for (int j = i; j < count - 1; j++) {
                    entries[j] = entries[j + 1];
                }
                entries[count - 1] = null;
                count--;
                found = true;
                System.out.println("Запис видалено.");
                break;
            }
        }

        if (!found) {
            System.out.println("Запис з такою датою не знайдено.");
        }
    }

    public static void viewRecords() {
        if (count == 0) {
            System.out.println("Записи відсутні.");
            return;
        }

        System.out.println("\nУсі записи:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". [" + entries[i].dateTime.format(formatter) + "] " + entries[i].text);
        }
    }
}
