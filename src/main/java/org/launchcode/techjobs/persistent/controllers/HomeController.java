package org.launchcode.techjobs.persistent.controllers;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    //TODO: add EmployerRepository with @Autowired annotation
    @Autowired
    private EmployerRepository employerRepository;

    //TODO: add SkillRepository with @Autowired
    @Autowired
    private SkillRepository skillRepository;

    //* Added JobRepository even though directions didn't tell us to do that....How else would I be able to save jobs
    @Autowired
    private JobRepository jobRepository;


    //TODO: pass in all the jobs to be displayed in a list
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }


    //TODO: pass all the employers to the model
    //TODO: pass all skills to the model
    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        return "add";
    }


    //TODO: add code inside method to select the employer object associated with the new job
    //Optional to check if there actually is an employer at the specific id
    //TODO: add @RequestParam List<Integer> skills
    //TODO: List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills); newJob.setSkills(skillObjs);
    //I want to put those things inside a conditional that will check if null or not.
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam(required = false) int employerId,
                                    @RequestParam(required = false) List<Integer> skills) {


    if (errors.hasErrors()) {
        model.addAttribute("title", "Add Job");
        return "add";
    } else {

        Optional<Employer> result = employerRepository.findById(employerId);
        if (result.isPresent()) {
            Employer employer = result.get();
            newJob.setEmployer(employer);
        }
    }
        if (skills != null) {
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);
        }

        jobRepository.save(newJob);
        return "redirect:";

    }


        @GetMapping("view/{jobId}")
        public String displayViewJob (Model model,@PathVariable int jobId){

            Optional optJob = jobRepository.findById(jobId);
            if (optJob.isPresent()) {
                Job job = (Job) optJob.get();
                model.addAttribute("job", job);
                return "view";
            } else {
                return "redirect:../";
            }
    }
}








