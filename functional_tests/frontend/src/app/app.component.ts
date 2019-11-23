import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient }    from '@angular/common/http';
import {Observable} from 'rxjs';

interface TodoModel {
  content: string;
  id: number;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  todoArray:TodoModel[]=[]

  constructor(private http: HttpClient){
  }

  ngOnInit(): void {
    this.updateTodos()
  }

  addTodo(value:string){
    var newTodo = <TodoModel>{
      content: value,
      id: 0
    }
    this.todoArray.push(newTodo)
    console.log(this.todoArray)
    this.saveTodo(newTodo).subscribe(data => {
      this.updateTodos()
    })
  }

  deleteItem(todo){
    this.deleteTodo(todo).subscribe(data => {
      this.updateTodos()
    })
  }

  todoSubmit(value:any){
    if(value!==""){
      this.todoArray.push(value.todo)
      //this.todoForm.reset()
    }else{
      alert('Field required **')
    }
  }

  saveTodo(todo:TodoModel) : Observable<TodoModel> {
    return this.http.post<TodoModel>(environment.backend + "/todos", todo)
  }

  deleteTodo(todo:TodoModel) : Observable<TodoModel> {
    return this.http.delete<TodoModel>(environment.backend + "/todos/" + todo.id)
  }

  updateTodos() {
    this.http.get<TodoModel[]>(environment.backend + "/todos").subscribe(data => {
      this.todoArray = data
    });
  }
}
