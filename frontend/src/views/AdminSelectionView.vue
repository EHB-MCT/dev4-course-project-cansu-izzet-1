<script setup>
import SelectionCard from "../components/SelectionCard.vue";
import selections from "../data/selections.json";
import ErrorContainer from "../components/ErrorContainer.vue";

const userRole = sessionStorage.getItem("role");
</script>

<template>
  <main id="adminSelectionViewMain" v-if="userRole === 'Admin'">
    <SelectionCard v-for="selection in selections" :selection="selection" />
  </main>
  <ErrorContainer
    v-else-if="userRole === 'User'"
    message="Regrettably, your current access privileges do not permit you to view the contents of this page."
    :has-button="false"
  />

  <ErrorContainer
    v-else
    message="Please note that you are currently not authenticated or logged into the system."
    :has-button="true"
  />
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
</style>
