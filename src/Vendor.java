import java.util.HashMap;

class Vendor {
    private HashMap<String, Item> Stock;
    private double balance;

    Vendor(int numCandy, int numGum) {
        Stock = new HashMap<String,Item>();
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = 0;
    }

    void resetBalance() {
        this.balance = 0;
    }

    double getBalance() {
        return this.balance;
    }

    void addMoney(double amt) {
        this.balance = this.balance + amt;
    }

    void select(String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            double currentPrice = item.getCurrentPrice();
            if (balance >= currentPrice && item.stock > 0) {
                item.purchase(1);
                this.balance = this.balance - currentPrice;
            }
            else if (item.stock <= 0) {
                System.out.println("Item out of stock");
            }
            else {
                System.out.println("Insufficient funds");
            }
        }
        else System.out.println("Item not found");
    }

    void emptyInventory() {
        Stock.clear();
    }

    void restockItem(String name, int amount) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            item.restock(amount);
        }
    }

    void restockItem(String name, double price, int amount) {
        restockItem(name, price, amount, "");
    }

    void restockItem(String name, double price, int amount, String description) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            item.restock(amount);
        } else {
            Stock.put(name, new Item(price, amount, description));
        }
    }

    void renameItem(String oldName, String newName) {
        if (Stock.containsKey(oldName)) {
            Item item = Stock.get(oldName);
            Stock.remove(oldName);
            Stock.put(newName, item);
        }
    }

    void removeItem(String name) {
        Stock.remove(name);
    }

    void applyDiscount(String name, double percentage) {
        if (Stock.containsKey(name)) {
            Stock.get(name).setDiscount(percentage);
        }
    }

    void markAsBestseller(String name, boolean status) {
        if (Stock.containsKey(name)) {
            Stock.get(name).setBestseller(status);
        }
    }

    void printInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String itemName : Stock.keySet()) {
            Item item = Stock.get(itemName);
            System.out.printf("%s - Stock: %d - Price: $%.2f - Sold: %d",
                    itemName, item.stock, item.getCurrentPrice(), item.getPurchaseCount());
            if (item.isBestseller) {
                System.out.print(" [BESTSELLER]");
            }
            if (item.discountPercentage > 0) {
                System.out.printf(" (%.0f%% OFF)", item.discountPercentage);
            }
            System.out.println("\nDescription: " + item.getDescription());
        }
    }

    HashMap<String, Item> getStock() {
        return Stock;
    }
}