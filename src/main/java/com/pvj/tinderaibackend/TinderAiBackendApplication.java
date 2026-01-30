package com.pvj.tinderaibackend;

import com.pvj.tinderaibackend.conversations.ChatMessage;
import com.pvj.tinderaibackend.conversations.Conversation;
import com.pvj.tinderaibackend.conversations.ConversationsRepository;
import com.pvj.tinderaibackend.profiles.Gender;
import com.pvj.tinderaibackend.profiles.Profile;
import com.pvj.tinderaibackend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ConversationsRepository conversationsRepository;

    public static void main(String[] args) {
        SpringApplication.run(TinderAiBackendApplication.class, args);
    }

    public void run(String... args) throws Exception {
        Profile profile = new Profile(
                "1",
                "John",
                "Doe",
                28,
                "Caucasian",
                Gender.MALE,
                "Love hiking and coffee",
                "https://example.com/image.jpg",
                "ENFP"
        );

        profileRepository.save(profile);
        System.out.println("Profile created: " + profile);
        profileRepository.findAll().forEach(System.out::println);

        Conversation conversation = new Conversation("1",profile.id(), List.of(
                new ChatMessage("Hello", profile.id(), LocalDateTime.now())
        ));
        conversationsRepository.save(conversation);
        System.out.println("Conversation Created: " + conversation);
        conversationsRepository.findAll().forEach(System.out::println);
    }
}
