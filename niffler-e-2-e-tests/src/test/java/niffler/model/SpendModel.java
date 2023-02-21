package niffler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.javafaker.Faker;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpendModel {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date spendDate;
    private String category;
    private CurrencyValues currency;
    private Double amount;
    private String description;
    private String username;

    public static SpendModel randomSpend() {
        Faker faker = new Faker();
        return SpendModel.builder()
                .spendDate(new Date())
                .category(Category.randomCategory())
                .currency(CurrencyValues.randomCurrencyValues())
                .amount((double) faker.number().numberBetween(1, 500000))
                .description(faker.hobbit().location())
                .username("dima").build();
    }

}
