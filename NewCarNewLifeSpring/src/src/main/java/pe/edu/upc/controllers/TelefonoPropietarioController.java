package pe.edu.upc.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.TelefonoPropietario;
import pe.edu.upc.serviceinterfaces.IPropietarioService;
import pe.edu.upc.serviceinterfaces.ITelefonoPropietarioService;

@Controller
@SessionAttributes
@Secured("ROLE_USER")
@RequestMapping("/telefonopropietario")

public class TelefonoPropietarioController {
	
	@Autowired
	private ITelefonoPropietarioService tService;
	
	@Autowired
	private IPropietarioService pService;
	
	
	@GetMapping("/new")
	public String newTelefono(Model model) {
		model.addAttribute("telefonopropietario", new TelefonoPropietario());
		model.addAttribute("listaPropietario", pService.list());
		return "telefonopropietario/telefonopropietario";
	}
	
	
	@GetMapping("/list")
	public String listTelefonoPropietario(Model model) {
		try {
			model.addAttribute("telefonopropietario", new TelefonoPropietario());
			model.addAttribute("listaTelefonoPropietario", tService.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "/telefonopropietario/listTelefonoPropietario";
	}
	
	@RequestMapping("/save")
	public String insertTelefono(@ModelAttribute @Valid TelefonoPropietario tel, BindingResult binRes, Model model, 
			SessionStatus status) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaPropietario", pService.list());
			return "telefonopropietario/telefonopropietario";
		} else {
			int flag = tService.insert(tel);
			if(flag > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listaPropietario", pService.list());
				//return "redirect:/telefonopropietario/list";
				return "/telefonopropietario/telefonopropietario";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				//return "redirect:/telefonopropietario/new";
				status.setComplete();
			}
		}
		model.addAttribute("listaTelefonoPropietario", tService.list());
		
		return "/telefonopropietario/listTelefonoPropietario";
		
	}
	
	@GetMapping("/listFind")
	public String listProductFind(Model model) {
		try {
			model.addAttribute("telefonopropietario", new TelefonoPropietario());
			model.addAttribute("listaTelefonoPropietario", tService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/propietario/find";
	}

	@RequestMapping("/find")
	public String find(Map<String, Object> model, @ModelAttribute TelefonoPropietario tel) throws ParseException {

		List<TelefonoPropietario> listTel;

		tel.setTtelefono(tel.getTtelefono());
		listTel = tService.fetchTelefonoByName(tel.getTtelefono());

		if (listTel.isEmpty()) {
			listTel = tService.fetchTelefonoByPropietario(tel.getTtelefono());
		}

		if (listTel.isEmpty()) {
			listTel = tService.findByNameTelefonoLikeIgnoreCase(tel.getTtelefono());
		}

		if (listTel.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaTelefonoPropietario", listTel);
		return "telefonopropietario/find";

	}

	@RequestMapping("/delete")
	public String deletetelefono(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				//tService.delete(id);
				tService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(":::::::");
			System.out.println(e.toString());
			model.put("mensaje", "No se puede eliminar el telefono");
		}
		model.put("listaTelefonoPropietario", tService.list());

//		return "redirect:/categories/list";
		return "/telefonopropietario/listTelefonoPropietario";
	}

	@GetMapping(value = "/list/{name}", produces = { "application/json" })
	public @ResponseBody List<TelefonoPropietario> fetchTel(@PathVariable String name, Model model) {
		List<TelefonoPropietario> tel = null;
		try {
			tel = tService.fetchTelefonoByName(name);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return tel;
	}
	
	@GetMapping(value = "/view/{id}")
	public String View(@PathVariable(value = "id") long id, Map<String, Object> model, RedirectAttributes flash) {
		//TelefonoPropietario tel = tService.listarId(id);
		Optional<TelefonoPropietario> tel =tService.findById(id);
		if(tel == null) {
			flash.addFlashAttribute(null, tel);
			return "redirect:/telefonopropietario/listTelefonoPropietario";
		}
		
		model.put("telefonopropietario", tel.get());
		//model.put("titulo", "telefono: " + tel.getTtelefono());
		
		return "telefonopropietario/view";
	}
	


}
