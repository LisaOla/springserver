package com.example.springserver;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "api/v1/blog")
public class JobsController {

    //Create array list
    ArrayList<Jobs> myJobs;

    // auto increment ID
    int autoIncrementID;

    public JobsController() {
        myJobs = new ArrayList<Jobs>();
        autoIncrementID = 0;
    }


    // CRUD - Create
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Jobs createJob(@RequestBody Jobs jobs) {
        //increase the autoIncrementID everytime you run the method so every job get new Id
        autoIncrementID++;
        jobs.setId(autoIncrementID);
        myJobs.add(jobs);
        System.out.println("creating job with Id =  " + autoIncrementID);

        return jobs;
    }


    // CRUD - Read

       // Read all jobs in the myJobs arraylist
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ArrayList<Jobs> listJobs() {
        System.out.println("Reading all the jobs ");
        return myJobs;
    }
       // Read a specific job by Id
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public Jobs listJobById(@PathVariable("id") int id) {

        Jobs jobsToDisplay = new Jobs();
        if(myJobs.isEmpty()){
            System.out.println("The list is empty");
        }else{
            System.out.println("Getting index of job with id " + id);
            int jobIndex = getJobIndexUsingID(id);
            if(jobIndex>-1){
                jobsToDisplay = myJobs.get(jobIndex);

            }else {
                System.out.println("Job id not found");
            }

        }

        return jobsToDisplay;
    }

    // CRUD - Update blog by Id
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public Jobs updateJob(@PathVariable("id") int id, @RequestBody Jobs jobsChanges) {
        Jobs jobsToUpdate = new Jobs();
        if(myJobs.isEmpty()){
            System.out.println("The list is empty");
        }else{
            System.out.println("Getting Job index with id " + id);
            int jobIndex = getJobIndexUsingID(id);
            if(jobIndex>-1) {
                jobsToUpdate = myJobs.get(jobIndex);
                if (jobsChanges.getTitle() != null) {
                    jobsToUpdate.setTitle(jobsChanges.getTitle());
                }
                if (jobsChanges.getBasesalary() != 0) {
                    jobsToUpdate.setBasesalary(jobsChanges.getBasesalary());
                }
                System.out.printf("The Job with Id %d is updated successfully \n", id);
            } else {
                System.out.println("Job id not found");

            }
        }

        return jobsToUpdate;

    }


    // CRUD - Delete blog by Id
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteJobById(@PathVariable("id") int id) {

        if(myJobs.isEmpty()){
            System.out.println("The list is empty");
        }else{
            int jobIndex = getJobIndexUsingID(id);
            if(jobIndex>-1) {
                myJobs.remove(jobIndex);
                System.out.printf("The Job with Id %d is deleted successfully\n", id);
            } else {
                    System.out.println("Job id not found");

                }
        }

    }



    // CRUD - Delete
    @RequestMapping(value = "clear", method = RequestMethod.DELETE)
    public void clearJobs() {
        myJobs.clear();
        System.out.println("The list is deleted successfully");
    }


    //Below method used to get the job index inside the jobs array using the provided job id, and used in other method that need to Read,Update or Delete a specfic post
    private int getJobIndexUsingID(int id) {
        int indexInJobArray ;
        boolean jobFound = false;

        for (indexInJobArray = 0; indexInJobArray < myJobs.size(); indexInJobArray++) {
            Jobs currentJobs = myJobs.get(indexInJobArray);
            if (currentJobs.getId() == id) {

                jobFound = true;
                break;
            }
        }
        if(!jobFound) {
            //if the job not found but the myJobs array is not empty we return -1
            indexInJobArray = -1;
//            System.out.println("indexInJobArray is " + indexInJobArray);
        }
        return indexInJobArray;
    }


}