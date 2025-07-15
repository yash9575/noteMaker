import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html'
})
export class NoteComponent implements OnInit {
  notes: any[] = [];
  newNote: any = { title: '', content: '', tags: '', notebookId: 'test-notebook' };
  editingNote: any = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadNotes();
  }

  loadNotes() {
    this.http.get('http://localhost:8080/api/notes?notebookId=test-notebook', { withCredentials: true })
      .subscribe((data: any) => this.notes = data);
  }

  createNote() {
    const note = { ...this.newNote, tags: this.newNote.tags.split(',').map((tag: string) => tag.trim()) };
    this.http.post('http://localhost:8080/api/notes', note, { withCredentials: true })
      .subscribe((data: any) => {
        this.notes.push(data);
        this.newNote = { title: '', content: '', tags: '', notebookId: 'test-notebook' };
      });
  }

  editNote(note: any) {
    this.editingNote = { ...note, tags: note.tags.join(', ') };
  }

  updateNote() {
    const note = { ...this.editingNote, tags: this.editingNote.tags.split(',').map((tag: string) => tag.trim()) };
    this.http.put(`http://localhost:8080/api/notes/${this.editingNote.id}`, note, { withCredentials: true })
  .subscribe(() => {
  const index = this.notes.findIndex(n => n.id === this.editingNote.id);
  this.notes[index] = note;
  this.editingNote = null;
});
}

cancelEdit() {
  this.editingNote = null;
}

deleteNote(id: string) {
  this.http.delete(`http://localhost:8080/api/notes/${id}`, { withCredentials: true })
    .subscribe(() => {
      this.notes = this.notes.filter(n => n.id !== id);
    });
}
}
