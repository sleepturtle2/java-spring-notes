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

Note: If you wanna avoid Cascase Delete, then put all CascadeTypes except 'REMOVE'

## Bidierctional One-to-One Mapping 
There is a mapping from Instructor --> Instructor Detail. This is a uni-directionl mapping. If we want to load an InstructorDetail object and get the corresponding Instructor object, we will need a bidirectional mapping. 
Note: Even with the bi-directional setup, we dont need to update the database schema, only the java code. Steps: 
1. Make updates to InstructorDetail class
    1. Add new field to reference Instructor 
    ```
    @Entity
    @Table(name="instructor_detail")
    public class InstructorDetail{
        @OneToOne(mappedBy="instructorDetail, cascade=CascadeType.ALL")
        private Instructor instructor; 

        //getters and setters here
    }
    ```
    2. Add getter/setter methods for Instructor 
    3. Add @OneToOne annotation
2. Create Main App
```
public static void main(String args[]){
    //get the instructor detail object 
    int theId = 1; 
    InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId); 

    //print associated instructor 
    System.out.println(tempInstructorDetail.getInstructor()); 
}
```

Note: More on mappedBy: it tells Hibernate 
    - to look at the instructorDetail property in the Instructor class
    - and Use information from the Instructor class @JoinColumn
    - to help find associated instructor

## Deleting a single object, in Bi-Directional Mapping 
Only delete InstructorDetail, keep the Instructor. 
```
//if we want to delete only instructorDetail, without deleting the instructor class, we have to modify the cascade to include everything except remove
	@OneToOne(mappedBy="instructorDetail", cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) 
	private Instructor instructor; 	
```
Main app: 
```

			// start a transaction
			session.beginTransaction();
			
			//get the instructor detail object 
			int theId = 3; 
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId); 
			
			//print the instructor detail 
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			//print the associated object 
			System.out.println("associated instructor: " + tempInstructorDetail.getInstructor());
			
			//delete the instructor detail 
			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
			session.delete(tempInstructorDetail);  
			
			//remove the associated object reference
			//break bi-directional link 
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail); 
			// commit transaction
			session.getTransaction().commit();
```

## One-to-Many BiDirectional Mapping 
One-to-Many mapping will be when an instructor will have many courses. Many-to-One mapping will be when many courses have one instructor. 
Note: If there is a project requirement that if you delete an instructor, do not delete the courses and vice-versa, then what it means it that we have to omit cascading deletes. 
Development Process: 
1. Define DB tables
    - table 'Course' has PK id, unique key title, foreign key instructor_id (references 'instructor' ('id'))

2. Create Course class
```
@Entity
@Table(name='course')
public class Course{
    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor; 
}
```
3. Update Instructor class
```
@Entity
@Table(name="instructor")
public class Instructor{
    @OneToMany(mappedBy="instructor")
    private List<Course>courses; 

    //getter/setter

    //add convenience methods for bi-directional
    public void add(Course tempCourse){
        if(courses == null)
        courses = new ArrayList<>(); 

        courses.add(tempCourse); 
        tempCourse.setInstructor(this); 
    }
}
```
4. Create Main app


## Fetch Types: Eager vs Lazy Loading 
When we fetch/retrieve data, should we retrieve everything? 
    - Easger will retrieve everything 
    - Lazy will retrieve on request 

- Eager Loading will load all dependant entities. Load instructor and all of their courses all at once 
- Lazy Loading will load the main entity first, then the dependent entities on demand. However, this requires an open Hibernate session because it needs a connection to database to retrieve data.  If the Hibernate session is closed, Hibernate will throw an exception(LazyInitializationException). So we can retrieve data using: 
    - 1. session.get and call appropriate getter methods
    - 2. Hibernate query with HQL

Best Practice: Prefer Lazy Loading instead of Eager Loading always!
```
@OneToMany(fetch=FetchType.LAZY, mappedBy="instructor")
private List<Course> courses; 
```

### Default Fetch Types: 
- @OneToOne - FetchType.EAGER
- @OneToMany - FetchType.LAZY
- @ManyToOne - FetchType.EAGER
- @ManyToMany - FetchType.LAZY

## One-To-Many Mapping (UniDirectional)
A course can have many reviews. If we delete the course, we should also delete the reviews (Cascading Delete). 
(Dev process similar to the other ones)

## Many-to-Many Mapping 
A course can have many students. A student can have many courses. We need to track which student is in which course and vice-versa. Here we will make use of a Join Table. 
Join Table: A table that provides a mapping between two tables. It has foreign keys for each table to define the mapping relationship. 
### @JoinTable
```
public class Course{
    @ManyToMany
    @JoinTable(
        name="course_student"
        joinColumns=@JoinColumn(name="course_id")
        inverseJoinColumns=@JoinColumn(name="student_id")
        )
        private List<Student> students; 
}
```
@JoinTable tells Hibernate: 
    - Look at the course_id column in the course_student table 
    - For other side(inverse), look at the student_id column in the course_student table
    - Use this information to find relationship between course and students
More on "inverse":
- In this context, we are defining the relationship in the Course class. 
- The Student class is on the 'other side', so it's called inverse
- 'Inverse' refers to the 'other side' of the relationship