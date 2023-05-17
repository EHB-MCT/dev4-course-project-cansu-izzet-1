<script setup>
import MyNavigation from "../components/MyNavigation.vue";
import StatisticCard from "../components/StatisticCard.vue";
import ErrorContainer from "../components/ErrorContainer.vue";

const userRole = sessionStorage.getItem("role");
</script>

<template>
  <MyNavigation v-if="userRole === 'Admin'" navigationType="adminNav" />
  <main id="adminSessionsViewMain" v-if="userRole === 'Admin'">
    <StatisticCard />
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
#adminSessionsViewMain {
  width: 80%;
  display: grid;
  grid-template-rows: auto auto 1fr;
  margin: 150px 0 100px 0;
  row-gap: 30px;
}
</style>
