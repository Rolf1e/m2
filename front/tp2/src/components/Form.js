import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";

function Form(props) {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');

  function handleChangeName(e) {
    setName(e.target.value);
  }

  function handleChangeDescription(e) {
    setDescription(e.target.value);
  }

  function handleSubmit(e) {
    e.preventDefault();
    props.addTask(name, description);
    setName('')
    setDescription('')
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2 className="label-wrapper">
        <label htmlFor="new-todo-input" className="label__lg">
          What needs to be done?
        </label>
      </h2>
      <Box components="form">
        <TextField
          id="new-todo-input"
          name="text"
          label="Name"
          autoComplete="off"
          value={name}
          onChange={handleChangeName}
        />
        <TextField
          id="new-todo-input"
          name="text"
          label="Description"
          autoComplete="off"
          value={description}
          onChange={handleChangeDescription}
        />
        <Button type="submit" size="large">
          Add
        </Button>
      </Box>
    </form>
  );
}

export default Form;
