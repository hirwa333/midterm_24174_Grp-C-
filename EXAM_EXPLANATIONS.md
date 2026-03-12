# PRACTICAL EXAMINATION EXPLANATIONS
## Smart Logistics and Transportation System

---

## 1. ENTITY RELATIONSHIP DIAGRAM (ERD) - 5 TABLES (3 Marks)

### Tables:
1. **PROVINCE** - Stores geographical regions
2. **LOCATION** - Stores specific addresses within provinces
3. **USER** - Stores user information
4. **VEHICLE** - Stores transportation vehicles
5. **SHIPMENT** - Stores shipment/cargo information
6. **SHIPMENT_VEHICLE** - Join table for Many-to-Many relationship

### Relationships Logic:
- **Province → Location (One-to-Many)**: One province contains many locations
- **Location → User (One-to-One)**: Each location has one registered user
- **Location → Vehicle (One-to-Many)**: One location can have many vehicles stationed
- **Shipment ↔ Vehicle (Many-to-Many)**: One shipment can use multiple vehicles, and one vehicle can transport multiple shipments

---

## 2. IMPLEMENTATION OF SAVING LOCATION (2 Marks)

### Code Location: LocationService.java

```java
public Location saveLocation(String locationName, String address, Long provinceId) {
    Province province = provinceRepository.findById(provinceId).get();
    Location location = new Location(locationName, address, province);
    return locationRepository.save(location);
}
```

### Explanation:
1. **Retrieve Province**: First, we fetch the Province entity using provinceId from the database
2. **Create Location Object**: We create a new Location object with the provided data and link it to the Province
3. **Save to Database**: The save() method persists the Location entity to the database
4. **Relationship Handling**: The @ManyToOne annotation in Location entity automatically stores the province_id as a foreign key in the location table
5. **Cascade Effect**: When we save Location, JPA automatically manages the foreign key relationship without manual SQL

---

## 3. SORTING AND PAGINATION (5 Marks)

### A. SORTING Implementation

#### Code Location: VehicleService.java

```java
public Page<Vehicle> getVehiclesWithPaginationAndSorting(int page, int size, String sortBy, String direction) {
    Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    return vehicleRepository.findAll(pageable);
}
```

### Sorting Explanation:
1. **Sort Object**: We create a Sort object using Sort.by(sortBy) which specifies which field to sort by
2. **Direction**: We check if direction is "asc" or "desc" and apply ascending() or descending()
3. **Integration with Pageable**: The Sort object is passed to PageRequest.of() to combine sorting with pagination
4. **Database Query**: Spring Data JPA translates this to SQL: "ORDER BY sortBy ASC/DESC"

### B. PAGINATION Implementation

### Pagination Explanation:
1. **PageRequest.of()**: Creates a Pageable object with page number and size
   - page: Which page to retrieve (0-indexed)
   - size: How many records per page
2. **Page Object**: Returns a Page<Vehicle> containing:
   - Content: List of vehicles for current page
   - Total elements: Total count in database
   - Total pages: Calculated as totalElements / size
3. **Performance Benefit**: Instead of loading all records, pagination uses SQL LIMIT and OFFSET
   - Example: Page 2, Size 10 → "LIMIT 10 OFFSET 10"
4. **Memory Efficiency**: Only loads required records into memory, not entire table
5. **User Experience**: Faster response time for large datasets

---

## 4. MANY-TO-MANY RELATIONSHIP (3 Marks)

### Code Location: Shipment.java and Vehicle.java

#### Shipment Entity:
```java
@ManyToMany
@JoinTable(
    name = "shipment_vehicle",
    joinColumns = @JoinColumn(name = "shipment_id"),
    inverseJoinColumns = @JoinColumn(name = "vehicle_id")
)
private List<Vehicle> vehicles;
```

#### Vehicle Entity:
```java
@ManyToMany(mappedBy = "vehicles")
private List<Shipment> shipments;
```

### Explanation:
1. **Join Table**: @JoinTable creates a separate table "shipment_vehicle" with two foreign keys
2. **joinColumns**: Specifies the foreign key column for the owning side (Shipment) → shipment_id
3. **inverseJoinColumns**: Specifies the foreign key column for the other side (Vehicle) → vehicle_id
4. **mappedBy**: In Vehicle entity, mappedBy="vehicles" indicates this is the inverse side, not owning the relationship
5. **Database Structure**: shipment_vehicle table has composite primary key (shipment_id, vehicle_id)
6. **Usage**: When we assign vehicles to a shipment, JPA automatically inserts records into the join table

---

## 5. ONE-TO-MANY RELATIONSHIP (2 Marks)

### Code Location: Province.java and Location.java

#### Province Entity (One Side):
```java
@OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
private List<Location> locations;
```

#### Location Entity (Many Side):
```java
@ManyToOne
@JoinColumn(name = "province_id", nullable = false)
private Province province;
```

### Explanation:
1. **@OneToMany**: In Province, indicates one province has many locations
2. **mappedBy**: Points to the field name "province" in Location entity that owns the relationship
3. **@ManyToOne**: In Location, indicates many locations belong to one province
4. **@JoinColumn**: Creates foreign key column "province_id" in location table
5. **Foreign Key**: province_id in location table references province_id in province table
6. **Cascade**: CascadeType.ALL means when we delete a province, all its locations are also deleted
7. **nullable=false**: Ensures every location must have a province

---

## 6. ONE-TO-ONE RELATIONSHIP (2 Marks)

### Code Location: User.java and Location.java

#### User Entity (Owning Side):
```java
@OneToOne
@JoinColumn(name = "location_id", unique = true, nullable = false)
private Location location;
```

#### Location Entity (Inverse Side):
```java
@OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
private User user;
```

### Explanation:
1. **@OneToOne**: Indicates one-to-one relationship between User and Location
2. **@JoinColumn**: Creates foreign key "location_id" in users table
3. **unique = true**: Ensures one location can only be assigned to one user (enforces one-to-one)
4. **Owning Side**: User entity owns the relationship because it has @JoinColumn
5. **Inverse Side**: Location uses mappedBy to reference the relationship without creating extra column
6. **Database**: Only users table has location_id foreign key column
7. **Bidirectional**: We can navigate from User to Location and vice versa

---

## 7. existsBy() METHOD (2 Marks)

### Code Location: LocationRepository.java

```java
boolean existsByLocationName(String locationName);
```

### Explanation:
1. **Method Naming Convention**: Spring Data JPA parses the method name
   - "exists" → Returns boolean
   - "By" → Indicates query criteria follows
   - "LocationName" → Field name to check
2. **Generated Query**: Spring automatically generates SQL:
   ```sql
   SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END 
   FROM Location l WHERE l.locationName = ?
   ```
3. **Return Value**: Returns true if at least one record exists, false otherwise
4. **Performance**: More efficient than findBy() because it doesn't load entire entity
5. **Use Case**: Check if location name already exists before saving (validation)
6. **No Implementation Needed**: We only declare the method, Spring implements it

### Usage Example:
```java
public boolean checkLocationExists(String locationName) {
    return locationRepository.existsByLocationName(locationName);
}
```

---

## 8. RETRIEVE USERS BY PROVINCE CODE OR NAME (4 Marks)

### Code Location: UserRepository.java

```java
@Query("SELECT u FROM User u WHERE u.location.province.provinceCode = :provinceCode")
List<User> findUsersByProvinceCode(@Param("provinceCode") String provinceCode);

@Query("SELECT u FROM User u WHERE u.location.province.provinceName = :provinceName")
List<User> findUsersByProvinceName(@Param("provinceName") String provinceName);
```

### Explanation:

#### Query Logic:
1. **JPQL Query**: Uses Java Persistence Query Language, not SQL
2. **Entity Navigation**: 
   - u.location → Navigate from User to Location entity
   - u.location.province → Navigate from Location to Province entity
   - u.location.province.provinceCode → Access provinceCode field
3. **@Query Annotation**: Defines custom query instead of method name parsing
4. **@Param**: Binds method parameter to query parameter
5. **Return Type**: List<User> returns all matching users

#### How It Works:
1. **Join Operation**: JPA automatically creates SQL joins:
   ```sql
   SELECT u.* FROM users u
   INNER JOIN location l ON u.location_id = l.location_id
   INNER JOIN province p ON l.province_id = p.province_id
   WHERE p.province_code = ?
   ```
2. **Parameter Binding**: :provinceCode is replaced with actual value at runtime
3. **Object Mapping**: Results are automatically mapped to User entities with all relationships loaded

#### Two Methods:
- **findUsersByProvinceCode**: Search by province code (e.g., "KGL" for Kigali)
- **findUsersByProvinceName**: Search by province name (e.g., "Kigali Province")

---

## VIVA-VOCE PREPARATION TIPS (7 Marks)

### Key Points to Remember:

1. **JPA vs Hibernate**: JPA is specification, Hibernate is implementation
2. **@Entity**: Marks class as database table
3. **@Id**: Marks primary key field
4. **@GeneratedValue**: Auto-generates primary key values
5. **Cascade Types**: ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH
6. **FetchType**: LAZY (load when accessed) vs EAGER (load immediately)
7. **Repository**: Interface extending JpaRepository provides CRUD methods
8. **Service Layer**: Contains business logic
9. **Controller Layer**: Handles HTTP requests/responses
10. **@RestController**: Combines @Controller and @ResponseBody

### Common Questions:
- Why use pagination? → Performance and memory efficiency
- What is a join table? → Table connecting two entities in Many-to-Many
- What is mappedBy? → Indicates inverse side of bidirectional relationship
- What is @JoinColumn? → Specifies foreign key column
- Why use existsBy()? → Efficient existence checking without loading entity

---

## API ENDPOINTS SUMMARY

### Province:
- POST /api/provinces - Create province
- GET /api/provinces - Get all provinces
- GET /api/provinces/exists/{code} - Check if exists

### Location:
- POST /api/locations - Create location
- GET /api/locations - Get all locations
- GET /api/locations/exists/{name} - Check if exists

### User:
- POST /api/users - Create user
- GET /api/users/province/code/{code} - Get users by province code
- GET /api/users/province/name/{name} - Get users by province name
- GET /api/users/paginated?page=0&size=10 - Get paginated users
- GET /api/users/exists/{username} - Check if exists

### Vehicle:
- POST /api/vehicles - Create vehicle
- GET /api/vehicles/sorted?page=0&size=10&sortBy=vehicleType&direction=asc
- GET /api/vehicles/exists/{number} - Check if exists

### Shipment:
- POST /api/shipments - Create shipment
- POST /api/shipments/{id}/assign-vehicles - Assign vehicles
- GET /api/shipments - Get all shipments

---

## DATABASE SETUP

1. Create MySQL database:
```sql
CREATE DATABASE logistics_db;
```

2. Update application.properties with your MySQL credentials

3. Run the application - tables will be created automatically

---

Good luck with your practical examination!
