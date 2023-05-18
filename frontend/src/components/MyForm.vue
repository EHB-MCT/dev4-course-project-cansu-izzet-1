<script setup>
import { computed, reactive } from "vue";
import { useRouter } from "vue-router";
import { onMounted } from "vue";
import { notify } from "@kyvg/vue3-notification";

const router = useRouter();
const { form } = defineProps(["form"]);

const isTextInput = computed(() => {
  return form.inputs.length > 0 && form.inputs[0].type === "text";
});

function updateSessionStorage(role, accessToken) {
  sessionStorage.setItem("role", role);
  sessionStorage.setItem("accessToken", accessToken);
}

const formData = reactive({});
const submitForm = async () => {
  const formFields = { ...formData };
  console.log(formFields);
  const response = await fetch(form.url, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(formFields),
  });
  const responseBody = await response.json();
  if (response.ok) {
    switch (form.title) {
      case "log in":
        if (responseBody.role == "Admin") {
          updateSessionStorage(responseBody.role, responseBody.accessToken);
          router.push("/adminSelection");
          notify({
            type: "success",
            title: form.successMessage,
          });
        } else if (responseBody.role == "User") {
          updateSessionStorage(responseBody.role, responseBody.accessToken);
          router.push("/sessions");
          notify({
            type: "success",
            title: form.successMessage,
          });
        }
        break;
      case "sign in":
        router.push("/");
        notify({
          type: "success",
          title: form.successMessage,
        });
        break;
      case "new session":
        console.log("new session form");
        notify({
          type: "success",
          title: form.successMessage,
        });
        setTimeout(() => {
          location.reload();
        }, 1500);
        break;
      default:
        console.log("todo");
    }
  } else {
    notify({
      type: "error",
      title: form.errorMessage,
    });
  }
};
</script>
<template>
  <form @submit.prevent="submitForm">
    <h1>{{ form.title.toUpperCase() }}</h1>
    <div v-if="isTextInput" id="inputsContainer">
      <div v-for="input in form.inputs">
        <label :for="input.id">{{ input.label }}</label>
        <input
          :type="input.type"
          :name="input.name"
          :id="input.id"
          :placeholder="input.placeholder"
          v-model="formData[input.name]"
          required
        />
      </div>
    </div>
    <div v-else class="checkBoxContainer">
      <div v-for="input in form.inputs">
        <input
          :type="input.type"
          :name="input.name"
          :id="input.id"
          :placeholder="input.placeholder"
          v-model="formData[input.name]"
          required
        />
        <label :for="input.id">{{ input.label }}</label>
      </div>
    </div>
    <div id="submitContainer">
      <button type="submit">SUBMIT</button>
      <div v-if="form.extra">
        <p>{{ form.extraText }}</p>
        <router-link :to="form.extraDirection">{{
          form.extraDirectionText.toUpperCase()
        }}</router-link>
      </div>
    </div>
  </form>
</template>

<style>
input[type="text"],
input[type="password"],
input[type="email"],
input[type="date"] {
  padding: 7px 20px;
  border: none;
  border-radius: 37px;
  font-size: 20px;
  color: #0d2b29;
  width: 100%;
}

input[type="date"] {
  color: #0d2b29;
}

input::-webkit-input-placeholder {
  /* Chrome/Opera/Safari/Edge */
  color: #808e7f;
}

input::-ms-input-placeholder {
  /* Microsoft Edge */
  color: #808e7f;
}

input:-ms-input-placeholder {
  /* IE 10+ */
  color: #808e7f;
}

form {
  padding: 55px;
  background-color: #123e3b;
  border: 3px solid #ede3c8;
  width: 450px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  border-radius: 60px;
}

h1,
label {
  color: #ede3c8;
  font-weight: 700;
}

h1 {
  font-size: 35px;
}

label {
  font-size: 22px;
}

p {
  font-size: 18px;
}

p,
a {
  color: #ede3c8;
}

#inputsContainer {
  display: flex;
  flex-direction: column;
  row-gap: 15px;
}

#inputsContainer div {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  row-gap: 5px;
}

#submitContainer {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  row-gap: 10px;
}

#submitContainer div {
  display: flex;
  justify-content: flex-start;
  column-gap: 10px;
  align-items: center;
}

#submitContainer div a {
  font-weight: 700;
  text-decoration: none;
  font-size: 19px;
}

.checkBoxContainer {
  /* background-color: red; */
  width: 100%;
  height: 160px;
  display: flex;
  align-items: flex-start;
  flex-direction: column;
  overflow-y: scroll;
  gap: 10px;
  padding-right: 10px;
}

.checkBoxContainer div {
  width: 100%;
  background-color: #0d2b29;
  border-radius: 50px;
  padding: 5px 20px;
  display: flex;
  align-items: center;
  column-gap: 10px;
}
</style>
