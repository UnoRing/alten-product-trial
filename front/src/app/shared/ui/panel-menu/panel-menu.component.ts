import {
    Component,
    EventEmitter,
    Output
} from "@angular/core";
import { MenuItem } from "primeng/api";
import { PanelMenuModule } from 'primeng/panelmenu';
  
  @Component({
    selector: "app-panel-menu",
    standalone: true,
    imports: [PanelMenuModule],
    template: `
        <p-panelMenu [model]="items" styleClass="w-full" (itemClick)="onMenuItemClick()" />
    `
  })
  export class PanelMenuComponent {
    @Output() menuItemClicked = new EventEmitter<void>();

    public readonly items: MenuItem[] = [
        {
            label: 'Accueil',
            icon: 'pi pi-home',
            routerLink: ['/home']
        },
        {
            label: 'Produits',
            icon: 'pi pi-barcode',
            routerLink: ['/products/list']
        },
        {
            label: 'Panier',
            icon: 'pi pi-shopping-cart',
            routerLink: ['/cart/view']
        },
        {
            label: 'Contact',
            icon: 'pi pi-envelope',
            routerLink: ['/contact/form']
        }
    ]

    onMenuItemClick() {
      this.menuItemClicked.emit();
    }
  }
  