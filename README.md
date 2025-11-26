# FIAP ADJ8 – Feedback App

## Descrição

O **Feedback App** é uma aplicação Spring Boot que permite que estudantes enviem feedback sobre aulas e que administradores consultem e gerenciem esses feedbacks.

A aplicação é deployada no **Google App Engine**, utiliza **Cloud SQL (Postgres)** e integra com **Pub/Sub** para notificações assíncronas de feedbacks urgentes. Além disso, fornece dados via **REST** para a geração de relatórios semanais.

### Interações principais

- **Feedback App → Pub/Sub: feedback-alerts → Notify Admin Function**  
  Feedbacks marcados como urgentes são publicados no tópico `feedback-alerts` para notificação de administradores.

- **Cloud Scheduler → Pub/Sub: weekly-feedback-reports → Weekly Report Function → REST → Feedback App**  
  O relatório semanal é disparado automaticamente pelo Cloud Scheduler (domingo 00:00) e consulta o Feedback App via REST para obter os dados necessários.

![Fluxo Feedback App](feedback-app.png)

---

## Permissões Necessárias

### Service Account de Deploy: `sa-deploy-feedback-app`

- `roles/appengine.deployer`
- `roles/artifactregistry.reader`
- `roles/appengine.serviceAdmin`
- `roles/storage.admin`
- `roles/logging.viewer`
- `roles/logging.logWriter`
- `roles/serviceusage.serviceUsageViewer`
- `roles/viewer`

### Service Account de Runtime: `sa-runtime-feedback-app`

- `roles/logging.logWriter`
- `roles/cloudsql.client`

Essas permissões permitem que a aplicação execute deploy, registre logs, consuma o banco Cloud SQL e publique notificações de feedbacks urgentes.

---

## Deploy

O deploy é realizado pelo script `deploy.sh`:

```bash
./deploy.sh
```

Ele realiza as seguintes etapas:

- Autenticação com a Service Account de Deploy (sa-deploy-feedback-app).

- Build da imagem Docker da aplicação.

- Configuração e autenticação do Docker com o Artifact Registry do GCP.

- Tag e push da imagem Docker para o Artifact Registry.

- Deploy da aplicação no App Engine.

## Estrutura do Projeto

```text
feedback-app/
├─ src/main/java/fiap_adj8/feedback_platform/feedback_app/
│  ├─ application/              # Casos de uso, portas e serviços da aplicação
│  ├─ domain/                   # Entidades e modelos de negócio (Feedback, LessonFeedbackSummary)
│  ├─ infra/adapter/in/http/    # Controllers REST e DTOs
│  ├─ infra/config/security/    # Configuração de Spring Security
├─ src/main/resources/
│  ├─ application.properties / application.yml
│  ├─ templates/                # Templates HTML (se necessário)
├─ pom.xml                       # Maven com dependências Spring Boot, GCP, Postgres
├─ deploy/deploy.sh              # Script de build e deploy
```

## Controllers

### UserRestController

- Endpoint:

- **GET /user/admin/email** → Retorna emails de todos administradores cadastrados.

### FeedbackRestController

- Endpoints principais:

- **POST /feedback** → Cria feedback (somente STUDENT).

- **GET /feedback** → Lista todos feedbacks com paginação.

- **GET /feedback/{id}** → Consulta feedback por ID.

- **GET /feedback/most-rated** → Retorna feedbacks mais avaliados.

- **GET /feedback/highest-ranked** → Retorna feedbacks com maior nota.

### ***Roles são verificadas via AuthHelper.***

## Segurança

Usuários em memória configurados via SecurityConfig:

- student@email.com → ROLE_STUDENT

- admin@email.com → ROLE_ADMIN

- Autenticação via HTTP Basic.

## Autorizações:

- /feedback/** → STUDENT ou ADMIN

- /admin/** → ADMIN

**Senhas codificadas com BCrypt.**

## Banco de Dados

- Cloud SQL (Postgres 16)

- Flyway é utilizado para versionamento e migração do banco.

- Usuário: fiap_user

- Senha: pass

## Dependências Principais

- Spring Boot: Web, Data JPA, Security, Validation

- GCP: Pub/Sub, Cloud SQL Postgres

- PostgreSQL Driver

- Flyway (versão 10.21.0)

- Lombok (opcional)

## Testes:

- JUnit + Testcontainers (Postgres)

- Spring Security Test
