package weather.input;

import java.util.Scanner;
import weather.model.WeatherState;

public class ConsoleWeatherInput {
    private final Scanner scanner = new Scanner(System.in);

    private int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);

            if (!scanner.hasNextInt()) {
                System.out.println("Помилка: введіть ціле число.");
                scanner.next();
                continue;
            }

            int value = scanner.nextInt();

            if (value >= min && value <= max) {
                return value;
            }

            System.out.println("Помилка: введіть число від " + min + " до " + max + ".");
        }
    }

    private boolean readBinaryValue(String prompt) {
        int value = readIntInRange(prompt, 0, 1);
        return value == 1;
    }

    public WeatherState readWeatherState() {
        boolean sun = readBinaryValue("Введіть sun (0 або 1): ");
        boolean rain = readBinaryValue("Введіть rain (0 або 1): ");
        boolean snow = readBinaryValue("Введіть snow (0 або 1): ");
        boolean cloud = readBinaryValue("Введіть cloud (0 або 1): ");

        return new WeatherState(sun, rain, snow, cloud);
    }

    public int readInputMode() {
        System.out.println("Оберіть спосіб задання погоди:");
        System.out.println("1. Ввести вручну");
        System.out.println("2. Згенерувати випадково");

        return readIntInRange("Ваш вибір: ", 1, 2);
    }
}
