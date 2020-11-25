# Tableta Inmultirii Spring Backend
```Spring``` backend for website Tableta Inmultirii.

Uses ```Java 11``` JPMS modules and applies ```DDD``` design rules.

Features:
- API Key security

- ```DynamoDB``` using ```AWS 2 SDK```

- ```S3 Cache```

- ```ETag```s

- ```Circuit Breaker``` pattern

## Code organization

### Domain Schools
  Domain of ```schools```, pure ```Java``` implementation without any framework dependencies
  
  Links: [domain-schools](domain-schools) - [src](domain-schools/src/main/java/ro/tabletainmultirii/api/schools/domain), [module](domain-schools/src/main/java/module-info.java)

### Domain Stats
  Domain of ```stats```, pure ```Java``` implementation without any framework dependencies
  
  Links: [domain-stats](domain-stats) - [src](domain-stats/src/main/java/ro/tabletainmultirii/api/stats/domain), [module](domain-stats/src/main/java/module-info.java)

### Application
  ```Spring``` application
  
  Links: [application](application) - [src](application/src/main/java/ro/tabletainmultirii/schools/application), [module](application/src/main/java/module-info.java), [application properties](application/src/main/resources)

### Controller
  REST controller part of the hexagon **ports** and adapters architecture
  
  Links: [controller](controller) - [src](controller/src/main/java/ro/tabletainmultirii/api/controllers), [module](controller/src/main/java/module-info.java), [application properties](controller/src/main/resources)
  
### Security
  Spring Security layer part of the hexagon **ports** and adapters architecture
  
  Implements API key authorization
  
  Links: [security](security) - [src](security/src/main/java/ro/tabletainmultirii/api/security), [module](security/src/main/java/module-info.java), [application properties](security/src/main/resources)
  
### S3 Cache
  Implementation that saves cache entry to S3 bucket. This allows implementation of the ```circuit breaker``` pattern in the UI, using the static image of the cache response from the S3 bucket. 
  
  Links: [s3cache](s3cache) - [src](s3cache/src/main/java/ro/tabletainmultirii/api/s3cache), [module](s3cache/src/main/java/module-info.java), [application properties](s3cache/src/main/resources)
  
### Infrastructure
  Domain repositories implementation part of the hexagon ports and **adapters** architecture
  
  Abstracts and hides repository implementation details from the application
  
  Links: [infrastructure](infrastructure) - [src](infrastructure/src/main/java/ro/tabletainmultirii/api/infrastructure), [module](infrastructure/src/main/java/module-info.java)
  
  - #### DynamoDB Schools Repository
     Implements ```schools``` domain repositories and uses ```DynamoDB```
     
     Active in profiles ```local``` and ```local-prod```
     
     Links: [repository-schools-dynamodb](repository-schools-dynamodb) - [src](repository-schools-dynamodb/src/main/java/ro/tabletainmultirii/api/repository/dynamodb/schools), [module](repository-schools-dynamodb/src/main/java/module-info.java), [application properties](repository-schools-dynamodb/src/main/resources)
     
   - #### DynamoDB Stats Repository
     Implements ```stats``` domain repositories and uses ```DynamoDB```
     
     Active in profiles ```local``` and ```local-prod```
     
     Links: [repository-stats-dynamodb](repository-stats-dynamodb - [src](repository-stats-dynamodb/src/main/java/ro/tabletainmultirii/api/repository/dynamodb/schools), [module](repository-stats-dynamodb/src/main/java/module-info.java), [application properties](repository-stats-dynamodb/src/main/resources)
     
   - #### Test repository 
     Implements ```stats``` and ```schools``` domain repositories and uses in memory DB
     
     Active in profiles ```"dev``` and ```default```
     
     Links: [repository-test](repository-test - [src](repository-test/src/main/java/ro/tabletainmultirii/api/repository/test), [module](repository-test/src/main/java/module-info.java)
     
## Deployment

Deployment as a ```Docker``` container using the provided ```Dockerfile``` based on ```Amazon Corretto```

Production deployment requires the following environment variables (provided by docker parameter ```--env-file local.env``` )

Example file ```local.env```
```
# repository-stats-dynamodb
AMAZON_AWS_ACCESSKEY_STATS=<accesskey>
AMAZON_AWS_SECRETKEY_STATS=<secretkey>
AMAZON_DYNAMODB_TABLE_STATS=<tablename>

# repository-schools-dynamodb
AMAZON_AWS_ACCESSKEY_SCHOOLS=<accesskey>
AMAZON_AWS_SECRETKEY_SCHOOLS=<secretkey>
AMAZON_DYNAMODB_TABLE_SCHOOLS=<tablename>

# cache
AMAZON_AWS_ACCESSKEY=<accesskey>
AMAZON_AWS_SECRETKEY=<secretkey>
AMAZON_S3_BUCKET=<bucketname>

# security
API_ACCESS_TOKEN=<apikey>
```
