package com.sportyshoes.SportyShoes.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.SportyShoes.dao.ShoeDao;
import com.sportyshoes.SportyShoes.model.PurchaseReport;
import com.sportyshoes.SportyShoes.model.Shoe;
import com.sportyshoes.SportyShoes.model.User;

@Controller
public class ShoeController {

	@GetMapping("/")
	public String adminLogin() {
		return "login";
	}

	@GetMapping("/adminHomepage")
	public String showHomepage(Model model) {
		ShoeDao shoeDao = new ShoeDao();

		List<User> userList = shoeDao.displayUser();
		model.addAttribute("userList",userList);

		List<Shoe> shoeList = shoeDao.displayShoe();
		model.addAttribute("shoeList",shoeList);

		List<PurchaseReport> reportList = shoeDao.getReport();
		model.addAttribute("reportList",reportList);

		return "adminHomepage";
	}

	@PostMapping("/adminLogin")
	public String adminHomepage(@RequestParam("user")String user,@RequestParam("pass")String pass,Model model) {
		if(user.equals("admin") && pass.equals("admin")) {

			return "redirect:/adminHomepage";
		}
		else {
			model.addAttribute("ERROR","Invalid Credentials");
			return "login";
		}
	}

	@GetMapping("/addShoe")
	public String addNewEmployee(Model model) {
		Shoe shoe = new Shoe();
		model.addAttribute("shoe", shoe);
		return "newShoe";
	}
	
	
	
	@PostMapping("/updateShoes")
	public String updateShoe(@ModelAttribute("shoe") Shoe shoe,Model model) {
		ShoeDao shoeDao = new ShoeDao();
		boolean result = shoeDao.updateShoe(shoe);
		if(result)
			return "redirect:/adminHomepage";
		return "login";
	}

	@PostMapping("/saveShoe")
	public String saveShoes(@ModelAttribute("shoe") Shoe shoe,Model model) {
		ShoeDao shoeDao = new ShoeDao();
		boolean result = shoeDao.saveShoe(shoe);
		if(result)
			return "redirect:/adminHomepage";
		return "login";
	}

	@GetMapping("/deleteShoes/{id}")
	public String deleteShoes(@PathVariable(value = "id") int id,Model model) {
		ShoeDao shoeDao = new ShoeDao();
		boolean result=shoeDao.deleteShoe(id);
		if(result)
			return "redirect:/adminHomepage";
		return "login";
	}

	@GetMapping("/updateShoe/{id}")
	public String updateShoes(@PathVariable(value = "id") int id,Model model) {
		ShoeDao shoeDao = new ShoeDao();
		Shoe shoe = shoeDao.searchShoe(id);
		model.addAttribute("shoe",shoe);
		return "updateShoe";
	}

}
