 Home Renovation Project Tracker API Documentation

This documentation covers the controllers for managing Budgets, Contractors, and Projects in the Home Renovation Project Tracker API.

Table of Contents
- [Budget Controller](#budget-controller)
  - [Create Budget](#create-budget)
  - [Get All Budgets](#get-all-budgets)
  - [Get Budget by ID](#get-budget-by-id)
  - [Update Budget](#update-budget)
  - [Delete Budget](#delete-budget)
- [Contractor Controller](#contractor-controller)
  - [Create Contractor](#create-contractor)
  - [Get All Contractors](#get-all-contractors)
  - [Get Contractor by ID](#get-contractor-by-id)
  - [Update Contractor](#update-contractor)
  - [Delete Contractor](#delete-contractor)
- [Project Controller](#project-controller)
  - [Create Project](#create-project)
  - [Get All Projects](#get-all-projects)
  - [Get Project by ID](#get-project-by-id)
  - [Update Project](#update-project)
  - [Delete Project](#delete-project)

Budget Controller

Create Budget
**Endpoint:** `POST /api/budgets`

**Request Body:**
```json
{
    "totalBudget": 50000.0,
    "usedBudget": 15000.0
}
Response:

{
    "id": 1,
    "totalBudget": 50000.0,
    "usedBudget": 15000.0,
    "remainingBudget": 35000.0
}
Get All Budgets
Endpoint: GET /api/budgets

Response:

[
    {
        "id": 1,
        "totalBudget": 50000.0,
        "usedBudget": 15000.0,
        "remainingBudget": 35000.0
    },
    ...
]
Get Budget by ID
Endpoint: GET /api/budgets/{id}

Response:

{
    "id": 1,
    "totalBudget": 50000.0,
    "usedBudget": 15000.0,
    "remainingBudget": 35000.0
}
Update Budget
Endpoint: PUT /api/budgets/{id}

Request Body:

{
    "totalBudget": 60000.0,
    "usedBudget": 20000.0
}
Response:

{
    "id": 1,
    "totalBudget": 60000.0,
    "usedBudget": 20000.0,
    "remainingBudget": 40000.0
}
Delete Budget
Endpoint: DELETE /api/budgets/{id}

Response: 204 No Content

Contractor Controller
Create Contractor
Endpoint: POST /api/contractors

Request Body:

{
    "name": "John Doe",
    "specialty": "Plumbing",
    "contactInfo": "john.doe@example.com"
}
Response:

{
    "id": 1,
    "name": "John Doe",
    "specialty": "Plumbing",
    "contactInfo": "john.doe@example.com"
}
Get All Contractors
Endpoint: GET /api/contractors

Response:

[
    {
        "id": 1,
        "name": "John Doe",
        "specialty": "Plumbing",
        "contactInfo": "john.doe@example.com"
    },
    ...
]
Get Contractor by ID
Endpoint: GET /api/contractors/{id}

Response:

{
    "id": 1,
    "name": "John Doe",
    "specialty": "Plumbing",
    "contactInfo": "john.doe@example.com"
}
Update Contractor
Endpoint: PUT /api/contractors/{id}

Request Body:

{
    "name": "John Doe",
    "specialty": "Electrical",
    "contactInfo": "john.doe@example.com"
}
Response:

{
    "id": 1,
    "name": "John Doe",
    "specialty": "Electrical",
    "contactInfo": "john.doe@example.com"
}
Delete Contractor
Endpoint: DELETE /api/contractors/{id}

Response: 204 No Content

Project Controller
Create Project
Endpoint: POST /api/projects

Request Body:

{
    "name": "Kitchen Renovation",
    "description": "Renovating the kitchen with new cabinets and appliances.",
    "budgetId": 1,
    "contractorId": 1
}
Response:

{
    "id": 1,
    "name": "Kitchen Renovation",
    "description": "Renovating the kitchen with new cabinets and appliances.",
    "budgetId": 1,
    "contractorId": 1
}
Get All Projects
Endpoint: GET /api/projects

Response:

[
    {
        "id": 1,
        "name": "Kitchen Renovation",
        "description": "Renovating the kitchen with new cabinets and appliances.",
        "budgetId": 1,
        "contractorId": 1
    },
    ...
]
Get Project by ID
Endpoint: GET /api/projects/{id}

Response:

{
    "id": 1,
    "name": "Kitchen Renovation",
    "description": "Renovating the kitchen with new cabinets and appliances.",
    "budgetId": 1,
    "contractorId": 1
}
Update Project
Endpoint: PUT /api/projects/{id}

Request Body:

{
    "name": "Living Room Renovation",
    "description": "Renovating the living room with new furniture and lighting.",
    "budgetId": 1,
    "contractorId": 2
}
Response:

{
    "id": 1,
    "name": "Living Room Renovation",
    "description": "Renovating the living room with new furniture and lighting.",
    "budgetId": 1,
    "contractorId": 2
}
Delete Project
Endpoint: DELETE /api/projects/{id}

Response: 204 No Content

This documentation provides an overview of the API endpoints for managing budgets, contractors, and projects in the Home Renovation Project Tracker. Each section includes the endpoint, sample request body (for POST and PUT requests), and expected response.
