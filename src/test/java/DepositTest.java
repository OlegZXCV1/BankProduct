import org.example.deposit.Deposit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DepositTest {

    @Test
    public void testTopUp() {
        Deposit deposit = new Deposit("USD", BigDecimal.valueOf(1000), "Savings Deposit");
        deposit.topUp(BigDecimal.valueOf(500));
        Assertions.assertEquals(BigDecimal.valueOf(1500), deposit.getBalance());
    }

    @Test
    public void testClose() {
        Deposit deposit = new Deposit("USD", BigDecimal.valueOf(1000), "Savings Deposit");
        deposit.close();
        Assertions.assertEquals(BigDecimal.valueOf(0), deposit.getBalance());
    }
}
