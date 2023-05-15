import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../views/LoginView.vue";
import SigninView from "../views/SigninView.vue";
import AdminSelectionView from "../views/AdminSelectionView.vue";
import AdminSessionsView from "../views/AdminSessionsView.vue";
import AdminSessionView from "../views/AdminSessionView.vue";
import SessionsView from "../views/SessionsView.vue";
import SessionView from "../views/SessionView.vue";
import AdminStatisticsView from "../views/AdminStatisticsView.vue";

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
    {
      path: "/adminSessions",
      name: "adminSessions",
      component: AdminSessionsView,
    },
    {
      path: "/adminSessions/:id",
      name: "adminSession",
      component: AdminSessionView,
    },
    {
      path: "/sessions",
      name: "sessions",
      component: SessionsView,
    },
    {
      path: "/sessions/:id",
      name: "session",
      component: SessionView,
    },
    {
      path: "/adminStatistics",
      name: "adminStatistics",
      component: AdminStatisticsView,
    },
  ],
});

export default router;
