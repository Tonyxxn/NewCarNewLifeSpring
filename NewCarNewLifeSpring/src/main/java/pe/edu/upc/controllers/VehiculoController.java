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

import pe.edu.upc.entities.Vehiculo;
import pe.edu.upc.serviceinterfaces.IModeloService;
import pe.edu.upc.serviceinterfaces.IPropietarioService;
import pe.edu.upc.serviceinterfaces.IVehiculoService;

@Controller
@SessionAttributes
@Secured("ROLE_USER")
@RequestMapping("/vehiculos")
public class VehiculoController {

	@Autowired
	private IModeloService  mS;
	
	@Autowired
	private IPropietarioService  pS;
	
	@Autowired
	private IVehiculoService vS;
	
	@GetMapping("/new")
	public String newVehiculo(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("listaModelos", mS.list());
		model.addAttribute("listaPropietarios", pS.list());
		return "vehiculo/vehiculo";
		
	}
	
	@GetMapping("/list")
	public String listaVehiculo(Model model) {
		try {
			model.addAttribute("vehiculo", new Vehiculo());
			model.addAttribute("listaVehiculos", vS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "vehiculo/listVehiculos";
	}
	
	@PostMapping("/save")
	public String saveVehiculo(@ModelAttribute @Valid Vehiculo vehiculo, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaModelos", mS.list());
			model.addAttribute("listaPropietarios", pS.list());
			return "vehiculo/vehiculo";
		} else {
			try {
				boolean rpta = vS.insert(vehiculo);
				if(rpta) {
					//model.addAttribute("mensaje", "Ya existe");
					//return "modelo/modelo";
					return "redirect:/vehiculos/list";
				} else {
					//model.addAttribute("mensaje", "Se guardo correctamente");
					//status.setComplete();
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/vehiculos/new";
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error al insertar");
			}
			return "redirect:/vehiculos/new";
		}
		//model.addAttribute("modelo", new Modelo());
		//return "redirect:/modelos/list";
		
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Vehiculo objPro = vS.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/vehiculos/list";
		} else {
			model.addAttribute("listaModelos", mS.list());
			model.addAttribute("listaPropietarios", pS.list());
			model.addAttribute("vehiculo", objPro);
			return "vehiculo/update";
		}
	}
	
	
	@RequestMapping("/delete")
	public String deleteVehiculo(Model model, @RequestParam(value="id") Integer id, @ModelAttribute Vehiculo vehiculo) {
		try {
			if(id!=null && id>0) {
				vS.eliminar(id);
				model.addAttribute("mensaje", "Se elimino correctamente");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar vehiculo");
		}
		model.addAttribute("vehiculo", vehiculo);
		model.addAttribute("listaVehiculos", vS.list());
		model.addAttribute("listaModelos", mS.list());
		model.addAttribute("listaPropietarios", pS.list());
		
		return "vehiculo/listVehiculos";
	}
	
	
	@GetMapping(value = "/view/{id}")
	public String ver(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Vehiculo> product = vS.findById(id);
		if (product == null) {
			flash.addFlashAttribute("error", "El Producto no existe en la base de datos");
			return "redirect:/vehiculoss/listar";
		}

		model.put("vehiculo", product.get());

		return "vehiculo/view";
	}
	
}
