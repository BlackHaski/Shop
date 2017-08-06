package shop.functions;

import shop.entity.products.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public Cart() {
    }

    public void addToCart(Product product, int count){
        this.products.put(product,count);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}
