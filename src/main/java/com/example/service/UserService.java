package com.example.service;

import com.example.entity.User;
import com.example.entity.Post;
import com.example.repository.UserRepository;
import com.example.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Создание пользователя
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Поиск пользователей по имени
    public List<User> findUsersByName(String name) {
        return userRepository.findByName(name);
    }

    // Поиск пользователей по домену email
    public List<User> findUsersByEmailDomain(String domain) {
        return userRepository.findByEmailEndingWith(domain);
    }

    // Получение всех постов пользователя по его ID
    public List<Post> findPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    // Создание пользователя и поста в одной транзакции
    @Transactional
    public void createUserAndPost(User user, Post post) {
        User savedUser = userRepository.save(user);  // Сохраняем пользователя
        post.setUser(savedUser);  // Привязываем пользователя к посту
        postRepository.save(post);  // Сохраняем пост
    }

    // Обновление пользователя
    public User updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }

    // Удаление пользователя по ID
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}