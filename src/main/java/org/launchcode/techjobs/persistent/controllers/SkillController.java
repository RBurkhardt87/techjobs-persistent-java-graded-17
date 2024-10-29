package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//? I made ("/skills") like the employer controller, and then it wouldn't do any validation error. I have been trouble with
//? the trailing forward slash with my controllers and redirects.

@Controller
@RequestMapping("skills/")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;


    //TODO: displayAddSkillForm
    @GetMapping("add")
    public String displayAddSkillForm(Model model){
        model.addAttribute(new Skill());
        return "skills/add";
    }


    //TODO: processAddSkillForm
    @PostMapping("add")
    public String processAddSkillForm(@Valid @ModelAttribute Skill newSkill,
                                      Errors errors, Model model){

        if (errors.hasErrors()) {
            return "skills/add";
        }
        skillRepository.save(newSkill);
        return "redirect:";
    }


    //TODO: displayViewSkill
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int skillId) {

        Optional optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            return "redirect:../";
        }
    }


    //TODO: index method that displays all skills
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

}
