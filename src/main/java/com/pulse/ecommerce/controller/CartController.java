package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.Cart;
import com.pulse.ecommerce.model.UserRecord;
import com.pulse.ecommerce.service.AuthService;
import com.pulse.ecommerce.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final AuthService authService;

    public CartController(CartService cartService, AuthService authService) {
        this.cartService = cartService;
        this.authService = authService;
    }



    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam Long variantId,
                            @RequestParam(defaultValue = "1") int quantity) {
        UserRecord currentUser = authService.getCurrentUser();
        if (currentUser == null) return "redirect:/login";

        cartService.addToCart(currentUser, variantId, quantity);
        return "redirect:/cart/view";
    }



    @PostMapping("/item/update")
    public String updateCartItem(@RequestParam Long itemId,
                                 @RequestParam int quantity) {
        UserRecord user = authService.getCurrentUser();
        if (user == null) return "redirect:/login";

        cartService.updateQuantity(user, itemId, quantity);
        return "redirect:/cart/view";
    }

    @PostMapping("/item/delete")
    public String deleteCartItem(@RequestParam Long itemId) {
        UserRecord user = authService.getCurrentUser();
        if (user == null) return "redirect:/login";

        cartService.removeItem(user, itemId);
        return "redirect:/cart/view";
    }



    @GetMapping("/view")
    public String viewCart(Model model) {
        System.out.println("HIT /cart/view");   // add this
        UserRecord user = authService.getCurrentUser();
        if (user == null) return "redirect:/login";
        Cart cart = cartService.getActiveCart(user);
        model.addAttribute("cart", cart);
        return "cart";
    }
}
