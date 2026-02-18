📘 Entity Class Diagram
+----------------------+
|        User          |
+----------------------+
| id : Long (PK)       |
| name : String        |
| email : String       |
| password : String    |
| role : String        |
| createdAt : DateTime |
| updatedAt : DateTime |
+----------------------+
        |
        | 1
        |---------------------*
                              |
                     +----------------------+
                     |   ApprovalRequest    |
                     +----------------------+
                     | id : Long (PK)       |
                     | title : String       |
                     | description : Text   |
                     | status : String      |
                     | createdAt : DateTime |
                     | updatedAt : DateTime |
                     | requesterId : Long(FK)|
                     | templateId : Long(FK) |
                     +----------------------+
                              |
                              | 1
                              |----------------------*
                                                     |
                                           +----------------------+
                                           |       Approval       |
                                           +----------------------+
                                           | id : Long (PK)       |
                                           | status : String      |
                                           | comments : String    |
                                           | actionDate : DateTime|
                                           | approverId : Long(FK)|
                                           | requestId : Long(FK) |
                                           +----------------------+

🧩 Workflow Entities
+---------------------------+
|     WorkflowTemplate      |
+---------------------------+
| id : Long (PK)            |
| name : String             |
| description : String      |
| createdAt : DateTime      |
+---------------------------+
            |
            | 1
            |----------------------*
                                   |
                         +--------------------------+
                         |     WorkflowLevel        |
                         +--------------------------+
                         | id : Long (PK)           |
                         | levelOrder : Integer     |
                         | roleRequired : String    |
                         | templateId : Long (FK)   |
                         +--------------------------+


🔗 Relationships Summary
1️⃣ User ↔ ApprovalRequest

One User can create many ApprovalRequests
ApprovalRequest.requesterId → User.id
Relationship: @ManyToOne

2️⃣ ApprovalRequest ↔ Approval

One Request can have multiple Approvals
Approval.requestId → ApprovalRequest.id
Relationship: @OneToMany

3️⃣ User ↔ Approval

One User can approve many requests
Approval.approverId → User.id
Relationship: @ManyToOne

4️⃣ WorkflowTemplate ↔ WorkflowLevel

One Template contains multiple Levels
WorkflowLevel.templateId → WorkflowTemplate.id
Relationship: @OneToMany

5️⃣ WorkflowTemplate ↔ ApprovalRequest

Each Request follows one Workflow Template
ApprovalRequest.templateId → WorkflowTemplate.id
Relationship: @ManyToOne