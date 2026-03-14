package com.linhdv.efms_core_service.service.settings;

import com.linhdv.efms_core_service.dto.settings.request.CreateUserRequest;
import com.linhdv.efms_core_service.dto.settings.request.UpdateUserRequest;
import com.linhdv.efms_core_service.dto.settings.response.UserResponse;
import com.linhdv.efms_core_service.entity.Company;
import com.linhdv.efms_core_service.entity.User;
import com.linhdv.efms_core_service.repository.settings.UserRepository;
import com.linhdv.efms_core_service.wrapper.PagedResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public PagedResponse<UserResponse> searchUsers(UUID companyId, String search, int page, int size) {
        Page<User> data = userRepository.search(companyId, search, PageRequest.of(page, size));
        List<UserResponse> content = data.getContent().stream().map(this::toResponse).toList();
        return PagedResponse.of(content, page, size, data.getTotalElements());
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        return toResponse(findOrThrow(id));
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email đã tồn tại trên hệ thống");
        }

        Company company = new Company();
        company.setId(req.getCompanyId());

        User user = new User();
        user.setCompany(company);
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword()); // TODO: BCrypt password in real scenario
        user.setRole(req.getRole());
        user.setIsActive(true);
        user.setCreatedAt(Instant.now());

        return toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse updateUser(UUID id, UpdateUserRequest req) {
        User user = findOrThrow(id);
        
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setRole(req.getRole());
        
        return toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse toggleActive(UUID id) {
        User user = findOrThrow(id);
        user.setIsActive(!user.getIsActive());
        return toResponse(userRepository.save(user));
    }

    @Transactional
    public void resetPassword(UUID id, String newPassword) {
        User user = findOrThrow(id);
        user.setPassword(newPassword); // TODO: BCrypt
        userRepository.save(user);
    }

    private User findOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng: " + id));
    }

    private UserResponse toResponse(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .name(u.getName())
                .email(u.getEmail())
                .role(u.getRole())
                .isActive(u.getIsActive())
                .companyId(u.getCompany().getId())
                .companyName(u.getCompany().getName())
                .createdAt(u.getCreatedAt())
                .build();
    }
}
