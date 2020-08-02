package hristovski.nikola.common.shared.domain.model.all.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    @Column(name = "amount", nullable = false)
    private final Double amount;

    public Money(Currency currency, Double amount) {
        Objects.requireNonNull(currency, "Currency must not be null!");
        Objects.requireNonNull(amount, "Amount must not be null!");

        this.currency = currency;
        this.amount = amount;
    }

    public static Money valueOf(Currency currency, Double amount) {
        return new Money(currency, amount);
    }

    public Money() {
        this.currency = Currency.MKD;
        this.amount = 0d;
    }

    public Money plus(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency, amount + money.amount);
    }

    public Money minus(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency, amount - money.amount);
    }

    public Money times(long value) {
        return new Money(currency, amount * value);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return amount.equals(money.amount) &&
                currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

//    @Override
//    public String toString() {
//        return String.format("%s %d", currency, amount);
//    }


}
