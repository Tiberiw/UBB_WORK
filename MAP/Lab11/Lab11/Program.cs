// See https://aka.ms/new-console-template for more information

using System.Globalization;
using Lab11.Domain;
using Lab11.Repository;
using Lab11.Repository.Utils;


DocumentInFileRepository documentInFileRepository =
    new DocumentInFileRepository("C:\\Users\\amari\\UBB_WORK\\MAP\\Lab11\\Lab11\\Data\\documente.txt");

InvoiceInFileRepository invoiceInFileRepository = new InvoiceInFileRepository(
    "C:\\Users\\amari\\UBB_WORK\\MAP\\Lab11\\Lab11\\Data\\facturi.txt",
    "C:\\Users\\amari\\UBB_WORK\\MAP\\Lab11\\Lab11\\Data\\documente.txt",
    "C:\\Users\\amari\\UBB_WORK\\MAP\\Lab11\\Lab11\\Data\\achizitii.txt");

AcquisitionInFileRepository acquisitionInFileRepository = new AcquisitionInFileRepository(
    "C:\\Users\\amari\\UBB_WORK\\MAP\\Lab11\\Lab11\\Data\\achizitii.txt",
    "C:\\Users\\amari\\UBB_WORK\\MAP\\Lab11\\Lab11\\Data\\documente.txt",
    invoiceInFileRepository);

foreach (Document document in documentInFileRepository.FindAll())
{
    Console.WriteLine(document);
}

Console.WriteLine("-----");

foreach (Invoice invoice in invoiceInFileRepository.FindAll())
{
    Console.WriteLine(invoice);
}

Console.WriteLine("----");

foreach (Acquisition acquisition in acquisitionInFileRepository.FindAll())
{
    Console.WriteLine(acquisition);
}

