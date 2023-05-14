<script setup>
import AdminSessionCard from "../components/AdminSessionCard.vue";
import MyNavigation from "../components/MyNavigation.vue";
import sessions from "../data/sessions.json";
import Breadcrumbs from "../components/Breadcrumbs.vue";
import MyForm from "../components/MyForm.vue";
import forms from "../data/forms.json";

const newSessionForm = forms.find((form) => form.title == "new session");
</script>

<template>
  <MyNavigation />
  <main id="adminSessionsViewMain">
    <Breadcrumbs />
    <div id="adminSessionsViewWrapper">
      <div id="adminSessionsContainer">
        <AdminSessionCard v-for="session in sessions" :session="session" />
      </div>
      <MyForm :form="newSessionForm" />
    </div>
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
  gap: 20px;
  overflow-y: scroll;
  padding-right: 10px;
}
</style>
