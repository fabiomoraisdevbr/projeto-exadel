# diagrama

<img width="1749" height="830" alt="image" src="https://github.com/user-attachments/assets/798af2d2-8809-487b-b99d-ec93c644b61e" />

# 🧩 Monorepo - Microservices com Mensageria

Este projeto é um **monorepo** contendo dois microsserviços desenvolvidos com foco em **mensageria assíncrona**, cache e comunicação entre serviços.

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


