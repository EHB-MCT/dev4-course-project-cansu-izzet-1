<script setup>
const { session } = defineProps(["session"]);
</script>

<template>
  <div class="adminSessionCardContainer">
    <p>{{ session.patient }}</p>
    <p>{{ session.data }}</p>
  </div>
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
