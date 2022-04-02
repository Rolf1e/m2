import './App.css';
import Todo from "./components/Todo";
import Form from "./components/Form";
import FilterButton from "./components/FilterButton";
import React, { useEffect, useState } from "react";
import { nanoid } from "nanoid";
import { TodoRepository } from './repositories/todo_repo';
import Stack from "@mui/material/Stack";
import Item from "@mui/material/Stack";
import { ButtonGroup } from '@mui/material';

const FILTER_MAP = {
  All: () => true,
  Active: task => !task.completed,
  Completed: task => task.completed
};

const FILTER_NAMES = Object.keys(FILTER_MAP);

function App() {

  const [tasks, setTasks] = useState([]);
  const [filter, setFilter] = useState('All');

  function addTask(name, description) {
    if (name !== "" && description !== "") {
      const newTask = { id: "todo-" + nanoid(), name: name, description: description, completed: false };
      TodoRepository.insert(newTask);
      setTasks([...tasks, newTask]);
    } else {
      alert('Invalid task name');
    }
  }

  function toggleTaskCompleted(id) {
    const updatedTasks = tasks.map(task => {
      // if this task has the same ID as the edited task
      if (id === task.id) {
        // use object spread to make a new object
        // whose `completed` prop has been inverted
        return { ...task, completed: !task.completed }
      }
      return task;
    });
    setTasks(updatedTasks);
  }

  function loadTodos() {
    return TodoRepository.get_all();
  }

  useEffect(() => {
    setTasks(loadTodos())
  }, []);


  function deleteTask(id) {
    const remainingTasks = tasks.filter(task => id !== task.id);
    TodoRepository.delete(id);
    setTasks(remainingTasks);
  }

  function editTask(id, newName) {
    const editedTaskList = tasks.map(task => {
      // if this task has the same ID as the edited task
      if (id === task.id) {
        TodoRepository.delete(id);
        const newTask = { ...task, name: newName };
        TodoRepository.insert(newTask);
        return newTask;

      }
      return task;
    });

    setTasks(editedTaskList);
  }

  const taskList = tasks
    .filter(FILTER_MAP[filter])
    .map(task => (
      <Todo
        id={task.id}
        name={task.name}
        description={task.description}
        completed={task.completed}
        key={task.id}
        toggleTaskCompleted={toggleTaskCompleted}
        deleteTask={deleteTask}
        editTask={editTask}
      />
    ));


  const filterList = FILTER_NAMES.map(name => (
    <FilterButton
      key={name}
      name={name}
      isPressed={name === filter}
      setFilter={setFilter}
    />
  ));

  // count task in list
  const tasksNoun = taskList.length !== 1 ? 'tasks' : 'task';
  const headingText = `${taskList.length} ${tasksNoun} remaining`;

  return (
    <div className="todoapp stack-large">
      <h1>Todo list</h1>
      <Stack>
        <Item>
          <Form addTask={addTask}/>
        </Item>
        <Item>
          <div>
            <p>Filter options:</p>
            <ButtonGroup> {filterList} </ButtonGroup>
          </div>
        </Item>
      </Stack>

      <h2 id="list-heading">{headingText}</h2>
      <Stack spacing={2}>
        {taskList}
      </Stack>
    </div>
  );
}

export default App;
