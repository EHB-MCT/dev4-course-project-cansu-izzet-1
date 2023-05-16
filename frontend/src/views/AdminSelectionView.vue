<script setup>
import SelectionCard from "../components/SelectionCard.vue";
import selections from "../data/selections.json";

const userRole = sessionStorage.getItem("role");
</script>

<template>
  <main id="adminSelectionViewMain" v-if="userRole === 'Admin'">
    <SelectionCard v-for="selection in selections" :selection="selection" />
  </main>
  <div class="errorContainers" v-else-if="userRole === 'User'">
    <p>You do not have permission to view this page.</p>
  </div>
  <div class="errorContainers" v-else>
    <p>You are not logged in</p>
    <RouterLink to="/"><button class="errorLoginBtn">Login</button></RouterLink>
  </div>
</template>

<style>
#adminSelectionViewMain {
  height: 100%;
  width: 50%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  column-gap: 5%;
}

.errorContainers {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  row-gap: 15px;
}

.errorLoginBtn {
  background-color: #123e3b;
}
</style>
