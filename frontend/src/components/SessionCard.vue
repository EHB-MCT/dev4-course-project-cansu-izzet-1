<script setup>
const { session, baseURL } = defineProps(["session", "baseURL"]);
</script>

<template>
  <router-link :to="`${baseURL.url}/${session.id}`">
    <div class="adminSessionCardContainer">
      <p>{{ session.username }}</p>
      <p>{{ session.date }}</p>
    </div>
  </router-link>
</template>

<style>
.adminSessionCardContainer {
  background-color: #123e3b;
  border-radius: 50px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  padding: 20px 30px;
}

.adminSessionCardContainer p {
  /* color: #123e3b; */
  color: #ede3c8;
  font-size: 22px;
  font-weight: 700;
}
</style>
