namespace Lab11.Domain;

public class Acquisition : Entity<string>
{
    public string Product { get; set; }
    public int Quantity { get; set; }
    public double ProductPrice { get; set; }
    public Invoice Invoice { get; set; }
    public override string ToString()
    {
        return Product + " " + Quantity + " " + ProductPrice;
    }
}