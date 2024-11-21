import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendorTest {
    private Vendor vendor;

    @BeforeEach
    public void setUp() {
        vendor = new Vendor(10, 10);
    }

    @Test
    public void testAddMoney() {
        vendor.addMoney(5.0);
        assertEquals(5.0, vendor.getBalance(), 0.001);
    }

    @Test
    public void testBuyItem() {
        vendor.addMoney(2.0);
        vendor.select("Candy");
        assertEquals(0.75, vendor.getBalance(), 0.001);
    }

    @Test
    public void testEmptyInventory() {
        vendor.emptyInventory();
        assertTrue(vendor.getStock().isEmpty());
    }

    @Test
    public void testRestockItem() {
        vendor.restockItem("Candy", 5);
        assertEquals(15, vendor.getStock().get("Candy").stock);
    }

    @Test
    public void testAddNewItem() {
        vendor.restockItem("Chips", 2.0, 5, "Tasty chips");
        assertTrue(vendor.getStock().containsKey("Chips"));
        assertEquals("Tasty chips", vendor.getStock().get("Chips").getDescription());
    }

    @Test
    public void testDiscount() {
        vendor.restockItem("Soda", 2.0, 5);
        vendor.applyDiscount("Soda", 50.0);
        assertEquals(1.0, vendor.getStock().get("Soda").getCurrentPrice(), 0.001);
    }

    @Test
    public void testBestseller() {
        vendor.restockItem("Book", 10.0, 5);
        vendor.markAsBestseller("Book", true);
        assertTrue(vendor.getStock().get("Book").isBestseller);
    }

    @Test
    public void testPurchaseTracking() {
        vendor.addMoney(5.0);
        vendor.select("Candy");
        assertEquals(1, vendor.getStock().get("Candy").getPurchaseCount());
        assertEquals(9, vendor.getStock().get("Candy").stock);
    }

    @Test
    public void testMultipleItemPurchases() {
        vendor.addMoney(10.0);
        vendor.select("Candy");
        vendor.select("Candy");
        vendor.select("Gum");
        assertEquals(2, vendor.getStock().get("Candy").getPurchaseCount());
        assertEquals(1, vendor.getStock().get("Gum").getPurchaseCount());
    }

    @Test
    public void testInsufficientFundsHandling() {
        vendor.addMoney(0.25);
        vendor.select("Candy");
        assertEquals(0.25, vendor.getBalance(), 0.001);
        assertEquals(0, vendor.getStock().get("Candy").getPurchaseCount());
    }

    @Test
    public void testOutOfStockPurchase() {
        vendor.getStock().get("Candy").stock = 1;
        vendor.addMoney(5.0);
        vendor.select("Candy");
        vendor.select("Candy");
        assertEquals(1, vendor.getStock().get("Candy").getPurchaseCount());
        assertEquals(0, vendor.getStock().get("Candy").stock);
    }

    @Test
    public void testRenameItem() {
        vendor.renameItem("Candy", "Sweets");
        assertTrue(vendor.getStock().containsKey("Sweets"));
        assertFalse(vendor.getStock().containsKey("Candy"));
    }

    @Test
    public void testRemoveItem() {
        vendor.removeItem("Candy");
        assertFalse(vendor.getStock().containsKey("Candy"));
        assertTrue(vendor.getStock().containsKey("Gum"));
    }

    @Test
    public void testCheckItemDescription() {
        vendor.restockItem("Snack", 1.5, 5, "Delicious treat");
        assertEquals("Delicious treat", vendor.getStock().get("Snack").getDescription());
    }

    @Test
    public void testUpdateItemDescription() {
        vendor.restockItem("Snack", 1.5, 5, "Old description");
        vendor.getStock().get("Snack").setDescription("New description");
        assertEquals("New description", vendor.getStock().get("Snack").getDescription());
    }

    @Test
    public void testPurchaseHistory() {
        vendor.addMoney(10.0);
        vendor.select("Candy");
        vendor.select("Gum");
        vendor.select("Candy");
        assertEquals(2, vendor.getStock().get("Candy").getPurchaseCount());
        assertEquals(1, vendor.getStock().get("Gum").getPurchaseCount());
    }
}