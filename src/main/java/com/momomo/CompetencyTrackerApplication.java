package com.momomo;

import com.momomo.model.Skill;
import com.momomo.model.SkillRepository;
import com.momomo.model.User;
import com.momomo.model.UserRepository;
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
    public CommandLineRunner demo(SkillRepository repository1, UserRepository repository2) {
        return (args) -> {
            User test = new User();
            test.setName("Charles");
            test.setUsername("CharBerg");

            Skill skill1 = new Skill();
            skill1.setName("Hearthstone");
            Skill skill2 = new Skill();
            skill2.setName("Software");

            List<Skill> skillList = new ArrayList<>();
            skillList.add(skill1);
            skillList.add(skill2);

            test.setSkills(skillList);

            repository2.save(test);

            /*User test2 = repository2.findByUsername("CharBerg");

            System.out.println(test2.getName());
            System.out.println(test2.getUsername());
            for(Skill skill: test2.getSkills()){
                System.out.println(skill.getName());
            }*/
        };
    }
}
