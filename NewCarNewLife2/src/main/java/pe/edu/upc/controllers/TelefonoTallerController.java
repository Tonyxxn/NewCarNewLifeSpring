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

import pe.edu.upc.entities.TelefonoTaller;
import pe.edu.upc.serviceinterfaces.ITallerMecanicoService;
import pe.edu.upc.serviceinterfaces.ITelefonoTallerService;

@Controller
@SessionAttributes
@Secured("ROLE_TALLER")
@RequestMapping("/telefonotalleres")
public class TelefonoTallerController {

	@Autowired
	private ITelefonoTallerService ttS;
	@Autowired
	private ITallerMecanicoService tmS;
	
	@GetMapping("/new")
	public String newTelefonoTaller(Model model) {
		model.addAttribute("telefonotaller", new TelefonoTaller());
		model.addAttribute("listaTaller", tmS.list());
		return "telefonotaller/telefonotaller";
		
	}
	
	@GetMapping("/list")
	public String listaTelefonoTaller(Model model) {
		try {
			model.addAttribute("telefonotaller", new TelefonoTaller());
			model.addAttribute("listaTelefonoTalleres", ttS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "telefonotaller/listTelefonoTalleres";
	}
	
	@PostMapping("/save")
	public String saveTelefonoTaller(@ModelAttribute("telefonotaller") @Valid TelefonoTaller telefonotaller, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaTaller", tmS.list());
			return "telefonotaller/telefonotaller";
		} else {
			int rpta = ttS.insert(telefonotaller);
			if(rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listaTaller", tmS.list());
				return "telefonotaller/telefonotaller";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaTelefonoTalleres", ttS.list());
		return "redirect:/telefonotalleres/list";
		
	}
	
	@RequestMapping("/delete")
	public String deleteEstadoTaller(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				ttS.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un telefono del taller");
		}
		model.put("listaTelefonoTalleres", ttS.list());
		model.put("listaTaller", tmS.list());

//		return "redirect:/categories/list";
		return "/telefonotaller/listTelefonoTalleres";
	}
	
	
	@RequestMapping("/update/{id}")
	public String irUpdate(@PathVariable(value = "id") int id, Model model) {
		
		try {
            Optional<TelefonoTaller> telefonotaller = ttS.findById(id);
            if (!telefonotaller.isPresent()) {
                model.addAttribute("info", "Estado no existe");
                return "redirect:/estadotalleres/list";
            } else {
            	model.addAttribute("listaTaller", tmS.list());
                model.addAttribute("telefonotaller", telefonotaller.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("error", e.getMessage());
        }
        return "/telefonotaller/update";
		
		
	}
	
}
