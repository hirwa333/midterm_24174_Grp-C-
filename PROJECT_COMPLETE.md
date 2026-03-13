

** Structure:** Province → District → Sector → Cell → Village (Rwanda's official hierarchy)

---

## 📊 Database Tables (9 Total)

### Administrative Hierarchy (5 Tables) 
1. **Province** - Kigali City, Eastern, Western, Northern, Southern
2. **District** - Gasabo, Kicukiro, Nyarugenge, etc.
3. **Sector** - Kimironko, Remera, Kacyiru, etc.
4. **Cell** - Kibagabaga, Nyarutarama, etc.
5. **Village** - Specific neighborhoods

### Operational Tables (3 Tables) 
6. **User** - Village managers
7. **Vehicle** - Transportation vehicles
8. **Shipment** - Cargo/shipments

### Join Table 
9. **Shipment_Vehicle** - Many-to-Many join table

---

## 🔗 All Relationships Implemented

###  One-to-Many (5 Examples)
1. Province → District
2. District → Sector
3. Sector → Cell
4. Cell → Village
5. Village → Vehicle

###  One-to-One (1 Example)
- Village ↔ User (village manager)

###  Many-to-Many (1 Example)
- Shipment ↔ Vehicle (via join table)|
---

## 🚀 How to Run

### 1. Create Database
```sql
CREATE DATABASE logistics_db;
```

### 2. Run Application
```bash
mvn spring-boot:run
```

### 3. Test (Follow QUICK_START.md)
Create hierarchy: Province → District → Sector → Cell → Village → User → Vehicle → Shipment

---

## 📂 Project Files

### Entities (8 files)
- Province.java, District.java, Sector.java, Cell.java, Village.java
- User.java, Vehicle.java, Shipment.java

### Repositories (8 files)
- ProvinceRepository, DistrictRepository, SectorRepository, CellRepository
- VillageRepository, UserRepository, VehicleRepository, ShipmentRepository

### Services (8 files)
- ProvinceService, DistrictService, SectorService, CellService
- VillageService, UserService, VehicleService, ShipmentService

### Controllers (8 files)
- ProvinceController, DistrictController, SectorController, CellController
- VillageController, UserController, VehicleController, ShipmentController

---

##  Demonstration Points

### 1. Saving Village (Requirement #2)
```java
public Village saveVillage(String villageName, Long cellId) {
    Cell cell = cellRepository.findById(cellId).get();  // Get parent
    Village village = new Village(villageName, cell);    // Link to parent
    return villageRepository.save(village);              // Save with FK
}
```

### 2. Pagination & Sorting (Requirement #3)
```java
Sort sort = Sort.by(sortBy).ascending();
Pageable pageable = PageRequest.of(page, size, sort);
return vehicleRepository.findAll(pageable);
```

### 3. existsBy() (Requirement #7)
```java
boolean existsByVillageName(String villageName);
// Spring generates: SELECT COUNT(*) > 0 FROM village WHERE village_name = ?
```

### 4. Users by Province (Requirement #8)
```java
@Query("SELECT u FROM User u WHERE u.village.cell.sector.district.province.provinceCode = :code")
List<User> findUsersByProvinceCode(@Param("code") String code);
// Navigates: User → Village → Cell → Sector → District → Province
```

---

## 🇷🇼 Rwanda Example Data

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
Vehicle: RAD123A (Truck, 5000kg)
    ↓
Shipment: SHP001 (Electronics, 500kg)
```

---

## 📚 Documentation Files

1. **README.md** - Project overview
2. **QUICK_START.md** - 5-minute setup guide
3. **ERD_EXPLANATION.md** - Complete ERD with relationships
4. **EXAM_EXPLANATIONS.md** - Detailed explanations (if needed)
5. **VIVA_VOCE_GUIDE.md** - Quick reference for questions

---

## ✅ Why This is Better

1. **Real Rwanda Structure** - Uses official administrative divisions
2. **More Relationships** - 5 One-to-Many relationships in hierarchy
3. **Professional** - Shows understanding of local context
4. **Practical** - How logistics actually works in Rwanda
5. **Impressive** - Teacher will recognize Rwanda's structure

**What a did:**
"I implemented Rwanda's administrative hierarchy - Province, District, Sector, Cell, and Village - to demonstrate One-to-Many relationships. This is how addresses actually work in Rwanda, making the logistics system realistic and practical."

**Show:**
1. ERD with Rwanda's structure
2. Cascade of relationships from Province down to Village
3. User manages a village (One-to-One)
4. Vehicles stationed at villages (One-to-Many)
5. Shipments use multiple vehicles (Many-to-Many)

