package com.ShopShoe.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShopShoe.dto.CartIndexDTO;
import com.ShopShoe.entity.CartEntity;
import com.ShopShoe.entity.CartIndexEntity;
import com.ShopShoe.entity.ProductEntity;
import com.ShopShoe.entity.UserEntity;
import com.ShopShoe.service.CartIndexService;
import com.ShopShoe.service.CartService;
import com.ShopShoe.service.ProductService;
import com.ShopShoe.service.UserService;
import com.ShopShoe.service.Implements.UserDetailsImpl;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartIndexService cartIndexService;
	
	/**
	 * @Param : None
	 * @return list cart index in cart
	 */
	public UserEntity getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl u = (UserDetailsImpl) authentication.getPrincipal();
		UserEntity currentUser = userService.getById(u.getId());
		return  currentUser;
	}

	@GetMapping()
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public List<CartIndexDTO> getAllProductInCart() {
		try{
			CartEntity cart = cartService.findByUser(getCurrentUser());
			if(cart != null) {
				List<CartIndexDTO> listIndex = cartIndexService.findByCart(cart);
				return listIndex;
			}
			return null;
		}catch (Exception e){
			return null;
		}
	}
	
	/**
	 * @Content add product to cart
	 * @param id
	 * @return success or no product found
	 */
	@PostMapping("/addProduct")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public String addToCart(@RequestParam("idProduct") String id) {
		try {
			ProductEntity product = productService.getById(Long.parseLong(id));
			CartEntity cart = cartService.findByUser(getCurrentUser());
			
			if(cart == null)
			{
				cart = new CartEntity();
				cart.setUser(getCurrentUser());
				cart = cartService.save(cart);			
			}
			if(product == null){
				return "No products found";
			}else{
				CartIndexEntity cartIndex = cartIndexService.findByProductAndCart(product, cart);

				if(cartIndex == null){
					cartIndex = new CartIndexEntity();
					cartIndex.setCart(cart);
					cartIndex.setProduct(product);
					cartIndex.setAmount(1);
				}
				else{
					cartIndex.setAmount(cartIndex.getAmount()+1);
				}
				cartIndexService.save(cartIndex);
				return "success";
			}
		} catch (Exception e) {
			return "Error";
		}
	}
	
	/**
	 * @Content change number product in cart
	 * @param idProduct
	 * @param value
	 * @return
	 */
	@PostMapping("/changProductQuality")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public String changProductQuality(@RequestParam String idProduct, @RequestParam String value) {
		try{
			CartEntity cartEntity = cartService.findByUser(getCurrentUser());
			if(cartEntity == null)
			{
				cartEntity = new CartEntity();
				cartEntity.setUser(getCurrentUser());
				cartEntity = cartService.save(cartEntity);
			}
			ProductEntity product = productService.getById(Long.parseLong(idProduct));
			if(product == null){
				return "No products found";
			}else {
				CartIndexEntity cartIndexEntity = cartIndexService.findByProductAndCart(product, cartEntity);
				cartIndexEntity.setAmount(Integer.parseInt(value));
				cartIndexService.save(cartIndexEntity);
				return "Change success";
			}
		}catch (Exception e){
			return "Error";
		}
	}
	@PostMapping("/deleteProductQuality")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public String deleteProductFromCart(@RequestParam String idProduct) {
		try{
			CartEntity cartEntity = cartService.findByUser(getCurrentUser());
			if(cartEntity == null)
			{
				cartEntity = new CartEntity();
				cartEntity.setUser(getCurrentUser());
				cartEntity = cartService.save(cartEntity);
			}
			ProductEntity product = productService.getById(Long.parseLong(idProduct));
			if(product == null){
				return "No products found";
			}else {
				CartIndexEntity cartIndexEntity = cartIndexService.findByProductAndCart(product, cartEntity);
				if(cartIndexEntity == null){
					return "This cart index is not in the cart";
				}else {
					cartIndexService.delete(cartIndexEntity);
					return "Delete success";
				}
			}
		}catch (Exception e){
			return "Error";
		}
	}
}
