import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';
import { AppRoutingModule } from './app-routing.module';
import { MatSelectModule } from '@angular/material/select';
import { AppComponent } from './app.component';
import { QuillEditorModule } from './quill/quill.module';
import {NotebookComponent} from "./notebook/notebook.component";
import { NoteComponent } from "./note/note.component";
import { RevisionComponent } from './revision/revision.component';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';



@NgModule({
  declarations: [AppComponent, NotebookComponent, NoteComponent, RevisionComponent],
  imports: [
    MatSidenavModule,
    MatToolbarModule,
    BrowserModule,
    MatIconModule,
    MatSelectModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatListModule,
    QuillEditorModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
