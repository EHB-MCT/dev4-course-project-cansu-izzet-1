<script setup>
import SessionCard from "../components/SessionCard.vue";
import MyNavigation from "../components/MyNavigation.vue";
import Breadcrumbs from "../components/Breadcrumbs.vue";
import MyForm from "../components/MyForm.vue";
import forms from "../data/forms.json";
import ErrorContainer from "../components/ErrorContainer.vue";
import { ref } from "vue";

const newSessionForm = forms.find((form) => form.title == "new session");
const userRole = sessionStorage.getItem("role");
const userAccessToken = sessionStorage.getItem("accessToken");

let sessions = ref([]);

fetch("http://localhost:8080/sessions", {
  method: "GET",
  headers: {
    Authorization: userAccessToken,
  },
})
  .then((response) => response.json())
  .then((result) => {
    sessions.value = result;
  });
</script>

<template>
  <MyNavigation v-if="userRole === 'Admin'" navigationType="adminNav" />
  <main id="adminSessionsViewMain" v-if="userRole === 'Admin'">
    <Breadcrumbs />
    <div id="adminSessionsViewWrapper">
      <div id="adminSessionsContainer">
        <SessionCard
          v-for="session in sessions.slice().reverse()"
          :session="session"
          :baseURL="{ url: '/adminSessions' }"
        />
      </div>
      <MyForm :form="newSessionForm" />
    </div>
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

#adminSessionsViewWrapper {
  display: grid;
  grid-template-columns: 1.5fr 1fr;
  column-gap: 30px;
  width: 100%;
  height: 560px;
}

#adminSessionsViewWrapper form {
  width: 100%;
}

#adminSessionsContainer {
  width: 100%;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: repeat(5, max-content);
  gap: 20px;
  overflow-y: scroll;
  padding-right: 10px;
}
</style>
