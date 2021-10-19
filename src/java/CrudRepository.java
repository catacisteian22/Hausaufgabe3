package java;

/*** CRUD operations repository interface*/
public interface CrudRepository<E> {

    /***@paramid-the id of the entity to be returnedid must not be null*@returnthe entity with the specified
     * id ornull - if there is no entity with the given id**/
    E findOne(Long id);
    /***@returnall entities*/
    Iterable<E> findAll();
    /***@paramentityentity must be not null*@returnnull- if the given entity
 is saved otherwisereturns the entity (id already exists)**/
    E save(E entity);
    /*** removes the entity with the specified id**@paramidid must be not null*@returnthe
 removed entity or null if thereis no entity with the given id**/

    E delete(Long id);
    /***@paramentityentity must not be null*@returnnull - if the entity is updated,
 otherwisereturns the entity - (e.g id does not exist).**/
    E update(E entity);
}
