import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient }    from '@angular/common/http';

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
    this.http.get<TodoModel[]>(environment.backend + "/todos").subscribe(data => {
      this.todoArray = data
    });
  }

  addTodo(value:string){
    var newTodo = <TodoModel>{
      content: value,
      id: 0
    }
    this.todoArray.push(newTodo)
    console.log(this.todoArray)
  }
  deleteItem(todo){
    for(let i=0 ;i<= this.todoArray.length ;i++){
      if(todo.content == this.todoArray[i]){ 
        this.todoArray.splice(i,1)
      }
    }
  }

  todoSubmit(value:any){
    if(value!==""){
      this.todoArray.push(value.todo)
      //this.todoForm.reset()
    }else{
      alert('Field required **')
    }
  }
}
