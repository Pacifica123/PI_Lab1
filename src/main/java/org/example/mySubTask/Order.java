package org.example.mySubTask;

public class Order {
    private ShoppingCart cart;
    private double totalCost;
    private double totalCostWithDiscount;
    boolean isCard;
    public Order(ShoppingCart cart, boolean isCardPay){
        this.cart = cart;
        this.totalCost = CostCalculator.calcTotal(cart);
        isCard = isCardPay;
    }
    public void placeAnOrder(){
        // логика добавления записи о заказе в БД
        this.totalCostWithDiscount = CostCalculator.calcWithDiscount(cart, isCard);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTotalCostWithDiscount() {
        return totalCostWithDiscount;
    }

    public static class OrderBuilder{
        private ShoppingCart cart;
        private double totalCost;
        private double totalCostWithDiscount;
        boolean isCard;
        public OrderBuilder(){
            cart = new ShoppingCart();
            isCard = false;
        }

        public void setCard(boolean card) {
            isCard = card;
        }

        public void setCart(ShoppingCart cart) {
            this.cart = cart;
        }
        public Order build(){
            return new Order(cart, isCard);
        }
    }
}
