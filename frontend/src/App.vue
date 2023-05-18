<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <notifications
    position="bottom left"
    classes="notificationClass"
    speed="300"
    width="550px"
  />
  <RouterView></RouterView>
</template>

<style>
.notificationClass {
  background-color: #123e3b !important;
  color: #ede3c8;
  padding: 25px 35px;
  margin-left: 30px;
  margin-bottom: 30px;
  font-size: 16px;
  font-weight: 400;
  border-radius: 50px;
  z-index: 9999;
  border: 1px solid #ede3c8;
}

.error {
  background-color: #851111 !important;
}
</style>
