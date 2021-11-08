In basic mapping, we had a class and we mapped it to a table. In advanced mappings, we can have multiple tables and have relationships between tables. 
- One-to-One
- One-to-Many, Many-to-One
- Many-to-Many

1. One-to-One Mapping 
- An instructor can have an 'instructor detail' entity, similar to 'instuctor profile'

2. One-to-Many Mapping 
An instructor can have many courses 

3. Many-to-Many Mapping 
A course can have many students. A student can have many courses. 

## Cascading Operations 
You can cascade operations. Then apply the same operation to related entities. 

For eg, if we delete an instructor, we should also delete their instructor_detail. This is known as 'cascade delete'. 

## Fetch Types 
There's easy Easy Loading vs Lazy Loading. When we fetch / retrieve data, should we retrieve everything? 
- Eager will retrieve everything 
- Lazy will retrieve on request 

Uni-Directional: Instructor -> Instructor Detail 
Bi-Directional: Instructor <--> Instructor Detail

## Dev Process for One-to-One
1. Prep Work- Define DB tables 
2. Create InstructorDetail class
3. Create Instructor class 
4. Create main app
```
public static void main(String args[]){
    ...
    //create the objects
    Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com"); 
    InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!"); 

    //associate the objects
    tempInstructor.setInstructor(tempInstructorDetail); 

    //start a transaction
    session.beginTransaction(); 

    session.save(tempInstructor);

    //commit transaction
    session.getTransaction().commit();  
}
```

## Entity LifeCycle 
Detach - If entity is detached, it is not associated with a Hibernate session 
Merge - If instance is detached from session, then merge will reattach to session
Persist - Transitions new instances to managed state. Next flush/commit will save in db
Remove - Transitions managed entity to be removed. Next flush/commit will delete from db 
Refresh - Reload /sync object with data from db. Prevents stale data 

Session calls: 

New/Transient
    - save/persist --> Persistent/Managed
Persistent/Managed
    - refresh --> Persistent/Managed
    - rollback/new --> New/Transient
    - delete/remove --> Removed 
    - commit/rollback/close --> Detached 
Removed
    - commit --> New/Transient
    - rollback --> Detached 
    - persist/rollback --> Persistent/Managed 
Detached 
    - merge --> Persistent/Managed

### @OneToOne - Cascade Types 
- PERSIST : If entity is persisted/saved, related entity will also be persisted 
- REMOVE : If entity is removed/deleted, related entity will also be deleted 
- REFRESH : If entity is refreshed, related entity will also be refreshed 
- DETACH : If entity is detached(not associated w/ session), then related entity will also be detached 
- MERGE : If entity is merged, then related entity will also be merged 
- ALL : All of above cascade types 

### Configure Cascade Type 
(By default, no operations are cascaded)
```
@Entity 
@Table(name="instructor")
public class Instructor{
    @OnetoOne(cascade=CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail; 
}
```

### Configure Multiple Cascade Types
```
@OnetoOne(cascade={
    CascadeType.DETACH, 
    CascadeType.MERGE, 
    CascadeType.PERSIST, 
    CascadeType.REFRESH, 
    CascadeType.REMOVE
})
```

## Bidierctional One-to-One Mapping 
There is a mapping from Instructor --> Instructor Detail. This is a uni-directionl mapping. If we want to load an InstructorDetail object and get the corresponding Instructor object, we will need a bidirectional mapping. 
Note: Even with the bi-directional setup, we dont need to update the database schema, only the java code. Steps: 
1. Make updates to InstructorDetail class
    1. Add new field to reference Instructor 
    2. Add getter/setter methods for Instructor 
    3. Add @OneToOne annotation
2. Create Main App