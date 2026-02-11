A. Authentication & Authorization

FR-1: System shall allow users to register and login.
FR-2: System shall issue JWT token upon successful login.
FR-3: System shall restrict access based on roles.
FR-4: Only Admin can configure workflows.
FR-5: Only assigned approver can approve a request at current level.

B. Request Management

FR-6: Employee shall create a request with:

Title
Description
Amount
Request Type

FR-7: System shall assign initial status upon submission.
FR-8: Employee shall view list of own requests.
FR-9: Employee shall cancel request if not yet approved.
FR-10: Employee cannot modify request after submission (optional decision).

C. Conditional Workflow Selection

FR-11: System shall evaluate request attributes (e.g., amount).
FR-12: Based on conditions, system shall select appropriate workflow template.
FR-13: System shall initiate first approval level automatically.

Example:

If amount < 50k → Manager only
If amount ≥ 50k → Manager → Finance
If amount ≥ 2L → Manager → Finance → Director

D. Approval Processing

FR-14: Approver shall see only requests assigned to them.
FR-15: Approver shall approve or reject with comment.
FR-16: On approval:

System moves to next level.
If last level → status becomes APPROVED.

FR-17: On rejection:

Status becomes REJECTED.
Workflow stops.

FR-18: System shall record approval timestamp and user.

E. Audit & Logging

FR-19: System shall maintain audit record for:

Submission
Approval
Rejection
Cancellation
FR-20: Audit history shall be viewable per request.

F. Admin Configuration

FR-21: Admin shall create workflow templates.
FR-22: Admin shall define conditions for workflow selection.
FR-23: Admin shall define approval levels sequentially.
FR-24: Admin shall assign role to each approval level.