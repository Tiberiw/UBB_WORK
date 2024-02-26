using Lab11.Domain;
using Lab11.Repository.Utils;

namespace Lab11.Repository;

public class DocumentInFileRepository: InFileRepository<string, Document>
{
    public DocumentInFileRepository(string FileName) : base(FileName, LineToEntityMapping.CreateDocument)
    {
        loadFromFile();
    }
    
    public override void loadFromFile()
    {
        List<Document> list = DataReader.ReadData(base.FileName, base.CreateEntity);
        list.ForEach(x => _dictionary[x.Id] = x);
    }
}