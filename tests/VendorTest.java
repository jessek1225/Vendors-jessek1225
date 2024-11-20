
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendorTest {
    private Vending vendor;

    @BeforeEach
    void setUp() {
        vendor = new Vending(10, 10);
    }

    @Test
    void testAddMoney() {
        vendor.addMoney(5.0);
        assertEquals(5.0, vendor.getBalance());
    }

    @Test
    void testBuyItem() {
        vendor.addMoney(2.0);
        vendor.select("Candy");
        assertEquals(0.75, vendor.getBalance());
    }

    @Test
    void testEmptyInventory() {
        vendor.emptyInventory();
        assertTrue(vendor.getStock().isEmpty());
    }

    @Test
    void testRestockItem() {
        vendor.restockItem("Candy", 5);
        Item candy = vendor.getStock().get("Candy");
        assertEquals(15, candy.stock);
    }

    @Test
    void testAddNewItemToInventory() {
        vendor.restockItem("Chips", 1.00, 5);
        assertTrue(vendor.getStock().containsKey("Chips"));
        assertEquals(5, vendor.getStock().get("Chips").stock);
    }

    // New test for renaming items
    @Test
    void testRenameItem() {
        vendor.renameItem("Candy", "Chocolate");
        assertNull(vendor.getStock().get("Candy"));
        assertNotNull(vendor.getStock().get("Chocolate"));
    }
}