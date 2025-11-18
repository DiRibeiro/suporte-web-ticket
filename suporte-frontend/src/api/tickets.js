import http from './http'

export function listarTickets(params) {
  return http.get('/tickets', { params })
}

export function buscarTicket(id) {
  return http.get(`/tickets/${id}`)
}

export function criarTicket(data) {
  return http.post('/tickets', data)
}

export function atualizarTicket(id, data) {
  return http.put(`/tickets/${id}`, data)
}

export function deletarTicket(id) {
  return http.delete(`/tickets/${id}`)
}
