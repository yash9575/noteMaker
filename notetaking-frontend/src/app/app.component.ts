import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  isAuthenticated = false;
  user: any = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.checkAuth();
  }

  login() {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  }

  logout() {
    document.cookie = 'jwt=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    this.isAuthenticated = false;
    this.user = null;
  }

  checkAuth() {
    this.http.get('http://localhost:8080/api/notebooks', { withCredentials: true })
      .subscribe({
        next: () => {
          this.isAuthenticated = true;
          this.user = { id: 'authenticated-user' }; // Replace with actual user data if needed
        },
        error: () => {
          this.isAuthenticated = false;
        }
      });
  }
}
