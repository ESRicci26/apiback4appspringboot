package br.com.javaricci.Back4App.Controller;

import br.com.javaricci.Back4App.Service.ClientService;
import br.com.javaricci.Back4App.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    //http://localhost:8081/Back4App/clients/contatos
	@GetMapping("/contatos")
    public String listClients(Model model) {
        List<Client> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        return "list.html";
    }


	@GetMapping("/new")
	public String newClient(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);

		return "form";
	}


    @PostMapping
    public String saveClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/clients/contatos";
    }


	@GetMapping("/edit/{id}")
	public ModelAndView showAlterarContatoPage(@PathVariable  String id, Model model) {
		ModelAndView mav = new ModelAndView("form.html");
		Client client = clientService.findClientById(id);
		mav.addObject("client", client);

		return mav;
	}


    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return "redirect:/clients/contatos";
    }


    @GetMapping("/pdf")
    public String generatePdfReport() {
        clientService.generatePdfReport();
        return "redirect:/clients/contatos";
    }


    @GetMapping("/csv")
    public String generateCsvReport() {
        clientService.generateCsvReport();
        return "redirect:/clients/contatos";
    }
}
