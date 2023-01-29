package niffler.testData;

import niffler.model.SpendModel;
import niffler.model.CurrencyValues;
import com.github.javafaker.Faker;

import java.util.Random;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Spend {

    private static final Faker faker = new Faker();
    private static final List<String> categories = List.of("Рестораны", "Продуктовые магазины", "Обучение в QA.GURU ADVANCED");

    public static SpendModel getRandom() {
        UUID id = UUID.randomUUID();
        Date spendDate = new Date();
        String category = categories.get(new Random().nextInt(categories.size()));
        CurrencyValues currency = CurrencyValues.randomCurrencyValues();
        Double amount = (double) faker.number().numberBetween(1, 500000);
        String description = faker.hobbit().location();
        String username = "dima";

        return new SpendModel(id, spendDate, category, currency, amount, description, username);
    }

}
