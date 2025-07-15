import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  isAuthenticated = false;

  login() {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  }
}
