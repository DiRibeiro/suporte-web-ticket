classDiagram
direction LR

class Usuario {
  +Long id
  +String nome
  +String email
}

class Tecnico {
  +Long id
  +String nome
  +String email
  +List<String> especialidades
}

class Categoria {
  +Long id
  +String nome
}

class Status {
  <<enumeration>>
  NOVO
  EM_ANDAMENTO
  RESOLVIDO
  FECHADO
}

class Ticket {
  +Long id
  +String titulo
  +String descricao
  +int impacto
  +int urgencia
  +int prioridade
  +Status status
  +LocalDateTime criadoEm
  +LocalDateTime atualizadoEm
  --
  +Categoria categoria
  +Usuario criador
  +Tecnico tecnicoAtribuido
}

class Comentario {
  +Long id
  +String mensagem
  +LocalDateTime criadoEm
  --
  +Ticket ticket
}

%% DTOs

class UsuarioDTO {
  +Long id
  +String nome
  +String email
}

class TecnicoDTO {
  +Long id
  +String nome
  +String email
  +List<String> especialidades
}

class CategoriaDTO {
  +Long id
  +String nome
}

class ComentarioDTO {
  +Long id
  +String mensagem
  +Long ticketId
  +LocalDateTime criadoEm
}

class TicketRequestDTO {
  +String titulo
  +String descricao
  +int impacto
  +int urgencia
  +int prioridade
  +Status status
  +Long categoriaId
  +Long criadorId
  +Long tecnicoAtribuidoId
}

class TicketResponseDTO {
  +Long id
  +String titulo
  +String descricao
  +int impacto
  +int urgencia
  +int prioridade
  +Status status
  +LocalDateTime criadoEm
  +LocalDateTime atualizadoEm
  --
  +Long categoriaId
  +String categoriaNome
  +Long criadorId
  +String criadorNome
  +String criadorEmail
  +Long tecnicoAtribuidoId
  +String tecnicoAtribuidoNome
  +String tecnicoAtribuidoEmail
}

%% RELACIONAMENTOS DE DOMÍNIO

Usuario "1" --> "0..*" Ticket : cria
Tecnico "1" --> "0..*" Ticket : atende
Categoria "1" --> "0..*" Ticket : classifica
Ticket "1" --> "0..*" Comentario : possui
Ticket "1" --> "1" Categoria
Ticket "1" --> "1" Usuario : criador
Ticket "0..1" --> "0..*" Tecnico : tecnicoAtribuido
Comentario "1" --> "1" Ticket

%% DTOs -> Entidades (mapeamento conceitual)

UsuarioDTO ..> Usuario
TecnicoDTO ..> Tecnico
CategoriaDTO ..> Categoria
ComentarioDTO ..> Comentario
TicketRequestDTO ..> Ticket
TicketResponseDTO ..> Ticket
