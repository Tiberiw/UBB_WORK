namespace Lab11.Repository.Utils;

public class DataReader
{
    public static List<T> ReadData<T>(string fileName, CreateEntity<T> createEntity)
    {
        List<T> list = new List<T>();
        using (StreamReader streamReader = new StreamReader(fileName))
        {
            string line;
            while ((line = streamReader.ReadLine()) != null)
            {
                T entity = createEntity(line);
                list.Add(entity);
            }
        }

        return list;
    }
}