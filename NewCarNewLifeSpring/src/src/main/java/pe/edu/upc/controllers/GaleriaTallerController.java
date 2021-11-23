package pe.edu.upc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.GaleriaTaller;
import pe.edu.upc.serviceinterfaces.IGaleriaTallerService;
import pe.edu.upc.serviceinterfaces.IUploadFileService;

@Controller
@RequestMapping("/galeriatalleres")
public class GaleriaTallerController {
	@Autowired
	private IGaleriaTallerService gtS;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/new")
	public String newGaleriaTaller(Model model) {
		model.addAttribute("galeriataller", new GaleriaTaller());
		model.addAttribute("galeriataller", new GaleriaTaller());
		return "galeriataller/galeriataller";
	}
	@GetMapping("/list")
	public String listProductsGaleriaTalleres(Model model) {
		try {
			model.addAttribute("galeriataller", new GaleriaTaller());
			model.addAttribute("listaGaleriaTaller", gtS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "galeriataller/listGaleriaTalleres";
	}

	@RequestMapping("/save")
	public String insertGaleriaTaller(@ModelAttribute @Valid GaleriaTaller objPro, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "galeriataller/galeriataller";
		} else {
			if (!foto.isEmpty()) {

				if (objPro.getIdGaleria() > 0 && objPro.gettArchivo() != null
						&& objPro.gettArchivo().length() > 0) {

					uploadFileService.delete(objPro.gettArchivo());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objPro.settArchivo(uniqueFilename);
			}
			boolean flag = gtS.insert(objPro);
			if (flag) {
				return "redirect:/galeriatalleres/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/galeriatalleres/new";
			}
		}
	}

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

		GaleriaTaller galeriataller = gtS.listarId(id);

		if (galeriataller == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "galeriataller/listGaleriaTalleres";
		}

		model.put("galeriataller", galeriataller);
		model.put("titulo", "Detalle de producto: " + galeriataller.gettDescripcion());

		return "galeriataller/ver";
	}

	@RequestMapping("/list")
	public String listGaleriaTalleres(Map<String, Object> model) {
		model.put("listaGaleriaTalleres", gtS.list());
		return "galeriataller/listGaleriaTalleres";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute GaleriaTaller gal) {
		gtS.listarId(gal.getIdGaleria());
		return "galeriataller/listGaleriaTalleres";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		GaleriaTaller objPro = gtS.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/galeriatalleres/list";
		} else {
			model.addAttribute("galeriataller", objPro);
			return "galeriataller/galeriataller";
		}
	}
}
