# Smart Logistics and Transportation Web Application
## Rwanda Administrative Structure

---

## Project Overview
Spring Boot application demonstrating database relationships using Rwanda's official administrative hierarchy for a logistics management system.

## Technologies Used
- Spring Boot 2.7.0
- Spring Data JPA
- PostgreSQL Database
- Maven

---

## Rwanda Administrative Structure

This project uses Rwanda's official administrative divisions:

```
Province → District → Sector → Cell → Village
```

**Example:**
- Province: Kigali City
- District: Gasabo
- Sector: Kimironko
- Cell: Kibagabaga
- Village: Nyarutarama

---

## Database Tables (8 Tables + 1 Join Table)

### Administrative Hierarchy (5 Tables)
1. **Province** - Rwanda's 5 provinces
2. **District** - Districts within provinces
3. **Sector** - Sectors within districts
4. **Cell** - Cells within sectors
5. **Village** - Villages within cells

### Operational Tables (3 Tables)
6. **User** - Village managers (One-to-One with Village)
7. **Vehicle** - Transportation vehicles (Many-to-One with Village)
8. **Shipment** - Cargo/shipments

### Join Table
9. **Shipment_Vehicle** - Many-to-Many relationship

---

## Relationships Implemented

✅ **One-to-Many (Multiple)**
- Province → District
- District → Sector
- Sector → Cell
- Cell → Village
- Village → Vehicle

✅ **One-to-One**
- Village ↔ User

✅ **Many-to-Many**
- Shipment ↔ Vehicle (via join table)

---

## Setup Instructions

### 1. Create PostgreSQL Database
```sql
CREATE DATABASE logistics_db;
```

### 2. Configure Database
File: `src/main/resources/application.properties`
```properties
spring.datasource.username=postgres
spring.datasource.password=WebTech2026!
```

### 3. Run Application
```bash
mvn spring-boot:run
```

Application runs on: **http://localhost:8080**

---

## Testing the Application

### Step 1: Create Administrative Hierarchy

```bash
# 1. Create Province
curl -X POST http://localhost:8080/api/provinces -H "Content-Type: application/json" -d "{\"provinceCode\":\"KGL\",\"provinceName\":\"Kigali City\"}"

# 2. Create District
curl -X POST "http://localhost:8080/api/districts?districtName=Gasabo&provinceId=1"

# 3. Create Sector
curl -X POST "http://localhost:8080/api/sectors?sectorName=Kimironko&districtId=1"

# 4. Create Cell
curl -X POST "http://localhost:8080/api/cells?cellName=Kibagabaga&sectorId=1"

# 5. Create Village (Demonstrates Saving with Relationship)
curl -X POST "http://localhost:8080/api/villages?villageName=Nyarutarama&cellId=1"
```

### Step 2: Test existsBy() Method
```bash
curl http://localhost:8080/api/villages/exists/Nyarutarama
# Returns: true
```

### Step 3: Create User (One-to-One)
```bash
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d "{\"username\":\"patrick\",\"email\":\"patrick@auca.ac.rw\",\"phone\":\"0788123456\",\"village\":{\"villageId\":1}}"
```

### Step 4: Get Users by Province Code
```bash
curl http://localhost:8080/api/users/province/code/KGL
# Returns: All users in Kigali province
```

### Step 5: Create Vehicle (Many-to-One)
```bash
curl -X POST http://localhost:8080/api/vehicles -H "Content-Type: application/json" -d "{\"vehicleNumber\":\"RAD123A\",\"vehicleType\":\"Truck\",\"capacity\":5000.0,\"village\":{\"villageId\":1}}"
```

### Step 6: Test Pagination and Sorting
```bash
curl "http://localhost:8080/api/vehicles/sorted?page=0&size=10&sortBy=vehicleType&direction=asc"
```

### Step 7: Create Shipment
```bash
curl -X POST http://localhost:8080/api/shipments -H "Content-Type: application/json" -d "{\"shipmentCode\":\"SHP001\",\"description\":\"Electronics\",\"weight\":500.0,\"status\":\"Pending\"}"
```

### Step 8: Assign Vehicles to Shipment (Many-to-Many)
```bash
curl -X POST http://localhost:8080/api/shipments/1/assign-vehicles -H "Content-Type: application/json" -d "[1]"
```

---

## Features Implemented (All Requirements Met)

✅ 1. **ERD with 5 tables** - Province, District, Sector, Cell, Village
✅ 2. **Saving Village** - Demonstrates relationship handling with Cell
✅ 3. **Sorting and Pagination** - Vehicle sorting by type, capacity, etc.
✅ 4. **Many-to-Many** - Shipment ↔ Vehicle with join table
✅ 5. **One-to-Many** - Province→District→Sector→Cell→Village chain
✅ 6. **One-to-One** - User ↔ Village (village manager)
✅ 7. **existsBy()** - Check if village exists before saving
✅ 8. **Users by Province** - Retrieve users by province code or name

---

## Project Structure

```
src/
├── main/
│   ├── java/com/logistics/
│   │   ├── entity/
│   │   │   ├── Province.java
│   │   │   ├── District.java
│   │   │   ├── Sector.java
│   │   │   ├── Cell.java
│   │   │   ├── Village.java
│   │   │   ├── User.java
│   │   │   ├── Vehicle.java
│   │   │   └── Shipment.java
│   │   ├── repository/
│   │   │   ├── ProvinceRepository.java
│   │   │   ├── DistrictRepository.java
│   │   │   ├── SectorRepository.java
│   │   │   ├── CellRepository.java
│   │   │   ├── VillageRepository.java
│   │   │   ├── UserRepository.java
│   │   │   ├── VehicleRepository.java
│   │   │   └── ShipmentRepository.java
│   │   ├── service/
│   │   │   ├── ProvinceService.java
│   │   │   ├── DistrictService.java
│   │   │   ├── SectorService.java
│   │   │   ├── CellService.java
│   │   │   ├── VillageService.java
│   │   │   ├── UserService.java
│   │   │   ├── VehicleService.java
│   │   │   └── ShipmentService.java
│   │   ├── controller/
│   │   │   ├── ProvinceController.java
│   │   │   ├── DistrictController.java
│   │   │   ├── SectorController.java
│   │   │   ├── CellController.java
│   │   │   ├── VillageController.java
│   │   │   ├── UserController.java
│   │   │   ├── VehicleController.java
│   │   │   └── ShipmentController.java
│   │   └── LogisticsApplication.java
│   └── resources/
│       └── application.properties
└── pom.xml
```

---

## Documentation Files

1. **README.md** - This file
2. **QUICK_START.md** - 5-minute setup guide
3. **ERD_EXPLANATION.md** - Complete ERD with Rwanda's structure
4. **EXAM_EXPLANATIONS.md** - Detailed explanations for all requirements
5. **VIVA_VOCE_GUIDE.md** - Quick reference for viva questions

---

## Why Rwanda's Administrative Structure?

✅ **Real-world application** - Uses actual Rwanda geography
✅ **Demonstrates hierarchy** - 5 connected One-to-Many relationships
✅ **Practical for logistics** - Vehicles stationed at village level
✅ **Familiar to Rwandans** - Everyone knows their Province/District/Sector
✅ **Professional** - Shows understanding of local context

---

## Author
Patrick DUSHIMIMANA
Adventist University of Central Africa (AUCA)
Course: Web Technology and Internet
Date: February 20, 2026

---

**Good luck with your practical examination! 🇷🇼**
