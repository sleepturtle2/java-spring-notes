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

Question: 
1. where are the methods like findByTestId defined? (most probably in the JpaRepository. but then why are we having to re-declare it? probably because to pass the appropriate data type to generics. Not sure)

