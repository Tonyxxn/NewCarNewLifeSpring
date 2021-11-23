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

import pe.edu.upc.entities.Propietario;
import pe.edu.upc.serviceinterfaces.ICategoryService;
import pe.edu.upc.serviceinterfaces.IPropietarioService;

@Controller
@SessionAttributes
@Secured("ROLE_ADMIN")
@RequestMapping("/propietario")
public class PropietarioController {
	
	@Autowired
	private IPropietarioService pService;
	
	@Autowired
	private ICategoryService cService;
	
	@GetMapping("/new")
	public String newPropietario(Model model) {
		model.addAttribute("propietario", new Propietario());
		model.addAttribute("listaCategorias", cService.list());
		//model.addAttribute("propietario", new Propietario());
		return "propietario/propietario";
	}
	
	
	
	@GetMapping("/list")
	public String listPropietario(Model model) {
		try {
			model.addAttribute("propietario", new Propietario());
			model.addAttribute("listaPropietario", pService.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}

		return "/propietario/listPropietario";
	}
	
	@RequestMapping("/save")
	public String insertPropietario(@ModelAttribute @Valid Propietario prop, BindingResult binRes, Model model, 
			SessionStatus status) throws ParseException{
		if(binRes.hasErrors()) {
			model.addAttribute("listaCategorias", cService.list());
			return "propietario/propietario";
		} else {
			int flag = pService.insert(prop);
			if(flag > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listaCategorias", cService.list());
				//return "redirect:/propietario/list";
				return "/propietario/propietario";
			} else {
				model.addAttribute("mensaje", "Se guado correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaPropietario", pService.list());
		return "/propietario/listPropietario";
		
	}
	
	@GetMapping("/listFind")
	public String listProductFind(Model model) {
		try {
			model.addAttribute("propietario", new Propietario());
			model.addAttribute("listaPropietario", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/propietario/find";
	}

	@RequestMapping("/find")
	public String find(Map<String, Object> model, @ModelAttribute Propietario prop) throws ParseException {

		List<Propietario> listProp;

		prop.setNpropietario(prop.getNpropietario());
		listProp = pService.fetchPropietarioByName(prop.getNpropietario());

		if (listProp.isEmpty()) {
			listProp = pService.fetchPropietarioByCategoryName(prop.getNpropietario());
		}

		if (listProp.isEmpty()) {
			listProp = pService.findByNamePropietarioLikeIgnoreCase(prop.getNpropietario());
		}

		if (listProp.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaPropietario", listProp);
		return "propietario/find";

	}

	@RequestMapping("/delete")
	public String deleteProduct(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un producto");
		}
		model.put("listaPropietario", pService.list());

//		return "redirect:/categories/list";
		return "/propietario/listPropietario";
	}

	@GetMapping(value = "/list/{name}", produces = { "application/json" })
	public @ResponseBody List<Propietario> fetchProducts(@PathVariable String name, Model model) {
		List<Propietario> prop = null;
		try {
			prop = pService.fetchPropietarioByName(name);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return prop;
	}
	
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") long id, Map<String, Object> model, RedirectAttributes flash) {
		Optional<Propietario> prop = pService.findById(id);
		
		if(prop == null) {
			flash.addFlashAttribute(null, prop);
			return "redirect:/propietario/listar";
		}
		
		model.put("propietario", prop.get());
		
		return "propietario/view";
	}

}
