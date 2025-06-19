import { computed, Injectable, signal } from "@angular/core";
import { Product } from "../../products/data-access/product.model";
import { CartItem } from "./cart.model";

@Injectable({
  providedIn: "root"
})
export class CartService {
  private readonly _cartItems = signal<CartItem[]>([]);

  public readonly cartItems = this._cartItems.asReadonly();
  
  public readonly cart = computed(() => {
    const items = this._cartItems();
    const totalItems = items.reduce((sum: number, item: CartItem) => sum + item.quantity, 0);
    const totalPrice = items.reduce((sum: number, item: CartItem) => sum + (item.product.price * item.quantity), 0);
    
    return {
      items,
      totalItems,
      totalPrice
    };
  });

  public addToCart(product: Product, quantity: number = 1): void {
    this._cartItems.update((items: CartItem[]) => [...items, { product, quantity }]);
  }

  public removeFromCart(productId: number): void {
    this._cartItems.update((items: CartItem[]) => items.filter((item: CartItem) => item.product.id !== productId));
  }

  public clearCart(): void {
    this._cartItems.set([]);
  }

  public getItemQuantity(productId: number): number {
    const item = this._cartItems().find((item: CartItem) => item.product.id === productId);
    return item ? item.quantity : 0;
  }

} 