<template>
    <form @submit.prevent="login">
      <label>Username:</label>
      <input v-model="username" type="text" />
      <br />
      <label>Password:</label>
    <input v-model="password" type="password" />
      <br />
      <button type="submit">Login</button>
    </form>
  </template>
  
  <script>
  export default {
    data() {
      return {
        username: "",
        password: "",
      };
    },
    methods: {
      async login() {
        try {
          const response = await axios.post("/api/login", {
            username: this.username,
            password: this.password
          });
          // Process the response from the API, e.g. by storing the token
        } catch (error) {
        // Handle errors returned by the API
        }
      }
    }
  };
  </script>
  