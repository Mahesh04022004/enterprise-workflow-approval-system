Non-Functional Requirements

This is what makes your project enterprise-level.

🔐 Security

NFR-1: All APIs must require authentication (except login).
NFR-2: Role-based access control must be enforced.
NFR-3: Users must not approve their own request.
NFR-4: Input validation must be applied.

⚡ Performance

NFR-5: Approval actions must complete within 2 seconds.
NFR-6: System must support at least 500 concurrent users (design assumption).

📈 Scalability

NFR-7: Architecture must allow adding new workflow types without major code changes.

🛠 Maintainability

NFR-8: Code must follow layered architecture.
NFR-9: Business logic must be separated from controller layer.
NFR-10: API responses must follow consistent structure.

🧾 Auditability

NFR-11: All state transitions must be logged.

🔄 Reliability

NFR-12: Workflow transitions must be transactional (no partial updates).

6️⃣ Assumptions

Each user has only one role (MVP).
Workflows are sequential only.
Conditions are based only on numeric amount.
Single organization only.

7️⃣ Constraints

Backend: Spring Boot

Database: MySQL/Postgres

JWT-based authentication
No microservices (monolith for MVP)