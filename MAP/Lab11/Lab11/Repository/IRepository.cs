using Lab11.Domain;

namespace Lab11.Repository;

public interface IRepository<ID,E> where E: Entity<ID>
{
    IEnumerable<E> FindAll();
}