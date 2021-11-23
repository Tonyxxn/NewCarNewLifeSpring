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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Marca;
import pe.edu.upc.serviceinterfaces.IMarcaService;

@Controller
@SessionAttributes
@Secured("ROLE_ADMIN")
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private IMarcaService mS;
	
	@GetMapping("/new")
	public String newMarca(Model model) {
		model.addAttribute("marca", new Marca());
		return "marca/marca";
		
	}
	
	@GetMapping("/list")
	public String listaMarca(Model model) {
		try {
			model.addAttribute("marca", new Marca());
			model.addAttribute("listaMarcas", mS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "marca/listMarcas";
	}
	
	@PostMapping("/save")
	public String saveMarca(@Valid Marca marca, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "marca/marca";
		} else {
			int rpta = mS.insert(marca);
			if(rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/marca/marca";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaMarcas", mS.list());
		
		return "redirect:/marcas/list";
		
	}
	
	
	
	@RequestMapping("/delete")
	public String deleteMarca(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				mS.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una marca");
		}
		model.put("listaMarcas", mS.list());

//		return "redirect:/categories/list";
		return "/marca/listMarcas";
	}
	
	
	@RequestMapping("/update/{id}")
	public String irUpdate(@PathVariable(value = "id") int id, Model model) {
		
		try {
            Optional<Marca> marca = mS.findById(id);
            if (!marca.isPresent()) {
                model.addAttribute("info", "Marca no existe");
                return "redirect:/marcas/list";
            } else {
                model.addAttribute("marca", marca.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("error", e.getMessage());
        }
        return "/marca/update";
		
		
	}
	
	
	@GetMapping("/listFind")
	public String listMarcasFind(Model model) {
		try {
			model.addAttribute("marca", new Marca());
			model.addAttribute("listaMarcas", mS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/marca/find";
	}


	@RequestMapping("/find")
	public String findByCategory(Map<String, Object> model, @ModelAttribute Marca marca) throws ParseException {

		List<Marca> listMarcas;
		marca.setNameMarca(marca.getNameMarca());
		listMarcas = mS.findByName(marca.getNameMarca());
		if (listMarcas.isEmpty()) {
			listMarcas = mS.findByNameLikeIgnoreCase(marca.getNameMarca());
		}
		if (listMarcas.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaMarcas", listMarcas);
		return "marca/find";

	}


}
