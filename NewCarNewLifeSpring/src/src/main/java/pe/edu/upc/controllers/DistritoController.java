package pe.edu.upc.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Distrito;
import pe.edu.upc.serviceinterfaces.IDistritoService;
import pe.edu.upc.serviceinterfaces.IProvinciaService;

@Controller
@SessionAttributes
@Secured("ROLE_ADMIN")
@RequestMapping("/distritos")
public class DistritoController {

	@Autowired
	private IDistritoService dService;
	@Autowired
	private IProvinciaService pService;

	@GetMapping("/new")
	public String newDistrito(Model model) {
		model.addAttribute("distrito", new Distrito());
		model.addAttribute("listaProvincia", pService.list());
		
		return "distrito/distrito";
	}

	@GetMapping("/list")
	public String listDistrito(Model model) {
		try {
			model.addAttribute("distrito", new Distrito());
			model.addAttribute("listaDistrito", dService.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "distrito/listaDistrito";
	}

	@PostMapping("/save")
	public String insertDistrito(@Valid Distrito distrito, BindingResult binRes, Model model, SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaProvincia", pService.list());
			return "distrito/distrito";
		} else {
			int rpta = dService.insert(distrito);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listaProvincia", pService.list());
				return "distrito/distrito";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaDistrito", dService.list());
		return "redirect:/distritos/list";
	}

	@RequestMapping("/delete")
	public String deleteDistrito(Model model, @RequestParam(value="id") Integer id, @ModelAttribute Distrito distrito) {
		try {
			if(id!=null && id>0) {
				dService.delete(id);
				model.addAttribute("mensaje", "Se elimino correctamente");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar modelo");
		}
		model.addAttribute("distrito", distrito);
		model.addAttribute("listaDistrito", dService.list());
		model.addAttribute("listaProvincia", pService.list());
		
		return "distrito/listaDistrito";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Distrito dis) {
		dService.listarId(dis.getIdDistrito());
		return "distrito/listaDistrito";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		try {
			Optional<Distrito> distrito = dService.listarId(id);
			if (!distrito.isPresent()) {
				model.addAttribute("info", "Modelo no existe");
				return "redirect:/modelos/list";
			} else {
				model.addAttribute("listaProvincia", pService.list());
				model.addAttribute("distrito", distrito.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/distrito/update";

	}
}
