package me.aceking.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.aceking.tasks.ITaskService;
import me.aceking.tasks.dtos.TaskResponseDto;
import me.aceking.users.dto.CreateUserDto;
import me.aceking.users.dto.UpdateUserDto;
import me.aceking.users.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final ITaskService taskService;


    @GetMapping("/signup")
    public String createUserForm(Model model) {
        model.addAttribute("user", new CreateUserDto(null, null, null, null));
        return "/users/newUser";
    }

    @PostMapping("/signup")
    public String createUser(@Valid @ModelAttribute("user") CreateUserDto user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, Model model) {
        UserResponseDto user = userService.getUserById(id);
        model.addAttribute("user", user);
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id"));
        Page<TaskResponseDto> tasks = taskService.getTasksByUserId(pageable, id);
        model.addAttribute("tasks", tasks);

        return "/users/user";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("user") UpdateUserDto user) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
