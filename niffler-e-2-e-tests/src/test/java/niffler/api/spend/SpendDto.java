package niffler.api.spend;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import niffler.models.user.Category;
import niffler.models.user.Currency;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public final class SpendDto extends BaseDto {

    private UUID id;
    private Date spendDate;
    private Category category;
    private Currency currency;
    private Double amount;
    private String description;
    private String username;

    public String toJson() {
        return GSON.toJson(this);
    }

}

