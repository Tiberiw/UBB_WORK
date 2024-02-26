using System.Globalization;
using Lab11.Domain;
using Lab11.Repository.Utils;

namespace Lab11.Repository;

public class InvoiceInFileRepository: InFileRepository<string,Invoice>
{

    private string DocumentsFile { get; set; }
    private string AcquisitionsFile { get; set; }

    public InvoiceInFileRepository(string File, string documentsFile, string acquisitionsFile)
        : base(File, LineToEntityMapping.CreateInvoice)
    {
        DocumentsFile = documentsFile;
        AcquisitionsFile = acquisitionsFile;
        loadFromFile();
    }
    
    public override void loadFromFile()
    {
        List <Document> documents = DataReader.ReadData(this.DocumentsFile, LineToEntityMapping.CreateDocument);
        List<Acquisition> acquisitions = DataReader.ReadData(this.AcquisitionsFile, LineToEntityMapping.CreateAcquisition);
        
        using (StreamReader sr = new StreamReader(base.FileName))
        {
            string line;
            while ((line = sr.ReadLine()) != null)
            {
                string[] fields = line.Split(',');

                Document document = documents.Find(x => x.Id.Equals(fields[0]));

                List<Acquisition> invoiceAcquisitions =
                    acquisitions.Where(x => x.Invoice.Id.Equals(document.Id)).ToList();
                
                //Without List<Acquisition>
                Invoice invoice = new Invoice()
                {
                    Id = document.Id,
                    IssuanceDate = document.IssuanceDate,
                    DueDate = DateTime.ParseExact(fields[1], "dd/MM/yyyy HH:mm", CultureInfo.InvariantCulture),
                    Category = (AcquisitionCategory)Enum.Parse(typeof(AcquisitionCategory), fields[2]),
                };
                
                Console.WriteLine("Intra aici");
                //Set List<Acquisition>
                invoiceAcquisitions.ForEach( invoiceAcquisitions => invoiceAcquisitions.Invoice = invoice);
                invoice.Acquisitions = invoiceAcquisitions; 
                
                base._dictionary[invoice.Id] = invoice;

            }
            
     
            
            
        }
    }
}