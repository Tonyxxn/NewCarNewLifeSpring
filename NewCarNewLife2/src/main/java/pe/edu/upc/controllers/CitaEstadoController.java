package pe.edu.upc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.CitaEstado;
import pe.edu.upc.entities.Estado;
import pe.edu.upc.serviceinterfaces.ICitaEstadoService;
import pe.edu.upc.serviceinterfaces.IEstadoService;


@Controller
@RequestMapping("/citaestados")
public class CitaEstadoController {

	@Autowired
	private ICitaEstadoService cService;
	
	@Autowired
	private IEstadoService eService;
	
	@GetMapping("/new")
	public String NewCitaEstado(Model model) {
		model.addAttribute("citasestado",new CitaEstado());
		model.addAttribute("listacitaestado",cService.list());
		model.addAttribute("listaestado",eService.list());
		model.addAttribute("citasestado",new CitaEstado());
		return "citaestado/citaestado";	
	}
	
	@GetMapping("/list")
	public String listCitaEstado(Model model) {
		try {
			model.addAttribute("citaestado", new CitaEstado());
			model.addAttribute("listacitaestado", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "citaestado/listCitaEstado";
	}
	
	@PostMapping("/save")
	public String SaveEstado(@Validated CitaEstado citaestado, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "citaestado/citaestado";
		} else {
			int rpta = cService.insert(citaestado);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe");
				return "citaestado/citaestado";
			} else {
				model.addAttribute("mensaje", "se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("citaestado", new Estado());
		return "redirect:/citaestado/list";
	}
}
