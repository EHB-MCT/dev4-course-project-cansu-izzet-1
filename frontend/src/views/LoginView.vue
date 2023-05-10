<script setup>
import MyForm from "../components/MyForm.vue";
import forms from "../data/forms.json";

const form = forms.find((form) => form.title == "log in");
</script>

<template>
  <MyForm :form="form" />
</template>
