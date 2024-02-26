using System.Globalization;
using System.Security.AccessControl;

namespace Lab11.Domain;

public class LineToEntityMapping
{
    public static Document CreateDocument(string line)
    {
        string[] fields = line.Split(',');
        
        Console.WriteLine(fields[0] + " " + fields[1] + " " + fields[2]);

        return new Document()
        {
            Id = fields[0],
            Name = fields[1],
            IssuanceDate = DateTime.ParseExact(fields[2], "dd/MM/yyyy HH:mm", CultureInfo.InvariantCulture)
        };
    }

    public static Acquisition CreateAcquisition(string line)
    {
        string[] fields = line.Split(',');

        return new Acquisition()
        {
            Id = fields[0],
            Product = fields[1],
            Quantity = int.Parse(fields[2]),
            ProductPrice = double.Parse(fields[3]),
            Invoice = new Invoice()
            {
                Id = fields[4]
            }
        };
    }

    public static Invoice CreateInvoice(string line)
    {
        string[] fields = line.Split(',');

        return new Invoice()
        {
            
            Id = fields[0],
            DueDate = DateTime.ParseExact(fields[1], "dd/MM/yyyy HH:mm", CultureInfo.InvariantCulture),
            Category = (AcquisitionCategory)Enum.Parse(typeof(AcquisitionCategory), fields[2])
            
        };
    }
}