# PROJECT STRUCTURE AND FLOW DIAGRAMS

## 1. DATABASE SCHEMA DIAGRAM

```
┌─────────────────┐
│    PROVINCE     │
├─────────────────┤
│ province_id (PK)│
│ province_code   │
│ province_name   │
└────────┬────────┘
         │ 1
         │
         │ N
┌────────▼────────┐
│    LOCATION     │
├─────────────────┤
│ location_id (PK)│
│ location_name   │
│ address         │
│ province_id (FK)│
└────┬───────┬────┘
     │ 1     │ 1
     │       │
     │ 1     │ N
┌────▼────┐ ┌▼────────────┐
│  USER   │ │   VEHICLE   │
├─────────┤ ├─────────────┤
│user_id  │ │vehicle_id   │
│username │ │vehicle_num  │
│email    │ │vehicle_type │
│phone    │ │capacity     │
│loc_id   │ │location_id  │
└─────────┘ └──────┬──────┘
                   │ N
                   │
                   │ N
            ┌──────▼──────────┐
            │SHIPMENT_VEHICLE │ (Join Table)
            ├─────────────────┤
            │shipment_id (FK) │
            │vehicle_id (FK)  │
            └──────┬──────────┘
                   │ N
                   │
                   │ N
            ┌──────▼──────┐
            │  SHIPMENT   │
            ├─────────────┤
            │shipment_id  │
            │shipment_code│
            │description  │
            │weight       │
            │status       │
            └─────────────┘
```

## 2. APPLICATION LAYER ARCHITECTURE

```
┌─────────────────────────────────────────────┐
│           PRESENTATION LAYER                │
│  (Controllers - Handle HTTP Requests)       │
├─────────────────────────────────────────────┤
│  ProvinceController                         │
│  LocationController                         │
│  UserController                             │
│  VehicleController                          │
│  ShipmentController                         │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│            BUSINESS LAYER                   │
│  (Services - Business Logic)                │
├─────────────────────────────────────────────┤
│  ProvinceService                            │
│  LocationService                            │
│  UserService                                │
│  VehicleService                             │
│  ShipmentService                            │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│          PERSISTENCE LAYER                  │
│  (Repositories - Data Access)               │
├─────────────────────────────────────────────┤
│  ProvinceRepository                         │
│  LocationRepository                         │
│  UserRepository                             │
│  VehicleRepository                          │
│  ShipmentRepository                         │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│              DATABASE                       │
│  (MySQL - logistics_db)                     │
└─────────────────────────────────────────────┘
```

## 3. REQUEST FLOW EXAMPLE

### Example: Get Users by Province Code

```
1. HTTP Request
   GET /api/users/province/code/KGL
   │
   ▼
2. UserController.getUsersByProvinceCode("KGL")
   │
   ▼
3. UserService.getUsersByProvinceCode("KGL")
   │
   ▼
4. UserRepository.findUsersByProvinceCode("KGL")
   │
   ▼
5. Spring Data JPA generates SQL:
   SELECT u.* FROM users u
   INNER JOIN location l ON u.location_id = l.location_id
   INNER JOIN province p ON l.province_id = p.province_id
   WHERE p.province_code = 'KGL'
   │
   ▼
6. MySQL executes query and returns results
   │
   ▼
7. JPA maps results to User entities
   │
   ▼
8. Service returns List<User>
   │
   ▼
9. Controller returns ResponseEntity<List<User>>
   │
   ▼
10. Spring converts to JSON and sends HTTP Response
```

## 4. RELATIONSHIP MAPPINGS

### One-to-One: User ↔ Location
```
USER Table              LOCATION Table
┌──────────┐           ┌──────────┐
│ user_id  │           │location_id│
│ username │           │loc_name   │
│ email    │           │address    │
│location_id├──────────►│province_id│
└──────────┘  (FK)     └──────────┘
   unique constraint ensures 1:1
```

### One-to-Many: Province → Location
```
PROVINCE Table          LOCATION Table
┌──────────┐           ┌──────────┐
│province_id│◄──────────┤province_id│ (FK)
│prov_code │      N    │location_id│
│prov_name │           │loc_name   │
└──────────┘           │address    │
     1                 └──────────┘
```

### Many-to-Many: Shipment ↔ Vehicle
```
SHIPMENT Table      SHIPMENT_VEHICLE      VEHICLE Table
┌──────────┐       ┌──────────────┐      ┌──────────┐
│shipment_id│◄──────┤shipment_id   │      │vehicle_id│
│ship_code │   N   │vehicle_id    ├──────►│veh_number│
│description│       └──────────────┘  N   │veh_type  │
│weight    │         (Join Table)         │capacity  │
└──────────┘                              └──────────┘
```

## 5. PAGINATION VISUALIZATION

### Without Pagination (Load All)
```
Database: [1][2][3][4][5][6][7][8][9][10]...[1000]
                        ▼
Memory:   [1][2][3][4][5][6][7][8][9][10]...[1000]
          ❌ Slow, Memory intensive
```

### With Pagination (Load Page)
```
Database: [1][2][3][4][5][6][7][8][9][10]...[1000]
                  ▼ LIMIT 5 OFFSET 0
Memory:   [1][2][3][4][5]
          ✅ Fast, Memory efficient
```

## 6. SORTING VISUALIZATION

### Ascending Sort by vehicleType
```
Before:  [Truck] [Van] [Pickup] [Truck] [Van]
After:   [Pickup] [Truck] [Truck] [Van] [Van]
         ▲ A comes before T, T before V
```

### Descending Sort by capacity
```
Before:  [5000] [2000] [7000] [1500] [6000]
After:   [7000] [6000] [5000] [2000] [1500]
         ▲ Highest to lowest
```

## 7. existsBy() PERFORMANCE

### Using findBy() - Loads entire entity
```
SELECT * FROM location WHERE location_name = 'Downtown'
▼
Load all columns into memory
▼
Return entity object
❌ Slower, more memory
```

### Using existsBy() - Only checks existence
```
SELECT COUNT(*) > 0 FROM location WHERE location_name = 'Downtown'
▼
Return boolean (true/false)
✅ Faster, less memory
```

## 8. CASCADE OPERATIONS

### CascadeType.ALL Example
```
Province (parent)
    │
    ├─ Location 1 (child)
    ├─ Location 2 (child)
    └─ Location 3 (child)

DELETE Province
    ▼ CASCADE
DELETE Location 1, 2, 3 automatically
```

## 9. DEPENDENCY INJECTION FLOW

```
Spring Container
┌─────────────────────────────────┐
│  Creates and manages beans:     │
│  ┌──────────────────┐           │
│  │ UserController   │           │
│  │   @Autowired     │           │
│  │   UserService ───┼───┐       │
│  └──────────────────┘   │       │
│                          ▼       │
│  ┌──────────────────────────┐   │
│  │ UserService              │   │
│  │   @Autowired             │   │
│  │   UserRepository ────┼───┐   │
│  └──────────────────────┘   │   │
│                              ▼   │
│  ┌──────────────────────────┐   │
│  │ UserRepository           │   │
│  └──────────────────────────┘   │
└─────────────────────────────────┘
Spring automatically injects dependencies
```

## 10. API ENDPOINT STRUCTURE

```
/api
├── /provinces
│   ├── POST /                    (Create)
│   ├── GET /                     (Get All)
│   └── GET /exists/{code}        (Check Exists)
│
├── /locations
│   ├── POST /                    (Create)
│   ├── GET /                     (Get All)
│   └── GET /exists/{name}        (Check Exists)
│
├── /users
│   ├── POST /                    (Create)
│   ├── GET /province/code/{code} (Get by Province Code)
│   ├── GET /province/name/{name} (Get by Province Name)
│   ├── GET /paginated            (Get with Pagination)
│   └── GET /exists/{username}    (Check Exists)
│
├── /vehicles
│   ├── POST /                    (Create)
│   ├── GET /sorted               (Get with Pagination & Sort)
│   └── GET /exists/{number}      (Check Exists)
│
└── /shipments
    ├── POST /                    (Create)
    ├── POST /{id}/assign-vehicles (Assign Vehicles)
    └── GET /                     (Get All)
```

---

This visual guide should help you understand and explain the project structure during your viva-voce!
