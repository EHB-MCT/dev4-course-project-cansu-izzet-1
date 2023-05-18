<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";
import MyNavigation from "../components/MyNavigation.vue";
import SessionInformationCard from "../components/SessionInformationCard.vue";
import sessionsInformation from "../data/sessionsInformation.json";
import Breadcrumbs from "../components/Breadcrumbs.vue";
import MyForm from "../components/MyForm.vue";
import forms from "../data/forms.json";
import ErrorContainer from "../components/ErrorContainer.vue";
import { notify } from "@kyvg/vue3-notification";

const userRole = sessionStorage.getItem("role");
const userAccessToken = sessionStorage.getItem("accessToken");

const route = useRoute();
const { id } = route.params;

let hasQuestionnaire = ref(null);
let questionnaire = ref([]);

let questions = ref([]);
const newQuestionnaireForm = forms.find(
  (form) => form.title == "create questionnaire"
);
const editQuestionnaireForm = forms.find(
  (form) => form.title == "edit questionnaire"
);

newQuestionnaireForm.sessionId = id;

fetch(`http://localhost:8080/questions`, {
  method: "GET",
  headers: {
    Authorization: userAccessToken,
  },
})
  .then((response) => response.json())
  .then((result) => {
    questions.value = result;
    console.log(questions.value);
  })
  .then(() => {
    if (newQuestionnaireForm.inputs <= 0 && editQuestionnaireForm.inputs <= 0) {
      questions.value.forEach((question) => {
        newQuestionnaireForm.inputs.push({
          label: question.description,
          type: "checkbox",
          name: "questions",
          id: question.id,
        });
        editQuestionnaireForm.inputs.push({
          label: question.description,
          type: "checkbox",
          name: "questions",
          id: question.id,
        });
      });
    } else {
      return;
    }
  });

const newQuestionForm = forms.find((form) => form.title == "create question");

let adminQuestionnaireInformation = sessionsInformation.find(
  (sessionInformation) => sessionInformation.information == "adminQuestionnaire"
);

let adminNoQuestionnaireInformation = sessionsInformation.find(
  (sessionInformation) =>
    sessionInformation.information == "adminNoQuestionnaire"
);

const showCreate = ref(false);
const showEdit = ref(false);

function handleButtonClick(button) {
  if (button.type == "create") {
    showCreate.value = true;
  } else if (button.type == "edit") {
    editQuestionnaireForm.questionnaireId = questionnaire.value.id;
    showEdit.value = true;
  } else if (button.type == "delete") {
    if (
      confirm(
        "Are you sure you want to delete the questionnaire? This action cannot be undone."
      )
    ) {
      fetch(`http://localhost:8080/questionnaires/${questionnaire.value.id}`, {
        method: "DELETE",
        headers: {
          Authorization: userAccessToken,
          "Content-Type": "application/json",
        },
      }).then(() => {
        notify({
          type: "error",
          title: "The questionnaire has been successfully deleted.",
        });
        setTimeout(() => {
          location.reload();
        }, 2000);
      });
    } else {
      notify({
        type: "success",
        title: "The questionnaire has not been deleted.",
      });
    }
  }
}

let session = ref([]);
fetch(`http://localhost:8080/sessions/${id}`, {
  method: "GET",
  headers: {
    Authorization: userAccessToken,
  },
})
  .then((response) => response.json())
  .then((result) => {
    session.value = result;
  })
  .then(() => {
    fetch(`http://localhost:8080/questionnaires/${session.value.id}`, {
      method: "GET",
      headers: {
        Authorization: userAccessToken,
      },
    })
      .then((response) => response.json())
      .then((result) => {
        questionnaire.value = result;
        hasQuestionnaire.value = true;
      })
      .catch(() => {
        hasQuestionnaire = false;
      });
  });
</script>

<template>
  <MyNavigation v-if="userRole === 'Admin'" navigationType="adminNav" />
  <main id="adminSessionViewMain" v-if="userRole === 'Admin'">
    <Breadcrumbs direction="/adminSessions" :session="session" />
    <SessionInformationCard
      @button-clicked="handleButtonClick"
      v-if="hasQuestionnaire"
      :sessionInformation="adminQuestionnaireInformation"
    />
    <SessionInformationCard
      @button-clicked="handleButtonClick"
      v-else="hasQuestionnaire"
      :sessionInformation="adminNoQuestionnaireInformation"
    />
    <div class="adminSessionViewFormContainer" v-if="showCreate">
      <MyForm :form="newQuestionnaireForm" />
      <MyForm :form="newQuestionForm" />
    </div>

    <div class="adminSessionViewFormContainer" v-if="showEdit">
      <MyForm :form="editQuestionnaireForm" />
      <MyForm :form="newQuestionForm" />
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
#adminSessionViewMain {
  width: 80%;
  display: grid;
  grid-template-rows: auto auto 1fr;
  margin: 150px 0 100px 0;
  row-gap: 30px;
}

.adminSessionViewFormContainer {
  display: grid;
  grid-template-columns: 1.5fr 1fr;
  column-gap: 30px;
  width: 100%;
  height: 100%;
}

.adminSessionViewFormContainer form {
  height: 100%;
  width: 100%;
}
</style>
