import http from './http'

export function listarTecnicos(params) {
  return http.get('/tecnicos', { params })
}

export function buscarTecnico(id) {
  return http.get(`/tecnicos/${id}`)
}

export function criarTecnico(data) {
  return http.post('/tecnicos', data)
}

export function atualizarTecnico(id, data) {
  return http.put(`/tecnicos/${id}`, data)
}

export function deletarTecnico(id) {
  return http.delete(`/tecnicos/${id}`)
}
