<template>
  <form id="loginForm" class="form">
    <h1>SIGN IN</h1>
    <div class="formInputsContainer">
      <div class="formInputContainers">
        <label for="username">Username</label>
        <input type="text" id="username" placeholder="John" />
      </div>
      <div class="formInputContainers">
        <label for="email">Email</label>
        <input type="email" id="email" placeholder="john@gmail.com" />
      </div>
      <div class="formInputContainers">
        <label for="password">Password</label>
        <input type="password" id="password" placeholder="*******" />
      </div>
    </div>
    <div class="formButtonContainer">
      <button type="submit">SUBMIT</button>
      <p>Already have an account?<router-link to="/">LOG IN</router-link></p>
    </div>
  </form>
</template>
