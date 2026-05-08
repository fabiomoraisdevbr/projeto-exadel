# diagrama

<img width="1726" height="935" alt="Screenshot 2026-05-08 071304" src="https://github.com/user-attachments/assets/2884f494-f0fb-49fc-b523-31809b78d175" />



# 🧩 Monorepo - Microservices com Mensageria

O desafio desse projeto era implementar uma arquitetura orientada a eventos, onde dois microsserviços se comunicam via eventos para dar uma resposta um request vindo do frontend. O endpoint da aplicação é o /customers/{id} , que também pode retornar o valor total dos pedidos do cliente. Ficou faltando implementar o expurgo do cache quando o cliente realiza um novo pedido.

---

## 🏗️ Arquitetura

O sistema é composto por dois microsserviços:

### 🔹 ms-customer
Responsável por:
- Receber requisições externas
- Verificar cache no Redis
- Orquestrar chamadas via mensageria

### 🔹 ms-order
Responsável por:
- Processar mensagens recebidas
- Calcular/gerar dados
- Retornar resposta via mensageria

---

## 🔄 Fluxo da Aplicação

1. O `ms-customer` recebe uma requisição
2. Verifica se o dado está no **Redis**
   - ✅ Se estiver → retorna imediatamente
   - ❌ Se não estiver:
     - Envia mensagem para o `ms-order` via RabbitMQ
3. O `ms-order` processa a mensagem
4. Gera uma resposta (`order.total.response`)
5. O `ms-customer`:
   - Processa a resposta
   - Armazena no Redis (TTL de 5 minutos)
   - Retorna para o cliente

---

## 🧰 Tecnologias Utilizadas

- **Java 25**
- **Spring Boot**
- **RabbitMQ** (mensageria)
- **Redis** (cache)
- **PostgreSQL** (persistência)
- **Gradle (Kotlin DSL)**
- **Monorepo**
- **docker**

---


