namespace Lab11.Domain;

public class Document: Entity<string>
{
        public string Name { get; set; }
        public DateTime IssuanceDate { get; set; }
        public override string ToString()
        {
                return Name + " " + IssuanceDate;
        }
}