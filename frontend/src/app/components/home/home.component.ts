import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true, // Importante para evitar app.module.ts
  imports: [FormsModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  user = {
    name: '',
    email: '',
    password: ''
  };
  message: string = '';

  constructor(private http: HttpClient) {}

  onSubmit() {
    this.http.post('http://localhost:8080/register', this.user).subscribe({
      next: (response) => {
        this.message = 'Registro exitoso';
        console.log('Respuesta del backend:', response);
      },
      error: (error) => {
        this.message = 'Error al registrar usuario';
        console.error('Error:', error);
      }
    });
  }
}
