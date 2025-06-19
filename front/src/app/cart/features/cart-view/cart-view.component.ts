import { CommonModule } from "@angular/common";
import { Component, inject } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { InputNumberModule } from "primeng/inputnumber";
import { CartService } from "../../data-access/cart.service";

@Component({
  selector: "app-cart-view",
  templateUrl: "./cart-view.component.html",
  styleUrls: ["./cart-view.component.scss"],
  standalone: true,
  imports: [CommonModule, CardModule, ButtonModule, InputNumberModule, FormsModule],
})
export class CartViewComponent {
  private readonly cartService = inject(CartService);
  
  public readonly cart = this.cartService.cart;
  
  public removeFromCart(productId: number): void {
    this.cartService.removeFromCart(productId);
  }
  
  public clearCart(): void {
    this.cartService.clearCart();
  }

} 