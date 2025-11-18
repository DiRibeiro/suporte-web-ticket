classDiagram
direction LR

%% CONTROLLERS
class TicketController
class UsuarioController
class TecnicoController
class CategoriaController
class ComentarioController

%% SERVICES
class TicketService
class UsuarioService
class TecnicoService
class CategoriaService
class ComentarioService

%% REPOSITORIES
class TicketRepository {
  <<interface>>
}
class UsuarioRepository {
  <<interface>>
}
class TecnicoRepository {
  <<interface>>
}
class CategoriaRepository {
  <<interface>>
}
class ComentarioRepository {
  <<interface>>
}

%% DOMAIN
class Ticket
class Usuario
class Tecnico
class Categoria
class Comentario
class Status {
  <<enumeration>>
}

%% CONTROLLER -> SERVICE
TicketController --> TicketService
UsuarioController --> UsuarioService
TecnicoController --> TecnicoService
CategoriaController --> CategoriaService
ComentarioController --> ComentarioService

%% SERVICE -> REPOSITORY
TicketService --> TicketRepository
TicketService --> UsuarioRepository
TicketService --> TecnicoRepository
TicketService --> CategoriaRepository

UsuarioService --> UsuarioRepository
TecnicoService --> TecnicoRepository
CategoriaService --> CategoriaRepository
ComentarioService --> ComentarioRepository
ComentarioService --> TicketRepository

%% REPOSITORY -> DOMAIN (entidade gerenciada)
TicketRepository ..> Ticket
UsuarioRepository ..> Usuario
TecnicoRepository ..> Tecnico
CategoriaRepository ..> Categoria
ComentarioRepository ..> Comentario
