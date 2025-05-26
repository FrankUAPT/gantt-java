package com.ganttchart;

import com.ganttchart.model.Task;
import com.ganttchart.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class GanttChartApplication {

    public static void main(String[] args) {
        SpringApplication.run(GanttChartApplication.class, args);
    }

    @Bean // This annotation makes Spring manage this bean
    public CommandLineRunner loadInitialData(TaskService taskService) {
        return args -> {
            // Check if data already exists to avoid duplication on restarts with a persistent H2 file (though current is in-memory)
            if (taskService.getAllTasks().isEmpty()) {
                System.out.println("Loading initial sample data...");

                // Sample tasks for "Conference Planning Project"
                Task epic = new Task("Conference Planning (Epic)",
                        LocalDate.now().plusDays(0),
                        LocalDate.now().plusDays(60));
                taskService.saveTask(epic); // Save and get the ID if needed for parent-child later

                Task venueSelection = new Task("Venue Selection",
                        LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(14));
                // venueSelection.setParentId(epic.getId()); // Example for future hierarchy
                taskService.saveTask(venueSelection);

                Task speakerManagement = new Task("Speaker Management",
                        LocalDate.now().plusDays(7),
                        LocalDate.now().plusDays(30));
                taskService.saveTask(speakerManagement);
                
                Task marketing = new Task("Marketing & Registration",
                        LocalDate.now().plusDays(15),
                        LocalDate.now().plusDays(45));
                taskService.saveTask(marketing);

                Task contentCreation = new Task("Content Creation",
                        LocalDate.now().plusDays(10),
                        LocalDate.now().plusDays(40));
                taskService.saveTask(contentCreation);


                // A few more detailed sub-tasks (without parent linking for now)
                 Task researchVenues = new Task("Research Potential Venues",
                        LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(7));
                taskService.saveTask(researchVenues);

                Task negotiateVenueContract = new Task("Negotiate Venue Contract",
                        LocalDate.now().plusDays(8),
                        LocalDate.now().plusDays(14));
                taskService.saveTask(negotiateVenueContract);
                
                Task venueConfirmedMilestone = new Task("MILESTONE: Venue Confirmed",
                        LocalDate.now().plusDays(14),
                        LocalDate.now().plusDays(14)); // Milestones are often zero-duration
                taskService.saveTask(venueConfirmedMilestone);


                System.out.println("Sample data loaded.");
            } else {
                System.out.println("Data already exists. Skipping sample data loading.");
            }
        };
    }
}
