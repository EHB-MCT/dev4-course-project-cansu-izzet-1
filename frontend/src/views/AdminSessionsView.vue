<script setup>
import AdminSessionCard from "../components/AdminSessionCard.vue";
import MyNavigation from "../components/MyNavigation.vue";
import sessions from "../data/sessions.json";
</script>

<template>
  <MyNavigation />
  <main id="adminSessionsViewMain">
    <div id="adminSessionsViewNewSessionContainer">
      <p>NEW SESSION</p>
    </div>
    <AdminSessionCard v-for="session in sessions" :session="session" />
  </main>
</template>

<style>
#adminSessionsViewMain {
  width: 80%;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
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
