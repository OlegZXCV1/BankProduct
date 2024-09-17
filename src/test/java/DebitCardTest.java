import org.example.card.DebitCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DebitCardTest {

    @Test
    public void testTopUp() {
        DebitCard card = new DebitCard("USD", BigDecimal.valueOf(100), "Standard Debit Card");
        card.topUp(BigDecimal.valueOf(50));
        Assertions.assertEquals(BigDecimal.valueOf(150), card.getBalance());
    }

    @Test
    public void testWithdraw() {
        DebitCard card = new DebitCard("USD", BigDecimal.valueOf(100), "Standard Debit Card");
        card.withdraw(BigDecimal.valueOf(30));
        Assertions.assertEquals(BigDecimal.valueOf(70), card.getBalance());
    }

    @Test
    public void testInsufficientFunds() {
        DebitCard card = new DebitCard("USD", BigDecimal.valueOf(100), "Standard Debit Card");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            card.withdraw(BigDecimal.valueOf(150));
        });
    }
}
