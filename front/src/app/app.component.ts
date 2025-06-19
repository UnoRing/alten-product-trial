import { CommonModule } from "@angular/common";
import { Component, inject } from "@angular/core";
import { RouterModule, RouterOutlet } from "@angular/router";
import { SplitterModule } from "primeng/splitter";
import { ToolbarModule } from "primeng/toolbar";
import { CartService } from "./cart/data-access/cart.service";
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";

@Component({
  selector: "app-root",
  standalone: true,
  imports: [RouterOutlet, RouterModule, ToolbarModule, SplitterModule, PanelMenuComponent, CommonModule],
  templateUrl: "./app.component.html",
  styleUrl: "./app.component.scss",
})
export class AppComponent {
  private readonly cartService = inject(CartService);
  
  public readonly title = "The Shop Of The Alten";
  public readonly cart = this.cartService.cart;

  public sidebarCollapsed = true;

  public toggleSidebar() {
    this.sidebarCollapsed = !this.sidebarCollapsed;
  }

  public collapseSidebar() {
    this.sidebarCollapsed = true;
  }
}
