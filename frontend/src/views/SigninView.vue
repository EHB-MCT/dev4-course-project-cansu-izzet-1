<script setup>
import MyForm from "../components/MyForm.vue";
import forms from "../data/forms.json";

const form = forms.find((form) => form.title == "sign in");
</script>

<template>
  <main id="signinViewMain">
    <MyForm :form="form" />
  </main>
</template>

<style>
#signinViewMain {
  height: 100%;
  display: flex;
  align-items: center;
}

#signinViewMain form {
  height: 650px;
}
</style>
