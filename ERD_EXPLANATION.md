# Entity Relationship Diagram (ERD) - Smart Logistics System
## Rwanda Administrative Structure

---

## Tables and Relationships

### RWANDA ADMINISTRATIVE HIERARCHY (5 Tables)

### 1. PROVINCE
- province_id (Primary Key)
- province_code (Unique) - e.g., "KGL", "EST", "WST", "NTH", "STH"
- province_name - e.g., "Kigali City", "Eastern Province"

### 2. DISTRICT
- district_id (Primary Key)
- district_name - e.g., "Gasabo", "Kicukiro", "Nyarugenge"
- province_id (Foreign Key -> PROVINCE)

### 3. SECTOR
- sector_id (Primary Key)
- sector_name - e.g., "Kimironko", "Remera", "Kacyiru"
- district_id (Foreign Key -> DISTRICT)

### 4. CELL
- cell_id (Primary Key)
- cell_name - e.g., "Kibagabaga", "Nyarutarama"
- sector_id (Foreign Key -> SECTOR)

### 5. VILLAGE
- village_id (Primary Key)
- village_name - Specific neighborhood/village name
- cell_id (Foreign Key -> CELL)

### OPERATIONAL TABLES (3 Tables)

### 6. USER
- user_id (Primary Key)
- username
- email
- phone
- village_id (Foreign Key -> VILLAGE) - ONE-TO-ONE

### 7. VEHICLE
- vehicle_id (Primary Key)
- vehicle_number
- vehicle_type
- capacity
- village_id (Foreign Key -> VILLAGE) - MANY-TO-ONE

### 8. SHIPMENT
- shipment_id (Primary Key)
- shipment_code
- description
- weight
- status

### 9. SHIPMENT_VEHICLE (Join Table for Many-to-Many)
- shipment_id (Foreign Key -> SHIPMENT)
- vehicle_id (Foreign Key -> VEHICLE)

---

## Relationships Explained

### ADMINISTRATIVE HIERARCHY (One-to-Many Chain)

**Province → District (One-to-Many)**
- One Province contains MANY Districts
- Example: Kigali City has Gasabo, Kicukiro, Nyarugenge districts
- Foreign Key: district_id in DISTRICT table

**District → Sector (One-to-Many)**
- One District contains MANY Sectors
- Example: Gasabo has Kimironko, Remera, Kacyiru sectors
- Foreign Key: sector_id in SECTOR table

**Sector → Cell (One-to-Many)**
- One Sector contains MANY Cells
- Example: Kimironko has Kibagabaga, Nyarutarama cells
- Foreign Key: cell_id in CELL table

**Cell → Village (One-to-Many)**
- One Cell contains MANY Villages
- Example: Kibagabaga cell has multiple villages
- Foreign Key: village_id in VILLAGE table

### OPERATIONAL RELATIONSHIPS

**Village ↔ User (One-to-One)**
- Each Village has exactly ONE User (manager)
- Each User manages exactly ONE Village
- Foreign Key: village_id in USER table with unique constraint

**Village → Vehicle (One-to-Many)**
- One Village can have MANY Vehicles stationed
- Each Vehicle is stationed at ONE Village
- Foreign Key: village_id in VEHICLE table

**Shipment ↔ Vehicle (Many-to-Many)**
- One Shipment can be transported by MANY Vehicles
- One Vehicle can transport MANY Shipments
- Join Table: SHIPMENT_VEHICLE with composite key (shipment_id, vehicle_id)

---

## Complete Hierarchy Visualization

```
PROVINCE (Kigali City)
    ↓ has many
DISTRICT (Gasabo, Kicukiro, Nyarugenge)
    ↓ has many
SECTOR (Kimironko, Remera, Kacyiru)
    ↓ has many
CELL (Kibagabaga, Nyarutarama)
    ↓ has many
VILLAGE (Specific neighborhoods)
    ↓ has one (1:1)
USER (Village manager)
    
VILLAGE
    ↓ has many (1:N)
VEHICLE (Trucks, Vans stationed at village)
    ↓ many-to-many (M:N)
SHIPMENT (Cargo transported by vehicles)
```

---

## Business Logic

**Why This Structure?**

1. **Real Rwanda Geography**: Follows official administrative divisions
2. **Hierarchical Management**: Each level manages the level below
3. **Logistics Operations**: 
   - Vehicles stationed at village level (most specific location)
   - Users manage villages (accountability)
   - Shipments assigned to vehicles (flexible transportation)

**Example Scenario:**
- Province: Kigali City
- District: Gasabo
- Sector: Kimironko
- Cell: Kibagabaga
- Village: Specific neighborhood in Kibagabaga
- User: Patrick manages this village
- Vehicles: 3 trucks stationed at this village
- Shipments: Electronics shipment assigned to 2 of these trucks

---

## Database Relationships Summary

| Relationship | Type | Example |
|--------------|------|---------|
| Province → District | One-to-Many | Kigali → Gasabo, Kicukiro |
| District → Sector | One-to-Many | Gasabo → Kimironko, Remera |
| Sector → Cell | One-to-Many | Kimironko → Kibagabaga |
| Cell → Village | One-to-Many | Kibagabaga → Villages |
| Village ↔ User | One-to-One | Village ↔ Manager |
| Village → Vehicle | One-to-Many | Village → Trucks |
| Shipment ↔ Vehicle | Many-to-Many | Cargo ↔ Trucks |

---

This structure demonstrates:
✅ 5 connected administrative tables (Rwanda's structure)
✅ One-to-One relationship (Village ↔ User)
✅ Multiple One-to-Many relationships (Province → District → Sector → Cell → Village)
✅ Many-to-Many relationship (Shipment ↔ Vehicle)
✅ Real-world Rwanda logistics system
