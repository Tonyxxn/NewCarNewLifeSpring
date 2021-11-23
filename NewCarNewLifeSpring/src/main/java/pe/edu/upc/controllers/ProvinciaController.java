package pe.edu.upc.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Provincia;
import pe.edu.upc.serviceinterfaces.IDepartamentoService;
import pe.edu.upc.serviceinterfaces.IProvinciaService;

@Controller
@SessionAttributes
@Secured("ROLE_ADMIN")
@RequestMapping("/provincias")
public class ProvinciaController {

	@Autowired
	private IProvinciaService pService;
	@Autowired
	private IDepartamentoService dService;

	@GetMapping("/new")
	public String newProvincia(Model model) {
		model.addAttribute("provincia", new Provincia());
		model.addAttribute("listaDepartamento", dService.list());
		return "provincia/provincia";
	}

	@GetMapping("/list")
	public String listProvincia(Model model) {
		try {
			model.addAttribute("provincia", new Provincia());
			model.addAttribute("listaProvincia", pService.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "provincia/listaProvincia";
	}

	@PostMapping("/save")
	public String insertProvincia(@ModelAttribute @Valid Provincia Provincia, BindingResult binRes, Model model, SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaDepartamento", dService.list());
			return "provincia/provincia";
		} else {
			int rpta = pService.insert(Provincia);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listaDepartamento", dService.list());
				return "provincia/provincia";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaProvincia", pService.list());
		return "redirect:/provincias/list";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Provincia pro) {
		pService.listProvincia(pro.getIdProvincia());
		return "provincia/listaProvincia";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable(value = "id") int id, Model model, RedirectAttributes objRedir) {

		try {
			Optional<Provincia> provincia = pService.listProvincia(id);
			if (!provincia.isPresent()) {
				model.addAttribute("info", "Provincia no existe");
				return "redirect:/provincias/list";
			} else {
				model.addAttribute("listaDepartamento", dService.list());
				model.addAttribute("provincia", provincia.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/provincia/update";
	}

	@RequestMapping("/delete")
	public String deleteProvincia(Model model, @RequestParam(value="id") Integer id, @ModelAttribute Provincia provincia) {
		try {
			if (id != null && id > 0) {
				pService.delete(id);
				model.addAttribute("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar provincia");
		}
		model.addAttribute("provincia", provincia);
		model.addAttribute("listaProvincia", pService.list());
		model.addAttribute("listaDepartamento", dService.list());
		
		return "provincia/listaProvincia";
	}

	@RequestMapping("/search")
	public String finProvincia(Model model, @ModelAttribute Provincia provincia) {

		List<Provincia> lista = pService.findByNameProvincia(provincia.getNameProvincia());

		model.addAttribute("listaProvincia", lista);

		return "provincia/listaProvincia";
	}

}
