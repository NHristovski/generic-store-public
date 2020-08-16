package hristovski.nikola.shopping_cart.domain.task;

import hristovski.nikola.shopping_cart.domain.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartClearerTask {

    private final ShoppingCartService shoppingCartService;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void clearUnusedShoppingCarts() {
        log.info("Started clearUnusedShoppingCarts cron job");
        this.shoppingCartService.clearUnusedShoppingCarts();
    }
}
