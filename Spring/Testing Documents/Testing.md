# Testing Document

## Testing controllers correct functions

--------------------------

### City controller

- [x] preAuthorization

- [x] getAll
- [x] getById
  - [x] 404 not found
- [x] post
  - [x] 409 conflict
- [x] put
  - [x] 409 conflict
- [x] delete
  - [x] 404 not found
- [x] 400 bad request


--------------------------

### Reservation controller

- [x] preAuthorization

- [x] getAll
- [x] getById
  - [x] 404 not found
- [x] post
  - [x] 409 conflict
  - [x] 406 not acceptable
- [x] put // update seat validation - own id
- [x] delete
  - [x] 404 not found
- [x] 400 bad request

--------------------------

### Authentication controller

- [x] authentication
- [x] refresh token

--------------------------

### Restaurant Controller

- [x] preAuthorization

- [x] getAll
- [x] getById
  - [x] 404 not found
- [x] post
  - [x] 409 conflict
- [x] put
  - [x] 409 conflict
- [x] delete
  - [x] 404 not found
- [x] 400 bad request


--------------------------

### UserController

- [x] preAuthorization

- [x] getAll
- [x] getById
  - [x] 404 not found
- [x] post
  - [x] 409 conflict
- [x] put
- [x] delete
  - [x] 404 not found
- [x] 400 bad request