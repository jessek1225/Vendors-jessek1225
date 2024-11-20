import java.util.HashMap;

public class VendorSystem {
    private HashMap<String, Vendor> vendors;

    public VendorSystem() {
        vendors = new HashMap<>();
    }

    public void addVendor(String name, Vendor vendor) {
        vendors.put(name, vendor);
    }

    public Vendor getVendor(String name) {
        return vendors.get(name);
    }

    public void printAllInventories() {
        for (String vendorName : vendors.keySet()) {
            System.out.println("\nInventory for " + vendorName + ":");
            Vendor vendor = vendors.get(vendorName);
            for (String itemName : vendor.getStock().keySet()) {
                Item item = vendor.getStock().get(itemName);
                System.out.printf("%s - Stock: %d - Price: $%.2f",
                        itemName, item.stock, item.getCurrentPrice());
                if (item.isBestseller) {
                    System.out.print(" [BESTSELLER]");
                }
                if (item.discountPercentage > 0) {
                    System.out.printf(" (%.0f%% OFF)", item.discountPercentage);
                }
                System.out.println();
            }
        }
    }
}