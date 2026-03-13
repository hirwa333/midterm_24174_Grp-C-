# Rwanda Smart Logistics - Sample Data Script
# Run this in PowerShell

Write-Host "Creating Rwanda Administrative Data..." -ForegroundColor Green

# 1. CREATE PROVINCES
Write-Host "`n1. Creating Provinces..." -ForegroundColor Yellow
Invoke-RestMethod -Uri "http://localhost:8080/api/provinces" -Method POST -ContentType "application/json" -Body '{"provinceCode":"KGL","provinceName":"Kigali City"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/provinces" -Method POST -ContentType "application/json" -Body '{"provinceCode":"EST","provinceName":"Eastern Province"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/provinces" -Method POST -ContentType "application/json" -Body '{"provinceCode":"WST","provinceName":"Western Province"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/provinces" -Method POST -ContentType "application/json" -Body '{"provinceCode":"NTH","provinceName":"Northern Province"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/provinces" -Method POST -ContentType "application/json" -Body '{"provinceCode":"STH","provinceName":"Southern Province"}'

# 2. CREATE DISTRICTS
Write-Host "`n2. Creating Districts..." -ForegroundColor Yellow
# Kigali City Districts
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Gasabo&provinceId=1" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Kicukiro&provinceId=1" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Nyarugenge&provinceId=1" -Method POST

# Eastern Province Districts
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Rwamagana&provinceId=2" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Kayonza&provinceId=2" -Method POST

# Western Province Districts
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Rubavu&provinceId=3" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Rusizi&provinceId=3" -Method POST

# Northern Province Districts
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Musanze&provinceId=4" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Gicumbi&provinceId=4" -Method POST

# Southern Province Districts
Invoke-RestMethod -Uri "http://localhost:8080/api/districts?districtName=Huye&provinceId=5" -Method POST

# 3. CREATE SECTORS
Write-Host "`n3. Creating Sectors..." -ForegroundColor Yellow
# Gasabo Sectors
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Kimironko&districtId=1" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Remera&districtId=1" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Kacyiru&districtId=1" -Method POST

# Kicukiro Sectors
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Gikondo&districtId=2" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Niboye&districtId=2" -Method POST

# Nyarugenge Sectors
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Nyarugenge&districtId=3" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Muhima&districtId=3" -Method POST

# Other Districts
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Kigabiro&districtId=4" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Mukarange&districtId=5" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/sectors?sectorName=Gisenyi&districtId=6" -Method POST

# 4. CREATE CELLS
Write-Host "`n4. Creating Cells..." -ForegroundColor Yellow
# Kimironko Cells
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Kibagabaga&sectorId=1" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Biryogo&sectorId=1" -Method POST

# Remera Cells
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Rukiri&sectorId=2" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Kisimenti&sectorId=2" -Method POST

# Kacyiru Cells
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Kamatamu&sectorId=3" -Method POST

# Other Sectors
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Gikondo&sectorId=4" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Niboye&sectorId=5" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Nyarugenge&sectorId=6" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Muhima&sectorId=7" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/cells?cellName=Kigabiro&sectorId=8" -Method POST

# 5. CREATE VILLAGES
Write-Host "`n5. Creating Villages..." -ForegroundColor Yellow
Invoke-RestMethod -Uri "http://localhost:8080/api/villages?villageName=Nyarutarama&cellId=1" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/villages?villageName=Kagugu&cellId=1" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/villages?villageName=Gacuriro&cellId=2" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/villages?villageName=Kimihurura&cellId=3" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/villages?villageName=Kiyovu&cellId=4" -Method POST

# 6. CREATE USERS
Write-Host "`n6. Creating Users..." -ForegroundColor Yellow
Invoke-RestMethod -Uri "http://localhost:8080/api/users" -Method POST -ContentType "application/json" -Body '{"username":"patrick","email":"patrick@logistics.rw","phone":"0788123456","village":{"villageId":1}}'
Invoke-RestMethod -Uri "http://localhost:8080/api/users" -Method POST -ContentType "application/json" -Body '{"username":"alice","email":"alice@logistics.rw","phone":"0788234567","village":{"villageId":2}}'
Invoke-RestMethod -Uri "http://localhost:8080/api/users" -Method POST -ContentType "application/json" -Body '{"username":"john","email":"john@logistics.rw","phone":"0788345678","village":{"villageId":3}}'
Invoke-RestMethod -Uri "http://localhost:8080/api/users" -Method POST -ContentType "application/json" -Body '{"username":"mary","email":"mary@logistics.rw","phone":"0788456789","village":{"villageId":4}}'
Invoke-RestMethod -Uri "http://localhost:8080/api/users" -Method POST -ContentType "application/json" -Body '{"username":"david","email":"david@logistics.rw","phone":"0788567890","village":{"villageId":5}}'

# 7. CREATE VEHICLES
Write-Host "`n7. Creating Vehicles..." -ForegroundColor Yellow
Invoke-RestMethod -Uri "http://localhost:8080/api/vehicles" -Method POST -ContentType "application/json" -Body '{"vehicleNumber":"RAD123A","vehicleType":"Truck","capacity":5000.0,"village":{"villageId":1}}'
Invoke-RestMethod -Uri "http://localhost:8080/api/vehicles" -Method POST -ContentType "application/json" -Body '{"vehicleNumber":"RAD456B","vehicleType":"Van","capacity":2000.0,"village":{"villageId":1}}'
Invoke-RestMethod -Uri "http://localhost:8080/api/vehicles" -Method POST -ContentType "application/json" -Body '{"vehicleNumber":"RAD789C","vehicleType":"Truck","capacity":7000.0,"village":{"villageId":2}}'
Invoke-RestMethod -Uri "http://localhost:8080/api/vehicles" -Method POST -ContentType "application/json" -Body '{"vehicleNumber":"RAD101D","vehicleType":"Pickup","capacity":1500.0,"village":{"villageId":3}}'
Invoke-RestMethod -Uri "http://localhost:8080/api/vehicles" -Method POST -ContentType "application/json" -Body '{"vehicleNumber":"RAD202E","vehicleType":"Truck","capacity":6000.0,"village":{"villageId":4}}'

# 8. CREATE SHIPMENTS
Write-Host "`n8. Creating Shipments..." -ForegroundColor Yellow
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments" -Method POST -ContentType "application/json" -Body '{"shipmentCode":"SHP001","description":"Electronics","weight":500.0,"status":"Pending"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments" -Method POST -ContentType "application/json" -Body '{"shipmentCode":"SHP002","description":"Food Items","weight":1200.0,"status":"In Transit"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments" -Method POST -ContentType "application/json" -Body '{"shipmentCode":"SHP003","description":"Construction Materials","weight":3000.0,"status":"Delivered"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments" -Method POST -ContentType "application/json" -Body '{"shipmentCode":"SHP004","description":"Medical Supplies","weight":800.0,"status":"Pending"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments" -Method POST -ContentType "application/json" -Body '{"shipmentCode":"SHP005","description":"Furniture","weight":2500.0,"status":"In Transit"}'

# 9. ASSIGN VEHICLES TO SHIPMENTS (Many-to-Many)
Write-Host "`n9. Assigning Vehicles to Shipments..." -ForegroundColor Yellow
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments/1/assign-vehicles" -Method POST -ContentType "application/json" -Body '[1,2]'
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments/2/assign-vehicles" -Method POST -ContentType "application/json" -Body '[3]'
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments/3/assign-vehicles" -Method POST -ContentType "application/json" -Body '[4,5]'
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments/4/assign-vehicles" -Method POST -ContentType "application/json" -Body '[1]'
Invoke-RestMethod -Uri "http://localhost:8080/api/shipments/5/assign-vehicles" -Method POST -ContentType "application/json" -Body '[5]'

Write-Host "`n✅ All Rwanda data created successfully!" -ForegroundColor Green
Write-Host "`nData Summary:" -ForegroundColor Cyan
Write-Host "- 5 Provinces" -ForegroundColor White
Write-Host "- 10 Districts" -ForegroundColor White
Write-Host "- 10 Sectors" -ForegroundColor White
Write-Host "- 10 Cells" -ForegroundColor White
Write-Host "- 5 Villages" -ForegroundColor White
Write-Host "- 5 Users" -ForegroundColor White
Write-Host "- 5 Vehicles" -ForegroundColor White
Write-Host "- 5 Shipments" -ForegroundColor White
Write-Host "`nTest in browser: http://localhost:8080/api/provinces" -ForegroundColor Yellow
