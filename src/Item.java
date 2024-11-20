class Item {
    double price;
    int stock;
    int purchaseCount;
    String description;
    boolean isBestseller;
    double discountPercentage;

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
        this.purchaseCount = 0;
        this.description = "";
        this.isBestseller = false;
        this.discountPercentage = 0.0;
    }

    Item(double price, int numPieces, String description) {
        this(price, numPieces);
        this.description = description;
    }

    double getCurrentPrice() {
        return price * (1 - discountPercentage / 100.0);
    }

    void setDiscount(double percentage) {
        this.discountPercentage = percentage;
    }

    void setBestseller(boolean status) {
        this.isBestseller = status;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    void purchase(int amount) {
        this.stock = this.stock - amount;
        this.purchaseCount = this.purchaseCount + amount;
    }

    int getPurchaseCount() {
        return purchaseCount;
    }
}