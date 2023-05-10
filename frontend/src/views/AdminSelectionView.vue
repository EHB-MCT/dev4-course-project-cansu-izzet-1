<script setup>
import SelectionCard from "../components/SelectionCard.vue";
import selections from "../data/selections.json";
</script>

<template>
  <main>
    <SelectionCard v-for="selection in selections" :selection="selection" />
  </main>
</template>

<style>
main {
  width: 50%;
  display: flex;
  justify-content: space-between;
  column-gap: 5%;
}
</style>
