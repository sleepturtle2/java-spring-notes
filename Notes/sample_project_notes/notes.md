Project Notes: 

****
Generic Notes: 
1. Autowire all the dependencies thru a constructor injection. 
2. ENTITY vs DOMAIN 
the domain object defines the business object and it's properties and methods. It's used to manipulate and move the data within the processing system. The Entity object exists to take those domain properties and map them to a persistent storage object, such as a database table.
*****

1. SAMPLE STRUCTURE 1

controller method: createTest(56)

endpoint: /tests/create

classes: 

TestDto = models/domain
TestInterfaceDto = models/domain

Test  = models/entity  

TestDao = repositories
TestInterfaceCodeDao = repositories 

methods: 

utils/Transformer.java: 
transformToTestEntity: TestDto -> Test 
transformToTestInterfaceCodeEntity: TestInterfaceCodeDto -> TestInterfaceCodeDao
transformToTestDto: Test -> TestDto 

Steps: 
1. Start from the controller always. 
```
@PostMapping("/test/create")
public ResponseEntity<TestDto> createTest(@RequestBody TestDto testDto){
    
    // service(@Service) / controller logic here

    return new ResponseEntity<>(projectService.createTest(testDto), HTTPStatus.CREATED); 
}
```
For a post mapping, create a ResponseEntity of type resp DTO (here TestDto). Pass the same in the resquest body. 
HTTPStatus is an enum with values corresp to every HTTP status code.  

2. handle the create method in the business service layer. send back TestDto type to the controller, inputs a TestDto. 
```
public TestDto createTest(TestDto testDto){
    
    //logger

    Test test = testDao.save(transformToTestEntity(testDto)); 

    /*
        get each testInterfaceCodeDto from list in testDto ( has @Data and @AllArgsConstructor which enables getters by default ) of type 
        TestInterfaceCodeDto.  for each, set the properties in the InterfaceDto from the testDto getters. 
        use the JPARepository save method to save Dto class as Dao class, ( util transformer method transforms TestInterfaceCodeEntity to TestInterfaceCodeDto)
    */

    for(TestInterfaceCodeDto testInterfaceCodeDto : testDto.getInterfaceCodeDto())
    {
        testInterfaceCodeDto.setTestId(test.getId()); 
        testInterfaceCodeDao.save(transformToTestInterfaceCodeEntity(testInterfaceCodeDto)); 
    }
    return transformToTestDto(test); 
}
```
Test is an entity. TestDao is a repository. The TestDto, which is a domain, is passed as a func param in a function which builds the Test entity using the builder annotation with the values we get from TestDto getters. Now use the save method (which is a method from JpaRepository, which this TestDao extends), to save the testEntity (return of the transform util on the TestDto) in the TestDao. 
  

The TestDto(a domain) contains a List of domains TestInterfaceCodeDto. Get each testInterfaceDto from the testDto using getters. In each, set the id (primary key) using a setter, using values from test entity getters. now the testInterfaceCodeDto has the id of the test entity. Now save the TestInterfaceCode in the  testInterfaceCodeDao. 

Finally return the TestDto domain (use util transformer for Test -> TestDto)


Note : 'Dao's always store and return entities. Reminder, having @Repository and @Entity respectively. 


Observations : 
1. The Jpa save method saves an entity (@Entity) into a Dao (@Repository).  Since er have a Dto instead, we transform Dto to Entity. 
2. The response requires a Dto. 
Why? 
The return of the controller method is a ResponseEntity instance. The parameters are a @Nullable T body (meaning optional), and a mandatory value from the HttpStatus enum. 

Here the T value is passed as a TestDto. 

Question: 
Relation between entity/domains and their interfaceCodeDto counterparts. 

****

2. Sample Dao
```
import org.springframework.stereotype.Repository; 

@Repository
public interface T_Dao extends JpaRepository<T_Dao, Long>{

    List<T_Dao> findByTestId(Long id); 

}
```

***** 

## 2. SAMPLE MODULE STUDY: "test-profile" endpoints 
(All following endpoints have /test-profile prefix. eg /count = /test-profile/count)


get - /counts : 
    RE returns a JSONObject with key as count. Value is returned from the Service layer file. Get the value from the Dao(Query in Dao). 

get - /get/all :
    -  RE returns a List of Dtos. From the SL, method runs findAll method(Jpa interface method) on the Dao instance. This method(findAll) returns a List of type T (JpaRepository usually has <ENTITY_CLASS, Long> or <ENTITY_CLASS, String>, for eg for TestProfileDao it is <TestProfile, Long>). Now this is List of entities. Need to transform to Dto and then pass it to the RE. (To change from entity to Dto, use a builder method on the Dto and build the object using ENTITY.getPROPERTY()). 


get - /test-profiles/{id}/get :
     RE returns Dto, id of type Long.  
    Dao stores Entity. so entity is retrieved from Dao, transformed to Dto (using builder methods).
    
     Dto has a list pf profile_dtl. That needs to be filled separately (querying on the db using Dto id). For that we get list of entities from the dao. Transform that to a list of profile_dtl using a transformer method (use builder methods on the dto using entity-get values). now list of profile_dtls inside the dto has been set.

     now just return RE of Dto. 

    NB - Here the service method uses the Optional Object as findById might return null. 


post - /test-profiles/create
    - RE returns Dto that is created. 

    Because this is a post method, we must run a validation middleware (pass the Dto, Http Protocol{type enum}).

    Controller layer = Get the Dto from the Service layer. Service layer returns an entity, which you need to transform to Dto (using builder methods on entity). This entity contains a list of dtl_dto. Get the list from the Dao as a list of entities and transform that to a list of dtos, storing into dtl_dto. set the list in the entity and return. 


    Service layer = there are two parts: one, getting the entity from the dao. two, getting the list of entities from another dao and set the list in the first entity. 
        First part. method is annotated by "@Transactional" because of row creation on the db (transaction). Input Dto -> Entity (use builder methods on the entity from dto-get values) -> save to Dao as entity. Next, check if input Dto not null, then set the list from the input Dto into the entity and save in the Dao. return entity which is saved. 

        Second part. get list of entities from dao -> transform to list of dto and save in the input Dto list of dtos. 



put - /{id}/update
    - RE returns a Dto. ResponseBody has a Dto
    Validate since put method (Dto, HttpMethod enum value = PUT)

    Very similar to above one. also has two similar parts. controller is the same. returns the updated Dto. 

    Service layer: 
        - First part. Uses an Optional object as findById could be null. save the incoming Dto to the Dao. Get list in similar way 
        - Second part. Similar to the previous endpoint 


delete - /{id}/delete
    [same like put]

****

Question: 
1. where are the methods like findByTestId defined? (most probably in the JpaRepository. but then why are we having to re-declare it? probably because to pass the appropriate data type to generics. Not sure)


NOTES: 
1. ResponseEntity
In the return type you either leave it empty or usually return a DTO. For JSON, use "ResponseEntity<JSONObject>".

    - The notation for returning JSONObject to the ResponseEntity: 
    ```
    Long testProfileCount = testProfileDao.getCountOfActiveTestProfiles(); 
    JSONObject jsonObj = new JSONObject(); 
    jsonObt.put("count", testProfileCount); 
    return jsonObj;
    ```

2. Optional
Use this when any type T might be null. 

Used in cases where you are querying the db for an id-based value. this might return null.
```
Optional<T> testProfile = testProfileDao.findById(id); 
return testProfile.orElseThrow(() -> new ResourceNotFoundException([entity String], [field String], [value Object])); 

//Object here can be Long too (wrapper classes considered objects)

```

3. Check for null (Dtos, etc) 
```
isNull.negate().test(OBJECT_TO_TEST)
```
The above code returns true if OBJECT_TO_TEST is not null. 

4. When there is write on the DB, it is a transaction. Such methods must be annotated by @Transactional.   

5. Class<?> : Class is a parameterizable class, hence you can use the syntax Class<T> where T is a type. By writing Class<?>, you're declaring a Class object which can be of any type (? is a wildcard). The Class type is a type that contains meta-information about a class.

It's always good practice to refer to a generic type by specifying his specific type, by using Class<?> you're respecting this practice (you're aware of Class to be parameterizable) but you're not restricting your parameter to have a specific type. 

Used on delete requests, as the return type is just a status object (flexible). 