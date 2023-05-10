<script setup>
const { selection } = defineProps(["selection"]);
</script>

<template>
  <router-link :to="selection.direction">
    <section>
      <h2>{{ selection.title.toUpperCase() }}</h2>
      <div>
        <img :src="selection.image" />
      </div>
    </section>
  </router-link>
</template>

<style>
h2 {
  font-size: 35px;
  color: #ede3c8;
}

section {
  width: 100%;
  background-color: #123e3b;
  border-radius: 60px;
  border: 3px solid #ede3c8;
  display: flex;
  flex-direction: column;
  align-items: center;
  row-gap: 30px;
  padding: 30px 30px 60px 30px;
  text-decoration: none;
}

div {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

img {
  width: 90%;
}
</style>
