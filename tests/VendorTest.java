import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendorTest {
    private Vendor vendor;

    @Before
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
}