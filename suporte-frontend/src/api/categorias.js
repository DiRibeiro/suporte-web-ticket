import http from './http'

export function listarCategorias(params) {
  return http.get('/categorias', { params })
}

export function buscarCategoria(id) {
  return http.get(`/categorias/${id}`)
}

export function criarCategoria(data) {
  return http.post('/categorias', data)
}

export function atualizarCategoria(id, data) {
  return http.put(`/categorias/${id}`, data)
}

export function deletarCategoria(id) {
  return http.delete(`/categorias/${id}`)
}
