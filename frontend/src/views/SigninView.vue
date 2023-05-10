<script setup>
import MyForm from "../components/MyForm.vue";
import forms from "../data/forms.json";

const form = forms.find((form) => form.title == "sign in");
</script>

<template>
  <MyForm :form="form" />
</template>
