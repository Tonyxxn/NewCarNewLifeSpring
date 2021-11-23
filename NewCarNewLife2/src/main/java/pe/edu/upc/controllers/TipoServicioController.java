package pe.edu.upc.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.TipoServicio;
import pe.edu.upc.serviceinterfaces.ITipoServicioService;

@Controller
@RequestMapping("/tiposservicio")
public class TipoServicioController {

	@Autowired
	private ITipoServicioService tsS;

	@GetMapping("/new")
	public String newTipoServicio(Model model) {
		model.addAttribute("tiposervicio", new TipoServicio());
		return "tipoServicio/tipoServicio";
	}

	@GetMapping("/list")
	public String listTiposServicio(Model model) {
		try {
			model.addAttribute("tiposervicio", new TipoServicio());
			model.addAttribute("listaTiposServicio", tsS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tiposervicio/listTiposServicio";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id != null && id > 0) {
				tsS.delete(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar");
		}
		model.put("listaTiposServicio", tsS.list());
		return "tiposervicio/listTiposServicio";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid TipoServicio tipoServicio, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipoServicio/tipoServicio";
		} else {
			int rpta = tsS.insert(tipoServicio);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "tipoServicio/tipoServicio";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tiposervicio", new TipoServicio());
		return "redirect:/tiposservicio/list";
	}
	
	@GetMapping("/update/{id}")
	public String detailsTipoServicio(@PathVariable int id, Model model, RedirectAttributes objRed) {
		Optional<TipoServicio> tipoServicio = tsS.listarId(id);
		if(tipoServicio == null) {
			objRed.addFlashAttribute("mensaje", "ocurrio un error");
			return "tipoServicio/tipoServicio";
		}else {
			model.addAttribute("tiposervicio", tipoServicio);
			return "tipoServicio/tipoServicio";
		}
	}

}
