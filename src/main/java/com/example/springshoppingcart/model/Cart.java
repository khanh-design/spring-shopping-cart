package com.example.springshoppingcart.model;

import java.util.HashMap;
import java.util.Map;
/*
* checkItemInCart(): kiem tra trong gio hang co san pham khong
* selectItemInCart(): Chon san pham can mua
* addProduct(): Them san pham vao gio hang
   * neu co mot san san pham thi cong don san pham cu
   * neu khong co san pham cu thi tien hanh them san pham moi
* countProductQuantity(): Dem so luong san pham
* countItemQuantity(): Dem tong so luong san pham
* countTotalPayment(): Tinh tong tien can thanh toan co trong gio hang
* */
public class Cart {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public Cart() {

    }

    public Cart(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }


    public boolean checkItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product)) {
                return true;
            }
        }
        return false;
    }

    public Map.Entry<Product, Integer> selectItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product)) {
                return entry;
            }
        }
        return null;
    }


    public void addProduct(Product product) {
        if (!checkItemInCart(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(), newQuantity);
        }
    }


    public Integer countProductQuantity() {
        Integer productQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    public Integer countItemQuantity() {
        return products.size();
    }


    public Float countTotalPayment() {
        float payment = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            payment += (float) (entry.getKey().getPrice() * entry.getValue());
        }
        return payment;
    }

}
