package com.springapps.shop.service;


import com.springapps.shop.dtos.WishlistRequestDTO;
import com.springapps.shop.entities.Product;
import com.springapps.shop.entities.User;
import com.springapps.shop.entities.Wishlist;
import com.springapps.shop.entities.WishlistItem;
import com.springapps.shop.exceptions.ResourceNotFoundException;
import com.springapps.shop.repositories.ProductRepository;
import com.springapps.shop.repositories.UserRepository;
import com.springapps.shop.repositories.WishlistItemRepository;
import com.springapps.shop.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishlistService {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private WishlistItemRepository wishlistItemRepository;
    private WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(UserRepository userRepository, ProductRepository productRepository, WishlistItemRepository wishlistItemRepository, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.wishlistItemRepository = wishlistItemRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Transactional
    public Wishlist addItemToWishlist(WishlistRequestDTO wishlistRequestDTO) {
        User user = userRepository.findById(wishlistRequestDTO.getUserId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
        Product product = productRepository.findById(wishlistRequestDTO.getProductId()).orElseThrow(() -> new ResourceNotFoundException("product not found"));
        Wishlist whishlist = user.getWishlist();//wishlist-ul userului
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setProduct(product);//setez cheia straina id-ul produsului in tabelul de wishlistitem
        wishlistItem.setWishlist(whishlist);//setez cheia straina id-ul wishlist in tabelul de wishlistitem
        whishlist.getWishlistItems().add(wishlistItem);//adaug wishlistitem in lista de wishlistiem a wishlist-ului
        return wishlistRepository.save(whishlist);//salvez wishlist
    }
}
