using Lab11.Domain;
using Lab11.Repository.Utils;

namespace Lab11.Repository;

public class AcquisitionInFileRepository: InFileRepository<string, Acquisition>
{
    private InvoiceInFileRepository InvoiceInFileRepository { set; get; }
    private string DocumentsFile { get; set; }

    public AcquisitionInFileRepository(string file, string documentsFile, InvoiceInFileRepository invoiceInFileRepository)
        : base(file, LineToEntityMapping.CreateAcquisition)
    {
        DocumentsFile = documentsFile;
        InvoiceInFileRepository = invoiceInFileRepository;
        loadFromFile();
    }
    
    public override void loadFromFile()
    {
        List <Document> documents = DataReader.ReadData(this.DocumentsFile, LineToEntityMapping.CreateDocument);

        using (StreamReader streamReader = new StreamReader(base.FileName))
        {
            string line;
            while ((line = streamReader.ReadLine()) != null)
            {
                string[] fields = line.Split(',');
                
                List<Invoice> invoices = InvoiceInFileRepository.FindAll().ToList();
                
                Acquisition acquisition = invoices.Find(x => x.Id.Equals(fields[4]))
                    .Acquisitions
                    .Find(aq => aq.Id.Equals(fields[0]));
                

                base._dictionary[acquisition.Id] = acquisition;
            }
        }
    }
}