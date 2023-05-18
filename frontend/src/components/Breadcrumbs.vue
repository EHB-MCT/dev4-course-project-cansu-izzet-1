<script setup>
const { direction, session } = defineProps(["direction", "session"]);
</script>

<template>
  <div v-if="direction" id="breadcrumbsContainer">
    <router-link :to="direction">Sessions</router-link>
    <span class="material-icons"> chevron_right </span>
    <p>{{ session.username }} - {{ session.date }}</p>
  </div>
  <div v-else="direction" id="breadcrumbsContainer">
    <p>Sessions</p>
  </div>
</template>

<style>
#breadcrumbsContainer {
  width: fit-content;
  display: flex;
  align-items: center;
  gap: 10px;
  background-color: #123e3b;
  padding: 15px 25px;
  border-radius: 50px;
}

#breadcrumbsContainer span {
  color: #ede3c8;
}

#breadcrumbsContainer p,
#breadcrumbsContainer a {
  font-size: 18px;
}

#breadcrumbsContainer a {
  opacity: 70%;
}
</style>
