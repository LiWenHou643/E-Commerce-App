package com.example.application.controller;

import com.example.application.config.Jwt.JwtUtils;
import com.example.application.dto.ApiResponse;
import com.example.application.dto.CartDTO;
import com.example.application.dto.CartItemDTO;
import com.example.application.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
    CartService cartService;
JwtUtils jwtUtils;
    @GetMapping("/carts")
    public ResponseEntity<ApiResponse<CartDTO>> getCartById(Authentication authentication, HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            Jwt jwt = jwtUtils.decode(token);
            boolean isExpired = jwtUtils.isExpired(jwt);
            int duration = jwtUtils.getDuration(jwt);
            System.out.println("Token: " + token);
        }
        Long userId = getUserId(authentication);
        var cart = cartService.getCartById(userId);

        return ResponseEntity.ok(
                ApiResponse.<CartDTO>builder()
                           .status("success")
                           .message("Cart retrieved successfully")
                           .data(cart)
                           .build()
        );
    }

    @PostMapping("/carts/items")
    public ResponseEntity<ApiResponse<CartDTO>> addItemToCart(@RequestBody CartItemDTO cartItemDTO, Authentication authentication) {
        Long userId = getUserId(authentication);
        var cart = cartService.addItemToCart(userId, cartItemDTO);

        return ResponseEntity.ok(
                ApiResponse.<CartDTO>builder()
                           .status("success")
                           .message("Item added to cart successfully")
                           .data(cart)
                           .build()
        );
    }

    @PutMapping("/carts/items")
    public ResponseEntity<ApiResponse<CartDTO>> updateItemInCart(@RequestBody CartItemDTO cartItemDTO, Authentication authentication) {
        Long userId = getUserId(authentication);
        var cart = cartService.updateItemInCart(userId, cartItemDTO);

        return ResponseEntity.ok(
                ApiResponse.<CartDTO>builder()
                           .status("success")
                           .message("Item updated in cart successfully")
                           .data(cart)
                           .build()
        );
    }

    @DeleteMapping("/carts/items/{productItemId}")
    public ResponseEntity<ApiResponse<CartDTO>> removeItemFromCart(@PathVariable Long productItemId, Authentication authentication) {
        Long userId = getUserId(authentication);
        var cart = cartService.removeItemFromCart(userId, productItemId);

        return ResponseEntity.ok(
                ApiResponse.<CartDTO>builder()
                           .status("success")
                           .message("Item removed from cart successfully")
                           .data(cart)
                           .build()
        );
    }

    private Long getUserId(Authentication authentication) {
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim("userId");
        }

        return null;
    }

}
