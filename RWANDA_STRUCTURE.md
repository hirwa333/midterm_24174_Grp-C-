# 🇷🇼 RWANDA ADMINISTRATIVE STRUCTURE - VISUAL GUIDE

## Complete Hierarchy

```
┌─────────────────────────────────────────────────────────────┐
│                        PROVINCE                             │
│  (Kigali City, Eastern, Western, Northern, Southern)        │
│  - province_id (PK)                                         │
│  - province_code (Unique)                                   │
│  - province_name                                            │
└────────────────────────┬────────────────────────────────────┘
                         │ ONE-TO-MANY
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                        DISTRICT                             │
│  (Gasabo, Kicukiro, Nyarugenge, Rwamagana, etc.)           │
│  - district_id (PK)                                         │
│  - district_name                                            │
│  - province_id (FK)                                         │
└────────────────────────┬────────────────────────────────────┘
                         │ ONE-TO-MANY
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                         SECTOR                              │
│  (Kimironko, Remera, Kacyiru, Nyamirambo, etc.)           │
│  - sector_id (PK)                                           │
│  - sector_name                                              │
│  - district_id (FK)                                         │
└────────────────────────┬────────────────────────────────────┘
                         │ ONE-TO-MANY
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                          CELL                               │
│  (Kibagabaga, Nyarutarama, Gacuriro, etc.)                 │
│  - cell_id (PK)                                             │
│  - cell_name                                                │
│  - sector_id (FK)                                           │
└────────────────────────┬────────────────────────────────────┘
                         │ ONE-TO-MANY
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                        VILLAGE                              │
│  (Specific neighborhoods/villages)                          │
│  - village_id (PK)                                          │
│  - village_name                                             │
│  - cell_id (FK)                                             │
└──────────┬──────────────────────────┬───────────────────────┘
           │ ONE-TO-ONE               │ ONE-TO-MANY
           ▼                          ▼
┌──────────────────────┐   ┌──────────────────────────────────┐
│       USER           │   │          VEHICLE                 │
│  - user_id (PK)      │   │  - vehicle_id (PK)               │
│  - username          │   │  - vehicle_number                │
│  - email             │   │  - vehicle_type                  │
│  - phone             │   │  - capacity                      │
│  - village_id (FK)   │   │  - village_id (FK)               │
└──────────────────────┘   └────────────┬─────────────────────┘
                                        │ MANY-TO-MANY
                                        ▼
                           ┌────────────────────────────┐
                           │  SHIPMENT_VEHICLE          │
                           │  (Join Table)              │
                           │  - shipment_id (FK)        │
                           │  - vehicle_id (FK)         │
                           └────────────┬───────────────┘
                                        │
                                        ▼
                           ┌────────────────────────────┐
                           │       SHIPMENT             │
                           │  - shipment_id (PK)        │
                           │  - shipment_code           │
                           │  - description             │
                           │  - weight                  │
                           │  - status                  │
                           └────────────────────────────┘
```

---

## Real Example: Complete Address in Rwanda

```
🇷🇼 RWANDA

Province: Kigali City
    ↓
District: Gasabo
    ↓
Sector: Kimironko
    ↓
Cell: Kibagabaga
    ↓
Village: Nyarutarama

👤 User: Patrick DUSHIMIMANA
   Email: patrick@auca.ac.rw
   Phone: 0788123456
   Role: Village Manager

🚛 Vehicles stationed here:
   - RAD123A (Truck, 5000kg)
   - RAD456B (Van, 2000kg)
   - RAD789C (Pickup, 1500kg)

📦 Shipments:
   - SHP001: Electronics (500kg) → Using RAD123A, RAD456B
   - SHP002: Food Items (1200kg) → Using RAD123A
```

---

## Relationship Types Summary

### 1️⃣ ONE-TO-MANY (5 Examples)

```
Province (1) ──────► District (Many)
District (1) ──────► Sector (Many)
Sector (1) ────────► Cell (Many)
Cell (1) ──────────► Village (Many)
Village (1) ───────► Vehicle (Many)
```

**Example:**
- Kigali City has 3 districts: Gasabo, Kicukiro, Nyarugenge
- Gasabo has many sectors: Kimironko, Remera, Kacyiru, etc.

---

### 2️⃣ ONE-TO-ONE (1 Example)

```
Village (1) ◄──────► User (1)
```

**Example:**
- Nyarutarama village ↔ Patrick (manager)
- Each village has exactly one manager
- Each manager manages exactly one village

---

### 3️⃣ MANY-TO-MANY (1 Example)

```
Shipment (Many) ◄──────► Vehicle (Many)
         ▲                    ▲
         │                    │
         └────► Join Table ◄──┘
           (shipment_vehicle)
```

**Example:**
- Shipment SHP001 uses vehicles RAD123A and RAD456B
- Vehicle RAD123A transports shipments SHP001 and SHP002

---

## Database Foreign Keys

```
district.province_id ──────► province.province_id
sector.district_id ────────► district.district_id
cell.sector_id ────────────► sector.sector_id
village.cell_id ───────────► cell.cell_id
user.village_id ───────────► village.village_id (UNIQUE)
vehicle.village_id ────────► village.village_id

shipment_vehicle.shipment_id ──► shipment.shipment_id
shipment_vehicle.vehicle_id ───► vehicle.vehicle_id
```

---

## API Flow Example

### Creating Complete Hierarchy

```
1. POST /api/provinces
   Body: {"provinceCode":"KGL", "provinceName":"Kigali City"}
   ↓ Creates province_id = 1

2. POST /api/districts?districtName=Gasabo&provinceId=1
   ↓ Creates district_id = 1, links to province_id = 1

3. POST /api/sectors?sectorName=Kimironko&districtId=1
   ↓ Creates sector_id = 1, links to district_id = 1

4. POST /api/cells?cellName=Kibagabaga&sectorId=1
   ↓ Creates cell_id = 1, links to sector_id = 1

5. POST /api/villages?villageName=Nyarutarama&cellId=1
   ↓ Creates village_id = 1, links to cell_id = 1

6. POST /api/users
   Body: {"username":"patrick", "village":{"villageId":1}}
   ↓ Creates user_id = 1, links to village_id = 1 (ONE-TO-ONE)

7. POST /api/vehicles
   Body: {"vehicleNumber":"RAD123A", "village":{"villageId":1}}
   ↓ Creates vehicle_id = 1, links to village_id = 1 (MANY-TO-ONE)

8. POST /api/shipments
   Body: {"shipmentCode":"SHP001", "description":"Electronics"}
   ↓ Creates shipment_id = 1

9. POST /api/shipments/1/assign-vehicles
   Body: [1]
   ↓ Creates record in shipment_vehicle table (MANY-TO-MANY)
```

---

## Query Navigation Example

### Get Users by Province Code

```java
@Query("SELECT u FROM User u 
        WHERE u.village.cell.sector.district.province.provinceCode = :code")
```

**Navigation Path:**
```
User → village → cell → sector → district → province → provinceCode
```

**Generated SQL:**
```sql
SELECT u.* 
FROM users u
INNER JOIN village v ON u.village_id = v.village_id
INNER JOIN cell c ON v.cell_id = c.cell_id
INNER JOIN sector s ON c.sector_id = s.sector_id
INNER JOIN district d ON s.district_id = d.district_id
INNER JOIN province p ON d.province_id = p.province_id
WHERE p.province_code = 'KGL'
```

---

## Why This Structure is Perfect for Exam

✅ **5 Connected Tables** - Province → District → Sector → Cell → Village
✅ **Real Rwanda Geography** - Official administrative structure
✅ **Multiple One-to-Many** - Demonstrates relationship chain
✅ **One-to-One** - Village ↔ User (unique constraint)
✅ **Many-to-Many** - Shipment ↔ Vehicle (join table)
✅ **Practical Application** - Logistics system for Rwanda
✅ **Professional** - Shows local knowledge and context

---

**This is exactly what your teacher expects! 🇷🇼**
