import { Routes } from "@angular/router";
import { CartViewComponent } from "./features/cart-view/cart-view.component";

export const CART_ROUTES: Routes = [
  {
    path: "view",
    component: CartViewComponent,
  },
  { path: "**", redirectTo: "view" },
]; 