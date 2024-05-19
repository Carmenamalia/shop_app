package com.springapps.shop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference("wishlistitem-product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    @JsonBackReference("wishlistitem-wishlist")
    private Wishlist wishlist;

    public WishlistItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    @Override
    public String toString() {
        return "WishlistItem{" +
                "id=" + id +
                ", product=" + product +
                ", wishlist=" + wishlist +
                '}';
    }
}
