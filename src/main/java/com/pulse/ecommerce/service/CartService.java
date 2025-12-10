package com.pulse.ecommerce.service;
import com.pulse.ecommerce.model.Cart;
import com.pulse.ecommerce.model.CartItem;
import com.pulse.ecommerce.model.ProductVariant;
import com.pulse.ecommerce.model.UserRecord;
import com.pulse.ecommerce.repository.CartItemRepository;
import com.pulse.ecommerce.repository.CartRepository;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final ProductVariantRepository variantRepo;

    public CartService(CartRepository cartRepo,
                       CartItemRepository cartItemRepo,
                       ProductVariantRepository variantRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.variantRepo = variantRepo;
    }

    @Transactional
    public void addToCart(UserRecord user, Long variantId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }

        ProductVariant variant = variantRepo.findById(variantId)
                .orElseThrow(() -> new IllegalArgumentException("Variant not found"));

        Cart cart = cartRepo.findByCustomerAndStatus(user, "ACTIVE")
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setCustomer(user);
                    c.setStatus("ACTIVE");
                    c.setCreatedAt(LocalDateTime.now());
                    c.setUpdatedAt(LocalDateTime.now());
                    return cartRepo.save(c);
                });

        CartItem item = cartItemRepo.findByCartAndVariant(cart, variant)
                .orElse(null);

        if (item == null) {
            item = new CartItem();
            item.setCart(cart);
            item.setVariant(variant);
            item.setQuantity(quantity);

            // IMPORTANT: keep collection in sync
            cart.getCartItems().add(item);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }

        cartItemRepo.save(item);

        cart.setUpdatedAt(LocalDateTime.now());
        cartRepo.save(cart);
    }

    @Transactional
    public void updateQuantity(UserRecord user, Long cartItemId, int newQuantity) {
        if (newQuantity <= 0) {
            removeItem(user, cartItemId);
            return;
        }

        CartItem item = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));

        // optional safety: ensure this item belongs to this user’s ACTIVE cart
        if (!item.getCart().getCustomer().getUserId().equals(user.getUserId())
                || !"ACTIVE".equals(item.getCart().getStatus())) {
            throw new IllegalStateException("Cannot modify another user's cart");
        }

        item.setQuantity(newQuantity);
        cartItemRepo.save(item);
    }
    @Transactional
    public void clearCart(Cart cart) {
        cart.getCartItems().clear();
        // if using CartItemRepo, deleteByCart(cart) etc.
        cart.setStatus("COMPLETED");
        cartRepo.save(cart);
    }

    @Transactional
    public void removeItem(UserRecord user, Long cartItemId) {
        CartItem item = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));

        if (!item.getCart().getCustomer().getUserId().equals(user.getUserId())
                || !"ACTIVE".equals(item.getCart().getStatus())) {
            throw new IllegalStateException("Cannot modify another user's cart");
        }

        cartItemRepo.delete(item);
    }
    public Cart getActiveCart(UserRecord user) {
        return cartRepo.findByCustomerAndStatus(user, "ACTIVE")
                .orElse(null);   // null = no cart yet
    }

}