package pe.edu.upc.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Departamento;
import pe.edu.upc.serviceinterfaces.IDepartamentoService;

@Controller
@SessionAttributes
@Secured("ROLE_ADMIN")
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private IDepartamentoService cService;

	@GetMapping("/new")
	public String newDepartamento(Model model) {
		model.addAttribute("departamento", new Departamento());
		return "departamento/departamento";
	}

	@GetMapping("/list")
	public String listDepartamento(Model model) {
		try {
			model.addAttribute("departamento", new Departamento());
			model.addAttribute("listaDepartamento", cService.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "departamento/listaDepartamento";
	}

	@PostMapping("/save")
	public String saveDepa(@Validated Departamento departamento, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "departamento/departamento";
		} else {
			int rpta = cService.insert(departamento);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "departamento/departamento";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaDepartamento", cService.list());
		return "redirect:/departamentos/list";
	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Departamento pro) {
		cService.listarId(pro.getIdDepartamento());
		return "departamento/listaDepartamento";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		
		try {
            Optional<Departamento> departamento = cService.listarId(id);
            if (!departamento.isPresent()) {
                model.addAttribute("info", "Departamento no existe");
                return "redirect:/departamentos/list";
            } else {
                model.addAttribute("departamento", departamento.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("error", e.getMessage());
        }
        return "/departamento/update";
		
	}

	@RequestMapping("/delete")
	public String deleteDepartamento(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				cService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una marca");
		}
		model.put("listaDepartamento", cService.list());

//		return "redirect:/categories/list";
		return "/departamento/listaDepartamento";
	}

	@RequestMapping("/search")
	public String finDepartamento(Model model, @ModelAttribute Departamento departamento) {

		List<Departamento> lista = cService.findByNameDepartamento(departamento.getNameDepartamento());

		model.addAttribute("listaDepartamento", lista);

		return "departamento/listaDepartamento";
	}

}
