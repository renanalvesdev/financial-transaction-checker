package br.com.renanlabs.mvc.financialtransactionchecker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.renanlabs.mvc.financialtransactionchecker.dto.RequestUser;
import br.com.renanlabs.mvc.financialtransactionchecker.model.User;
import br.com.renanlabs.mvc.financialtransactionchecker.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("list")
	public String listagem(Model model) {
		System.out.println("Teste");
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "user/list";
	}

	/** methods to handling showing of form **/

	@GetMapping("form")
	public String showNewForm(RequestUser requisicao) {
		return "user/form";
	}

	@GetMapping("/form/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id);
		RequestUser requestUser = new RequestUser(user);
		model.addAttribute("requestUser", requestUser);

		return "user/form";
	}

	// save
	@PostMapping("save")
	public String save(@Valid RequestUser request, BindingResult result) {

		if (result.hasErrors()) {
			return "user/form";
		}

		User user = request.toUser();
		userService.save(user);
		
		return "redirect:/user/list";
	}
}
