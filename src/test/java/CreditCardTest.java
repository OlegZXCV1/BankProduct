import org.example.card.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CreditCardTest {

    @Test
    public void testTopUp() {
        CreditCard card = new CreditCard("USD", BigDecimal.valueOf(100), "Standard Credit Card", 5);
        card.topUp(BigDecimal.valueOf(50));
        Assertions.assertEquals(BigDecimal.valueOf(150), card.getBalance());
    }

    @Test
    public void testWithdrawAndDebt() {
        CreditCard card = new CreditCard("USD", BigDecimal.valueOf(100), "Standard Credit Card", 5);
        card.withdraw(BigDecimal.valueOf(100));
        Assertions.assertEquals(BigDecimal.valueOf(105).setScale(2), card.getDebt());
    }
}
