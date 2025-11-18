<template>
  <div class="app">
    <header class="app-header">
      <h1>Suporte - Sistema de Tickets</h1>
      <nav>
        <router-link to="/cliente/tickets">Cliente</router-link>
        <router-link to="/admin/tickets">Tickets (Admin)</router-link>
        <router-link to="/admin/usuarios">Usuários</router-link>
        <router-link to="/admin/tecnicos">Técnicos</router-link>
        <router-link to="/admin/categorias">Categorias</router-link>
      </nav>
    </header>

    <main class="app-main">
      <router-view />
    </main>
  </div>
</template>

<script setup>
</script>

<style>
.app {
  color: #111827;
}

.app-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem 2rem;
  background-color: #1e40af;
  color: #fff;
}

.app-header h1 {
  font-size: 1.1rem;
  margin: 0;
}

.app-header nav a {
  color: #bfdbfe;
  margin-left: 1rem;
  text-decoration: none;
  font-size: 0.9rem;
}

.app-header nav a.router-link-active {
  font-weight: bold;
  text-decoration: underline;
}

.app-main {
  padding: 2rem;
}
</style>
