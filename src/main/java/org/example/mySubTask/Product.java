package org.example.mySubTask;

public class Product {
    private final Long id;
    private String name;
    private double price;
    private Double specificDiscount; // например для срока годности
    private ProductCategory category;

    public ProductBuilder builder;
    // Базовые вещи (конструктор, геттеры, сеттеры)

    public Product(Long id, String name, double price, Double specificDiscount, ProductCategory category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        if (specificDiscount == 0.0 || specificDiscount.isNaN()) {
            // предпологаемая обработка разных случаев
            // в упрощенном виде просто заменим во всех случаях на 0.0
            this.specificDiscount = 0.0;
        }
        else {
            this.specificDiscount = specificDiscount;
        }
    }


    public Double getSpecificDiscount() {
        return specificDiscount;
    }
    public void setSpecificDiscount(Double value){
        specificDiscount = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public Long getId() {
        return id;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }


    public static class ProductBuilder {
        Long id;
        String name; double price;
        Double specificDiscount;
        ProductCategory category;
        ProductBuilder(){
            this.id = null;
            this.name = "Nameless";
            this.price = Double.NaN;
            this.specificDiscount = 0.0;
            this.category = ProductCategory.OTHER;
        }

        //Сеттеры Строителя
        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setCategory(ProductCategory category) {
            this.category = category;
        }

        public void setSpecificDiscount(Double specificDiscount) {
            this.specificDiscount = specificDiscount;
        }

        public Product build() throws Exception {
            if (this.id == null || Double.isNaN(this.price)){
                throw new Exception("Не указан id или цена");
            }
            else {
                return new Product(id, name, price, specificDiscount, category);
            }
        }
    }

    public enum ProductCategory{
        OTHER,
        FRUIT

    }
}
