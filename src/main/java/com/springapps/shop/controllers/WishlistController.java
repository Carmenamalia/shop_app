package com.springapps.shop.controllers;


import com.springapps.shop.dtos.WishlistRequestDTO;
import com.springapps.shop.entities.Wishlist;
import com.springapps.shop.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public ResponseEntity<Wishlist> addToWishlist(@RequestBody WishlistRequestDTO wishlistRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistService.addItemToWishlist(wishlistRequestDTO));
    }
}
