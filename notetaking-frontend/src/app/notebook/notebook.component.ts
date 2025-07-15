import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-notebook',
  templateUrl: './notebook.component.html'
})
export class NotebookComponent implements OnInit {
  notebooks: any[] = [];
  newNotebook: any = { title: '', userId: 'test-user' }; // Replace with authenticated user ID
  editingNotebook: any = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadNotebooks();
  }

  loadNotebooks() {
    this.http.get('http://localhost:8080/api/notebooks?userId=test-user')
      .subscribe((data: any) => this.notebooks = data);
  }

  createNotebook() {
    this.http.post('http://localhost:8080/api/notebooks', this.newNotebook)
      .subscribe((data: any) => {
        this.notebooks.push(data);
        this.newNotebook.title = '';
      });
  }

  editNotebook(notebook: any) {
    this.editingNotebook = { ...notebook };
  }

  updateNotebook() {
    this.http.put(`http://localhost:8080/api/notebooks/${this.editingNotebook.id}`, this.editingNotebook)
  .subscribe(() => {
  const index = this.notebooks.findIndex(n => n.id === this.editingNotebook.id);
  this.notebooks[index] = this.editingNotebook;
  this.editingNotebook = null;
});
}

cancelEdit() {
  this.editingNotebook = null;
}

deleteNotebook(id: string) {
  this.http.delete(`http://localhost:8080/api/notebooks/${id}`)
    .subscribe(() => {
      this.notebooks = this.notebooks.filter(n => n.id !== id);
    });
}
}
