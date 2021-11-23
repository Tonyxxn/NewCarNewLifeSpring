package pe.edu.upc.controllers;

import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Servicio;
import pe.edu.upc.serviceinterfaces.IServicioService;
import pe.edu.upc.serviceinterfaces.ITallerMecanicoService;
import pe.edu.upc.serviceinterfaces.ITipoServicioService;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

	/////////////////////////////////////////////////////////////

	@Autowired
	private IServicioService sS;

	@Autowired
	private ITipoServicioService tsS;
	
	@Autowired
	private ITallerMecanicoService tmS;

	/////////////////////////////////////////////////////////////

	@GetMapping("/new")
	public String newServicio(Model model) {
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("listaTiposServicio", tsS.list());
		model.addAttribute("listaTalleresMecanicos", tmS.list());
		return "servicio/servicio";
	}

	@GetMapping("/list")
	public String listServicios(Model model) {
		try {
			model.addAttribute("servicio", new Servicio());
			model.addAttribute("listaServicios", sS.list());
			model.addAttribute("listaTalleresMecanicos", tmS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "servicio/listServicios";
	}

	@RequestMapping("/save")
	public String insertServicio(@ModelAttribute @Valid Servicio objSer, BindingResult binRes, Model model,
			SessionStatus status) throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaTiposServicio", tsS.list());
			return "servicio/servicio";
		} else {
			boolean flag = sS.insert(objSer);
			if (flag) {
				return "redirect:/servicios/list";
			} else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/servicios/new";
			}
		}
	}

	@RequestMapping("/delete")
	@Transactional
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				sS.delete(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar");
		}
		model.put("listaServicios", sS.list());
		return "servicio/listServicios";
	}
	
	@GetMapping("/update/{id}")
	public String detailsTipoServicio(@PathVariable int id, Model model, RedirectAttributes objRed) {
		Optional<Servicio> servicio = sS.listarId(id);
		if(servicio == null) {
			objRed.addFlashAttribute("mensaje", "ocurrio un error");
			return "servicio/servicio";
		}else {
			model.addAttribute("servicio", servicio);
			model.addAttribute("listaTiposServicio", tsS.list());
			model.addAttribute("listaTalleresMecanicos", tmS.list());
			return "servicio/servicio";
		}
	}

}
