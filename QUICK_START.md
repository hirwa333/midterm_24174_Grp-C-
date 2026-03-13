# QUICK START GUIDE - Rwanda Administrative Structure

## STEP 1: Database Setup (1 minute)

Open PostgreSQL (psql or pgAdmin) and run:
```sql
CREATE DATABASE logistics_db;
```

## STEP 2: Run Application (2 minutes)

```bash
cd "Smart logistics and transportation web application"
mvn spring-boot:run
```

Wait for: "Started LogisticsApplication"

## STEP 3: Test with Rwanda's Administrative Hierarchy

### Test 1: Create Province
```bash
curl -X POST http://localhost:8080/api/provinces -H "Content-Type: application/json" -d "{\"provinceCode\":\"KGL\",\"provinceName\":\"Kigali City\"}"
```

### Test 2: Create District (One-to-Many with Province)
```bash
curl -X POST "http://localhost:8080/api/districts?districtName=Gasabo&provinceId=1"
```

### Test 3: Create Sector (One-to-Many with District)
```bash
curl -X POST "http://localhost:8080/api/sectors?sectorName=Kimironko&districtId=1"
```

### Test 4: Create Cell (One-to-Many with Sector)
```bash
curl -X POST "http://localhost:8080/api/cells?cellName=Kibagabaga&sectorId=1"
```

### Test 5: Create Village (One-to-Many with Cell) - DEMONSTRATES SAVING
```bash
curl -X POST "http://localhost:8080/api/villages?villageName=Nyarutarama&cellId=1"
```

### Test 6: Check if Village Exists (existsBy method)
```bash
curl http://localhost:8080/api/villages/exists/Nyarutarama
```
Should return: true

### Test 7: Create User (One-to-One with Village)
```bash
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d "{\"username\":\"patrick\",\"email\":\"patrick@auca.ac.rw\",\"phone\":\"0788123456\",\"village\":{\"villageId\":1}}"
```

### Test 8: Get Users by Province Code
```bash
curl http://localhost:8080/api/users/province/code/KGL
```
Should return: List with patrick

### Test 9: Create Vehicle (Many-to-One with Village)
```bash
curl -X POST http://localhost:8080/api/vehicles -H "Content-Type: application/json" -d "{\"vehicleNumber\":\"RAD123A\",\"vehicleType\":\"Truck\",\"capacity\":5000.0,\"village\":{\"villageId\":1}}"
```

### Test 10: Get Vehicles with Pagination and Sorting
```bash
curl "http://localhost:8080/api/vehicles/sorted?page=0&size=10&sortBy=vehicleType&direction=asc"
```

### Test 11: Create Shipment
```bash
curl -X POST http://localhost:8080/api/shipments -H "Content-Type: application/json" -d "{\"shipmentCode\":\"SHP001\",\"description\":\"Electronics\",\"weight\":500.0,\"status\":\"Pending\"}"
```

### Test 12: Assign Vehicle to Shipment (Many-to-Many)
```bash
curl -X POST http://localhost:8080/api/shipments/1/assign-vehicles -H "Content-Type: application/json" -d "[1]"
```

---

## ALL REQUIREMENTS DEMONSTRATED ✅

1. ✅ **ERD with 5 tables** - Province, District, Sector, Cell, Village (Rwanda's structure)
2. ✅ **Saving Village** - Test 5 (demonstrates relationship handling)
3. ✅ **Sorting & Pagination** - Test 10
4. ✅ **Many-to-Many** - Test 12 (Shipment ↔ Vehicle)
5. ✅ **One-to-Many** - Tests 2,3,4,5 (Province→District→Sector→Cell→Village)
6. ✅ **One-to-One** - Test 7 (User ↔ Village)
7. ✅ **existsBy()** - Test 6
8. ✅ **Users by Province** - Test 8

---

## RWANDA ADMINISTRATIVE HIERARCHY CREATED

```
Province: Kigali City (KGL)
    ↓
District: Gasabo
    ↓
Sector: Kimironko
    ↓
Cell: Kibagabaga
    ↓
Village: Nyarutarama
    ↓
User: patrick (manager)
    ↓
Vehicle: RAD123A (Truck)
    ↓
Shipment: SHP001 (Electronics)
```

---

## API ENDPOINTS

### Administrative Hierarchy
- POST /api/provinces - Create province
- POST /api/districts - Create district
- POST /api/sectors - Create sector
- POST /api/cells - Create cell
- POST /api/villages - Create village
- GET /api/villages/exists/{name} - Check if exists

### Operational
- POST /api/users - Create user
- GET /api/users/province/code/{code} - Get users by province code
- GET /api/users/province/name/{name} - Get users by province name
- POST /api/vehicles - Create vehicle
- GET /api/vehicles/sorted - Get with pagination & sorting
- POST /api/shipments - Create shipment
- POST /api/shipments/{id}/assign-vehicles - Assign vehicles

