import { CommonModule } from '@angular/common';
import { Component, OnInit, inject, signal } from "@angular/core";
import { FormsModule } from '@angular/forms';
import { CartService } from "app/cart/data-access/cart.service";
import { Product } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { InputNumberModule } from 'primeng/inputnumber';
import { RatingModule } from 'primeng/rating';

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [CommonModule, DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent, InputNumberModule, FormsModule, RatingModule],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  private readonly cartService = inject(CartService);

  public readonly products = this.productsService.products;
  public cartQuantities: { [key: number]: number } = {};
  public readonly cartItems = this.cartService.cartItems;

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  ngOnInit() {
    this.productsService.get().subscribe();
    this.initializeCartQuantities();
  }

  private initializeCartQuantities(): void {
    this.products().forEach(product => {
      this.cartQuantities[product.id] = this.getCartQuantity(product.id);
    });
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  public addToCart(product: Product) {
    const quantity = this.cartQuantities[product.id] || 1;
    if (quantity > 0) {
      this.cartService.addToCart(product, quantity);
      this.cartQuantities[product.id] = 1; // Reset to 1 after adding
    }
  }

  public updateCartQuantity(productId: number, quantity: any): void {
    const numQuantity = typeof quantity === 'number' ? quantity : 1;
    this.cartQuantities[productId] = numQuantity;
  }

  public getCartQuantity(productId: number): number {
    return this.cartService.getItemQuantity(productId);
  }

  public onImageError(event: any): void {
    event.target.src = 'assets/images/placeholder.svg';
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  public isInCart(productId: number): boolean {
    return this.cartItems().some(item => item.product.id === productId);
  }
}
