<script setup>
import MyNavigation from "../components/MyNavigation.vue";
import StatisticCard from "../components/StatisticCard.vue";
import ErrorContainer from "../components/ErrorContainer.vue";
import { ref } from "vue";

const userRole = sessionStorage.getItem("role");
const userAccessToken = sessionStorage.getItem("accessToken");

let responses = ref([]);

fetch("http://localhost:8080/responses", {
  method: "GET",
  headers: {
    Authorization: userAccessToken,
  },
})
  .then((response) => response.json())
  .then((result) => {
    responses.value = result;
  });
</script>

<template>
  <MyNavigation v-if="userRole === 'Admin'" navigationType="adminNav" />
  <main id="adminStatisticsViewMain" v-if="userRole === 'Admin'">
    <StatisticCard
      v-for="response in responses.slice().reverse()"
      :response="response"
    />
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
#adminStatisticsViewMain {
  height: 700px;
  overflow-y: scroll;
  width: 80%;
  display: grid;
  grid-template-rows: auto auto 1fr;
  margin: 150px 0 100px 0;
  row-gap: 30px;
  padding-right: 40px;
}
</style>
