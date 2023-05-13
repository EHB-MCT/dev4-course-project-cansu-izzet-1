<script setup>
import AdminSessionCard from "../components/AdminSessionCard.vue";
import MyNavigation from "../components/MyNavigation.vue";
import sessions from "../data/sessions.json";
import Breadcrumbs from "../components/Breadcrumbs.vue";
</script>

<template>
  <MyNavigation />
  <main id="adminSessionsViewMain">
    <Breadcrumbs />
    <div id="adminSessionsContainer">
      <div id="adminSessionsViewNewSessionContainer">
        <p>NEW SESSION</p>
      </div>
      <AdminSessionCard v-for="session in sessions" :session="session" />
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

#adminSessionsContainer {
  width: 100%;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
  overflow-y: scroll;
  padding-right: 10px;
}

#adminSessionsViewNewSessionContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-color: #123e3b;
  border-radius: 50px;
}

#adminSessionsViewNewSessionContainer p {
  font-weight: 700;
  font-size: 22px;
}
</style>
