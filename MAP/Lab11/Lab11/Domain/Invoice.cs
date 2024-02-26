namespace Lab11.Domain;


public class Invoice : Document
{
    public DateTime DueDate { get; set; }
    
    public List<Acquisition> Acquisitions { get; set; }
    
    public AcquisitionCategory Category { get; set; }

    public override string ToString()
    {
        return base.ToString() + " " + DueDate + " " + Acquisitions + " " + Category;
    }
}