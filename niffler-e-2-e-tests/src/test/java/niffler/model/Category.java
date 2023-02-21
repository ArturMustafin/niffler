package niffler.model;

import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public enum Category {

    RESTAURANTS("Рестораны"),
    SHOP("Продуктовые магазины"),
    EDUCATION("Обучение в QA.GURU ADVANCED");

    private final String description;

    private static final Random RANDOM = new Random();

    public static String randomCategory() {
        Category[] Category = values();
        return Category[RANDOM.nextInt(Category.length)].description;
    }

}
