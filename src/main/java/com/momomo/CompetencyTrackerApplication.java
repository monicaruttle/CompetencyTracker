package com.momomo;

import com.momomo.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CompetencyTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetencyTrackerApplication.class);
	}
    @Bean
    public CommandLineRunner demo(SkillRepository repository1, UserRepository repository2, LearningMaterialRepository repository3) {
        return (args) -> {
            User test = new User();
            test.setName("Charles");
            test.setUsername("CharBerg");

            Skill skill1 = new Skill();
            skill1.setName("Hearthstone");
            Skill skill2 = new Skill();
            skill2.setName("Software");

            LearningMaterial learningMaterial = new Book();
            learningMaterial.setName("Hearthstone and Software");

            List<Skill> skillList = new ArrayList<>();
            skillList.add(skill1);
            skillList.add(skill2);

            test.setSkills(skillList);
            repository1.save(skill1);
            repository1.save(skill2);
            repository2.save(test);
            repository3.save(learningMaterial);

            for(User test2: repository2.findAll()){
                System.out.println(test2.getName());
                System.out.println(test2.getUsername());
                for(Skill skill: test2.getSkills()){
                    System.out.println(skill.getName());
                }

            }

            LearningMaterial learningMaterial1 = repository3.findByName("Hearthstone and Software");
            System.out.println(learningMaterial1.getName());
        };
    }
}
