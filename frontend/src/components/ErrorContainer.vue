<script setup>
const { message, hasButton } = defineProps(["message", "hasButton"]);
</script>

<template>
  <div class="errorContainer">
    <p>{{ message }}</p>
    <RouterLink v-if="hasButton" to="/"
      ><button class="errorLoginBtn">Login</button></RouterLink
    >
  </div>
</template>

<style>
.errorContainer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  row-gap: 15px;
  width: 600px;
  text-align: center;
}

.errorContainer p {
  font-size: 20px;
}

.errorLoginBtn {
  background-color: #123e3b;
}
</style>
