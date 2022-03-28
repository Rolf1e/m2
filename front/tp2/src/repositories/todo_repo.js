const TODO_STORE = 'TODOS';

export class TodoRepository {

  static insert(new_todo) {
    const todos = TodoRepository.get_all();
    const new_todos = todos.concat([new_todo]);
    window.localStorage.setItem(TODO_STORE, JSON.stringify(new_todos));
  }

  static get_all() {
    let todo_store = window.localStorage.getItem(TODO_STORE);
    if (todo_store === null) {
      todo_store = [];
      window.localStorage.setItem(TODO_STORE, JSON.stringify(todo_store));
      return [];
    }
    console.log(todo_store);
    return JSON.parse(todo_store);
  }

  static get(todo_name) {
    const store = TodoRepository.get_all();
    return store.filter(todo => todo.name === todo_name);
  }

  static delete(todo_id) {
    const store = TodoRepository.get_all();
    const new_store = store.filter(todo => todo.id !== todo_id);
    window.localStorage.setItem(TODO_STORE, JSON.stringify(new_store));
  }

}
