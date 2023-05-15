<script setup>
import MyNavigation from "../components/MyNavigation.vue";
import StatisticCard from "../components/StatisticCard.vue";
</script>

<template>
  <MyNavigation />
  <main id="adminSessionsViewMain">
    <StatisticCard />
  </main>
</template>

<style>
#adminSessionsViewMain {
  width: 80%;
  display: grid;
  grid-template-rows: auto auto 1fr;
  margin: 150px 0 100px 0;
  row-gap: 30px;
}
</style>
