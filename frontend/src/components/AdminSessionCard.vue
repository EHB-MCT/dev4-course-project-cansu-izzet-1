<script setup>
const { session } = defineProps(["session"]);
</script>

<template>
  <router-link :to="`/adminSessions/${session.id}`">
    <div class="adminSessionCardContainer">
      <p>{{ session.patient }}</p>
      <p>{{ session.data }}</p>
    </div>
  </router-link>
</template>

<style>
.adminSessionCardContainer {
  background-color: #fff;
  border-radius: 50px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  padding: 20px 30px;
}

.adminSessionCardContainer p {
  color: #123e3b;
  font-size: 22px;
}
</style>
