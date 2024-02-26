using Lab11.Domain;

namespace Lab11.Repository;

public delegate E CreateEntity<E>(string line);

public abstract class InFileRepository<ID,E> : InMemoryRepository<ID,E> where E : Entity<ID>
{
    protected string FileName { get; set; }
    protected CreateEntity<E> CreateEntity { get; set; }
    
    public InFileRepository(String fileName, CreateEntity<E> createEntity)
    {
        this.FileName = fileName;
        this.CreateEntity = createEntity;
    }
    
    public abstract void loadFromFile();
}