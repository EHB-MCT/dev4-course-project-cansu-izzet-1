<script setup>
import { useRouter } from "vue-router";
import { notify } from "@kyvg/vue3-notification";

const router = useRouter();

function handleLogout() {
  sessionStorage.removeItem("role");
  sessionStorage.removeItem("accessToken");
  router.push("/");
  notify({
    type: "success",
    title: "You have been logged out. Thank you for using our platform!",
  });
}
</script>
<template>
  <nav>
    <div>
      <router-link to="/adminSessions">SESSIONS</router-link>
      <router-link to="/adminStatistics">STATISTICS</router-link>
      <span class="material-icons logoutBtn" @click="handleLogout">
        logout
      </span>
    </div>
  </nav>
</template>
<style>
nav {
  background-color: #123e3b;
  width: 100%;
  height: 80px;
  position: absolute;
  top: 0;
  display: flex;
  justify-content: flex-end;
  padding: 0 10%;
}

nav div {
  display: flex;
  justify-content: center;
  align-items: center;
  column-gap: 90px;
}

nav div a {
  font-size: 22px;
  font-weight: 700;
}

.logoutBtn {
  color: #ede3c8;
  font-size: 30px;
  cursor: pointer;
}
</style>
