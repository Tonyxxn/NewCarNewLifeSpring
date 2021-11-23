package pe.edu.upc.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.EstadoTaller;
import pe.edu.upc.serviceinterfaces.IEstadoTallerService;

@Controller
//@Secured("ROLE_TALLER")
@RequestMapping("/estadotalleres")
public class EstadoTallerController {

	@Autowired
	private IEstadoTallerService etS;
	
	@GetMapping("/new")
	public String newEstadoTaller(Model model) {
		model.addAttribute("estadotaller", new EstadoTaller());
		return "estadotaller/estadotaller";
		
	}
	
	@GetMapping("/list")
	public String listaEstadoTaller(Model model) {
		try {
			model.addAttribute("estadotaller", new EstadoTaller());
			model.addAttribute("listaEstadoTalleres", etS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "estadotaller/listEstadoTalleres";
	}
	
	@PostMapping("/save")
	public String saveEstadoTaller(@ModelAttribute("estadotaller") @Valid EstadoTaller estadotaller, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "estadotaller/estadotaller";
		} else {
			int rpta = etS.insert(estadotaller);
			if(rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "estadotaller/estadotaller";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaEstadoTalleres", etS.list());
		return "redirect:/estadotalleres/list";
		
	}
	
	@RequestMapping("/delete")
	public String deleteEstadoTaller(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				etS.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un estado de taller");
		}
		model.put("listaEstadoTalleres", etS.list());

//		return "redirect:/categories/list";
		return "/estadotaller/listEstadoTalleres";
	}
	
	
	@RequestMapping("/update/{id}")
	public String irUpdate(@PathVariable(value = "id") int id, Model model) {
		
		try {
            Optional<EstadoTaller> estadoTaller = etS.findById(id);
            if (!estadoTaller.isPresent()) {
                model.addAttribute("info", "Estado no existe");
                return "redirect:/estadotalleres/list";
            } else {
                model.addAttribute("estadotaller", estadoTaller.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("error", e.getMessage());
        }
        return "/estadotaller/update";
		
		
	}
	
}
