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