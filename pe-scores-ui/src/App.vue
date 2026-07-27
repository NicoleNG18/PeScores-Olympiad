<template>
  <div id="app">
    <!-- Тук Vue Router автоматично зарежда съответното View (в случая HomeView) -->
    <router-view />
  </div>
</template>

<script setup>
</script>

<style>
/* Глобални нулиращи стилове */
body {
  margin: 0;
  font-family: system-ui, -apple-system, sans-serif;
  background-color: #f8fafc;
  color: #0f172a;
}
</style>