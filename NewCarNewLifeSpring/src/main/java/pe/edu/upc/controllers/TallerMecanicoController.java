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

import pe.edu.upc.entities.TallerMecanico;
import pe.edu.upc.serviceinterfaces.ICategoryService;
import pe.edu.upc.serviceinterfaces.IDistritoService;
import pe.edu.upc.serviceinterfaces.IEstadoTallerService;
import pe.edu.upc.serviceinterfaces.ITallerMecanicoService;

@Controller
@SessionAttributes
@Secured("ROLE_ADMIN")
@RequestMapping("/taller")
public class TallerMecanicoController {

	@Autowired
	private ITallerMecanicoService tService;
	@Autowired
	private IDistritoService dService;
	
	@Autowired
	private IEstadoTallerService eService;
	
	@Autowired
	private ICategoryService cService;

	@GetMapping("/new")
	public String newTaller(Model model) {
		model.addAttribute("listaDistrito", dService.list());
		model.addAttribute("listaEstadoTaller", eService.list());
		model.addAttribute("listaCategory", cService.list());
		model.addAttribute("taller", new TallerMecanico());
		return "taller/taller";
	}

	@GetMapping("/list")
	public String listTaller(Model model) {
		try {
			model.addAttribute("taller", new TallerMecanico());
			model.addAttribute("listaTaller", tService.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "taller/listaTaller";
	}

	@PostMapping("/save")
	public String insertTaller(@ModelAttribute @Valid TallerMecanico taller, BindingResult binRes, Model model, SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaDistrito", dService.list());
			model.addAttribute("listaEstadoTaller", eService.list());
			model.addAttribute("listaCategory", cService.list());
			return "taller/taller";
		} else {
			int rpta = tService.insert(taller);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listaDistrito", dService.list());
				model.addAttribute("listaEstadoTaller", eService.list());
				model.addAttribute("listaCategory", cService.list());
				return "taller/taller";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaTaller", tService.list());
		return "/taller/listaTaller";
	}

	@RequestMapping("/delete")
	public String deleteTaller(Model model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				tService.delete(id);
				model.addAttribute("mensaje", "Se eliminó correcto");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar distrito");
		}
		model.addAttribute("listaTaller", tService.list());
		//return "redirect:/taller/list";
		return "/taller/listaTaller";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute TallerMecanico taller) {
		tService.listId(taller.getIdTallerMecanico());
		return "taller/listaTaller";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable(value = "id") int id, Model model, RedirectAttributes objRedir) {

		try {
			Optional<TallerMecanico> taller = tService.listId(id);
			if (!taller.isPresent()) {
				model.addAttribute("info", "Taller no existe");
				return "redirect:/modelos/list";
			} else {
				model.addAttribute("listaDistrito", dService.list());
				model.addAttribute("listaEstadoTaller", eService.list());
				model.addAttribute("listaCategory", cService.list());
				model.addAttribute("taller", taller.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/taller/update";
	}

}
