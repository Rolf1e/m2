import React, { useState } from "react";
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import Item from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Checkbox from "@mui/material/Checkbox";

function Todo(props) {
  const [isEditing, setEditing] = useState(false);
  const [newName, setNewName] = useState('');

  function handleChange(e) {
    setNewName(e.target.value);
  }

  function handleSubmit(e) {
    e.preventDefault();
    props.editTask(props.id, newName);
    setNewName("");
    setEditing(false);
  }

  const editingTemplate = (
    <form className="stack-small" onSubmit={handleSubmit}>
      <div className="form-group">
        <label className="todo-label" htmlFor={props.id}>
          New name for {props.name}
        </label>
        <TextField id={props.id} value={newName} onChange={handleChange} />
      </div>
      <ButtonGroup size="large">
        <Button onClick={() => setEditing(false)}>
          Cancel
          <span className="visually-hidden">renaming {props.name}</span>
        </Button>
        <Button type="submit">
          Save
          <span className="visually-hidden">new name for {props.name}</span>
        </Button>
      </ButtonGroup>
    </form >
  );

  const viewTemplate = (
    <div>
      <div className="c-cb">

        <Grid container spacing={2}>
          <Grid item xs={1}>
            <Item>
              <Checkbox
                id={props.id}
                checked={props.completed}
                onChange={() => props.toggleTaskCompleted(props.id)}
                size="large"
              />
            </Item>
          </Grid>
          <Grid item xs={8}>
            <Item> <strong>{props.name}</strong> </Item>
          </Grid>
          <Grid item xs={5}>
            <Item> <u>Description:</u> <p>{props.description}</p> </Item>
          </Grid>
        </Grid>

      </div>
      <ButtonGroup>
        <Button onClick={() => setEditing(true)} >
          Edit <span className="visually-hidden">{props.name}</span>
        </Button>

        <Button color="error"
          onClick={() => props.deleteTask(props.id)} >
          Delete <span className="visually-hidden">{props.name}</span>
        </Button>
      </ButtonGroup>
    </div >
  );

  return (
    <Item>
      <Box sx={{ border: 1, borderRadius: 3, padding: 1 }}>
        {isEditing ? editingTemplate : viewTemplate}
      </Box>
    </Item>
  );
}

export default Todo;
