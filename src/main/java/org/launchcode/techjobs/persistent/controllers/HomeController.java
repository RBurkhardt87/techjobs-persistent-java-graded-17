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

    //TODO: add EmployerRepository field with @Autowired annotation
    @Autowired
    private EmployerRepository employerRepository;

    //TODO: add SkillRepository field with @Autowired
    @Autowired
    private SkillRepository skillRepository;

    //! I added a jobRepository to get it to save. Directions didn't tell us to do that....
    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    //TODO: pass all the employers to the model using the findAll() method on the employerRepository
    //TODO: added skills to be passed into the view
    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        return "add";
    }

    // ---------------------------------------------------------------------------------------------------
//*     This is how I would have set up the method, without trying to follow the directions. Seems to work just fine
//    @PostMapping("add")
//    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
//                                    Errors errors, Model model) {
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "add";
//        }
//        jobRepository.save(newJob);
//        return "redirect:";
//    }

    //------------------------------------------------------------------------------------------------------
//* This is the original starter code for this POST handler request
//* It provided @RequestParam int employerId
//* I understand that @RequestParam can be used for form submission, but I am not really sure why it is needed her for
//* the select options. We only pick one.

//    @PostMapping("add")
//    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
//                                    Errors errors, Model model, @RequestParam int employerId) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "add";
//        }
//
//        return "redirect:";
//    }
//



    //* int can't be null, so it would be need to be the wrapper class Integer for the @RequestParam
    //* This is what I feel the directions want me to do, or something along these lines, but...
    //* Asks me to add a @RequestParam, did I need to add an Employer instance as well?
    //* I just added required=false
    //* I still would need a jobRepository so I could actually save the job to the database

//    @PostMapping("add")
//    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
//                                    Errors errors, Model model, @RequestParam(required = false) Integer employerId) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "add";
//        } else {
//            if (employerId != null) {
//                Optional<Employer> result = employerRepository.findById(employerId);
//                if (result.isPresent()) {
//                    Employer employer = result.get();
//                    newJob.setEmployer(employer);
//                }
//            }
//                jobRepository.save(newJob);
//                return "redirect:";
//            }
//        }



        //-------------------------------------------------------------------------------------
        //* Working on this same handler for task 4. Keeping in separate since I have questions regarding task 3
        //* Had to change the conditional to not test employerId for null because the test fails if I change int to Integer
        @PostMapping("add")
        public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                        Errors errors, Model model, @RequestParam(required = false) int employerId,
                                        @RequestParam(required = false) List<Integer> skills) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        } else {
//            if (employerId != null) {
//                Optional<Employer> result = employerRepository.findById(employerId);
//                if (result.isPresent()) {
//                    Employer employer = result.get();
//                    newJob.setEmployer(employer);
//                }
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

    //------------------------------------------------------------------------------------
        @GetMapping("view/{jobId}")
        public String displayViewJob (Model model,@PathVariable int jobId){

            return "view";
        }

    }


//REFERENCE FROM CARRIES LECTURE VIDEOS
//This is for displaying by id not for processing

//if (artistId != null){
//Optional<Artist> result = artistRepository.findById(artistId);
//if (result.isPresent()) {
//Artist artist = result.get();
//Model.addAttribute(“artworks”, artist.getArtworks());
//
//        } else {
//        model.addAttribute(“artworks”, artworkRepository.findAll();
//	}
//            return “artworks/index”;
//        }






