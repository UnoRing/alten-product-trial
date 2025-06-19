import { Component, inject, signal } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MessageService } from "primeng/api";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { InputTextModule } from "primeng/inputtext";
import { InputTextareaModule } from "primeng/inputtextarea";
import { ToastModule } from "primeng/toast";

@Component({
  selector: "app-contact-form",
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CardModule,
    ButtonModule,
    InputTextModule,
    InputTextareaModule,
    ToastModule
  ],
  providers: [MessageService],
  templateUrl: "./contact-form.component.html",
  styleUrls: ["./contact-form.component.scss"]
})
export class ContactFormComponent {
  private readonly fb = new FormBuilder();
  private readonly messageService = inject(MessageService);
  
  public readonly isSubmitting = signal(false);
  
  public readonly contactForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    message: ['', [Validators.required, Validators.maxLength(300)]]
  });
  
  public isFieldInvalid(fieldName: string): boolean {
    const field = this.contactForm.get(fieldName);
    return field ? field.invalid && (field.dirty || field.touched) : false;
  }
  
  public onSubmit(): void {
    if (this.contactForm.valid) {
      this.isSubmitting.set(true);

      // Pretend to send
      console.log('Sending contact form', this.contactForm.value);
      
      setTimeout(() => {
        this.messageService.add({
          severity: 'success',
          summary: 'Succès',
          detail: 'Demande de contact envoyée avec succès'
        });
        
        this.contactForm.reset();
        this.isSubmitting.set(false);
      }, 1000);
    } else {
      Object.keys(this.contactForm.controls).forEach(key => {
        const control = this.contactForm.get(key);
        control?.markAsTouched();
      });
    }
  }
} 