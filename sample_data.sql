-- Sample Test Data for Smart Logistics System
-- Run these after the application creates the tables

-- Insert Provinces
INSERT INTO province (province_code, province_name) VALUES ('KGL', 'Kigali');
INSERT INTO province (province_code, province_name) VALUES ('EST', 'Eastern Province');
INSERT INTO province (province_code, province_name) VALUES ('WST', 'Western Province');
INSERT INTO province (province_code, province_name) VALUES ('NTH', 'Northern Province');
INSERT INTO province (province_code, province_name) VALUES ('STH', 'Southern Province');

-- Insert Locations
INSERT INTO location (location_name, address, province_id) VALUES ('Downtown Kigali', 'KN 5 Ave', 1);
INSERT INTO location (location_name, address, province_id) VALUES ('Kimironko', 'KG 11 Ave', 1);
INSERT INTO location (location_name, address, province_id) VALUES ('Rwamagana Center', 'RW 101 St', 2);
INSERT INTO location (location_name, address, province_id) VALUES ('Rubavu Port', 'RB 50 Ave', 3);
INSERT INTO location (location_name, address, province_id) VALUES ('Musanze Hub', 'MS 20 St', 4);

-- Insert Users (One-to-One with Location)
INSERT INTO users (username, email, phone, location_id) VALUES ('patrick', 'patrick@logistics.rw', '0788123456', 1);
INSERT INTO users (username, email, phone, location_id) VALUES ('alice', 'alice@logistics.rw', '0788234567', 2);
INSERT INTO users (username, email, phone, location_id) VALUES ('john', 'john@logistics.rw', '0788345678', 3);
INSERT INTO users (username, email, phone, location_id) VALUES ('mary', 'mary@logistics.rw', '0788456789', 4);
INSERT INTO users (username, email, phone, location_id) VALUES ('david', 'david@logistics.rw', '0788567890', 5);

-- Insert Vehicles (Many-to-One with Location)
INSERT INTO vehicle (vehicle_number, vehicle_type, capacity, location_id) VALUES ('RAD123A', 'Truck', 5000.0, 1);
INSERT INTO vehicle (vehicle_number, vehicle_type, capacity, location_id) VALUES ('RAD456B', 'Van', 2000.0, 1);
INSERT INTO vehicle (vehicle_number, vehicle_type, capacity, location_id) VALUES ('RAD789C', 'Truck', 7000.0, 2);
INSERT INTO vehicle (vehicle_number, vehicle_type, capacity, location_id) VALUES ('RAD101D', 'Pickup', 1500.0, 3);
INSERT INTO vehicle (vehicle_number, vehicle_type, capacity, location_id) VALUES ('RAD202E', 'Truck', 6000.0, 4);
INSERT INTO vehicle (vehicle_number, vehicle_type, capacity, location_id) VALUES ('RAD303F', 'Van', 2500.0, 5);

-- Insert Shipments
INSERT INTO shipment (shipment_code, description, weight, status) VALUES ('SHP001', 'Electronics', 500.0, 'Pending');
INSERT INTO shipment (shipment_code, description, weight, status) VALUES ('SHP002', 'Food Items', 1200.0, 'In Transit');
INSERT INTO shipment (shipment_code, description, weight, status) VALUES ('SHP003', 'Construction Materials', 3000.0, 'Delivered');
INSERT INTO shipment (shipment_code, description, weight, status) VALUES ('SHP004', 'Medical Supplies', 800.0, 'Pending');
INSERT INTO shipment (shipment_code, description, weight, status) VALUES ('SHP005', 'Furniture', 2500.0, 'In Transit');

-- Insert Shipment-Vehicle relationships (Many-to-Many)
INSERT INTO shipment_vehicle (shipment_id, vehicle_id) VALUES (1, 1);
INSERT INTO shipment_vehicle (shipment_id, vehicle_id) VALUES (1, 2);
INSERT INTO shipment_vehicle (shipment_id, vehicle_id) VALUES (2, 3);
INSERT INTO shipment_vehicle (shipment_id, vehicle_id) VALUES (3, 4);
INSERT INTO shipment_vehicle (shipment_id, vehicle_id) VALUES (3, 5);
INSERT INTO shipment_vehicle (shipment_id, vehicle_id) VALUES (4, 1);
INSERT INTO shipment_vehicle (shipment_id, vehicle_id) VALUES (5, 6);

-- Queries to test the implementation

-- 1. Get all users from Kigali province by code
SELECT u.* FROM users u
INNER JOIN location l ON u.location_id = l.location_id
INNER JOIN province p ON l.province_id = p.province_id
WHERE p.province_code = 'KGL';

-- 2. Get all users from Kigali province by name
SELECT u.* FROM users u
INNER JOIN location l ON u.location_id = l.location_id
INNER JOIN province p ON l.province_id = p.province_id
WHERE p.province_name = 'Kigali';

-- 3. Get vehicles with pagination (page 0, size 3)
SELECT * FROM vehicle LIMIT 3 OFFSET 0;

-- 4. Get vehicles sorted by vehicle_type ascending
SELECT * FROM vehicle ORDER BY vehicle_type ASC;

-- 5. Check if location exists
SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END 
FROM location WHERE location_name = 'Downtown Kigali';

-- 6. Get all shipments with their assigned vehicles (Many-to-Many)
SELECT s.shipment_code, s.description, v.vehicle_number, v.vehicle_type
FROM shipment s
INNER JOIN shipment_vehicle sv ON s.shipment_id = sv.shipment_id
INNER JOIN vehicle v ON sv.vehicle_id = v.vehicle_id;

-- 7. Get location with its user (One-to-One)
SELECT l.location_name, l.address, u.username, u.email
FROM location l
INNER JOIN users u ON l.location_id = u.location_id;

-- 8. Get province with all its locations (One-to-Many)
SELECT p.province_name, l.location_name, l.address
FROM province p
LEFT JOIN location l ON p.province_id = l.province_id;
