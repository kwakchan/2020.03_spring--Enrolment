package kr.ac.ks.app;

import kr.ac.ks.app.entity.*;
import kr.ac.ks.app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {
	private final PersonRepository personRepository;
	private final SocialMediaRepository socialMediaRepository;
	private final InterestsRepository interestsRepository;
	private final SkillRepository skillRepository;
	private final WorkRepository workRepository;

	public AppApplication(PersonRepository personRepository, SocialMediaRepository socialMediaRepository, InterestsRepository interestsRepository, SkillRepository skillRepository, WorkRepository workRepository){
		this.personRepository = personRepository;
		this.socialMediaRepository = socialMediaRepository;
		this.interestsRepository = interestsRepository;
		this.skillRepository = skillRepository;
		this.workRepository = workRepository;
	}

	public static void main(String[] args){
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		personRepository.save(new Person("곽찬", "백엔드 & 모바일", "hello@test.com", "010-1234-5678"));

		socialMediaRepository.save(new SocialMedia("https://www.github.com/anonymous","","https://www.instagram.com/anonymous","https://www.facebook.com/anonymous"));

		interestsRepository.save(new Interests("국내 여행"));
		interestsRepository.save(new Interests("햇살과 달빛 아래서 독서"));
		interestsRepository.save(new Interests("축구게임"));
		
		skillRepository.save(new Skill("JSP", 80));
		skillRepository.save(new Skill("Spring", 20));
		skillRepository.save(new Skill("Android", 30));
		
		workRepository.save(new Work("3rd grade","KSU & KSU LINC+","새벽타임","Wareable까지"));
	}
}