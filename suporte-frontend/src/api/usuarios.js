import http from './http'

export function listarUsuarios(params) {
  return http.get('/usuarios', { params })
}

export function buscarUsuario(id) {
  return http.get(`/usuarios/${id}`)
}

export function criarUsuario(data) {
  return http.post('/usuarios', data)
}

export function atualizarUsuario(id, data) {
  return http.put(`/usuarios/${id}`, data)
}

export function deletarUsuario(id) {
  return http.delete(`/usuarios/${id}`)
}
