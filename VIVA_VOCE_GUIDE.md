# VIVA-VOCE QUICK REFERENCE GUIDE

## QUICK ANSWERS FOR COMMON QUESTIONS

### 1. What is JPA?
Java Persistence API - a specification for managing relational data in Java applications. It provides object-relational mapping (ORM) to convert Java objects to database tables.

### 2. What is Hibernate?
Hibernate is an implementation of JPA specification. It is the actual framework that does the work.

### 3. What is Spring Data JPA?
A Spring module that makes it easy to implement JPA-based repositories. It reduces boilerplate code by providing repository interfaces.

### 4. Explain @Entity annotation
Marks a Java class as a database table. Each instance of the class represents a row in the table.

### 5. Explain @Id and @GeneratedValue
- @Id: Marks a field as the primary key
- @GeneratedValue: Tells JPA to automatically generate the primary key value (auto-increment)

### 6. What is the difference between @OneToMany and @ManyToOne?
- @OneToMany: Used on the "one" side (e.g., Province has many Locations)
- @ManyToOne: Used on the "many" side (e.g., Many Locations belong to one Province)
They are opposite sides of the same relationship.

### 7. What is mappedBy?
Indicates the inverse (non-owning) side of a bidirectional relationship. It points to the field name in the owning entity.

### 8. What is @JoinColumn?
Specifies the foreign key column in the database. Used on the owning side of the relationship.

### 9. What is a Join Table?
A separate table used in Many-to-Many relationships to store the associations between two entities. Contains foreign keys from both tables.

### 10. Explain CascadeType
Defines operations that should cascade from parent to child:
- ALL: All operations cascade
- PERSIST: Save operations cascade
- MERGE: Update operations cascade
- REMOVE: Delete operations cascade
- REFRESH: Refresh operations cascade
- DETACH: Detach operations cascade

### 11. What is FetchType?
Determines when related entities are loaded:
- LAZY: Load related entities only when accessed (better performance)
- EAGER: Load related entities immediately with parent (more data upfront)

### 12. Why use Pagination?
- Improves performance by loading only needed records
- Reduces memory usage
- Faster response times
- Better user experience with large datasets

### 13. How does Pagination work?
Uses SQL LIMIT and OFFSET:
- LIMIT: Number of records to retrieve
- OFFSET: Number of records to skip
Example: Page 2, Size 10 → LIMIT 10 OFFSET 10

### 14. What is Pageable?
An interface in Spring Data that contains pagination information (page number, size, sorting). Passed to repository methods.

### 15. What is Sort?
A Spring Data class that defines sorting criteria (field name and direction - ascending/descending).

### 16. How does existsBy() work?
Spring Data JPA parses the method name and generates a query that checks if a record exists. Returns boolean without loading the entire entity.

### 17. What is @Query annotation?
Allows writing custom JPQL or SQL queries instead of relying on method name parsing.

### 18. What is JPQL?
Java Persistence Query Language - similar to SQL but works with entity objects instead of tables.

### 19. What is @Param?
Binds a method parameter to a named parameter in the query.

### 20. Explain the Repository pattern
Separates data access logic from business logic. Repository interfaces provide methods to interact with the database.

### 21. What is the Service layer?
Contains business logic and coordinates between controllers and repositories. Keeps controllers thin.

### 22. What is @RestController?
Combines @Controller and @ResponseBody. Indicates the class handles REST API requests and returns JSON/XML.

### 23. What is @RequestMapping?
Maps HTTP requests to handler methods. Specifies the URL path for the controller or method.

### 24. What is @Autowired?
Enables dependency injection. Spring automatically provides the required bean instance.

### 25. Why use DTOs (Data Transfer Objects)?
To transfer only necessary data between layers, avoiding exposing entire entity structure and preventing circular reference issues.

---

## RELATIONSHIP SUMMARY TABLE

| Relationship | Annotation | Foreign Key Location | Example |
|--------------|-----------|---------------------|---------|
| One-to-One | @OneToOne | Owning side table | User ↔ Location |
| One-to-Many | @OneToMany | Many side table | Province → Location |
| Many-to-One | @ManyToOne | Many side table | Location → Province |
| Many-to-Many | @ManyToMany | Join table | Shipment ↔ Vehicle |

---

## ANNOTATION CHEAT SHEET

### Entity Annotations
- @Entity - Mark class as entity
- @Table(name="...") - Specify table name
- @Id - Primary key
- @GeneratedValue - Auto-generate key
- @Column - Column properties

### Relationship Annotations
- @OneToOne - One-to-one relationship
- @OneToMany - One-to-many relationship
- @ManyToOne - Many-to-one relationship
- @ManyToMany - Many-to-many relationship
- @JoinColumn - Foreign key column
- @JoinTable - Join table for many-to-many

### Spring Annotations
- @SpringBootApplication - Main application class
- @RestController - REST controller
- @Service - Service layer
- @Repository - Repository layer
- @Autowired - Dependency injection
- @RequestMapping - Map HTTP requests
- @GetMapping - Map GET requests
- @PostMapping - Map POST requests
- @PathVariable - Extract URL path variable
- @RequestParam - Extract query parameter
- @RequestBody - Extract request body

---

## KEY POINTS FOR DEMONSTRATION

### 1. Show ERD
Point to ERD_EXPLANATION.md and explain each table and relationship.

### 2. Show Location Saving
Open LocationService.java and explain the saveLocation method.

### 3. Show Pagination
Open VehicleService.java and explain PageRequest.of() and how it works.

### 4. Show Sorting
Explain Sort.by() and how it integrates with Pageable.

### 5. Show Many-to-Many
Open Shipment.java and explain @JoinTable with join columns.

### 6. Show One-to-Many
Open Province.java and Location.java, explain @OneToMany and @ManyToOne.

### 7. Show One-to-One
Open User.java and Location.java, explain @OneToOne with unique constraint.

### 8. Show existsBy()
Open LocationRepository.java and explain method naming convention.

### 9. Show Province Query
Open UserRepository.java and explain @Query with entity navigation.

### 10. Test with Postman
Show API endpoints working with sample data.

---

## CONFIDENCE BOOSTERS

Remember:
✅ You understand the business logic (logistics system)
✅ You can explain each relationship with real-world examples
✅ You know why each feature is important (performance, data integrity)
✅ You can trace the flow: Controller → Service → Repository → Database
✅ You have working code that demonstrates everything

Good luck with your examination!
