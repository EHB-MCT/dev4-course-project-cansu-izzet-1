<script setup>
import MyForm from "../components/MyForm.vue";
import forms from "../data/forms.json";

const form = forms.find((form) => form.title == "log in");
</script>

<template>
  <main id="loginViewMain">
    <MyForm :form="form" />
  </main>
</template>

<style>
#loginViewMain {
  height: 100%;
  display: flex;
  align-items: center;
}

#loginViewMain form {
  height: 550px;
}
</style>
