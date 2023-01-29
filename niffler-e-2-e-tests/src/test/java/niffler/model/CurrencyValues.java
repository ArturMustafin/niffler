package niffler.model;

import java.util.Random;

public enum CurrencyValues {
    RUB, USD, EUR, KZT;

    private static final Random RANDOM = new Random();

    public static CurrencyValues randomCurrencyValues() {
        CurrencyValues[] CurrencyValues = values();
        return CurrencyValues[RANDOM.nextInt(CurrencyValues.length)];
    }

}
