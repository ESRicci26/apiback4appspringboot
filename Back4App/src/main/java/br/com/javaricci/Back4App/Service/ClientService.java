package br.com.javaricci.Back4App.Service;

import br.com.javaricci.Back4App.Model.Client;
import br.com.javaricci.Back4App.Repository.ClientRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAllClients() {
        return clientRepository.findAllClients();
    }

    public Client findClientById(String id) {
        return clientRepository.findClientById(id);
    }

    public void saveClient(Client client) {
        if (client.getId() == null || client.getId().isEmpty()) {
            clientRepository.addClient(client);
        } else {
            clientRepository.updateClient(client);
        }
    }

    public void deleteClient(String id) {
        clientRepository.deleteClient(id);
    }

    public void generatePdfReport() {
        List<Client> clients = clientRepository.findAllClients();
        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream("report.pdf"));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Paragraph title = new Paragraph("Relatório de Funcionários API Backup4App");
            document.add(title);

            Table table = new Table(new float[]{2, 4, 2, 2, 2});
            table.addCell("ID");
            table.addCell("Nome");
            table.addCell("Salario");
            table.addCell("Estoque");
            table.addCell("V/F");

            for (Client client : clients) {
                table.addCell(client.getId());
                table.addCell(client.getName());
                table.addCell(String.valueOf(client.getPrice()));
                table.addCell(String.valueOf(client.getStock()));
                table.addCell(String.valueOf(client.isSelling()));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateCsvReport() {
        List<Client> clients = clientRepository.findAllClients();
        try (FileWriter out = new FileWriter("report.csv");
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID", "Nome", "Salario", "Estoque", "V/F"))) {
            for (Client client : clients) {
                printer.printRecord(
                        client.getId(),
                        client.getName(),
                        client.getPrice(),
                        client.getStock(),
                        client.isSelling()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
