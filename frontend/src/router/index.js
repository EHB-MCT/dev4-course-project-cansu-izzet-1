import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../views/LoginView.vue";
import SigninView from "../views/SigninView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      component: LoginView,
    },
    {
      path: "/signin",
      name: "signin",
      component: SigninView,
    },
  ],
});

export default router;
