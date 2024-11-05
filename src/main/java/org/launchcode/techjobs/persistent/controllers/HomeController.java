package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
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

    @Autowired
    private EmployerRepository employerRepository;

    //! I added a jobRepository to get it to save. Directions didn't tell us to do that....
    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	    model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());

        return "add";
    }

    // ---------------------------------------------------------------------------------------------------
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model) {
    if (errors.hasErrors()){
        model.addAttribute("title", "Add Job");
        return "add";
    }
        jobRepository.save(newJob);
        return "redirect:";
    }

    //------------------------------------------------------------------------------------------------------

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
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



//        if (errors.hasErrors()) {
//        model.addAttribute("title", "Add Job");
//            return "add";
//                    } else {
//                    if (employerId != null) {
//Optional<Employer> result = employerRepository.findById(employerId);
//                if (result.isPresent()) {
//Employer employer = result.get();
//                    newJob.setEmployer(employer);
//                    employerRepository.save(newJob.getEmployer());
//        }
//        }
//        }