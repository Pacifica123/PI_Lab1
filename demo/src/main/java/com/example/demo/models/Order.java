package com.example.demo.models;

public record Order(
        Long id,
        String email,
        String cardNum,
        String nameOwner,
        String paymentService,
        Long costBefore,
        Long costAfter,
        String productId,
        String address
) {
    public class OrderBuilder{
        public OrderBuilder(){
            id = null;
        }
        Long id;
        String email;
        String cardNum;
        String nameOwner;
        String paymentService;
        Long costBefore;
        Long costAfter;
        String productId;
        String address;

        public void setEmail(String email) {
            this.email = email;
        }
        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }
        public void setCostAfter(Long costAfter) {
            this.costAfter = costAfter;
        }
        public void setCostBefore(Long costBefore) {
            this.costBefore = costBefore;
        }
        public void setNameOwner(String nameOwner) {
            this.nameOwner = nameOwner;
        }
        public void setPaymentService(String paymentService) {
            this.paymentService = paymentService;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Order build(){
            return new Order(id, email, cardNum, nameOwner, paymentService, costBefore, costAfter, productId, address);
        }
    }
}
