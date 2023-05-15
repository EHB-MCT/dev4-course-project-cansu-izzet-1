<script setup>
import SelectionCard from "../components/SelectionCard.vue";
import selections from "../data/selections.json";
</script>

<template>
  <main id="adminSelectionViewMain">
    <SelectionCard v-for="selection in selections" :selection="selection" />
  </main>
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
