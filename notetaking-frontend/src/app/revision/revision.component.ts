import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-revision',
  templateUrl: './revision.component.html'
})
export class RevisionComponent {
  questionType = 'one-word';
  questions = '';

  constructor(private http: HttpClient) {}

  generateQuestions() {
    this.http.post('http://localhost:8080/api/revision/questions', null, { params: { noteId: 'test-note', questionType: this.questionType } })
      .subscribe((data: any) => this.questions = data);
  }
}
