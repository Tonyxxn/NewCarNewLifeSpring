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

import pe.edu.upc.entities.Modelo;
import pe.edu.upc.serviceinterfaces.IMarcaService;
import pe.edu.upc.serviceinterfaces.IModeloService;

@Controller
@SessionAttributes
@Secured("ROLE_ADMIN")
@RequestMapping("/modelos")
public class ModeloController {

	@Autowired
	private IModeloService  mS;
	
	@Autowired
	private IMarcaService maS;
	
	@GetMapping("/new")
	public String newModelo(Model model) {
		model.addAttribute("modelo", new Modelo());
		model.addAttribute("listaMarcas", maS.list());
		return "modelo/modelo";
		
	}
	@GetMapping("/list")
	public String listaModelo(Model model) {
		try {
			model.addAttribute("modelo", new Modelo());
			model.addAttribute("listaModelos", mS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "modelo/listModelos";
	}
	@PostMapping("/save")
	public String saveModelo(@ModelAttribute @Valid Modelo modelo, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaMarcas", maS.list());
			return "modelo/modelo";
		} else {
			int rpta = mS.insert(modelo);
			//boolean rpta = mS.insert(modelo);
			if(rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listaMarcas", maS.list());
				return "modelo/modelo";
				//return "redirect:/modelos/list";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
				//model.addAttribute("mensaje", "Ocurrió un error");
				//return "redirect:/modelos/new";
			}
		}
		
		model.addAttribute("listaModelos", mS.list());
		return "redirect:/modelos/list";
		
	}
	@RequestMapping("/list")
	public String listModelos(Map<String, Object> model) {
		model.put("listaModelos", mS.list());
		return "modelo/listModelos";

	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Modelo mod) {
		mS.findById(mod.getIdModelo());
		return "modelo/listModelos";

	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable(value = "id") int id, Model model, RedirectAttributes objRedir) {

		try {
			Optional<Modelo> modelo = mS.findById(id);
			if (!modelo.isPresent()) {
				model.addAttribute("info", "Modelo no existe");
				return "redirect:/modelos/list";
			} else {
				model.addAttribute("listaMarcas", maS.list());
				model.addAttribute("modelo", modelo.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/modelo/update";
	}
	
	@RequestMapping("/delete")
	public String deleteModelo(Model model, @RequestParam(value="id") Integer id, @ModelAttribute Modelo modelo) {
		try {
			if(id!=null && id>0) {
				mS.eliminar(id);
				model.addAttribute("mensaje", "Se elimino correctamente");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar modelo");
		}
		model.addAttribute("modelo", modelo);
		model.addAttribute("listaModelos", mS.list());
		model.addAttribute("listaMarcas", maS.list());
		
		return "modelo/listModelos";
	}
	
}
