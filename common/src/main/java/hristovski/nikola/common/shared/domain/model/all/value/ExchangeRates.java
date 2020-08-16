package hristovski.nikola.common.shared.domain.model.all.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class ExchangeRates {

    public static Map<Currency, Map<Currency, Double>> exchangeRates;

    static {
        exchangeRates = new HashMap<>();

        Map<Currency, Double> eurMap = new HashMap<>();
        eurMap.put(Currency.MKD, 61.77d);
        eurMap.put(Currency.USD, 1.18d);
        eurMap.put(Currency.EUR, 1.0d);
        exchangeRates.put(Currency.EUR, eurMap);

        Map<Currency, Double> usdMap = new HashMap<>();
        usdMap.put(Currency.MKD, 52.16d);
        usdMap.put(Currency.USD, 1.0d);
        usdMap.put(Currency.EUR, 0.84d);
        exchangeRates.put(Currency.USD, usdMap);

        Map<Currency, Double> mkdMap = new HashMap<>();
        mkdMap.put(Currency.MKD, 1.0d);
        mkdMap.put(Currency.USD, 0.019d);
        mkdMap.put(Currency.EUR, 0.016d);
        exchangeRates.put(Currency.MKD, mkdMap);
    }

}
