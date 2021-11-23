package pe.edu.upc.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Estado;
import pe.edu.upc.serviceinterfaces.IEstadoService;

@Controller
@SessionAttributes
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private IEstadoService eService;

	@GetMapping("/new")
	public String newEstado(Model model) {
		model.addAttribute("estado", new Estado());
		return "estado/estado";
	}

	@GetMapping("/list")
	public String listEstados(Model model) {
		try {
			model.addAttribute("estado", new Estado());
			model.addAttribute("listaEstados", eService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "estado/listEstados"; // hay que crea template

	}

	@PostMapping("/save")
	public String SaveEstado(@Validated Estado estado, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "estado/estado";
		} else {
			int rpta = eService.insert(estado);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe");
				return "estado/estado";
			} else {
				model.addAttribute("mensaje", "se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("estado", new Estado());
		return "redirect:/estados/list";
	}
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				eService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una categoria");
		}
		model.put("listEstados", eService.list());

//		return "redirect:/categories/list";
		return "/estado/listEstados";
	}
}
