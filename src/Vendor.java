import java.util.HashMap;

class Vending {
    private static HashMap<String, Item> Stock = new HashMap<String,Item>();
    private double balance;

    Vending(int numCandy, int numGum) {
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
            if (balance >= item.price) {
                item.purchase(1);
                this.balance = this.balance - item.price;
            }
            else
                System.out.println("Gimme more money");
        }
        else System.out.println("Sorry, don't know that item");
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
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            item.restock(amount);
        } else {
            Stock.put(name, new Item(price, amount));
        }
    }

    // Added method to rename items
    void renameItem(String oldName, String newName) {
        if (Stock.containsKey(oldName)) {
            Item item = Stock.get(oldName);
            Stock.remove(oldName);
            Stock.put(newName, item);
        }
    }

    HashMap<String, Item> getStock() {
        return Stock;
    }
}