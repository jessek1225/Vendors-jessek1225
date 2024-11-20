
class Item {
    String name;   // Added name property
    double price;
    int stock;

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    void purchase(int amount) {
        this.stock = this.stock - amount;
    }

    // Added for renaming
    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}