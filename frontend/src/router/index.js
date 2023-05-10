import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../views/LoginView.vue";
import SigninView from "../views/SigninView.vue";
import AdminSelectionView from "../views/AdminSelectionView.vue";

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
    {
      path: "/adminSelection",
      name: "adminSelection",
      component: AdminSelectionView,
    },
  ],
});

export default router;
